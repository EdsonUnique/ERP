package edson.web.erp.role.domain;

import java.util.HashSet;
import java.util.Set;

import edson.web.erp.menu.domain.Menu;
import edson.web.erp.resource.domain.Resource;

/**
 * 权限管理系统的角色实体类
 * @author Edson
 *
 */
public class Role {
	
	private Integer role_id;
	private String name;
	private String short_name;
	
	private Set<Resource> resources=new HashSet<Resource>();
	private  Set<Menu> menus=new HashSet<Menu>();
	
	
	
	
	
	public final Set<Menu> getMenus() {
		return menus;
	}
	public final void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	public final Set<Resource> getResources() {
		return resources;
	}
	public final void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	public final Integer getRole_id() {
		return role_id;
	}
	public final void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getShort_name() {
		return short_name;
	}
	public final void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	
	

}
