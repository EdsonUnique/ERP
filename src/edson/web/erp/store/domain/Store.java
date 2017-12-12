package edson.web.erp.store.domain;

import java.util.Set;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.goods.domain.Goods;

public class Store {

	private Integer store_id;
	private String name;
	private String address;
	private Employee store_emp;//仓库管理员
	
	
	public final Integer getStore_id() {
		return store_id;
	}
	public final void setStore_id(Integer store_id) {
		this.store_id = store_id;
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
	public final Employee getStore_emp() {
		return store_emp;
	}
	public final void setStore_emp(Employee store_emp) {
		this.store_emp = store_emp;
	}
	
	
}
