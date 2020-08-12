<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/jsp/head.jsp"%>
</head>
<body>
    <%@include file="top.jsp"%>
    <div class="center" id="center">
        <div class="mainContent">
            <aside>
                <ul class="am-nav">
                    <li class="am-active"><a href="#">个人中心</a></li>
                    <li><a href="javascript:location.reload();">个人资料</a></li>
                    <li><a onclick="PERCEN.postArticle()">发布文章</a></li>
                    <li><a onclick="PERCEN.myRelease(${user.id})">我的发布</a></li>
                    <li><a onclick="PERCEN.myCollection(${user.id}, 0, 1)">我的收藏</a></li>
                    <li><a onclick="PERCEN.myCourse(${user.id})">我的课程</a></li>
                    <c:if test="${user.username == 'zhangsan'}">
                        <li><a onclick="PERCEN.articleExam()">文章审核</a></li>
                    </c:if>
                    <li><a onclick="PERCEN.changePass()">修改密码</a></li>
                    <li><a onclick="PERCEN.loginOut()">退出登录</a></li>
                </ul>
            </aside>
            <div class="perCenter-main">
                <div id="perCenter-main-body">
                    <div id="index-name">
                        <a href="#">个人资料</a>
                    </div>
                    <hr>
                    <div>
                        <div>
                            <c:choose>
                                <c:when test="${user.avatar != null}">
                                    <a onclick="PERCEN.changeAvatar()"><img class="avatar" src="${pageContext.request.contextPath}${user.avatar}"/></a>
                                </c:when>
                                <c:otherwise>
                                    <a onclick="PERCEN.changeAvatar()"><img class="avatar" src="${pageContext.request.contextPath}/libs/img/1.jpg"/></a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div id="avatar-right">
                            <table>
                                <tr>
                                    <td>昵称：</td>
                                    <td>${user.nickname}</td>
                                </tr>
                                <tr>
                                    <td>介绍：</td>
                                    <c:choose>
                                        <c:when test="${user.introduction != null}">
                                            <td>${user.introduction}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>这个人很懒，什么都没留下！</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </table>
                            <hr>
                            <div id="details">
                                <div id="nick">
                                    <span id="user-name">用户名：${user.username}</span>
                                    <span id="nick-mid"><a onclick="PERCEN.userInfoEdit()">修改资料</a></span>
                                </div>
                                <div>
                                    <ul>
                                        <li class="ul-li">性别：${user.sex}</li>
                                        <li class="ul-li">生日：${user.birthday2}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>