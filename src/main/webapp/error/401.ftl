<#include "../common-template/macro-common_page.ftl">

<@commonPage "401 Unauthorized!">
    <h2>401 Unauthorized!</h2>
    <img class="img-error" src="${staticServePath}/images/401.png" alt="401" title="401 Unauthorized!"/>
    <div class="a-error">
        Please <a href="/login">start</a> or return to <a href="${servePath}">Index</a>.
    </div>
    <script type="text/javascript">
        (function () {
            window.location.href="/login";
        })()
    </script>
</@commonPage>
