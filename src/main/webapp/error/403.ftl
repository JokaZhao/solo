
<#include "../common-template/macro-common_page.ftl">

<@commonPage "403 Forbidden!">
<h2>403 Forbidden!</h2>
<img class="img-error" src="${staticServePath}/images/403.png" alt="403" title="403 Forbidden!" />
<div class="a-error">
    ${msg!}
    Return to <a href="${servePath}">Index</a>.
</div>
</@commonPage>
