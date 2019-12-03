$(function(){
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
            console.log(result);
            console.log(textStatus);

            var auth = {
                "userName":userName,
                "kid":data.kid,
                "pw":pw,
                "token":data.token,
                "ticket":result.ticket
            };

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