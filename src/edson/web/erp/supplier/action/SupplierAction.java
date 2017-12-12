package edson.web.erp.supplier.action;

import java.util.*;

import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.supplier.service.SupplierService;
import edson.web.erp.supplier.service.SupplierServiceInter;
import edson.web.erp.utils.base.*;

public class SupplierAction extends BaseAction implements ModelDriven<Supplier>{

	private SupplierServiceInter service;
	private Supplier supplier=new Supplier();
	
	private List<Supplier>suppliers;
	
	public String list() throws Exception {
		suppliers=service.findListByCriteriaMap(null);
		return super.list();
	}

	
	public String query() throws Exception {
		suppliers=service.findListByCriteriaMap(popToMap(supplier));
		return super.query();
	}

	
	public String addOrUpdateUI() throws Exception {
		if(supplier.getSupplier_id()!=null){
			//修改
			supplier=service.findById(supplier.getSupplier_id());
		}
		return super.addOrUpdateUI();
	}

	
	public String add() throws Exception {
		service.save(supplier);
		return list();
	}

	
	public String update() throws Exception {
		service.update(supplier);
		return list();
	}

	
	public String delete() throws Exception {
		service.delete(supplier);
		return list();
	}

	
	public Map popToMap(Object bean) throws Exception {
		
		return super.popToMap(bean);
	}


	@Override
	public Supplier getModel() {
		return supplier;
	}


	public final void setService(SupplierServiceInter service) {
		this.service = service;
	}


	public final List<Supplier> getSuppliers() {
		return suppliers;
	}
	
	

	
}