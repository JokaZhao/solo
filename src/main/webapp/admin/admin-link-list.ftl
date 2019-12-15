
<div>
    <div id="linkTable"></div>
    <div id="linkPagination" class="fn__margin12 fn__right"></div>
</div>
<div class="fn__clear"></div>
<div class="form form__no-table">
${addLinkLabel}
    <label>${linkTitle1Label}</label>
    <input id="linkTitle" type="text"/>
    <label>${url1Label}</label>
    <input id="linkAddress" type="text"/>
    <label>${linkDescription1Label}</label>
    <input id="linkDescription" type="text"/>
    <label>${linkIcon1Label}</label>
    <input id="linkIcon" type="text"/><br><br>
    <button onclick="admin.linkList.add();" class="fn__right">${saveLabel}</button>
    <div class="fn__clear"></div>
</div>
<div id="updateLink" class="fn__none form form__no-table" data-title="${updateLinkLabel}">
    <label>${linkTitle1Label}</label>
    <input id="linkTitleUpdate" type="text"/>
    <label>${url1Label}</label>
    <input id="linkAddressUpdate" type="text"/>
    <label>${linkDescription1Label}</label>
    <input id="linkDescriptionUpdate" type="text"/>
    <label>${linkIcon1Label}</label>
    <input id="linkIconUpdate" type="text"/><br><br>
    <button onclick="admin.linkList.update();" class="fn__right">${updateLabel}</button>
    <div class="fn__clear"></div>
</div>
${plugins}