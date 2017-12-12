package edson.web.erp.goods.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import edson.web.erp.goods.domain.Goods;
import edson.web.erp.goods.service.GoodsServiceInter;
import edson.web.erp.goodsType.domain.GoodsType;
import edson.web.erp.goodsType.service.GoodsTypeServiceInter;
import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.supplier.service.SupplierServiceInter;
import edson.web.erp.utils.base.BaseAction;

public class GoodsAction extends BaseAction implements ModelDriven<Goods>{

	private GoodsServiceInter service;
	private SupplierServiceInter supp_service;
	private GoodsTypeServiceInter goodsType_service;
	
	private Goods goods=new Goods();
	private List<Supplier> suppliersAll;
	private List<GoodsType> goodsTypesAll;
	
	private List<Goods>goodsAll;
	
	
	@Override
	public String list() throws Exception {
		suppliersAll=supp_service.findListByCriteriaMap(null);
		goodsAll=service.findListByCriteriaMap(null);
		return super.list();
	}

	@Override
	public String query() throws Exception {
		suppliersAll=supp_service.findListByCriteriaMap(null);
		
		if(goods.getSupplier()==null || goods.getSupplier().getSupplier_id()==null){
			goods.setSupplier(null);
		}
		Map<String,Object>criteriaMap=popToMap(goods);
		
		//增加比较条件
		String origin_price1=ServletActionContext.getRequest().getParameter("origin_price1");
		String sale_price1=ServletActionContext.getRequest().getParameter("sale_price1");
		
		if(origin_price1!=null && !origin_price1.trim().equals("")){
			criteriaMap.put("origin_price1",  Double.valueOf(origin_price1));
		}
		
		if(sale_price1!=null && !sale_price1.trim().equals("")){
			criteriaMap.put("sale_price1", Double.valueOf(sale_price1));
		}
		
		goodsAll=service.findListByMyCriteriaMap(criteriaMap);
		return super.query();
	}

	@Override
	public String addOrUpdateUI() throws Exception {
		
		//加载所有有商品类别的供应商,用于页面显示
		suppliersAll=supp_service.findListWithoutType();
		//商品类别加载第一个供应商的类别
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		criteriaMap.put("supplier.supplier_id", 1);
		goodsTypesAll=goodsType_service.findListByCriteriaMap(criteriaMap);
		if(goods.getGoods_id()!=null){
			//修改  加载修改的供应商的类别
			goods=service.findById(goods.getGoods_id());
			criteriaMap.put("supplier.supplier_id", goods.getSupplier().getSupplier_id());
			goodsTypesAll=goodsType_service.findListByCriteriaMap(criteriaMap);
		}
		return super.addOrUpdateUI();
	}

	@Override
	public String add() throws Exception {
		service.save(goods);
		return list();
	}

	@Override
	public String update() throws Exception {
		service.update(goods);
		return list();
	}

	@Override
	public String delete() throws Exception {
		service.delete(goods);
		return list();
	}

	@Override
	public Map popToMap(Object bean) throws Exception {
		
		return super.popToMap(bean);
	}
	
	
	
	
	//################################################################
	//Ajax请求
	List<GoodsType> goodsTypesAjax;
	public List<GoodsType> getGoodsTypesAjax() {
		return goodsTypesAjax;
	}
	

	public String ajaxGetGoodsType(){
		//struts自动将java数据封装为JSON数据格式
		//在struts文件中配置
		String supp_id=ServletActionContext.getRequest().getParameter("id");
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		criteriaMap.put("supplier.supplier_id", Integer.valueOf(supp_id));
		goodsTypesAjax=goodsType_service.findListByCriteriaMap(criteriaMap);
		
		return "ajaxGetGoodsType";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//####################################################################
	public final void setService(GoodsServiceInter service) {
		this.service = service;
	}

	public Goods getModel() {
		return goods;
	}

	public final List<Goods> getGoodsAll() {
		return goodsAll;
	}

	public final List<Supplier> getSuppliersAll() {
		return suppliersAll;
	}

	public final void setSupp_service(SupplierServiceInter supp_service) {
		this.supp_service = supp_service;
	}

	public final List<GoodsType> getGoodsTypesAll() {
		return goodsTypesAll;
	}

	public final void setGoodsType_service(GoodsTypeServiceInter goodsType_service) {
		this.goodsType_service = goodsType_service;
	}
	
	
	
	
}