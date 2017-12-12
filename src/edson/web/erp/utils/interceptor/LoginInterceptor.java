package edson.web.erp.utils.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import edson.web.erp.emp.domain.Employee;
/**
 * 登录拦截
 * @author Edson
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//检查session域中是否有登录对象
		//没有跳转到登陆页面
		//有则放行
		Employee emp=(Employee) ActionContext.getContext().getSession().get(Employee.EMP_LOGIN_OBJECT);
		String actionName=invocation.getProxy().getActionName();
		
		if("login_loginUI".equalsIgnoreCase(actionName)){
			return invocation.invoke();
		}
		
		if(emp==null){
			return "global_login";
		}
		
		return invocation.invoke();
	}

}
