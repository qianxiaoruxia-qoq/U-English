<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <%@include file="/WEB-INF/jsp/head.jsp"%>
    </head>
    <body>
        <input type="hidden" id="sort" value="${sort}">
        <%@include file="top.jsp"%>
        <div class="center" id="center">
            <div class="center-span">
                <span id="user-name">${menuName}</span>
                <span id="nick-mid"><a onclick="ARTICLE.toMore(${sort}, 1)">更多>></a></span>
            </div>
            <div class="clear"></div>
            <div class="left-main">
                <div class="article-head">
                    <span>最新</span>
                </div>
                <div class="new-list">
                </div>

            </div>
            <div class="right-menu">
                <div class="article-head">
                    <span>最热</span>
                </div>
                <div class="hot-list">
                </div>
            </div>
        </div>
    </body>
<script>
    $(function () {
        var sort = $("#sort").val();
        ARTICLE.getList("create_time", "new-list", sort);
        ARTICLE.getList("hot", "hot-list", sort);
    })
</script>
</html>
