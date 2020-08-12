<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/jsp/head.jsp"%>
</head>
<body>
<input type="hidden" id="sort" value="${course.flag}">
<input type="hidden" id="courseId" value="${course.id}">
<%@include file="top.jsp"%>
<div class="center" id="center">
    <div class="left-main">
        <div id="course-media">
            <video src="${pageContext.request.contextPath}/${course.media}"  width=100%" controls="controls">
            </video>
        </div>
        <div class="article-head">
            <span>${course.name}</span>
        </div>
    </div>
    <div class="right-menu">
        <div class="article-head">
            <span>全部课程</span>
        </div>
        <div id="course-main-list">

        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        var sort = $("#sort").val();
        var courseId = $("#courseId").val();
        ARTICLE.getCourseList(sort);
        var loginUser = $("#loginUser").val();
        if (loginUser != null || loginUser != "") {
            ARTICLE.addLearnCourse(courseId);
        }
    })
</script>
</html>
