<#include "macro-common_page.ftl">
<@commonPage "${welcomeToSoloLabel}!">
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
</@commonPage>