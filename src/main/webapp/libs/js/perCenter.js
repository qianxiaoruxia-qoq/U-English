var PERCEN = {
    userInfoEdit: function () {
        AJAX.html("/perCenter/editUserInfo", {},function (html) {
            $("#details").empty();
            $("#details").html(html);
        });
    },
    updateUserInfo:function () {
        AJAX.post("/perCenter/updateUserInfo", $("#userInfoEditForm").serialize(),function (result) {
            if (result.status == 200) {
                layer.alert("修改成功！");
                location.reload();
            } else {
                layer.alert(result.message);
            }
        });
    },
    changeAvatar: function () {  // 更改头像
        AJAX.html("/perCenter/changeAvatar", {},function (html) {
            layer.open({
                type: 1,
                title: "更换头像",
                offset: "100px",
                content: html,
                area: ["630px","515px"],
                btn: ['修改'],
                yes: function (index, layero) {
                    PERCEN.updateAvatar();
                }
            });
        });
    },
    updateAvatar: function () {
        var x = $('#x').val();
        var y = $('#y').val();
        var finalWidth = $('#w').val();
        var finalHeight = $('#h').val();
        var pic = $("#base64Pic").val();
        // 问题：当传大图片时会出现无法解析表单的错误，最大图片不能超过2M
        AJAX.post("/perCenter/updateAvatar",
            {x: x, y: y, finalWidth: finalWidth, finalHeight: finalHeight, pic: pic},
            function (result) {
            if (result.status == 200) {
                layer.closeAll();
                layer.alert("修改成功！");
                location.reload();
            } else {
                layer.alert(result.message);
            }
        });
    },
    postArticle: function () {  // 上传文章
        AJAX.html("/perCenter/postArticle", {},function (html) {
            $("#perCenter-main-body").html(html);
        });
    },
    myRelease: function(userId) {  // 我的发布
        AJAX.html("/perCenter/myRelease", {userId: userId},function (html) {
            $("#perCenter-main-body").html(html);
        });
    },
    delArticle: function(articleId) {
        layer.confirm("是否删除文章？", function () {
            AJAX.post("/perCenter/delArticle",{ articleId: articleId }, function (result) {
                if (result.status == 200) {
                    layer.closeAll();
                    layer.alert("删除文章成功！", function () {
                        location.reload();
                    });
                } else {
                    layer.alert(result.message);
                }
            });
        })
    },
    changePass: function () {
        AJAX.html("/perCenter/changePass", {},function (html) {
            $("#perCenter-main-body").html(html);
        });
    },
    myCollection: function(userId, sign, currentPage) {  // 我的收藏
        AJAX.html("/perCenter/myCollection", {userId: userId, sign:sign, currentPage: currentPage},function (html) {
            $("#perCenter-main-body").html(html);
        });
    },
    myCourse: function(userId) {
        AJAX.html("/perCenter/myCourse", {userId: userId},function (html) {
            $("#perCenter-main-body").html(html);
        });
    },
    articleExam: function() {
        AJAX.html("/perCenter/myRelease", {currentPage: 1, ispass: 0},function (html) {
            $("#perCenter-main-body").html(html);
        });
    },
    savePass: function () {
        var oldPass = $("#oldPass").val();
        var newPass = $("#newPass").val();

        if (oldPass == null || oldPass == "") {
            alert("请输入旧密码！");
            return;
        }

        if (newPass == null || newPass == "") {
            alert("请输入新密码！");
            return;
        }

        AJAX.post("/perCenter/savePass",
            {oldPass: oldPass, newPass: newPass},
            function (result) {
            if (result.status == 200) {
                layer.closeAll();
                layer.alert("修改成功！");
                $("#oldPass").val("");
                $("#newPass").val("");
            } else {
                layer.alert(result.message);
            }
        });
    },
    loginOut: function () {
        layer.confirm("是否确认退出登录？", function () {
            AJAX.post("/perCenter/loginOut",{}, function (result) {
                if (result.status == 200) {
                    layer.closeAll();
                    layer.alert("退出登录成功！");
                    location.reload();
                } else {
                    layer.alert(result.message);
                }
            });
        })
    },
    symbol: function (articleId, sign) {
        var loginUser = $("#loginUser").val();
        if (loginUser == null || loginUser == "") {
            alert("请先登录！");
            return;
        }

        AJAX.post("/perCenter/symbol",{articleId: articleId, sign: sign}, function (result) {
            if (result.status == 200) {
                ARTICLE.getWriter(articleId);
            } else {
                layer.alert(result.message);
            }
        });
    },
    delCollection: function (userId, articleId) {
        AJAX.post("/perCenter/symbol",{articleId: articleId, sign: 0}, function (result) {
            if (result.status == 200) {
                PERCEN.myCollection(userId, 0, 1);
            } else {
                layer.alert(result.message);
            }
        });
    }
}