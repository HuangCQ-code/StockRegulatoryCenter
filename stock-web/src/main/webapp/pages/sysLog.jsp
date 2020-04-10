<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath() + "/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>操作日志管理</title>
    <meta name="description" content="">
    <meta name="keywords" content="">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">

    <link rel="stylesheet" href="<%=path%>plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="<%=path%>plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="<%=path%>css/style.css">
    <link rel="stylesheet" href="<%=path%>plugins/table/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/dialog/bootstrap-dialog.min.css">
    <link rel="stylesheet" href="<%=path%>plugins/jquery.mloading/mloading.css">
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

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                操作日志管理
            </h1>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content">
            <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">操作日志管理</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--数据列表-->
                        <table id="dataList">

                        </table>
                        <!--数据列表/-->

                    </div>
                    <!-- 数据表格 /-->

                </div>
                <!-- /.box-body -->
            </div>

        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- @@close -->
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- 底部导航 /-->

    <!-- 查看详情 模态框 -->
    <div id="showModal" class="modal fade modalTop" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作日志详情</h4>
                </div>
                <div class="modal-body">

                    <div class="box-body">
                        <div class="form-horizontal">

                            <div class="row data-type table-bordered">
                                <div class="col-sm-2 title">用户名</div>
                                <div class="col-sm-4 data text" id="showUsername"></div>

                                <div class="col-sm-2 title">iP</div>
                                <div class="col-sm-4 data text" id="showIp"></div>

                                <div class="col-sm-2 title">访问url</div>
                                <div class="col-sm-10 data text" id="showUrl"></div>

                                <div class="col-sm-2 title">访问方法</div>
                                <div class="col-sm-10 data text" id="showMethod"></div>

                                <div class="col-sm-2 title myParameter">参数</div>
                                <div class="col-sm-10 data myParameter" id="showParameters"></div>

                                <div class="col-sm-2 title">处理时长（毫秒）</div>
                                <div class="col-sm-4 data text" id="showExecutionTime"></div>

                                <div class="col-sm-2 title">访问时间</div>
                                <div class="col-sm-4 data text" id="showVisitMillis"></div>

                                <div class="col-sm-2 title">创建时间</div>
                                <div class="col-sm-4 data text" id="showCreateTime"></div>

                                <div class="col-sm-2 title">更新时间</div>
                                <div class="col-sm-4 data text" id="showUpdateTime"></div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>

        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <!--模态窗口/-->

    <script src="<%=path%>plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="<%=path%>plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="<%=path%>plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path%>plugins/adminLTE/js/app.min.js"></script>
    <script src="<%=path%>plugins/table/bootstrap-table.min.js"></script>
    <script src="<%=path%>plugins/table/bootstrap-table-zh-CN.js"></script>
    <script src="<%=path%>plugins/dialog/bootstrap-dialog.min.js"></script>
    <script src="<%=path%>plugins/jquery.mloading/mloading.js"></script>
    <script src="<%=path%>js/util.js"></script>
    <script src="<%=path%>js/date.js"></script>
    <script>
        $(document).ready(function () {
            initTable();

            // 激活导航位置
            setSidebarActive("sysLog");
        });

        function initTable() {
            var url = "<%=path%>sysLog/findAllSysLog.do";
            $('#dataList').bootstrapTable({
                method: 'POST',
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded",
                cache: false,
                striped: true,                      //是否显示行间隔色
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                url: url,
                showColumns: false, //右上角的显示 和 隐藏 列的按钮
                pagination: true,
                queryParams: queryParams,
                minimumCountColumns: 2,
                pageNumber: 1,                      //初始化加载第一页，默认第一页
                pageSize: 5,                       //每页的记录行数（*）
                pageList: [5, 10, 20],//可供选择的每页的行数（*）
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showExport: false,
                exportDataType: 'all',
                clickToSelect: true,    //是否启用点击选中行
                showPaginationSwitch: false,
                responseHandler: function (data) {
                    return {
                        "total": data.total,
                        "rows": data.list
                    };
                },
                onLoadSuccess: function (data) {    //加载成功后的事件

                },
                onClickRow: function (row) {
                    detail(row.id);
                },
                columns: [
                    {
                        field: '',
                        title: '序号',
                        align: 'center',
                        valign: 'middle',
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    }, {
                        field: 'username',
                        title: '用户名',
                        align: 'center',
                        valign: 'middle',
                    }, {
                        field: 'ip',
                        title: 'iP',
                        align: 'center',
                        valign: 'middle',
                    }, {
                        field: 'method',
                        title: '访问方法',
                        align: 'center',
                        valign: 'middle',
                    }, {
                        field: 'parameters',
                        title: '参数',
                        align: 'center',
                        valign: 'middle',
                    }, {
                        field: 'visitMillis',
                        title: '访问时间',
                        align: 'center',
                        valign: 'middle',
                        width: '156',
                        formatter: function (value, row, index) {
                            return dateFormat(value);
                        }
                    }
                    ],
            }).bootstrapTable('resetView');
        };

        function queryParams(params) {
            var param = {
                pageNum: this.pageNumber,
                pageSize: this.pageSize,
            }
            return param;
        };

        //打开详情页面
        function detail(id) {
            var row = $('#dataList').bootstrapTable('getRowByUniqueId', id);
            $("#showUsername").html(row.username);
            $("#showIp").html(row.ip);
            $("#showUrl").html(row.url);
            $("#showMethod").html(row.method);
            $("#showParameters").html(row.parameters);
            $("#showVisitMillis").html(dateFormat(row.visitMillis));
            $("#showExecutionTime").html(row.executionTime);
            $("#showCreateTime").html(dateFormat(row.createMillis));
            $("#showUpdateTime").html(dateFormat(row.lastUpdateMillis));
            $('#showModal').modal();
        };

    </script>
</body>

</html>