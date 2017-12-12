package edson.web.erp.utils.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import edson.web.erp.utils.exception.PrivilegeException;

/**
 * 全局异常拦截器
 * @author Edson
 *
 */
public class GlobalExceptionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) {
		
		try{
			return invocation.invoke();
		}catch (Exception e) {
			
			if(!(e instanceof PrivilegeException)){
				e.printStackTrace();//不是权限异常，则打印具体异常信息
			}
			
			ActionSupport as=(ActionSupport) invocation.getAction();
			as.addActionError(e.getMessage());
			
			return "global_exception";
		}
		
		
	}

}
