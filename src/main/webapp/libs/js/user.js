var USER = {
    list: function () {
        AJAX.html("/user/list/html", {},function (html) {
            if (html.endWith("</h5>")) {
                layer.alert(html);
            } else {
                $("#right").html(html);
            }
        });
    },
    add: function () {
        AJAX.html("/user/add/html", {},function (html) {
            if (html.endWith("</h5>")) {
                layer.alert(html);
            } else {
                $("#right").html(html);
            }
        });
    },
    save: function () {
        AJAX.post("/user/save", $("#userAddForm").serialize(),function (result) {
            if (result.status == 200) {
                layer.closeAll();
                layer.alert("保存成功！");
                USER.add();
            } else {
                layer.alert(result.message);
            }
        });
    },
    edit:function () {
        var arr = getCheckItem();
        if (arr.length != 1) {
            layer.alert("请只选择一条记录");
            return;
        }
        AJAX.html("/user/edit/html", {userId: arr[0]},function (html) {
            if (html.endWith("</h5>")) {
                layer.alert(html);
            } else {
                layer.open({
                    type: 1,
                    title: "编辑用户",
                    offset: "100px",
                    content: html,
                    area: ["600px"],
                    btn: ['修改'],
                    yes: function (index, layero) {
                        USER.update();
                    }
                });
            }
        });
    },
    update:function () {
        AJAX.post("/user/update", $("#userEditForm").serialize(),function (result) {
            if (result.status == 200) {
                layer.closeAll();
                layer.alert("修改成功！");
                USER.list();
            } else {
                layer.alert(result.message);
            }
        });
    },
    editUserRole: function() {
        var arr = getCheckItem();
        if (arr.length != 1) {
            layer.alert("请只选择一条记录");
            return;
        }
        AJAX.html("/user/editUserRole/html", {userId: arr[0]},function (html) {
            if (html.endWith("</h5>")) {
                layer.alert(html);
            } else {
                layer.open({
                    type: 1,
                    title: "编辑角色",
                    offset: "200px",
                    content: html,
                    area: ["600px"],
                    btn: ['修改'],
                    yes: function (index, layero) {
                        USER.updateUserRole();
                    }
                });
            }
        });
    },
    updateUserRole: function () {
        var obj = document.getElementsByName("roleCheck");
        var check_val = new Array();
        for(k in obj){
            if(obj[k].checked)
                check_val.push(obj[k].value);
        }
        if (check_val.length == 0) {
            layer.alert("请选择至少一条记录");
            return;
        }
        var userId = $("#userId").val();
        AJAX.post("/user/updateUserRole", {roleIds: check_val.join(), userId: userId}, function (result) {
            if (result.status == 200) {
                layer.closeAll();
                layer.alert("修改成功！");
                USER.list();
            } else {
                layer.alert(result.message);
            }
        });
    },
    editArea: function () {
        var arr = getCheckItem();
        if (arr.length != 1) {
            layer.alert("请只选择一条记录");
            return;
        }
        AJAX.html("/user/editArea/html", {userId: arr[0]},function (html) {
            if (html.endWith("</h5>")) {
                layer.alert(html);
            } else {
                layer.open({
                    type: 1,
                    title: "编辑区域",
                    offset: "200px",
                    content: html,
                    area: ["600px"],
                    btn: ['完成']
                });
            }
        });
    },
    editUserRoleArea: function (roleId, userId) {
        if (roleId == 1) {
            layer.alert("管理员无管理区域！");
            return;
        }

        AJAX.html("/user/editUserRoleArea/html", {roleId: roleId, userId: userId},function (html) {
            if (html.endWith("</h5>")) {
                layer.alert(html);
            } else {
                layer.open({
                    type: 1,
                    title: "编辑区域",
                    offset: "100px",
                    content: html,
                    area: ["600px"],
                    btn: ['修改'],
                    yes: function (index, layero) {
                        USER.updateUserManArea();
                    }
                });
            }
        });
    },
    updateUserManArea: function () {
        AJAX.post("/user/saveUserManArea", $("#userRoleAreaEditForm").serialize(),function (result) {
            if (result.status == 200) {
                layer.closeAll();
                layer.alert("修改成功！");
                USER.list();
            } else {
                layer.alert(result.message);
            }
        });
    },
    resetPwd: function () {
        var arr = getCheckItem();
        if (arr.length == 0) {
            layer.alert("请至少选择一条记录");
            return;
        }
        layer.confirm("确认重置这些用户的密码？", function () {
            AJAX.post("/user/resetPwd", {userIds: arr.join()}, function (result) {
                if (result.status == 200) {
                    layer.closeAll();
                    layer.alert("重置成功！");
                    USER.list();
                } else {
                    layer.alert(result.message);
                }
            });
        });
    },
    accountStart: function () {
        var arr = getCheckItem();
        if (arr.length == 0) {
            layer.alert("请至少选择一条记录");
            return;
        }
        layer.confirm("确认启用这些账户？", function () {
            AJAX.post("/user/accountState", {userIds: arr.join(), status: 1}, function (result) {
                if (result.status == 200) {
                    layer.closeAll();
                    layer.alert("启用成功！");
                    USER.list();
                } else {
                    layer.alert(result.message);
                }
            });
        });
    },
    accountEnd: function () {
        var arr = getCheckItem();
        if (arr.length == 0) {
            layer.alert("请至少选择一条记录");
            return;
        }
        layer.confirm("确认停用这些账户？", function () {
            AJAX.post("/user/accountState", {userIds: arr.join(), status: 0}, function (result) {
                if (result.status == 200) {
                    layer.closeAll();
                    layer.alert("停用成功！");
                    USER.list();
                } else {
                    layer.alert(result.message);
                }
            });
        });
    }
}