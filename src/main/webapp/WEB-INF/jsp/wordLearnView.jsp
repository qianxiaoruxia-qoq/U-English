<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/jsp/head.jsp"%>
</head>
<body>
<input type="hidden" id="userId" value="${user.id}">
<%@include file="top.jsp"%>
<div class="center" id="center">
    <div class="center-span">
        <span id="user-name">${menuName}</span>
    </div>
    <div class="clear"></div>
    <div class="left-main">
        <div class="word-list" id="word-list">
        </div>
    </div>
    <div class="right-menu">
        <div class="wordbook-head">
            <span>在学词书</span>
            <div class="learn-list">
            </div>
        </div>
        <div class="wordbook-head">
            <span>已学词书</span>
            <div class="learned-list">
            </div>
        </div>
        <div class="wordbook-head">
            <span>未学词书</span>
            <div class="not-learn-list">
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        ARTICLE.getWord($("#userId").val());
        ARTICLE.getBook("learn-list", $("#userId").val(), 0);
        ARTICLE.getBook("learned-list", $("#userId").val(), 1);
        ARTICLE.getBook("not-learn-list", $("#userId").val(), -1);
    })
</script>
</html>
