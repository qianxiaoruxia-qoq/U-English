<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/jsp/head.jsp"%>
</head>
    <body>
        <%@include file="top.jsp"%>
        <div class="center" id="center">
            <div class="am-g center-up-menu">
                <div class="am-u-sm-5 am-slider am-slider-default carousel" data-am-flexslider="{playAfterPaused: 8000}">
                    <ul class="am-slides">
                        <c:forEach var="menu" items="${picMenus}">
                            <li>
                                <a href="${pageContext.request.contextPath}/${menu.url}"><img src="${menu.pic}" /></a>
                                <div class="am-slider-desc">${menu.menuName}</div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="am-u-sm-7">
                    <table class="am-table">
                        <c:forEach var="menu" items="${secondMenus}">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/${menu.url}">${menu.menuName}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div >
                <div class="center-jiugong">
                    <div class="center-jiugong-text">
                        <a>听力</a>
                    </div>
                    <div class="center-jiugong-main">
                        <ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-thumbnails">
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=11"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/001.jpg" /></a>
                                <div class="introduce">
                                    afdafs
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=12"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/002.jpg" /></a>
                                <div class="introduce">
                                    听力1
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=13"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/003.jpg" /></a>
                                <div class="introduce">
                                    听力2
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=14"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/004.jpg" /></a>
                                <div class="introduce">
                                    听力3
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=15"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/001.jpg" /></a>
                                <div class="introduce">
                                    La Da Dee
                                </div>
                            </li>
                            <li><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/002.jpg" /></li>
                            <li><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/003.jpg" /></li>
                            <li><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/004.jpg" /></li>
                        </ul>
                    </div>
                </div>
                <div class="center-jiugong">
                    <div class="center-jiugong-text">
                        <a>文章</a>
                    </div>
                    <div class="center-jiugong-main">
                        <ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-thumbnails">
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=4"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/001.jpg" /></a>
                                <div class="introduce">
                                    啊啊啊啊
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=5"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/002.jpg" /></a>
                                <div class="introduce">
                                    发布文章2
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=6"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/003.jpg" /></a>
                                <div class="introduce">
                                    测试2
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=7"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/004.jpg" /></a>
                                <div class="introduce">
                                    测试3
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=8"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/001.jpg" /></a>
                                <div class="introduce">
                                    测试4
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/article/showArticle?articleId=8"><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/002.jpg" /></a>
                                <div class="introduce">
                                    测试5
                                </div>
                            </li>
                            <li><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/003.jpg" /></li>
                            <li><img class="am-thumbnail" src="http://amazeui.shopxo.net/static/images/004.jpg" /></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>