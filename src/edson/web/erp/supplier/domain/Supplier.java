package edson.web.erp.supplier.domain;

import java.util.HashSet;
import java.util.Set;

import edson.web.erp.goodsType.domain.GoodsType;

/**
 * 供应商实体类
 * @author Edson
 *
 */
public class Supplier {

	private Integer supplier_id;
	private String name;//供应商名
	private String address;
	private String telephone;
	private String contact;//联系人
	private Integer is_send;//是否送货
	
	private Set<GoodsType> goodsTypes=new HashSet<GoodsType>();
	
	private String is_send_view;
	
	
	public final String getIs_send_view() {
		return is_send_view;
	}
	public final Integer getSupplier_id() {
		return supplier_id;
	}
	public final void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getAddress() {
		return address;
	}
	public final void setAddress(String address) {
		this.address = address;
	}
	public final String getTelephone() {
		return telephone;
	}
	public final void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public final String getContact() {
		return contact;
	}
	public final void setContact(String contact) {
		this.contact = contact;
	}
	public final Integer getIs_send() {
		return is_send;
	}
	public final void setIs_send(Integer is_send) {
		this.is_send = is_send;
		if(is_send!=null){
			if(is_send==1){
				is_send_view="送货";
			}
			
			if(is_send==0){
				is_send_view="自提";
			}
		}
	}
	public final Set<GoodsType> getGoodsTypes() {
		return goodsTypes;
	}
	public final void setGoodsTypes(Set<GoodsType> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}
	
	
}
