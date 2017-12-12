<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">

	var $j=jQuery.noConflict();//避免jQuery与el表达式冲突
	$j(function() {
		$j("#query").click(function() {
			$j("#currentPage").val(1);
			$j("form:first").submit();
		});
		//分页跳转
		$j("#fir").click(function(){
			$j("#currentPage").val(1);
			$j("form:first").submit();		
		});
		$j("#pre").click(function(){
			$j("#currentPage").val("${pageBean.currentPage-1}");
			$j("form:first").submit();		
		});
		$j("#next").click(function(){
			$j("#currentPage").val("${pageBean.currentPage+1}");
			$j("form:first").submit();		
		});
		$j("#last").click(function(){
			$j("#currentPage").val("${pageBean.totalPage}");
			$j("form:first").submit();		
		});
		
	});
	
	function showMsg(msg,uuid){
		//top.document.getElementById("context-msg").style.display = "block";
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="${pageContext.request.contextPath}/department/dept_delete?did="+uuid;
		top.lock.show();
	}
	
	
</script>

<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">部门管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${pageContext.request.contextPath }/department/dept_queryOrList" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;&nbsp;&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">部门名称:</td>
						<td width="142"><input type="text" value="${model.name }" name="name" size="18" /></td>
						<td width="60">电话:</td>
						<td width="149"><input type="text" name="telephone" value="${model.telephone }"  size="18" /></td>
						<td width="70"><a id="query"> <img
								src="${pageContext.request.contextPath}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a href="${pageContext.request.contextPath}/department/dept_addOrUpdateUI"><img
								src="${pageContext.request.contextPath}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<s:if test="pageBean.pageData.size()<=0">
				<center>
					<span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
				</center>
				</s:if>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath}/images/table_bg.gif) repeat-x;">
						<td width="13%" height="30">编号</td>
						<td width="13%">部门名称</td>
						<td width="16%">电话</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="pageBean.pageData" var="dept">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">
							<s:property value="#dept.did"/>
						</td>
						<td>
							<s:property value="#dept.name"/>
						</td>
						<td>
							<s:property value="#dept.telephone"/>
						</td>
						<td>
							<img src="${pageContext.request.contextPath}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="${pageContext.request.contextPath }/department/dept_addOrUpdateUI?did=${dept.did}" class="xiu">修改</a>
							</span> 
							<img src="${pageContext.request.contextPath}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该部门?','${dept.did}')">删除</a>
							</span>
						</td>
					</tr>
					</s:iterator>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="51%">&nbsp;</td>
						<td width="13%">共<s:property value="pageBean.totalRecord"/>条记录
						<td width="6%">
							<a id="fir" class="sye" href="#">首&nbsp;&nbsp;页</a>
							<input type="hidden" name="currentPage" id="currentPage">
						</td>
						<s:if test="pageBean.currentPage>1">
						<td width="6%">
							<a id="pre" class="sye"  href="#">上一页</a>
						</td>
						</s:if>
						<s:if test="pageBean.currentPage<pageBean.totalPage">
						<td width="6%">
							<a id="next" class="sye" href="#">下一页</a>
						</td>
						</s:if>
						<td width="6%">
							<a id="last" class="sye" href="#">末&nbsp;&nbsp;页</a>
						</td>
						<td width="12%">当前第<span style="color:red;">
							<s:if test="pageBean.pageData.size()>0">
							<s:property value="pageBean.currentPage"/>
							</s:if>
							<s:else>
								0
							</s:else>
							</span>/<s:property value="pageBean.totalPage"/>页</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>


