<#include "macro-common_page.ftl">
<@commonPage "${welcomeToSoloLabel}!">
<h2>
    <span>${welcomeToSoloLabel} Solo</span>
</h2>

<div id="github">
<#--    <div class="github__icon startAction">-->
<#--        <img src="${staticServePath}/images/github.png"/>-->
<#--    </div>-->
<#--    <br>-->
<#--    <button class="startAction">${useGitHubAccountLoginLabel}</button><br>-->
    <div id="loginBox">
        <div class="login-box active">
            <div class="login-body">
                <div class="form form__no-table">
                    <div class="form-group">
                        <label>用户名</label>
                        <input id="userName" type="text">
                    </div>
                    <div class="form-group">
                        <label>登录密码</label>
                        <input id="password" type="password">
                    </div>
                    <div class="form-group">
                        <button class="login-button" id="login">登录</button>
                    </div>
                </div>
            </div>
            <div class="github__text">
                <ul>
                    <li id='msg'></li>
                </ul>
            </div>
        </div>
        <input type="hidden" id="kid" name="kid" value="${kid}" />
        <input type="hidden" id="token" name="token" value="${token}" />
    </div>
    <div id="loading" style="display: none">
        <div class="text-wrapper">
            <div class="text part1">
                <div>
                    <span class="letter"><div class="character">L</div> <span></span></span>
                    <span class="letter"><div class="character">o</div> <span></span></span>
                    <span class="letter"><div class="character">a</div> <span></span></span>
                    <span class="letter"><div class="character">d</div> <span></span></span>
                    <span class="letter"><div class="character">i</div> <span></span></span>
                    <span class="letter"><div class="character">n</div> <span></span></span>
                    <span class="letter"><div class="character">g</div> <span></span></span>
                </div>
            </div>
            <div class="how-to"><span>正在加载中，请您耐心等待...</span></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${staticServePath}/js/lib/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticServePath}/js/login.min.js" charset="utf-8"></script>
<#include "service.ftl">
<link type="text/css" rel="stylesheet"  href="${staticServePath}/scss/loading.css?${staticResourceVersion}" charset="utf-8"/>
</@commonPage>