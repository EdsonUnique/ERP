package edson.web.erp.menu.domain;

import java.util.HashSet;
import java.util.Set;

import edson.web.erp.role.domain.Role;

/**
 * 菜单实体
 * @author Edson
 *
 */
public class Menu {

	private Integer menu_id;
	private String name;
	private String path;//菜单路径
	private Menu parent_menu;//所属父菜单
	
	private Set<Menu> children_menu;//子菜单
	
	private Set<Role>roles=new HashSet<Role>();//角色与菜单关系  多对多
	
	public static final Integer SYSTEM_MENU_ID=1;//系统菜单id
	
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
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
	public final Menu getParent_menu() {
		return parent_menu;
	}
	public final void setParent_menu(Menu parent_menu) {
		this.parent_menu = parent_menu;
	}
	public final Set<Menu> getChildren_menu() {
		return children_menu;
	}
	public final void setChildren_menu(Set<Menu> children_menu) {
		this.children_menu = children_menu;
	}
	public final Set<Role> getRoles() {
		return roles;
	}
	public final void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
}
