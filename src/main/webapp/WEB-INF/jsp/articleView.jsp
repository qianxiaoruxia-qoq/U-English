<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/jsp/head.jsp"%>
</head>
<body>
<%@include file="top.jsp"%>
<div class="center" id="center">
    <input type="hidden" id="articleId" value="${article.id}"/>
    <input type="hidden" id="userId" value="${user.id}"/>
    <input type="hidden" id="userAvatar" value="${user.avatar}"/>
    <input type="hidden" id="userNickname" value="${user.nickname}"/>
    <input type="hidden" id="sort" value="${article.sort}"/>
    <input type="hidden" id="media" value="${article.media}"/>
    <input type="hidden" id="ispass" value="${article.isPass}"/>
    <input type="hidden" id="selectText"/>
    <div class="mainContent">
        <%--<aside>
            本文生词
        </aside>
        <div class="perCenter-main">--%>
            <div id="perCenter-main-body">
                <article class="am-article">
                    <p class="am-article-divider"></p>
                    <div class="am-article-hd">
                        <h1 class="am-article-title">${article.title}</h1>
                        <br>
                        <p class="am-article-meta" id="article-message">
                        </p>
                        <c:if test="${user.id == 1 && article.isPass == 0}">
                            <p class="article-pass">
                                <a onclick="ARTICLE.articleExam(${article.id})">审核</a>
                            </p>
                        </c:if>

                    </div>
                    <div class="clear"></div>

                    <p class="am-article-divider"></p>
                    <div id="media-show">
                    </div>
                    <hr/>

                    <div class="am-article-bd" id="article-content">
                        <p>${article.content}</p>
                    </div>
                </article>
                <hr>
                <div>
                    发表评论
                    <div class="am-form-group" style="margin-top: 10px">
                        <textarea id="comment-text" rows="5" id="doc-ta-1" style="width: 100%;"></textarea>
                    </div>
                    <button class="am-btn am-btn-secondary" id="comment-btn" type="submit" onclick="saveComment(${article.id})">发表评论</button>
                </div>
                <div style="clear: right"></div>
                <hr>
                <div id="comment-area">

                </div>
            </div>
        <%--</div>--%>
    </div>
</div>
</body>
<script>
    $(function () {
        ARTICLE.addHot($("#articleId").val());
        ARTICLE.getWriter($("#articleId").val());
        var sort = $("#sort").val();
        if (sort == 2 || sort == 3 || sort == 4) {
            var media = $("#media").val();
            ARTICLE.getMedia(media, sort);
        }
        ARTICLE.getComment($("#articleId").val());

        //去掉默认的contextmenu事件，否则会和右键事件同时出现。
        document.oncontextmenu = function(e){
            e.preventDefault();
        };
        document.getElementById("article-content").onmousedown = function(e){
            if(e.button ==2){
                var selectText = $("#selectText").val();
                ARTICLE.selectList("'" + selectText + "'");
            }
        }
    })

    function saveComment (articleId) {
        var loginUser = $("#loginUser").val();
        if (loginUser == null || loginUser == "") {
            alert("请先登录！");
            return;
        }
        // 文章内容
        var comment = $("#comment-text").val();
        if (comment == null || comment == "") {
            alert("你还什么都没写啊！");
        }
        var path = "${pageContext.request.contextPath}";
        var date = new Date();
        AJAX.post("/perCenter/saveComment", {comment: comment, articleId: articleId}, function (result) {
            if (result.status == 200) {
                ARTICLE.getComment($("#articleId").val());
            } else {
                layer.alert(result.message);
            }
        });
    }

    var testDiv = document.getElementById("article-content");

    testDiv.onmouseup = function(){
        var selectionObj = null, rangeObj = null, selectedText = "", selectedHtml = "";
        if(window.getSelection){
            selectionObj = window.getSelection();
            selectedText = selectionObj.toString();
            rangeObj = selectionObj.getRangeAt(0);
            var docFragment = rangeObj.cloneContents();
            var tempDiv = document.createElement("div");
            tempDiv.appendChild(docFragment);
            selectedHtml = tempDiv.innerHTML;
        }else if(document.selection){
            selectionObj = document.selection;
            rangeObj = selectionObj.createRange();
            selectedText = rangeObj.text;
            selectedHtml = rangeObj.htmlText;
        }
        $("#selectText").val(selectedText);
    };
</script>