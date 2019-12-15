
<#macro comment_script oId commentable>
<#if isLoggedIn && commentable>
    <div style="position: fixed;bottom: -300px;width: 100%;opacity: 0;background-color: #f1f7fe;padding: 20px 0;transition: all .15s ease-in-out;z-index: 100;left: 0;"
         id="soloEditor">
        <div style="max-width: 768px;margin: 0 auto;padding: 0 10px;">
            <div id="soloEditorComment"></div>
            <div style="display: flex;margin-top: 10px;line-height: 30px">
                <div style="flex: 1;" id="soloEditorReplyTarget"></div>
                <div style="color: #d23f31" id="soloEditorError"></div>
                <span id="soloEditorCancel" style="margin: 0 10px;padding: 0 12px;cursor: pointer">
                    ${cancelLabel}
                </span>
                <button id="soloEditorAdd" style="border-radius: 4px;background-color: #60b044;border:1px solid #569e3d;color: #fff;padding: 0 12px">
                    ${confirmLabel}
                </button>
            </div>
        </div>
    </div>
</#if>
<script type="text/javascript">
    Util.addScript('${staticServePath}/js/page${miniPostfix}.js?${staticResourceVersion}', 'soloPageScript')
    var page = new Page({
        "commentContentCannotEmptyLabel": "${commentContentCannotEmptyLabel}",
        "oId": "${oId}",
        "blogHost": "${blogHost}",
        "randomArticles1Label": "${randomArticles1Label}",
        "externalRelevantArticles1Label": "${externalRelevantArticles1Label}"
    });
    $(document).ready(function () {
        page.load();
        <#nested>
    });
</script>
</#macro>