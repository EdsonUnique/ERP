package edson.web.erp.utils.publicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象：分页查询时，封装分页数据的对象
 * @author Edson
 *
 */
public class PageBean<T> {
	/**
	 * 查询的数据的总记录数
	 */
	private int totalRecord;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 一页内显示的记录数
	 */
	private int one_page_record;
	/**
	 * 访问的路径
	 */
	private String path;
	/**
	 * 显示的数据集合
	 */
	private List<T> pageData=new ArrayList<T>();
	
	public PageBean(int totalRecord, int currentPage, int one_page_record,
			List<T> pageData) {
		super();
		this.totalRecord = totalRecord;
		this.currentPage = currentPage;
		this.one_page_record = one_page_record;
		this.pageData = pageData;
		/**
		 * 计算总页数
		 */
		this.totalPage=(int) Math.ceil(this.totalRecord*1.0/one_page_record);
	}
	public final int getTotalRecord() {
		return totalRecord;
	}
	public final void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public final int getTotalPage() {
		return totalPage;
	}
	public final void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public final int getCurrentPage() {
		return currentPage;
	}
	public final void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public final int getOne_page_record() {
		return one_page_record;
	}
	public final void setOne_page_record(int one_page_record) {
		this.one_page_record = one_page_record;
	}
	public final String getPath() {
		return path;
	}
	public final void setPath(String path) {
		this.path = path;
	}
	public final List<T> getPageData() {
		return pageData;
	}
	public final void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	
	
}
