$(function() {

   $(".input input").focus(function() {

      $(this).parent(".input").each(function() {
         $("label", this).css({
            "line-height": "18px",
            "font-size": "18px",
            "font-weight": "100",
            "top": "0px"
         })
         $(".spin", this).css({
            "width": "100%"
         })
      });
   }).blur(function() {
      $(".spin").css({
         "width": "0px"
      })
      if ($(this).val() == "") {
         $(this).parent(".input").each(function() {
            $("label", this).css({
               "line-height": "60px",
               "font-size": "24px",
               "font-weight": "300",
               "top": "10px"
            })
         });

      }
   });

   $(".alt-2").click(function() {
      if (!$(this).hasClass('material-button')) {
         $(".shape").css({
            "width": "100%",
            "height": "100%",
            "transform": "rotate(0deg)"
         })

         setTimeout(function() {
            $(".overbox").css({
               "overflow": "initial"
            })
         }, 600)

         $(this).animate({
            "width": "140px",
            "height": "140px"
         }, 500, function() {
            $(".box").removeClass("back");

            $(this).removeClass('active')
         });

         $(".overbox .title").fadeOut(300);
         $(".overbox .input").fadeOut(300);
         $(".overbox .button").fadeOut(300);

         $(".alt-2").addClass('material-buton');
      }

   })

   $(".material-button").click(function() {

      if ($(this).hasClass('material-button')) {
         setTimeout(function() {
            $(".overbox").css({
               "overflow": "hidden"
            })
            $(".box").addClass("back");
         }, 200)
         $(this).addClass('active').animate({
            "width": "700px",
            "height": "700px"
         });

         setTimeout(function() {
            $(".shape").css({
               "width": "50%",
               "height": "50%",
               "transform": "rotate(45deg)"
            })

            $(".overbox .title").fadeIn(300);
            $(".overbox .input").fadeIn(300);
            $(".overbox .button").fadeIn(300);
         }, 700)

         $(this).removeClass('material-button');

      }

      if ($(".alt-2").hasClass('material-buton')) {
         $(".alt-2").removeClass('material-buton');
         $(".alt-2").addClass('material-button');
      }

   });

});

var LOR = {
   login: function () {
      var username = $("#username").val();
      var pass = $("#pass").val();
      if (username == "" || username == null) {
         layer.alert("请输入用户名！");
         return;
      }
      if (pass == "" || pass == null) {
         layer.alert("请输入密码！");
         return;
      }
      // 数字、字母，4-16个字符
      var uReg = /^[a-zA-Z0-9]{4,16}$/;
      if (!uReg.test(username)) {
         layer.alert("用户名格式必须是数字、字母下划线，4-16个字符！");
         return;
      }
      // 数字、字母，6-16个字符
      var pReg = /^[a-zA-Z0-9]{6,16}$/;
      if (!pReg.test(pass)) {
         layer.alert("密码格式必须是数字、字母，6-16个字符！");
         return;
      }

      AJAX.post("/lor/login", $("#loginForm").serialize(),function (result) {
         if (result.status == 200) {
            $("#login").modal('close');
            $("#menu-hd").html(username);
            $("#loginUser").val(username);
         } else {
            layer.alert(result.message);
         }
      });
   },
   register: function () {
      var regname = $("#regname").val();
      var regpass = $("#regpass").val();
      var reregpass = $("#reregpass").val();

      if (regname == "" || regname == null) {
         layer.alert("请输入用户名！");
         return;
      }
      if (regpass == "" || regpass == null) {
         layer.alert("请输入密码！");
         return;
      }

      if (reregpass == "" || reregpass == null) {
         layer.alert("请输入确认的密码！");
         return;
      }

      // 数字、字母，4-16个字符
      var uReg = /^[a-zA-Z0-9]{4,16}$/;
      if (!uReg.test(regname)) {
         layer.alert("用户名格式必须是数字、字母，4-16个字符！");
         return;
      }
      // 数字、字母，6-16个字符
      var pReg = /^[a-zA-Z0-9]{6,16}$/;
      if (!pReg.test(regpass)) {
         layer.alert("密码格式必须是数字、字母，6-16个字符！");
         return;
      }

      var pReg = /^[a-zA-Z0-9]{6,16}$/;
      if (!pReg.test(reregpass)) {
         layer.alert("密码格式必须是数字、字母，6-16个字符！");
         return;
      }

      if (regpass != reregpass) {
         layer.alert("两次输入的密码不相同！");
         retuan;
      }

      AJAX.post("/lor/register", $("#registerForm").serialize(),function (result) {
         if (result.status == 200) {
            $("#loginUser").val(result.data);
            $("#login").modal('close');
            $("#menu-hd").html(regname);
            $("#loginUser").val(regname);
         } else {
            layer.alert(result.message);
         }
      });
   }
}