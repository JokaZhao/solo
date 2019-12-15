
<#include "../common-template/macro-common_page.ftl">

<@commonPage "${articleViewPwdLabel}">
<h2>
${articleTitle}
</h2>
<br><br><br>
<form class="form" method="POST" action="${servePath}/console/article-pwd">
    <label for="pwdTyped">访问密码</label>
    <input type="password" id="pwdTyped" name="pwdTyped" />
    <input type="hidden" name="articleId" value="${articleId}" />
    <div style="text-align: fn__right">
         <#if msg??>
            <span class="error">${msg}</span>
         </#if>
        <button id="confirm" type="submit">${confirmLabel}</button>
    </div>
</form>
<br><br><br>
</@commonPage>