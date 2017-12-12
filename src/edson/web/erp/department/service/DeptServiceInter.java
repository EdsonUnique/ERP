package edson.web.erp.department.service;

import org.springframework.transaction.annotation.Transactional;

import edson.web.erp.department.domain.Department;
import edson.web.erp.utils.base.BaseServiceInter;
import edson.web.erp.utils.publicBean.PageBean;

@Transactional
public interface DeptServiceInter extends BaseServiceInter<Department>{

}