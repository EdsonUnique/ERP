package edson.web.erp.emp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.department.domain.Department;
import edson.web.erp.emp.domain.Employee;
import edson.web.erp.emp.service.EmpServiceInter;
import edson.web.erp.resource.domain.Resource;
import edson.web.erp.role.domain.Role;
import edson.web.erp.role.service.RoleServiceInter;
import edson.web.erp.utils.base.BaseAction;
import edson.web.erp.utils.ensecret.MD5Utils;

public class EmpAction extends BaseAction implements ModelDriven<Employee>{

	private Employee emp=new Employee();
	private EmpServiceInter service;
	private List<Department> depts;//部门表所有部门信息
	private List<Employee>emps;//员工表所有员工信息
	private Map<Integer,String>genders=Employee.GENDER_MAP;
	
	private RoleServiceInter role_service;//通过属性注入方式产生service对象
	private List<Role>rolesAll;
	private List<Object>roles_id=new ArrayList<Object>();//员工拥有的角色id，用于回显
	
	private String newPassword;
	
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		//查询数据库
		//登录成功，将用户存到session中，跳转到主页，显示用户信息
		//登录失败，回到登陆页面，显示登录失败信息
		HttpServletRequest request = ServletActionContext.getRequest();
		String loginIp = request.getHeader("x-forwarded-for"); 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getRemoteAddr(); 
		}
		//获取ip固定写法
		Employee a_emp=service.login(emp.getName(), emp.getPassword(),loginIp);
		
		if(a_emp==null){
			this.addActionError("用户名或密码错误！");
			return "fail_login";
		}
		//登录成功，查询用户所拥有的权限,拼成字符串存到session中
		List<Resource> res=service.findEmpResource(a_emp.getEmp_id());
		StringBuilder sb=new StringBuilder();
		if(res!=null){
			for(Resource r:res){
				sb.append(r.getPath());
				sb.append("#");
			}
		}
		ActionContext.getContext().getSession().put("myPrivilege", sb);
		ActionContext.getContext().getSession().put(emp.EMP_LOGIN_OBJECT, a_emp);
		
		return "success_login";
	}
	
	public String logout(){
		ActionContext.getContext().getSession().remove(emp.EMP_LOGIN_OBJECT);
		return "success_logout";
	}
	
	@Override
	public String list() throws Exception{
		depts=service.findDeptList();
		//跳转到list页面
		emps=service.findListByCriteriaMap(null);
		
		return super.list();
	}
	
	public String query() throws Exception{
		//查询
		if(emp.getDept().getDid()==null){
			emp.setDept(null);//如果部门id为空说明部门不作为查询条件
		}
		emps=service.findListByCriteriaMap(popToMap(emp));
		depts=service.findDeptList();
		return "success_list";
	}
	
	@Override
	public String addOrUpdateUI() throws Exception{
		rolesAll=role_service.findListByCriteriaMap(null);
		depts=service.findDeptList();
		if(emp.getEmp_id()==null){
			//添加操作
			//查询所有部门用于显示添加
			return super.addOrUpdateUI();
		}
		//修改操作
		emp=service.findById(emp.getEmp_id());
		for(Role r:emp.getRoles()){
			roles_id.add(r.getRole_id());
		}
		return super.addOrUpdateUI();
	}
	
	@Override
	public String add() throws Exception{
		
		//获取角色信息，可能添加有多个角色
		String[] roles_id=ServletActionContext.getRequest().getParameterValues("role_id");
		for(int i=0;i<roles_id.length;i++){
			Role temp=role_service.findById(Integer.valueOf(roles_id[i]));
			emp.getRoles().add(temp);
		}
		
		service.save(emp);
		emps=service.findListByCriteriaMap(null);
		depts=service.findDeptList();
		return super.add();
	}
	

	@Override
	public String update() throws Exception{
		
		//获取角色信息，可能添加有多个角色
		String[] roles_id=ServletActionContext.getRequest().getParameterValues("role_id");
		if(roles_id!=null){
			for(int i=0;i<roles_id.length;i++){
				Role temp=role_service.findById(Integer.valueOf(roles_id[i]));
				emp.getRoles().add(temp);
			}
		}
		service.update(emp);
		emps=service.findListByCriteriaMap(null);
		depts=service.findDeptList();
		return super.update();
	}


	@Override
	public String delete()throws Exception {
		service.delete(emp);
		emps=service.findListByCriteriaMap(null);
		depts=service.findDeptList();
		return super.delete();
	}
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	public String changePwdUI(){
		return "success_changePwdUI";
	}
	
	/**
	 * 修改密码
	 */
	public String changePwd(){
		//前台js校验原始密码是否为空，两次填写的密码是否一致
		//后台判断原始密码(加密后)是否准确
		//不正确则添加提示信息并显示，准确则修改密码，退出登录，跳转到登陆页面
		Employee e=(Employee) ActionContext.getContext().getSession().get(Employee.EMP_LOGIN_OBJECT);
		if(!e.getPassword().equals(MD5Utils.md5(emp.getPassword()))){
			this.addActionMessage("原始密码不正确！");
			return changePwdUI();
		}
		e.setPassword(MD5Utils.md5(newPassword));
		service.update(e);
		return logout();
	}
	
	
	

	@Override
	public Map popToMap(Object bean) throws Exception {
		return super.popToMap(bean);
	}


	@Override
	public Employee getModel() {
		return emp;
	}

	public final void setService(EmpServiceInter service) {
		this.service =service;
	}


	public final List<Department> getDepts() {
		return depts;
	}


	public final Map<Integer, String> getGenders() {
		return genders;
	}


	public final List<Employee> getEmps() {
		return emps;
	}

	public final String getNewPassword() {
		return newPassword;
	}

	public final void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public final void setRole_service(RoleServiceInter role_service) {
		this.role_service = role_service;
	}

	public final List<Role> getRolesAll() {
		return rolesAll;
	}

	public final List<Object> getRoles_id() {
		return roles_id;
	}
	
	

	
	
	
	
	
	
	
}