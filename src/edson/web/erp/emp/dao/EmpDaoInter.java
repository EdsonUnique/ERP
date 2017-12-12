package edson.web.erp.emp.dao;

import edson.web.erp.department.domain.Department;
import edson.web.erp.emp.domain.*;
import java.util.*;

import edson.web.erp.menu.domain.Menu;
import edson.web.erp.resource.domain.Resource;
import edson.web.erp.utils.base.*;

public interface EmpDaoInter extends BaseDaoInter<Employee>{
	/**
	 * 查找所有部门信息
	 * @return 所有部门信息
	 */
	List<Department> findDeptList();
	/**
	 * 查找用户拥有的所有权限资源
	 * @param emp_id
	 * @return
	 */
	List<Resource> findEmpResource(Integer emp_id);
	/**
	 * 根据登录用户id查询一级菜单信息
	 * @param emp_id
	 * @return
	 */
	List<Menu> findOneLevelMenu(Integer emp_id);

}