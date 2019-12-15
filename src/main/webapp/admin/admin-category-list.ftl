
<div>
    <div id="categoryTable"></div>
    <div id="categoryPagination" class="fn__margin12 fn__right"></div>
</div>
<div class="fn__clear"></div>
<div class="form form__no-table">
    <label for="categoryName">${linkTitle1Label}</label>
    <input id="categoryName" type="text"/>
    <label for="categoryURI">URI：</label>
    <input id="categoryURI" type="text"/>
    <label for="categoryDesc">${linkDescription1Label}</label>
    <input id="categoryDesc" type="text"/>
    <label for="categoryTags">${tags1Label}</label>
    <span class="tag__select">
        <input id="categoryTags" type="text"/>
    </span><br>
    <button onclick="admin.categoryList.add();" class="fn__right">${saveLabel}</button>
    <div class="fn__clear"></div>
</div>
${plugins}
