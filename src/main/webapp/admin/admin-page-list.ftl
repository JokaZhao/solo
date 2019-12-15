
<div>
    <div id="pageTable">
    </div>
    <div id="pagePagination" class="fn__margin12 fn__right">
    </div>
    <div class="fn__clear"></div>
</div>
<div class="form">
    <div>
        <label>${title1Label}</label>
        <input id="pageTitle" type="text"/>
    </div>
    <div>
        <label>${permalink1Label}</label>
        <input id="pagePermalink" type="text"/>
    </div>
    <div>
        <label>${icon1Label}</label>
        <input id="pageIcon" type="text"/>
    </div>
    <div>
        <label>${openMethod1Label}</label>
        <select id="pageTarget">
            <option value="_self">${targetSelfLabel}</option>
            <option value="_blank">${targetBlankLabel}</option>
            <option value="_parent">${targetParentLabel}</option>
            <option value="_top">${targetTopLabel}</option>
        </select>
    </div>
    <div class="fn__right">
        <button onclick="admin.pageList.submit();">${saveLabel}</button>
    </div>
    <div class="fn__clear"></div>
</div>
<div class="fn__clear"></div>
${plugins}
