package edson.web.erp.emp.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edson.web.erp.department.domain.Department;
import edson.web.erp.role.domain.Role;

/**
 * 员工类
 * @author Edson
 *
 */
public class Employee {
	/**
	 * 登录成功后Session域中用户的Key
	 */
	public static final String EMP_LOGIN_OBJECT="login_emp";
	/**
	 * 性别常量
	 */
	public  static final Map GENDER_MAP=new HashMap<Integer,String>();
	{
		GENDER_MAP.put(0, "女");
		GENDER_MAP.put(1, "男");
	}

	
/*########################################################################################*/
	/**
	 * 员工id
	 */
	private Integer emp_id;
	/**
	 * 员工名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 真实名
	 */
	private String realName;
	private String email;
	private String telephone;
	private Integer gender;
	private String address;
	private String birthday;
	
	private String loginTime;//登录时间
	private String loginIP;//登录的IP地点
	private Integer loginTimes;//登录次数
	
	private Department dept;
	private Set<Role>roles=new HashSet<Role>();

/*########################################################################################*/
	
	public final Integer getEmp_id() {
		return emp_id;
	}
	public final void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	public final String getRealName() {
		return realName;
	}
	public final void setRealName(String realName) {
		this.realName = realName;
	}
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}
	public final String getTelephone() {
		return telephone;
	}
	public final void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public final Integer getGender() {
		return gender;
	}
	public final void setGender(Integer gender) {
		this.gender = gender;
	}
	public final String getAddress() {
		return address;
	}
	public final void setAddress(String address) {
		this.address = address;
	}
	public final String getBirthday() {
		return birthday;
	}
	public final void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public final Department getDept() {
		return dept;
	}
	public final void setDept(Department dept) {
		this.dept = dept;
	}
	public final String getLoginTime() {
		return loginTime;
	}
	public final void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public final String getLoginIP() {
		return loginIP;
	}
	public final void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public final Integer getLoginTimes() {
		return loginTimes;
	}
	public final void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	public final Set<Role> getRoles() {
		return roles;
	}
	public final void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
}
