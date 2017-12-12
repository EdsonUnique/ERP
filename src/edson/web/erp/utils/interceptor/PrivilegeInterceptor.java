package edson.web.erp.utils.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import edson.web.erp.utils.exception.PrivilegeException;
/**
 * 权限拦截器
 * @author Edson
 *
 */
public class PrivilegeInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取需要拦截权限的所有资源
		//由于每次操作都需判断，因此将查询所有资源的操作在服务器启动时就应查询
		StringBuilder allRes=(StringBuilder) ServletActionContext.getServletContext().getAttribute("resAll");
		
		//查询登录用户拥有的权限
		//在用户登录时就加载用户所拥有的全部权限，并存到Session域
		//当用户修改权限时，应迫使用户退出登录，使权限更新
		StringBuilder myPrivilege=(StringBuilder) ActionContext.getContext().getSession().get("myPrivilege");
		
		//获取当前操作所需要的资源
		String actionName=invocation.getProxy().getActionName();
		
		//只有存在在资源表的资源访问才需要权限
		if(allRes!=null && !allRes.toString().contains(actionName)){
			return invocation.invoke();
		}
		//判断是否有权限
		if(myPrivilege!=null &&!myPrivilege.toString().contains(actionName)){
			//没有权限
			throw new PrivilegeException("对不起，您没有相应的操作权限，请联系管理员！");
		}
		return invocation.invoke();
		
	}

}
