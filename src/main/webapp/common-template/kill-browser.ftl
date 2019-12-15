
<#include "macro-common_page.ftl">

<@commonPage "Kill IE!">
<div class="kill">
    ${killBrowserLabel}
    <div class="kill__btns">
        <button onclick="closeIframe();">${closeLabel}</button> &nbsp;
        <button onclick="closeIframeForever();">${closeForeverLabel}</button>
    </div>
    <img src='${staticServePath}/images/kill-browser.png' title='Kill IE6' class="kill__img" alt='Kill IE6'/>
</div>
<script>
    var closeIframe = function () {
        window.parent.$('iframe').prev().remove()
        window.parent.$('iframe').remove()
    }

    var closeIframeForever = function () {
        window.parent.Cookie.createCookie('showKill', true, 365)
        closeIframe()
    }
</script>
</@commonPage>