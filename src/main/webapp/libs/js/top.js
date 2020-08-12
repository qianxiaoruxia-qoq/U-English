var TOP = {
    login: function () {
        $("#login").modal();
    },
    backHome: function () {
        AJAX.html("/", {},function (html) {
            document.open(html)
        });
    },
    search: function () {
        var searchInput = $("#searchInput").val();
        if (searchInput == "" || searchInput == null) {
            layer.alert("请输入要查询的内容！");
            return;
        }
        $("#center").empty();
        AJAX.html("/article/toMore", {key: searchInput, currentPage: 1},function (html) {
            $("#center").html(html);
        });
    }
}