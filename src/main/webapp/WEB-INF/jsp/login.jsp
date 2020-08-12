<%@ page contentType="text/html;charset=UTF-8" %>
<div class="am-modal am-modal-no-btn" id="login">
    <div class="am-popup-inner">
            <div class="materialContainer">
                <div class="box">
                    <form id="loginForm">
                        <div class="title">登录</div>
                        <div class="input">
                            <label for="username">用户名</label>
                            <input type="text" name="username" id="username" onkeyup="value=value.replace(/[\W]/g,'');" minlength="4" maxlength="16">
                            <span class="spin"></span>
                        </div>
                        <div class="input">
                            <label for="pass">密码</label>
                            <input type="password" name="pass" id="pass" onkeyup="this.value=this.value.replace(/[\W]/g,'');" minlength="6" maxlength="16">
                            <span class="spin"></span>
                        </div>
                        <div class="button login">
                            <button type="button" onclick="LOR.login()">
                                <span>登录</span>
                            </button>
                        </div>
                    </form>
                    <a href="javascript:" class="pass-forgot">忘记密码？</a>
                </div>

                <div class="overbox am-modal-dialog">
                    <div class="material-button alt-2">
                        <span class="shape"></span>
                    </div>
                    <form id="registerForm">
                        <div class="title">注册</div>
                        <div class="input">
                            <label for="regname">用户名</label>
                            <input type="text" name="regname" id="regname" onkeyup="value=value.replace(/[\W]/g,'');" minlength="4" maxlength="16">
                            <span class="spin"></span>
                        </div>
                        <div class="input">
                            <label for="regpass">密码</label>
                            <input type="password" name="regpass" id="regpass" onkeyup="this.value=this.value.replace(/[\W]/g,'');" minlength="6" maxlength="16">
                            <span class="spin"></span>
                        </div>
                        <div class="input">
                            <label for="reregpass">确认密码</label>
                            <input type="password" name="reregpass" id="reregpass" onkeyup="this.value=this.value.replace(/[\W]/g,'');" minlength="6" maxlength="16">
                            <span class="spin"></span>
                        </div>
                        <div class="button">
                            <button type="button" onclick="LOR.register()">
                                <span>注册</span>
                            </button>
                        </div>
                    </form>

                </div>

            </div>
    </div>
</div>
