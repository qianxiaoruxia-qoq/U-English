<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="am-form am-form-horizontal" id="userInfoEditForm">
    <input type="hidden" value="${user.id}" name="id">
    <input type="hidden" value="${user.password}" name="password">
    <div class="am-form-group">
        <label class="am-u-sm-2 am-form-label">用户名</label>
        <div class="am-u-sm-10">
            <input name="username" value="${user.username}" readonly>
        </div>
    </div>

    <div class="am-form-group">
        <label class="am-u-sm-2 am-form-label">昵称</label>
        <div class="am-u-sm-10">
            <input name="nickname" value="${user.nickname}">
        </div>
    </div>

    <div class="am-form-group">
        <label class="am-u-sm-2 am-form-label">性别</label>
        <div class="am-u-sm-10">
            <input name="sex" value="${user.sex}">
        </div>
    </div>

    <div class="am-form-group">
        <label class="am-u-sm-2 am-form-label">生日</label>
        <div class="am-u-sm-10">
            <input type="text" class="am-form-field" name="birthday" value="${user.birthday2}" placeholder="日历组件" data-am-datepicker readonly />
        </div>
    </div>

    <div class="am-form-group">
        <label class="am-u-sm-2 am-form-label">简介</label>
        <div class="am-u-sm-10">
            <input name="introduction" value="${user.introduction}">
        </div>
    </div>
    <a href="javascript:location.reload();" class="am-btn am-btn-secondary" type="button">取消</a>
    <button class="am-btn am-btn-secondary" type="button" onclick="PERCEN.updateUserInfo()">提交</button>
</form>
<script src="${pageContext.request.contextPath}/libs/AmazeUI-2.7.2/assets/js/amazeui.js"></script>