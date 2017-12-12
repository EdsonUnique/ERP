<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=roles]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=roles]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });

		});
		
	});
	
		
	
</script>

<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath }${model.emp_id==null?'/employee/emp_add':'/employee/emp_update'}" method="post"> 
  			<input type="hidden" name="emp_id" value="${model.emp_id }">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">用&nbsp;户&nbsp;名</td>
				      <td width="32%">
				      	<input type="text" size="25" name="name" value="${model.name }"/>
				      </td>
				      <td width="18%"align="center">真实姓名</td>
				      <td width="32%">
				      	<input type="text" size="25" name="realName" value="${model.realName }"/>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
				      <td>
				      	<input type="password" size="25" name="password" value="${model.password }"/>
				      </td>
				      <td  align="center">确认密码</td>
				      <td >
				      	<input type="password" size="25" name="rePassword" value="${model.password }"/>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">电子邮箱</td>
				      <td>
				      	<input type="text" size="25" name="email" value="${model.email }"/>
				      <td align="center">电话号码</td>
				      <td>
				      	<input type="text" size="25" name="telephone" value="${model.telephone }"/>
					  </td>
				     </tr>
				      <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				      <td>
				      	<select style="width:190px" name="gender">
								<option value="-1">----请-选-择----</option>
								 <s:iterator value="genders" var="gender">
									<option value="${gender.key }" ${model.gender==gender.key?'selected':null }>${gender.value }</option>
								</s:iterator>
						</select>
					  </td>
				      <td align="center">地&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				      <td>
				      	<input type="text" size="25" name="address" value="${model.address }"/>
					  </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">出生日期</td>
				      <td>
				      	<input type="text" size="25" id="birthday" name="birthday" value="${model.birthday }" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
					  </td>
				      <td align="center">所属部门</td>
				      <td>
				      	<select style="width:190px" name="model.dept.did">
							<option value="-1">----请-选-择----</option>
							<s:iterator value="depts" var="dept">
								<option value="${dept.did }" ${model.dept.did==dept.did?'selected':null }>${dept.name }</option>
							</s:iterator>
						</select>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				       <s:iterator value="rolesAll" var="role">
				       	<s:if test="roles_id!=null && roles_id.size()>0">
					       	<input type="checkbox"  name="role_id" ${roles_id.contains(role.role_id)?'checked':'' } value="${role.role_id }">${role.name }
				      	</s:if>
				      	<s:else>
				      		<input type="checkbox" name="role_id"  value="${role.role_id }">${role.name }
				      	</s:else>
				      </s:iterator>	
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:$('form')[0].submit()"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:history.go(-1)"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${pageContext.request.contextPath}/images/content_bbg.jpg" /></div>
</div>
