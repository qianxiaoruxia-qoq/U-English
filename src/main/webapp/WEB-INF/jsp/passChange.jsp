<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="changPassword">
    <div>
        <span>修改密码</span>
    </div>
    <div class="am-input-group am-input-group-secondary changePassw">
        <span class="am-input-group-label"><i class="am-icon-user am-icon-fw"></i></span>
        <input type="password" id="oldPass" class="am-form-field" placeholder="请输入旧密码">
    </div>
    <div class="am-input-group am-input-group-secondary changePassw">
        <span class="am-input-group-label"><i class="am-icon-user am-icon-fw"></i></span>
        <input type="password" id="newPass" class="am-form-field" placeholder="请输入新密码">
    </div>
    <button type="button" class="am-btn am-btn-primary changePassw" onclick="PERCEN.savePass()">修改</button>
</div>
