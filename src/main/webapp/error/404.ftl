
<#include "../common-template/macro-common_page.ftl">

<@commonPage "404 Not Found!">
<h2>404 Not Found!</h2>
<img class="img-error" src="${staticServePath}/images/404.gif" title="404" alt="404 Not Found!"/>
<div class="a-error">
    Please <a href="${loginURL}">start</a> or return to <a href="${servePath}">Index</a>.
</div>
</@commonPage>