package edson.web.erp.role.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.menu.domain.Menu;
import edson.web.erp.menu.service.MenuServiceInter;
import edson.web.erp.resource.domain.Resource;
import edson.web.erp.resource.service.ResourceServiceInter;
import edson.web.erp.role.domain.Role;
import edson.web.erp.role.service.RoleServiceInter;
import edson.web.erp.utils.base.BaseAction;

public class RoleAction extends BaseAction implements ModelDriven<Role>{

	private RoleServiceInter service;
	private ResourceServiceInter res_service;//属性注入另一模块业务层
	private MenuServiceInter menu_service;
	
	private Role role=new Role();
	private List<Role>roles;
	private List<Resource>resourcesAll;//查询所有资源
	private List<Object>reserved_ids=new ArrayList<Object>();//角色拥有的资源的id
	private List<Object>menu_ids=new ArrayList<Object>();//角色拥有的资源的id
	private List<Menu>menuAll;
	
	public String list() throws Exception {
		
		roles=service.findListByCriteriaMap(null);
		
		return super.list();
	}
	
	@Override
	public String addOrUpdateUI() throws Exception {
		resourcesAll=service.findResourcesList();
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		menuAll=menu_service.findOneLevelMenu();
		
		if(role.getRole_id()!=null){//修改
			
			
			role=service.findById(role.getRole_id());
			//获取已有的资源id回显
			for(Resource r:role.getResources()){
				reserved_ids.add(r.getResource_id());
			}
			//获取已有的菜单id回显
			for(Menu m:role.getMenus()){
				menu_ids.add(m.getMenu_id());
			}
			
			return super.addOrUpdateUI();
		}
		
		return super.addOrUpdateUI();
	}
	
	@Override
	public String add() throws Exception {
		//为角色添加资源
		String[] ids=ServletActionContext.getRequest().getParameterValues("reserved_id");
		//为角色添加菜单项
		String[] menu_ids=ServletActionContext.getRequest().getParameterValues("menu_ids");

		if(ids!=null){
			for(int i=0;i<ids.length;i++){
				Resource temp=res_service.findById(Integer.valueOf(ids[i]));
				role.getResources().add(temp);
			}
		}
		if(menu_ids!=null){
			for(int i=0;i<menu_ids.length;i++){
				Menu temp=menu_service.findById(Integer.valueOf(menu_ids[i]));
				role.getMenus().add(temp);
			}
		}
		service.save(role);
		return list();
	}

	@Override
	public String update() throws Exception {
		String[] ids=ServletActionContext.getRequest().getParameterValues("reserved_id");
		if(ids!=null){
			for(int i=0;i<ids.length;i++){
				Resource temp=res_service.findById(Integer.valueOf(ids[i]));
				role.getResources().add(temp);
			}
		}
		String[] menu_ids=ServletActionContext.getRequest().getParameterValues("menu_ids");
		if(menu_ids!=null){
			for(int i=0;i<menu_ids.length;i++){
				Menu temp=menu_service.findById(Integer.valueOf(menu_ids[i]));
				role.getMenus().add(temp);
			}
		}
		service.update(role);
		return list();
	}
	
	@Override
	public String query() throws Exception {
		roles=service.findListByCriteriaMap(popToMap(role));
		return super.query();
	}

	@Override
	public String delete() throws Exception {
		service.delete(role);
		return list();
	}

	@Override
	public Map popToMap(Object bean) throws Exception {
		return super.popToMap(bean);
	}

	@Override
	public Role getModel() {
		return role;
	}

	public final void setService(RoleServiceInter service) {
		this.service = service;
	}

	public final List<Role> getRoles() {
		return roles;
	}

	public final List<Resource> getResourcesAll() {
		return resourcesAll;
	}

	public final List<Object> getReserved_ids() {
		return reserved_ids;
	}

	public final void setRes_service(ResourceServiceInter res_service) {
		this.res_service = res_service;
	}

	public final List<Menu> getMenuAll() {
		return menuAll;
	}

	public final void setMenu_service(MenuServiceInter menu_service) {
		this.menu_service = menu_service;
	}

	public final List<Object> getMenu_ids() {
		return menu_ids;
	}
	
	
	
	
	

	
}