package edson.web.erp.supplier.dao;

import edson.web.erp.goods.domain.Goods;
import edson.web.erp.goodsType.domain.GoodsType;
import edson.web.erp.supplier.domain.*;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import edson.web.erp.utils.base.*;

public class SupplierDao extends BaseDao<Supplier>implements SupplierDaoInter {

	@Override
	public List<Supplier> findListWithoutType() {
		String hql="from Supplier s where s.supplier_id in (select sgt.supplier.supplier_id from s.goodsTypes sgt)";
		List<Supplier>temp=this.getHibernateTemplate().find(hql);
		return temp.size()>0?temp:null;
	}

	@Override
	public List<Supplier> findHasAllSuppliers() {
		String hql="select distinct s from Supplier s left join s.goodsTypes st left join st.the_goods sg where s.supplier_id=sg.supplier ";
		List<Supplier>temp=this.getHibernateTemplate().find(hql);
		return temp.size()>0?temp:null;
	}
	
	/*
	 * hql语句测试
	 * 
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext_supplier.xml");
		SupplierDao dao=(SupplierDao) ctx.getBean("supplierDao");
		List<Supplier>temp=dao.findHasAllSuppliers();
		System.out.println(temp.size());
	}
*/
}