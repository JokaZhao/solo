
<#include "macro-common_head.ftl"/>
<#macro commonPage title>
<!DOCTYPE html>
<html>
<head>
    <#if !blogTitle??>
    <#assign blogTitle = "Solo">
    </#if>
    <@head title="${title} - ${blogTitle}">
        <link type="text/css" rel="stylesheet"
        href="${staticServePath}/scss/start.css?${staticResourceVersion}" charset="utf-8"/>
        <meta name="robots" content="none"/>
    </@head>
</head>
<body>
<div class="wrap">
    <div class="content-wrap">
        <div class="content">
            <div class="main">
            <#nested>
            </div>
        </div>
    </div>
    <div class="footerWrapper">
        <div class="footer">
            Powered by Joka.Copyright © ${year}
        </div>
    </div>
</div>
</body>
</html>
</#macro>
