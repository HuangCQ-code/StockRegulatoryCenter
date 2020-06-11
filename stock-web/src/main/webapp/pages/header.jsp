<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<% String path = request.getContextPath() + "/"; %>
<!-- 页面头部 -->
<header class="main-header">
	<!-- Logo -->
	<a href="<%=path%>index.jsp" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>Stock</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>Stock</b> 后台管理</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span class="hidden-xs">
							<security:authentication property="principal.username"></security:authentication>
						</span>
					</a>
					<ul class="dropdown-menu">
						<li class="user-footer">
							<div class="pull-right">
								<a href="<%=path%>logout.do" class="btn btn-default btn-flat">注销</a>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
</header>
<!-- 页面头部 /-->