/* 设置超时时间，15秒 */
var ajax_timeout = 15 * 1000;

var layerLoadIndex;
/* 给属性设默认值,无论post还是get方法，底层都是在掉ajax方法 */
$.ajaxSetup({
    /* 所有在请求发送之前执行这个函数 */
    beforeSend: function () {
        /* load一个层，就会给这个层起一个索引，也就是编号，
        其返回值也就是这个编号，这个编号是唯一的，存到layerLoadIndex中 */
        layerLoadIndex = layer.load(2, {
            time: ajax_timeout
        });
    },
    /* 请求完成执行的函数，不管是否失败，都会执行 */
    complete: function () {
        layer.close(layerLoadIndex);
    },
    /* 是否缓存 */
    cache: false,
    /* 如果请求失败，则调这个error函数，请求结束后必调complete函数 */
    error: function (xhr, status, error) {
        if ("timeout" == status) {
            layer.alert("_请求超时_");
        } else if ("error" == status) {
            layer.alert("_请求错误_");
        } else if ("abort" == status) {
            layer.alert("_请求中止_");
        } else if ("parsererror" == status) {
            layer.alert("_解析错误_");
        } else {
            layer.alert("_未知错误_");
        }
    }
});

// 判断是否以某个字符结尾
String.prototype.endWith=function(endStr){
    var d=this.length-endStr.length;
    return (d>=0&&this.lastIndexOf(endStr)==d)
}

/* 一开始就加载完了，但是找不到myroleId对象 */
/*
$("#myroleId").on("change",function () {
    var roleId = $("#myroleId").val();
    AJAX.post("/user/onchange", {roleId: roleId},function (result) {
        if (result.status == 200) {
            layer.alert("触发事件！")
            location.reload();
        } else {
            layer.alert(result.message);
        }
    });
});
*/

function selectChange() {
    var roleId = $("#myroleId").val();
    AJAX.post("/user/choiceOk", {roleId: roleId},function (result) {
        if (result.status == 200) {
            location.reload();
        } else {
            layer.alert(result.message);
        }
    });
}

function getCheckItem() {
    var containerId = "bootstrap-table";

    /* js参数可以不定义，可以任意传，内部默认把参数都封装到arguments数组里 */
    if (arguments.length == 1) {
        containerId = arguments[0];
    }
    var arr = new Array();
    /* 根据id拿到容器对象，find(":checkbox")拿到所有type=CheckBox的对象。each方法就是for循环迭代，每迭代一次执行一次里面的方法 */
    $("#" + containerId).find(":checkbox").each(function () {
        /* this代表当前的CheckBox对象，然后用$(this)将原生的CheckBox对象包装成jQuery对象 */
        /* 然后prop("checked")拿到属性，勾上就是checked=true，没勾上checked=false */
        /* if ($(this).prop("checked"))表达式表示CheckBox是否被勾上 */
        if ($(this).prop("checked")) {
            /* 如果是true的话就往数组里push CheckBox的value，这里的value取的是id的值 */
            arr.push($(this).val());
        }
    });
    return arr;
}

/*
method: 方法
dataType：期望的返回的数据类型
url
data：数据
successFunction：成功的函数
 */
function doAjax(method, dataType, url, data, successFunction) {
    /* 调用ajax方法，并给属性赋值，其他属性使用默认值，也就是上面方法设置的值和本来的默认值 */
    $.ajax({
        data: data,
        dataType: dataType,
        success: successFunction,
        type: method,
        url: ctx + url
    });
}

var AJAX = {
    html: function (url,data, successFunction) {
        doAjax("GET", "html", url, data, successFunction);
    },
    get: function (url, data, successFunction) {
        doAjax("GET", "json", url, data, successFunction);
    },
    post: function (url, data, successFunction) {
        doAjax("POST", "json", url, data, successFunction);
    }
}
