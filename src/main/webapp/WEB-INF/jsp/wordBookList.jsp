<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="am-list ">
    <c:forEach var="wordBook" items="${wordBooks}">
        <li class="article-item">
            <div class="am-g">
                <div class="am-u-sm-10">
                    ${wordBook.name}
                </div>
                <div class="am-u-sm-2">
                    <span class="am-badge am-badge-danger"><a onclick="WORD.learnWordBook(${wordBook.id})">学习</a></span>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>
