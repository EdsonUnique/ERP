package edson.web.erp.resource.domain;

import java.util.HashSet;
import java.util.Set;

import edson.web.erp.role.domain.Role;

/**
 * 资源实体类
 * @author Edson
 *
 */
public class Resource {
	
	private Integer resource_id;
	private String name;
	private String path;//访问资源的路径
	private Integer is_operation;//是否可访问  1：可访问  0 ：不可访问 可视
	private Integer is_action;//访问路径 1 action访问  0 URL访问

	
	private Set<Role> roles=new HashSet<Role>();
	
	
	
	public final Integer getIs_action() {
		return is_action;
	}
	public final void setIs_action(Integer is_action) {
		this.is_action = is_action;
	}
	public final Set<Role> getRoles() {
		return roles;
	}
	public final void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public final Integer getResource_id() {
		return resource_id;
	}
	public final void setResource_id(Integer resource_id) {
		this.resource_id = resource_id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getPath() {
		return path;
	}
	public final void setPath(String path) {
		this.path = path;
	}
	public final Integer getIs_operation() {
		return is_operation;
	}
	public final void setIs_operation(Integer is_operation) {
		this.is_operation = is_operation;
	}
	
	
}
