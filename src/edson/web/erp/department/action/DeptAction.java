package edson.web.erp.department.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.department.domain.Department;
import edson.web.erp.department.service.DeptServiceInter;
import edson.web.erp.utils.publicBean.PageBean;

public class DeptAction extends ActionSupport implements ModelDriven<Department>{

	private Department dept=new Department();
	private DeptServiceInter service;
	/**
	 * 存放分页数据的对象
	 */
	private PageBean<Department> pageBean;
	private String currentPage;//获取当前页信息
	
	/**
	 * 添加新的部门
	 * @return
	 */
	public String add(){
		service.save(dept);
		//添加成功后跳转到list页面
		return "redirect_list";
	}
	
	/**
	 * 根据条件查询或查询全部
	 * @return
	 */
	public String queryOrList(){
		if(currentPage==null){
			currentPage="1";
		}
		//分页查询数据
		//如果当前页为空则查询第一页，每页显示多少条记录由javabean中的常量指定
		//查询的信息为空显示未查到
		if(dept.getName()==null || dept.getTelephone()==null ||(dept.getName().trim().equals("") && dept.getTelephone().trim().equals(""))){
			//查询所有
			pageBean=service.findListByPageBean(Integer.valueOf(currentPage));
		}else{
			//条件查询
		//根据部门名称和电话查询，三种情况：name,telephone,name和telephone
		//通过字符串拼接形成hql语句
		//封装到Pagebean中
			Map<String,Object>criteriaMap=new HashMap<String,Object>();//传递判断条件
			criteriaMap.put("name", dept.getName());
			criteriaMap.put("telephone", dept.getTelephone());
			pageBean=service.queryByCriteriaObjects(criteriaMap, Integer.valueOf(currentPage));
		}
			return "success_list";
	}
	/**
	 * 跳转到添加或修改页面
	 * @return
	 */
	public String addOrUpdateUI(){
		//根据id是否为null 判断是添加还是删除
		//根据id查询部门对象进行回显
		if(dept.getDid()!=null){
			//修改方法
			dept=service.findById(dept.getDid());
		}
		return "success_addOrUpdateUI";
	}
	
	public String update(){
		service.update(dept);
		return "redirect_list";
	}
	
	public String delete(){
		service.delete(dept);
		return "redirect_list";
	}
	
	
	@Override
	public Department getModel() {
		return dept;
	}

	public final void setService(DeptServiceInter service) {
		this.service = service;
	}
	public final PageBean<Department> getPageBean() {
		return pageBean;
	}
	public final String getCurrentPage() {
		return currentPage;
	}
	public final void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	
	

}
