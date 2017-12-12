package edson.web.erp.department.domain;

public class Department {
	/**
	 * 分页查询时，一页内显示的记录数
	 */
	public static final int ONE_PAGE_RECORD=1;
	/**
	 * 设置分页时访问的路径
	 */
	public static final String PATH="/department/dept_queryOrList";
	
	private Integer did;
	private String name;
	private String telephone;
	
	public final Integer getDid() {
		return did;
	}
	public final void setDid(Integer did) {
		this.did = did;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getTelephone() {
		return telephone;
	}
	public final void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

}
