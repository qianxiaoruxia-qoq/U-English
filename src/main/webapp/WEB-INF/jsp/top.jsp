<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--头 -->
<header>
    <article>
        <div class="mt-logo">
            <!--顶部导航条 -->
            <div class="am-container header">
                <input type="hidden" id="loginUser" value="${sessionScope.loginUser}"/>
                <ul class="message-l">
                    <div class="topMessage">
                        <div class="menu-hd" id="menu-hd">
                            <c:choose>
                                <c:when test="${sessionScope.loginUser != null}">
                                    ${sessionScope.loginUser.username}
                                </c:when>
                                <c:otherwise>
                                    <a onclick="TOP.login()" target="_top">登录/注册</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </ul>
                <%@include file="login.jsp"%>
                <ul class="message-r">
                    <div class="topMessage my-shangcheng">
                        <div class="menu-hd MyShangcheng"><a onclick="perCenter()" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
                    </div>
                    <%--<div class="topMessage mini-cart">
                        <div class="menu-hd"><a id="mc-menu-hd" href="#" target="_top"><i class="am-icon-calendar  am-icon-fw"></i><span>签到</span><strong id="J_MiniCartNum" class="h"></strong></a></div>
                    </div>
                    <div class="topMessage favorite">
                        <div class="menu-hd"><a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a></div>
                    </div>--%>
                </ul>
            </div>

            <!--悬浮搜索框-->

            <div class="nav white">
                <div class="logoBig">
                    <li><img src="${pageContext.request.contextPath}/libs/img/logobig.png" /></li>
                </div>

                <div class="search-bar pr">
                    <a name="index_none_header_sysc" href="#"></a>
                    <form>
                        <input id="searchInput" type="text" placeholder="搜索" autocomplete="off">
                        <input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="button" onclick="TOP.search()">
                    </form>
                </div>
            </div>

            <div class="clear"></div>
        </div>
    </article>
</header>
<div id="top-menu">
    <c:forEach var="menu" items="${menus}">
        <div class="am-dropdown" data-am-dropdown>
            <button class="am-btn am-btn-primary am-dropdown-toggle" data-am-dropdown-toggle style="width:100%;" >${menu.menuName}</button>
            <ul class="am-dropdown-content">
                <c:forEach var="subMenu" items="${menu.subMenus}">
                    <li><a onclick="clickF('${subMenu.url}')">${subMenu.menuName}</a></li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>

</div>
<b class="line"></b>
<script>
    function perCenter() {
        var loginUser = $("#loginUser").val();
        if (loginUser == null || loginUser == "") {
            alert("请先登录！");
            return;
        }
        document.location.href = "${pageContext.request.contextPath}/perCenter/perCenter";
    }

    function clickF(url) {
        document.location.href = "${pageContext.request.contextPath}/menu/" + url;
    }
</script>