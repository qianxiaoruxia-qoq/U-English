<%@ page import="com.ue.util.PageResult" %>
<%@ page import="com.ue.pojo.Article" %>
<%@ page import="com.ue.util.LoginUser" %>
<%@ page import="com.ue.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myRelease">
    <%
        User user = LoginUser.getLoginUser(request);
    %>
    <div id="index-name">
        <a href="#">我的收藏</a>
    </div>
    <div id="myRelease-list">
        <ul class="am-list am-list-static">
            <c:forEach var="myCollection" items="${pageResult.object}">
                <li>
                    <div class="am-g">
                        <div class="am-u-sm-10">
                            <a onclick="viewArticle(${myCollection.id})">${myCollection.title}</a>
                        </div>
                        <div class="am-u-sm-2">

                            <span class="am-badge am-badge-danger"><a onclick="PERCEN.delCollection(<%=user.getId()%>, ${myCollection.id})">取消关注</a></span>
                        </div>
                    </div>
                    <div class="article-other">
                            ${myCollection.createTime2}&nbsp;&nbsp;&nbsp;
                                <span class="am-icon-thumbs-o-down"></span>&nbsp;${myCollection.badNum}&nbsp;&nbsp;&nbsp;
                                <span class="am-icon-thumbs-o-up"></span>&nbsp;${myCollection.goodNum}&nbsp;&nbsp;&nbsp;
                                <span class="am-icon-heart-o"></span>&nbsp;${myCollection.collection}
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

    <ul class="am-pagination am-pagination-right">
        <%
            PageResult<Article> pageResult = (PageResult<Article>) request.getAttribute("pageResult");
            int i;
            for (i = 1; i <= pageResult.getPageCount(); i++) {
                if (i == 1 && pageResult.getCurrentPage() > 1) { %>
                    <li><a onclick="PERCEN.myCollection(<%=user.getId()%>, 0, <%=(pageResult.getCurrentPage() - 1)%>)">&laquo;</a></li>
                <%}
                if (i == pageResult.getCurrentPage()) {%>

        <li class="am-active"><a><%=i%></a></li>

        <%        } else {%>

        <li><a onclick="PERCEN.myCollection(<%=user.getId()%>, 0, <%=i%>)"><%=i%></a></li>

        <%
                }
                if (i == pageResult.getPageCount() && pageResult.getCurrentPage() < pageResult.getPageCount()) {%>
        <li><a onclick="PERCEN.myCollection(<%=user.getId()%>, 0, <%=(pageResult.getCurrentPage() + 1)%>)">&raquo;</a></li>
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