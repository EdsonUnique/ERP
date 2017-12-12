package edson.web.erp.menu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.emp.service.EmpServiceInter;
import edson.web.erp.menu.domain.Menu;
import edson.web.erp.menu.service.MenuServiceInter;
import edson.web.erp.role.domain.Role;
import edson.web.erp.role.service.RoleServiceInter;
import edson.web.erp.utils.base.BaseAction;

public class MenuAction extends BaseAction implements ModelDriven<Menu>{

	private Menu menu=new Menu();
	private MenuServiceInter service;
	private EmpServiceInter emp_service;
	
	private List<Menu> menus;//用于前台list展示
	private List<Menu> query_menus;//用于前台查询显示
	private List<Role>rolesAll;
	
	private List<Object>role_ids=new ArrayList<Object>();//菜单拥有的角色，用于前台回显
	private RoleServiceInter role_service;
	
	@Override
	public String list() throws Exception {
		//展示所有菜单
		//除了系统菜单不显示，不允许修改，系统菜单的父菜单为1
		Map<String,Object>criteriaMap=new HashMap<String, Object>();
		criteriaMap.put("menu_id", Menu.SYSTEM_MENU_ID);
		menus=service.findListByNotCriteriaMap(criteriaMap);
		query_menus=service.findListByCriteriaMap(null);//所有菜单，用于前台查询显示
		return super.list();
	}

	@Override
	public String query() throws Exception {
		if(menu.getParent_menu().getMenu_id()==null){
			menu.setParent_menu(null);
		}
		menus=service.findListByCriteriaMap(popToMap(menu));
		query_menus=service.findListByCriteriaMap(null);//所有菜单，用于前台查询显示
		return super.list();
	}

	@Override
	public String addOrUpdateUI() throws Exception {
		//查询系统菜单和所有一级菜单用于显示
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		criteriaMap.put("menu_id", Menu.SYSTEM_MENU_ID);
		criteriaMap.put("parent_menu.menu_id", Menu.SYSTEM_MENU_ID);
		menus=service.findByMyCriteriaMap(criteriaMap);
		
		//查询所有角色
		rolesAll=role_service.findListByCriteriaMap(null);
		
		if(menu.getMenu_id()!=null){//修改
			menu=service.findById(menu.getMenu_id());
			
			//查询已有的角色用于回显,并将id放到集合中
			for(Role r:menu.getRoles()){
				role_ids.add(r.getRole_id());
			}
			
		}
		return super.addOrUpdateUI();
	}

	@Override
	public String add() throws Exception {
		
		//获取页面的菜单角色关系再进行保存
		String[] role_ids=ServletActionContext.getRequest().getParameterValues("role_ids");
		
		if(role_ids!=null){
			for(int i=0;i<role_ids.length;i++){
				Role role=role_service.findById(Integer.valueOf(role_ids[i]));
				menu.getRoles().add(role);
			}
		}
		service.save(menu);
		return list();
	}

	@Override
	public String update() throws Exception {
		//获取页面的菜单角色关系再进行保存
		String[] role_ids=ServletActionContext.getRequest().getParameterValues("role_ids");
		
		if(role_ids!=null){
			for(int i=0;i<role_ids.length;i++){
				Role role=role_service.findById(Integer.valueOf(role_ids[i]));
				menu.getRoles().add(role);
			}
		}
		service.update(menu);
		return list();
	}

	@Override
	public String delete() throws Exception {
		menu=service.findById(menu.getMenu_id());
		service.delete(menu);
		return list();
	}
	/**
	 * 登录成功后，根据用户的角色不同显示不同的菜单
	 * 使用jQuery treeview显示菜单
	 * 将菜单信息以json显示传回前台页面
	 * @throws IOException 
	 */
	public void showMenu() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		String req=request.getParameter("root");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//根据登录用户的信息不同显示不同的菜单页面
		Employee emp=(Employee) ActionContext.getContext().getSession().get(Employee.EMP_LOGIN_OBJECT);
		
		StringBuilder parent=new StringBuilder();
		
		if("source".equalsIgnoreCase(req)){
			//一级菜单显示  根据登录用户的角色查询菜单
			List<Menu>parents=emp_service.findOneLevelMenu(emp.getEmp_id());
			
			if(parents !=null && parents.size()>0){
				parent.append("[");
				
				for(Menu m:parents){
					parent.append("{\"text\":\"");
					parent.append(m.getName());
					parent.append("\",\"hasChildren\":\"true\",\"classes\":\"folder\",");
					parent.append("\"id\":\"");
					parent.append(m.getMenu_id());
					parent.append("\"},");
				}
				//去掉最后一个逗号
				parent.substring(0, parent.lastIndexOf(","));
				parent.append("]");
			}
		}else{
			if(req!=null){
				//根据root传过来的值查询一级菜单下的二级菜单
				List<Menu> children= new LinkedList<Menu>(service.findById(Integer.valueOf(req)).getChildren_menu());
				//lazy=false直接查询一级菜单获取二级菜单
				
				if(children !=null && children.size()>0){
					parent.append("[");
					
					for(Menu m:children){
						parent.append("{\"text\":\"<a class='hei' target='main' href='");
						parent.append(m.getPath());
						parent.append("'>");
						parent.append(m.getName());
						parent.append("</a>");
						parent.append("\",\"hasChildren\":\"false\",\"classes\":\"file\",");
						parent.append("\"id\":\"");
						parent.append(m.getMenu_id());
						parent.append("\"},");
					}
					//去掉最后一个逗号
					parent.substring(0, parent.lastIndexOf(","));
					parent.append("]");
				}
				
			}
		}
		
		
		//通过response写回浏览器
		PrintWriter pw=response.getWriter();
		pw.write(parent.toString());
		pw.flush();
		pw.close();
		
	}
	
	
	
	
	
	
	
	
	

	@Override
	public Map popToMap(Object bean) throws Exception {
		
		return super.popToMap(bean);
	}

	@Override
	public Menu getModel() {
		// TODO Auto-generated method stub
		return menu;
	}

	public final void setService(MenuServiceInter service) {
		this.service = service;
	}

	public final List<Menu> getMenus() {
		return menus;
	}

	public final List<Menu> getQuery_menus() {
		return query_menus;
	}

	public final List<Object> getRole_ids() {
		return role_ids;
	}

	public final void setRole_service(RoleServiceInter role_service) {
		this.role_service = role_service;
	}

	public final List<Role> getRolesAll() {
		return rolesAll;
	}

	public final void setEmp_service(EmpServiceInter emp_service) {
		this.emp_service = emp_service;
	}
	
	

	
}