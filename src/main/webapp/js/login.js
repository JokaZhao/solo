$(function(){

    $(document).keydown(function(event){
        if(event.keyCode===13){
            $("#login").click();
        }
    });

    $("#login").click(function(){
        var pw = $('#password').val();
        var userName = $('#userName').val();

        if(isEmpty(pw) || isEmpty(userName)){

            $('#msg').text('用户名或者密码为空');

            $('ul').slideToggle();
            setTimeout(function(){
                $('ul').slideToggle();
                $('#msg').text('');
            },2000);
        }

        showLoading();


        var data = {};

        data.kid = $('#kid').val();
        data.token = $('#token').val();
        data.userName = userName;

        console.log(data);
        request('/getTicket',data,function (result, textStatus){

            var ticket = result.ticket;

            var ky = CryptoJS.enc.Utf8.parse(ticket);


            var srcs = CryptoJS.enc.Utf8.parse(pw);

            var pwEncode = CryptoJS.AES.encrypt(srcs, ky, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            //这个加密出来的msg是hex编码的
            var pendingMsg = pwEncode.toString();

            var auth = {
                "userName":userName,
                "kid":data.kid,
                "pw":pendingMsg,
                "token":data.token,
                "ticket":ticket
            };

            console.log(auth);

            request("/auth",auth,function (result, status) {
                console.log(result);
                console.log(status);
            });
        });


    });

    function request(url, data, f) {
        $.ajax({
            url: Service.servePath + url,
            type: 'POST',
            async: true,
            data: JSON.stringify(data),
            cache: false,
            success: function (result, textStatus) {
                f(result,textStatus);
            }
        })
    }

    function showLoading() {
        $('#loginBox').css("display","none");
        $('#loading').css("display","block");
    }

    function hideLoading(){
        $('#loginBox').css("display","block");
        $('#loading').css("display","none");
    }

    function isEmpty(obj){
        if(typeof obj == "undefined" || obj == null || obj == ""){
            return true;
        }else{
            return false;
        }
    }
});