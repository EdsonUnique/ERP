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
		top.$('hid-action').value="${pageContext.request.contextPath}/employee/emp_delete?emp_id="+uuid;
		top.lock.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${pageContext.request.contextPath }/employee/emp_query" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">用&nbsp;户&nbsp;名</td>
						<td><input type="text" size="14" name="name" /></td>
						<td>真实姓名</td>
						<td><input type="text" size="14" name="realName"/></td>
						<td>电&nbsp;&nbsp;&nbsp;&nbsp;话</td>
						<td><input type="text" size="14" name="telephone"/></td>
						<td>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
						<td>
							<select class="kuan" name="gender">
								<option value="">----请-选-择----</option>
								<s:iterator value="genders" var="the_gender">
									<option value="${the_gender.key }">${the_gender.value }</option>
								</s:iterator>
							</select>
						</td>
							
						<td width="70"><a href="${pageContext.request.contextPath}/employee/emp_addOrUpdateUI"> <img src="${pageContext.request.contextPath}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td  height="30">电子邮件</td>
						<td><input type="text" size="14" name="email"/></td>
						<td>出生日期</td>
						<td>
							<input type="text"  size="14" name="birthday" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>登录时间</td>
						<td>
							<input type="text" size="14" name="loginTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>部门名称</td>
						<td>
							<select class="kuan" name="model.dept.did">
								<option value="">----请-选-择----</option>
								<s:iterator value="depts" var="the_dept">
									<option value="${the_dept.did }">${the_dept.name }</option>
								</s:iterator>
							</select>
						</td>
						<td><a id="query"> <img src="${pageContext.request.contextPath}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<s:if test="emps.size()>0">
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath}/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">用户名</td>
						<td width="10%">真实姓名</td>
						<td width="5%">性别</td>
						<td width="12%">出生日期</td>
						<td width="10%">电话</td>
						<td width="12%">电子邮件</td>
						<td width="9%">所属部门</td>
						<td width="16%">最后登录时间</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="emps" var="emp">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">${emp.name }</td>
						<td>${emp.realName}</td>
						<td>${emp.gender==0?'女':'男'}</td>
						<td>${emp.birthday}</td>
						<td>${emp.telephone}</td>
						<td>${emp.email}</td>
						<td>${emp.dept.name}</td>
						<td>${emp.loginTime }</td>
						<td>
							<img src="${pageContext.request.contextPath}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="${pageContext.request.contextPath }/employee/emp_addOrUpdateUI?emp_id=${emp.emp_id}" class="xiu">修改</a>
							</span> 
							<img src="${pageContext.request.contextPath}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？','${emp.emp_id}')">删除</a>
							</span>
						</td>
					</tr>
					</s:iterator>
				</table>
			</div>
			</s:if>
			<s:else><span style="color:red;font-size:20px; font-weight:bold;" >没有您要的数据！</span></s:else>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
