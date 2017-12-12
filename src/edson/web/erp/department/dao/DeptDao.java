package edson.web.erp.department.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edson.web.erp.department.domain.Department;
import edson.web.erp.utils.DBSql.PageHibernateCallback;
import edson.web.erp.utils.base.BaseDao;
import edson.web.erp.utils.publicBean.PageBean;

public class DeptDao extends BaseDao<Department> implements DeptDaoInter{

}
