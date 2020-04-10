<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath() + "/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Stock 后台管理</title>
    <meta name="description" content="">
    <meta name="keywords" content="">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="
    ,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="<%=path%>plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="<%=path%>plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/bootstrap-slider/slider.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">

    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- 底部导航 /-->
</div>

<script src="<%=path%>plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="<%=path%>plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="<%=path%>plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>plugins/adminLTE/js/app.min.js"></script>
<script src="<%=path%>plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script src="../js/util.js"></script>
<script>

    $(document).ready(function () {
        // 激活导航位置
        setSidebarActive("admin-index");
    });
</script>
</body>

</html>