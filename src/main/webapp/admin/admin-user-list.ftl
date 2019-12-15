
<div>
    <div id="userTable"></div>
    <div id="userPagination" class="fn__margin12 fn__right"></div>
</div>
<div class="fn__clear"></div>
<div class="form form__no-table">
<div id="userUpdate" class="fn__none form form__no-table">
    <label for="userNameUpdate">${userName1Label}<span style="color: #991a1a">${changeUserNameTipLabel}</span></label>
    <input id="userNameUpdate" type="text"/>
    <label for="userURLUpdate">${userURL1Label}</label>
    <input id="userURLUpdate" type="text"/>
    <label for="userAvatarUpdate">${userAvatar1Label}</label>
    <input id="userAvatarUpdate" type="text"/>
    <label for="userB3KeyUpdate">B3 Key</label>
    <input id="userB3KeyUpdate" type="text"/>
    <br><br>
    <button onclick="admin.userList.update();" class="fn__right">${updateLabel}</button>
    <div class="fn__clear"></div>
</div>
${plugins}
