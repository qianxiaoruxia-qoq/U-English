<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="post-article">
    <legend>文章发布</legend>
    <div class="am-g">
        <div>
            <input id="article-title" class="am-form-field" placeholder="请输入标题">
        </div>
        <br>
        <div id="articleEditor"></div>
    </div>
    <button class="am-btn am-btn-secondary" id="article-btn" type="submit" onclick="saveArticle()">提交</button>
</div>
<script>
    $(function () {
        //加载富文本编辑器
        createItemImageEditor();
    })

    var managerEditor;
    // 创建富文本编辑器
    function createItemImageEditor() {
        var E = window.wangEditor;
        managerEditor = new E("#articleEditor");
        // 配置服务器端地址
        managerEditor.customConfig.uploadImgServer = "${pageContext.request.contextPath}/perCenter/upload";
        // 自定义文件名
        managerEditor.customConfig.uploadFileName = "imgFile";
        // 隐藏“网络图片”
        managerEditor.customConfig.showLinkImg = false;

        managerEditor.customConfig.uploadImgHooks = {
            success: function (xhr, editor, result) {
                // 图片上传并返回结果，图片插入成功之后触发
                // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                // alert("success:" + result.data[0]);
                // successFn(result);
            },
        }
        managerEditor.create();
    }

    function saveArticle () {
        // 文章标题
        var title = $("#article-title").val();
        // 文章内容
        var article = managerEditor.txt.html();
        if (title == null || title == "") {
            alert("请先起个名字吧！");
        }
        if (article == null || article == "") {
            alert("你还什么都没写啊！");
        }
        AJAX.post("/perCenter/saveArticle", {title: title, article: article}, function (result) {
            if (result.status == 200) {
                layer.closeAll();
                layer.alert("发布成功！", function () {
                    location.reload();
                });
            } else {
                layer.alert(result.message);
            }
        });
    }
</script>