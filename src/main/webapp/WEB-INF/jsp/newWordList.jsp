<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myRelease-list">
    <ul class="am-list am-list-static">
        <c:forEach var="newWord" items="${newWords}">
            <li>
                <div class="am-g">
                    <div class="am-u-sm-10">
                        ${newWord.wordEnglish}&nbsp;&nbsp;&nbsp;${newWord.wordChinese}
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
