<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/libs/AmazeUI-2.7.2/assets/css/admin.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/libs/AmazeUI-2.7.2/assets/js/amazeui.js"></script>
<div class="am-slider am-slider-default carousel" id="word-learn" data-am-flexslider="{animationLoop: false, slideshow: false, prevText: '上一个', nextText: '下一个'}">
    <ul class="am-slides" id="my-slider">
        <c:forEach var="word" items="${words}">
            <li>
                <img class="word-img" src="${word.wordImg}" />
                <div class="am-slider-desc word-introduce">
                        ${word.wordEnglish}<br>
                    ${word.wordChinese}<br>
                    <a onclick="WORD.addNewWord(${word.id})">加入生词本</a>
                </div>
            </li>
        </c:forEach>
        <li>
            <img class="word-img" src="" />
            <div class="am-slider-desc" id="word-complete">
                <span id="word-span">恭喜你完成今天的单词学习</span>
            </div>
        </li>
    </ul>
</div>