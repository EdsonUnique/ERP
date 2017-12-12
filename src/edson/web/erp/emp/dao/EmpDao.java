package edson.web.erp.emp.dao;

import edson.web.erp.department.domain.Department;
import edson.web.erp.emp.domain.*;
import java.util.*;

import edson.web.erp.menu.domain.Menu;
import edson.web.erp.resource.domain.Resource;
import edson.web.erp.utils.base.*;

public class EmpDao extends BaseDao<Employee>implements EmpDaoInter {

	@Override
	public List<Department> findDeptList() {
		List<Department> temp= this.getHibernateTemplate().find("from Department");
		return temp.size()>0?temp:null;
	}

	@Override
	public List<Resource> findEmpResource(Integer emp_id) {
		String hql="select role_resource from Employee emp right join emp.roles as emp_role right join emp_role.resources as role_resource where emp.emp_id=?";
		List<Resource> res=this.getHibernateTemplate().find(hql,emp_id);
		return res.size()>0?res:null;
	}

	@Override
	public List<Menu> findOneLevelMenu(Integer emp_id) {
		String hql="select distinct menu from Employee emp join emp.roles emp_role right join emp_role.menus menu where emp.emp_id=? and menu.parent_menu=1 order by menu.menu_id";
		List<Menu> temp=this.getHibernateTemplate().find(hql,emp_id);
		return temp.size()>0?temp:null;
	}

}