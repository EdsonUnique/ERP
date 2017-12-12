<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			//$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
	function showMsg(msg,uuid){
		//top.document.getElementById("context-msg").style.display = "block";
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="${pageContext.request.contextPath}/resource/resource_delete?resource_id="+uuid;
		top.lock.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">资源管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${pageContext.request.contextPath }/resource/resource_query" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">资源名称</td>
						<td width="123"><input type="text" size="18" name="name" /></td>
						<td width="62">资源类别</td>
						<td width="142">
							<select class="kuan" name="is_action">
								<option value="">----请-选-择----</option>
								<option value="0">URL</option>
								<option value="1">action访问</option>
							</select>
						</td>
						<td width="60">操作类别</td>
						<td width="149">
							<select class="kuan" name="is_operation">
								<option value="">----请-选-择----</option>
								<option value="1">可操作</option>
								<option value="0">可视</option>
							</select>
						</td>
						<td width="70"><a id="query"> <img src="${pageContext.request.contextPath}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a href="${pageContext.request.contextPath}/resource/resource_addOrUpdateUI"><img src="${pageContext.request.contextPath}/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath}/images/table_bg.gif) repeat-x;">
						<td height="30">资源名称</td>
						<td>资源类别</td>
						<td>操作类别</td>
						<td>资源值</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="resources" var="the_resource">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">${the_resource.name }</td>
						<td>${the_resource.is_action==1?'action调用':'URL访问' }</td>
						<td>${the_resource.is_operation==1?'可操作':'可视' }</td>
						<td align="left">${the_resource.path }</td>
						<td>
							<img src="${pageContext.request.contextPath}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="${pageContext.request.contextPath}/resource/resource_addOrUpdateUI?resource_id=${the_resource.resource_id}" class="xiu">修改</a>
							</span> 
							<img src="${pageContext.request.contextPath}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？',${the_resource.resource_id})">删除</a>
							</span>
						</td>
					</tr>
					</s:iterator>
				</table>
			</div>
		 </form>
	</div>
	<div class="content-bbg"></div>
</div>
