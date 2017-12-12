package edson.web.erp.utils.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction<T> extends ActionSupport{
	
	/**
	 * 展示
	 * @return "success_list" 结果转发到list展示页面
	 */
	public String list() throws Exception{
		
		return "success_list";
	}
	/**
	 * 查询
	 * @return "success_list" 结果转发到list展示页面
	 */
	public String query()throws Exception{
		
		
		return "success_list";
	}
	/**
	 * 跳转到添加或修改页面
	 * @return "success_addOrUpdateUI" 转发到添加或修改页面
	 */
	public String addOrUpdateUI()throws Exception{
		
		return "success_addOrUpdateUI";
	}
	/**
	 * 添加
	 * @return "success_list" 转发到list展示页面
	 */
	public String add()throws Exception{
		return "success_list";
	}
	/**
	 * 修改
	 * @return "success_list" 修改后转发到展示页面
	 */
	public String update()throws Exception{
		return "success_list";
	}
	/**
	 * 删除
	 * @return "success_list" 删除后转发到展示页面
	 */
	public String delete()throws Exception{
		return "success_list";
	}
	/**
	 * 获得当前登陆者
	 * @param loginName session中登陆者的Key
	 * @return 登陆者对象
	 */
	public T getLogin(String loginName){
		return (T) ActionContext.getContext().getSession().get(loginName);
	}
	/**
	 * 获取页面传递的一个参数值
	 * @param pname 参数key
	 * @return 参数值
	 */
	public String getParamter(String pname){
		return ServletActionContext.getRequest().getParameter(pname);
	}
	/**
	 *获取页面传递的一个参数值数组
	 * @param pname 参数key
	 * @return 参数值数组
	 */
	public String[] getParamters(String pname){
		return ServletActionContext.getRequest().getParameterValues(pname);
	}
	
	/**
	 * 将javabean中作为条件查询的条件属性填入map中
	 * 基本类型属性的填充
	 * 若属性返回值为对象，请手动填充
	 * @param bean 实体类
	 * @return 保存条件的map
	 * @throws Exception
	 */
	public Map popToMap(Object bean) throws Exception{
		Class clazz=bean.getClass();
		Method[] ms=clazz.getDeclaredMethods();
		
		Map<String,Object>map=new HashMap<String,Object>();
		for(Method m:ms){
			if(m.getName().startsWith("get") && !m.getName().endsWith("view")){ //去除视图值
				Object value=m.invoke(bean, null);
				if(value!=null && !value.toString().trim().equals("") 
						&& !(value instanceof Set)//条件的返回属性不为集合类型
						&& !(value instanceof List)
						&& !(value instanceof Map)
						)
				map.put(toFirstLower(m.getName().substring(3)), value);
			}
		}
		
		return map;
	}
	/**
	 * 首字母变小写
	 * @param str
	 * @return
	 */
	private String toFirstLower(String str){
		return str.substring(0,1).toLowerCase()+str.substring(1);
	}
}
