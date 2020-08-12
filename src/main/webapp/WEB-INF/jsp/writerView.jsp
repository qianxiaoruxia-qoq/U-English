<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
${articleUser.nickname}&nbsp;&nbsp;&nbsp;
${article.createTime2}&nbsp;&nbsp;&nbsp;
<c:choose>
    <c:when test="${myBad == 1}">
        <a onclick="PERCEN.symbol(${article.id}, '-1')"><span class="am-icon-thumbs-down"></span></a>&nbsp;${bad}&nbsp;&nbsp;&nbsp;
    </c:when>
    <c:otherwise>
        <a onclick="PERCEN.symbol(${article.id}, '-1')"><span class="am-icon-thumbs-o-down"></span></a>&nbsp;${bad}&nbsp;&nbsp;&nbsp;
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${myGood == 1}">
        <a onclick="PERCEN.symbol(${article.id}, '1')"><span class="am-icon-thumbs-up"></span></a>&nbsp;${good}&nbsp;&nbsp;&nbsp;
    </c:when>
    <c:otherwise>
        <a onclick="PERCEN.symbol(${article.id}, '1')"><span class="am-icon-thumbs-o-up"></span></a>&nbsp;${good}&nbsp;&nbsp;&nbsp;
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${myCollection == 1}">
        <a onclick="PERCEN.symbol(${article.id}, '0')"><span class="am-icon-heart"></span></a>&nbsp;${collection}
    </c:when>
    <c:otherwise>
        <a onclick="PERCEN.symbol(${article.id}, '0')"><span class="am-icon-heart-o"></span></a>&nbsp;${collection}
    </c:otherwise>
</c:choose>



