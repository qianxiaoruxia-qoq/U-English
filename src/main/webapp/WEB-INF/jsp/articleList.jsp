<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="am-list ">
    <c:forEach var="article" items="${pageResult.object}">
        <li class="article-item">
            <div class="am-g">
                <div class="am-u-sm-10">
                    <a onclick="viewArticle(${article.id})" class="article-title">${article.title}</a>
                </div>
            </div>
            <div class="article-other">
                    ${article.createTime2}&nbsp;&nbsp;&nbsp;
                <span class="am-icon-thumbs-o-down"></span>&nbsp;${article.badNum}&nbsp;&nbsp;&nbsp;
                <span class="am-icon-thumbs-o-up"></span>&nbsp;${article.goodNum}&nbsp;&nbsp;&nbsp;
                <span class="am-icon-heart-o"></span>&nbsp;${article.collection}
            </div>
        </li>
    </c:forEach>
</ul>
<script>
    function viewArticle (articleId) {
        document.location.href = "${pageContext.request.contextPath}/article/showArticle?articleId=" + articleId;
    }
</script>