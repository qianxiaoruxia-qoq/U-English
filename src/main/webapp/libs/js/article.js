var ARTICLE = {
    paging: function (userId, currentPage) {
        $("#perCenter-main-body").empty();
        AJAX.html("/article/myRelease", {userId: userId, currentPage: currentPage},function (html) {
            $("#perCenter-main-body").html(html);
        });
    },
    viewArticle: function (articleId) {
        document.location.href = "${pageContext.request.contextPath}/article/showArticle";
        AJAX.html("/article/showArticle", {articleId: articleId},function (html) {

        });
    },
    getComment: function (articleId) {
        AJAX.html("/article/getComment", {articleId: articleId},function (html) {
            $("#comment-area").html(html);
        });
    },
    getWriter: function (articleId) {
        AJAX.html("/article/getWriter", {articleId: articleId},function (html) {
            $("#article-message").html(html);
        });
    },
    addHot: function (articleId) {
        AJAX.post("/article/addHot",{articleId: articleId}, function (result) {
            if (result.status == 200) {
            } else {
                layer.alert(result.message);
            }
        });
    },
    getList: function (order, list, sort) {
        $("." + list).empty();
        AJAX.html("/article/articleList", {order: order, sort: sort},function (html) {
            $("." + list).html(html);
        });
    },
    toMore: function (sort, currentPage) {
        $("#center").empty();
        AJAX.html("/article/toMore", {sort: sort, currentPage: currentPage},function (html) {
            $("#center").html(html);
        });
    },
    getMedia: function (media, sort) {
        $("#media-show").empty();
        AJAX.html("/article/getMedia", {media: media, sort: sort},function (html) {
            $("#media-show").html(html);
        });
    },
    getWord: function (userId) {
        $("#word-list").empty();
        AJAX.html("/article/getWord", {userId: userId},function (html) {
            $("#word-list").html(html);
        });
    },
    getBook: function (list, userId, islearn) {
        $("." + list).empty();
        AJAX.html("/article/getWordBookList", {userId: userId, islearn: islearn},function (html) {
            $("." + list).html(html);
        });
    },
    getCourseList: function (flag) {
        $("#course-main-list").empty();
        AJAX.html("/article/getCourseList", {flag: flag},function (html) {
            $("#course-main-list").html(html);
        });
    },
    addLearnCourse: function (courseId) {
        AJAX.post("/article/addLearnCourse",{courseId: courseId}, function (result) {
            if (result.status == 200) {
            } else {
                layer.alert(result.message);
            }
        });
    },
    articleExam: function (articleId) {
        AJAX.html("/article/articleExam", {articleId: articleId},function (html) {
            layer.open({
                type: 1,
                title: "审核",
                content: html,
                area: ["600px"],
                btn: ['确认'],
                yes: function (index, layero) {
                    ARTICLE.saveExam();
                }
            });
        });
    },
    saveExam: function () {
        var articleId = $("#articleId").val();
        var ispass = $("#ispass-select").val();
        var reason = $("#reason").val();
        AJAX.post("/article/saveExam",{articleId: articleId, ispass: ispass, reason: reason}, function (result) {
            if (result.status == 200) {
                layer.closeAll();
                layer.alert("审核成功！");
            } else {
                layer.alert(result.message);
            }
        });
    },
    selectList: function (selectText) {
        AJAX.html("/article/selectList", {selectText: selectText},function (html) {
            layer.open({
                type: 1,
                content: html,
                area: ["600px", "500px"]
            });
        });
    }
}