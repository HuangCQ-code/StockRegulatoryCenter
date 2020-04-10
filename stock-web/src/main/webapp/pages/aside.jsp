<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<% String path = request.getContextPath() + "/"; %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <security:authorize access="hasRole('ADMIN')">
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-users"></i><span>权限管理</span>
                        <span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
                    </a>

                    <ul class="treeview-menu">
                        <li id="manager">
                            <a href="<%=path%>manager/findAll.do">
                                <i class="fa fa-circle-o"></i> 用户管理
                            </a>
                        </li>
                        <li id="role">
                            <a href="<%=path%>role/findAll.do">
                                <i class="fa fa-circle-o"></i> 角色管理
                            </a>
                        </li>
                        <li id="permission">
                            <a href="<%=path%>permission/findAll.do">
                                <i class="fa fa-circle-o"></i> 资源权限管理
                            </a>
                        </li>
                    </ul>
                </li>
            </security:authorize>

            <security:authorize access="hasRole('ADMIN')">
                <li id="sysLog">
                    <a href="<%=path%>sysLog/toSysLog.do">
                        <i class="fa fa-file-o"></i> <span>操作日志</span>
                    </a>
                </li>
            </security:authorize>
            <!-- fa-gear  设置图标 -->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>