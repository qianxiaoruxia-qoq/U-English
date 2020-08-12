<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td>
            <input type="file" onchange="showImg()" id="pic" accept=".jpg, .jpeg, .png">
        </td>
    </tr>
    <tr>
        <td>
            <img src="" id="avatarImg" style=""/>
        </td>
    </tr>
</table>
<input type="hidden" id="x">
<input type="hidden" id="y">
<input type="hidden" id="w">
<input type="hidden" id="h">
<input type="hidden" id="base64Pic">

<script type="text/javascript">

    function showImg() {
        var picFile = document.getElementById("pic");
        var file = picFile.files[0];
        var reader = new FileReader();  //新建一个FileReader对象
        reader.readAsDataURL(file);     //将读取的文件转换成base64格式
        // 当文件读取完毕时的回调函数
        reader.onload = function (e) {
            var base64Data = e.target.result;

            // 当传大图片时会出现无法解析表单的错误，最大图片不能超过2M
            /*var strLength=base64Data.length;
            var fileLength=parseInt(strLength-(strLength/8)*2);
            if (fileLength > 2097152) {
                alert("上传的图片不能超过2M！");
                return;
            }*/

            document.getElementById("avatarImg").src = base64Data;
            $('#base64Pic').val(base64Data);
            $('#avatarImg').Jcrop({  // 使用jcrop插件进行截图，并在base64Pic中显示
                onChange: showPreview,
                onSelect: showPreview,
                aspectRatio: 1
            });
        }
    }

    // 得到所截取的图像的左上角所对应的的图片的位置的x,y轴位置及所截图像的长宽
    function showPreview(coords)
    {
        $('#x').val(coords.x);
        $('#y').val(coords.y);
        $('#w').val(coords.w);
        $('#h').val(coords.h);
    }
</script>
