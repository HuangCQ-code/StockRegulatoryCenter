<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath() + "/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>登录</title>

    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">

    <link rel="stylesheet" href="<%=path%>plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="<%=path%>plugins/jquery.mloading/mloading.css">
    <link rel="stylesheet" href="<%=path%>plugins/dialog/bootstrap-dialog.min.css">
</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <b>Stock</b> 后台管理系统
    </div>
    <div class="login-box-body">
        <p class="login-box-msg">用户登录</p>
        <form id="loginForm" onkeydown="if(event.keyCode==13){return false;}">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user" ></i></span>
                    <input type="text" id="username" name="username" class="form-control" placeholder="用户名" onfocus="hiddenErrMes()">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock" ></i></span>
                    <input type="password" id="password" name="password" class="form-control" placeholder="密码" onfocus="hiddenErrMes()">
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <input type="button" class="btn btn-primary btn-block btn-flat" onclick="doSubmit()" value="登录">
                </div>
            </div>
        </form>
        <div id="err" class="hidden">
            <span style="color: #dd4b39" id="errMes"></span>
        </div>
    </div>
</div>

<script src="<%=path%>plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="<%=path%>plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>plugins/formValidation/formValidation.min.js"></script>
<script src="<%=path%>plugins/formValidation/bootstrap.min.js"></script>
<script src="<%=path%>plugins/formValidation/zh_CN.js"></script>
<script src="<%=path%>plugins/dialog/bootstrap-dialog.min.js"></script>
<script src="<%=path%>plugins/jquery.mloading/mloading.js"></script>
<script src="<%=path%>js/util.js"></script>
<script>
    $(function () {
        var message = getUrlParam("message");
        if (message == 'expired') {
            confirmTip("会话已超时，请重新登录", toLoginPage, "normal", "danger");
        }
        if (message == 'maxSession') {
            confirmTip("当前账号已在其他设备登录", toLoginPage, "normal", "danger");
        }
        initValidation();
    });

    function initValidation(){
        $('#loginForm').formValidation({
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            locale: 'zh_CN',
            fields: {
                username: {
                    message: '输入不合法！',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                password: {
                    message: '输入不合法！',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 16,
                            message: '密码长度必须在6到16之间'
                        }
                    }
                },

            }
        });
    };

    function doSubmit() {
        loadShow();
        hiddenErrMes();
        $("#loginForm").data("formValidation").validate();
        if($("#loginForm").data("formValidation").isValid() == false) {
            loadHide();
            return ;
        }
        var datas = {
            username: $("#username").val(),
            password: $("#password").val()
        };
        $.ajax({
            type: 'post',
            url: '<%=path%>login.do',
            data: datas,
            dateType: "html",
            success: function (data) {
                loadHide();
                if (data == 0){
                    window.location = '<%=path%>pages/main.jsp';
                } else if (data == 2) {
                    $("#err").removeClass('hidden');
                    $("#errMes").html("用户名或密码错误！");
                } else if (data == 3) {
                    $("#err").removeClass('hidden');
                    $("#errMes").html("用户名不存在！");
                } else if (data == 4) {
                    $("#err").removeClass('hidden');
                    $("#errMes").html("您的账号已被冻结，请联系管理员！");
                } else {
                    $("#err").removeClass('hidden');
                    $("#errMes").html("登录失败！");
                }
            },
            error: function () {
                loadHide();
                $("#err").removeClass('hidden');
                $("#errMes").html("网络超时");
            }
        });
    };

    function hiddenErrMes(){
        $("#err").addClass('hidden');
    };

</script>
</body>

</html>