package edson.web.erp.goodsType.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.goodsType.domain.GoodsType;
import edson.web.erp.goodsType.service.GoodsTypeServiceInter;
import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.supplier.service.SupplierServiceInter;
import edson.web.erp.utils.base.BaseAction;

public class GoodsTypeAction extends BaseAction implements ModelDriven<GoodsType>{

	private GoodsTypeServiceInter service;
	private SupplierServiceInter supp_service;
	
	private GoodsType goodsType=new GoodsType();
	private List<GoodsType> goodsTypes;
	
	private List<Supplier>suppliers;
	
	
	@Override
	public String list() throws Exception {
		suppliers=supp_service.findListByCriteriaMap(null);
		goodsTypes=service.findListByCriteriaMap(null);
		return super.list();
	}


	@Override
	public String query() throws Exception {
		
		if(goodsType.getSupplier()==null || goodsType.getSupplier().getSupplier_id()==null){
			goodsType.setSupplier(null);
		}
		
		goodsTypes=service.findListByCriteriaMap(popToMap(goodsType));
		suppliers=supp_service.findListByCriteriaMap(null);
		return super.query();
	}


	@Override
	public String addOrUpdateUI() throws Exception {

		suppliers=supp_service.findListByCriteriaMap(null);
		
		if(goodsType.getGoodsType_id()!=null){
			//修改
			goodsType=service.findById(goodsType.getGoodsType_id());
		}
		
		return super.addOrUpdateUI();
	}

	@Override
	public String add() throws Exception {
		service.save(goodsType);
		return list();
	}


	@Override
	public String update() throws Exception {
		service.update(goodsType);
		return list();
	}


	@Override
	public String delete() throws Exception {
		service.delete(goodsType);
		return list();
	}

	@Override
	public Map popToMap(Object bean) throws Exception {
		return super.popToMap(bean);
	}


	@Override
	public GoodsType getModel() {
		return goodsType;
	}


	public final void setService(GoodsTypeServiceInter service) {
		this.service = service;
	}


	public final List<GoodsType> getGoodsTypes() {
		return goodsTypes;
	}


	public final List<Supplier> getSuppliers() {
		return suppliers;
	}


	public final void setSupp_service(SupplierServiceInter supp_service) {
		this.supp_service = supp_service;
	}
	
	
	
}