
<h2>
    <span>${welcomeToSoloLabel}</span>
    <a target="_blank" href="https://solo.b3log.org">
        <span class="error">&nbsp;Solo</span>
    </a>
</h2>

<div id="github">
    <div class="github__icon startAction">
        <img src="${staticServePath}/images/github.png"/>
    </div>
    <br>
    <button class="startAction">${useGitHubAccountLoginLabel}</button><br>
    <a class="github__link" href="javascript:$('ul').slideToggle()">查看 GitHub 数据使用说明</a>
    <div class="github__text">
        <ul>
            <li>获取用户名、头像等用于初始化</li>
            <li>获取公开仓库信息用于展示</li>
            <li>不会对你的已有数据进行写入</li>
        </ul>
    </div>
</div>
<script type="text/javascript" src="${staticServePath}/js/lib/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript">
    (function () {
        try {
            $('.startAction').click(function () {
                var isAgreen = $('#isAgreenCheck').prop('checked') ? '0' : '1'
                window.location.href = '${servePath}/oauth/github/redirect?referer=__' + isAgreen
                $('#github').addClass('github--loading')
            })
        } catch (e) {
            document.querySelector('.main').innerHTML = "${staticErrorLabel}"
        }
    })()
</script>
