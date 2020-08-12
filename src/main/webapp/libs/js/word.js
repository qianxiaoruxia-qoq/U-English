var WORD = {
    learnWordBook: function (wordBookId) {
        var loginUser = $("#loginUser").val();
        if (loginUser == null || loginUser == "") {
            alert("请先登录！");
            return;
        }

        AJAX.post("/wordBook/addlearnWordBook",{wordBookId: wordBookId}, function (result) {
            if (result.status == 200) {
                location.reload();
            } else {
                layer.alert(result.message);
            }
        });
    },
    searchWord: function (selectText) {
        AJAX.html("/article/wordView", {selectText: selectText},function (html) {
            $("#click-div").html(html);
        });
    },
    addNewWord: function (wordId) {
        var loginUser = $("#loginUser").val();
        if (loginUser == null || loginUser == "") {
            alert("请先登录！");
            return;
        }
        AJAX.post("/wordBook/addNewWord",{wordId: wordId}, function (result) {
            if (result.status == 200) {
                layer.alert("添加成功！")
            } else {
                layer.alert(result.message);
            }
        });
    },
    getNewWordList: function () {
        AJAX.html("/wordBook/getNewWordList", {},function (html) {
            $("#newword-main-list").html(html);
        });
    }
}