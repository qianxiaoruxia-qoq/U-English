<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article class="am-comment">
    <c:forEach var="comment" items="${comments}">
        <a href="#link-to-user-home">
            <img src="${pageContext.request.contextPath}${comment.userAvatar}" alt="" class="am-comment-avatar" width="48" height="48"/>
        </a>

        <div class="am-comment-main">
            <header class="am-comment-hd">
                <!--<h3 class="am-comment-title">评论标题</h3>-->
                <div class="am-comment-meta">
                    <a href="#link-to-user" class="am-comment-author">${comment.userNickname}</a>
                    评论于 <time>${comment.commentTime2}</time>
                </div>
            </header>

            <div class="am-comment-bd">${comment.comment}</div>
        </div>
        <br>
    </c:forEach>

    <%-- 评论样板 --%>
    <%--<a href="#link-to-user-home">
        <img src="" alt="" class="am-comment-avatar" width="48" height="48"/>
    </a>
    <div class="am-comment-main">
        <header class="am-comment-hd">
            <!--<h3 class="am-comment-title">评论标题</h3>-->
            <div class="am-comment-meta">
                <a href="#link-to-user" class="am-comment-author">某人</a>
                评论于 <time>2014-7-12 15:30</time>
            </div>
        </header>
        <div class="am-comment-bd">
            ...
        </div>
    </div>--%>
</article>
