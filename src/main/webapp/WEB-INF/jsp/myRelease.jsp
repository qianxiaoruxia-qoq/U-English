<%@ page import="com.ue.util.PageResult" %>
<%@ page import="com.ue.pojo.Article" %>
<%@ page import="com.ue.util.LoginUser" %>
<%@ page import="com.ue.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myRelease">
    <div id="index-name">
        <a href="#">我的发布</a>
    </div>
    <div id="myRelease-list">
        <ul class="am-list am-list-static">
            <c:forEach var="myArticle" items="${pageResult.object}">
                <li>
                    <div class="am-g">
                        <div class="am-u-sm-6">
                            <a onclick="viewArticle(${myArticle.id})">${myArticle.title}</a>
                        </div>
                        <div class="am-u-sm-6" style="color: red;">
                            <c:if test="${myArticle.isPass == -1}">
                                审核未通过：${myArticle.unPassReason}
                            </c:if>
                        </div>
                        <div class="am-u-sm-2">
                            <span class="am-badge am-badge-danger"><a onclick="PERCEN.delArticle(${myArticle.id})">删除</a></span>
                        </div>
                    </div>
                    <div class="article-other">
                            ${myArticle.createTime2}&nbsp;&nbsp;&nbsp;
                                <span class="am-icon-thumbs-o-down"></span>&nbsp;${myArticle.badNum}&nbsp;&nbsp;&nbsp;
                                <span class="am-icon-thumbs-o-up"></span>&nbsp;${myArticle.goodNum}&nbsp;&nbsp;&nbsp;
                                <span class="am-icon-heart-o"></span>&nbsp;${myArticle.collection}
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

    <ul class="am-pagination am-pagination-right">
        <%
            PageResult<Article> pageResult = (PageResult<Article>) request.getAttribute("pageResult");
            if (pageResult == null) {
                pageResult = new PageResult<>();
            }
            User user = LoginUser.getLoginUser(request);
            int i;
            for (i = 1; i <= pageResult.getPageCount(); i++) {
                if (i == 1 && pageResult.getCurrentPage() > 1) { %>
                    <li><a onclick="ARTICLE.paging(<%=user.getId()%>, <%=(pageResult.getCurrentPage() - 1)%>)">&laquo;</a></li>
                <%}
                if (i == pageResult.getCurrentPage()) {%>

        <li class="am-active"><a><%=i%></a></li>

        <%        } else {%>

        <li><a onclick="ARTICLE.paging(<%=user.getId()%>, <%=i%>)"><%=i%></a></li>

        <%
                }
                if (i == pageResult.getPageCount() && pageResult.getCurrentPage() < pageResult.getPageCount()) {%>
        <li><a onclick="ARTICLE.paging(<%=user.getId()%>, <%=(pageResult.getCurrentPage() + 1)%>)">&raquo;</a></li>
                <%}
            }
        %>
    </ul>
</div>
<script>
    function viewArticle (articleId) {
        document.location.href = "${pageContext.request.contextPath}/article/showArticle?articleId=" + articleId;
    }
</script>