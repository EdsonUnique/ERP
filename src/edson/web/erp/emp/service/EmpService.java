package edson.web.erp.emp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edson.web.erp.department.domain.Department;
import edson.web.erp.emp.dao.EmpDaoInter;
import edson.web.erp.emp.domain.Employee;
import edson.web.erp.menu.domain.Menu;
import edson.web.erp.resource.domain.Resource;
import edson.web.erp.utils.base.BaseService;
import edson.web.erp.utils.ensecret.MD5Utils;
import edson.web.erp.utils.publicBean.PageBean;

public class EmpService extends BaseService<Employee>implements EmpServiceInter {

	private EmpDaoInter dao;
	
	@Override
	public void save(Employee t) {
		t.setPassword(MD5Utils.md5(t.getPassword()));
		dao.save(t);
	}

	@Override
	public Employee findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Employee t) {
		dao.update(t);
	}

	@Override
	public void delete(Employee t) {
		dao.delete(t);
	}

	@Override
	public PageBean<Employee> findListByPageBean(Integer currentPage) {
		return null;
	}

	@Override
	public PageBean<Employee> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findByCriteriaMap(criteriaMap);
	}

	
	@Override
	public List<Employee> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		return dao.findListByCriteriaMap(criteriaMap);
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee login(String name, String password,String ip) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("password", MD5Utils.md5(password));
		
		Employee emp=findByCriteriaMap(map);
		if(emp!=null){
			//登录成功，记录登录信息
			if(emp.getLoginTimes()==null){//第一次登录
				emp.setLoginTimes(0);
			}
			emp.setLoginTime(new Date().toLocaleString().split(" ")[0]);
			emp.setLoginIP(ip);
			emp.setLoginTimes(emp.getLoginTimes()+1);
			dao.update(emp);
		}
		return emp;
	}

	public final void setDao(EmpDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public List<Department> findDeptList() {
		return dao.findDeptList();
	}

	@Override
	public List<Resource> findEmpResource(Integer emp_id) {
		return dao.findEmpResource(emp_id);
	}

	@Override
	public List<Menu> findOneLevelMenu(Integer emp_id) {
		return dao.findOneLevelMenu(emp_id);
	}


}