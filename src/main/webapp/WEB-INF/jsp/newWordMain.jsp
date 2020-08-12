<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/jsp/head.jsp"%>
</head>
<body>
<%@include file="top.jsp"%>
<div class="center" id="center">
    <div class="center-span">
        <span id="user-name">${menuName}</span>
    </div>
    <div class="clear"></div>
    <div class="course-main" id="newword-main-list">

    </div>
</div>
<script>
    $(function () {
        var loginUser = $("#loginUser").val();
        if (loginUser == null || loginUser == "") {
            alert("请先登录！");
            return;
        }
        WORD.getNewWordList();
    })
</script>
</body>