package org.lldm.xaltipac.service.forms;

import java.io.Serializable;

/**
 * Clase PageData para realizar filtrados.
 * @author Juan Mateo.
 *
 */

public class PageData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2825803269542190632L;
	
	Integer page;
	Integer size;
	String filter;
	String dateStart;
	String dateEnd;
	
	public PageData() {
		page = 1;
		size = 10;
		filter = "";
		dateStart = "";
		dateEnd = "";
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		if(page == null || page < 1) {
			page = 1;
		}
		return page - 1;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		if(size == null || size < 10 || size > 50) {
			size = 10;
		}
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	public String getFilter() {
		if(filter == null) filter = "";
		filter.replace(" ", "");
		return filter;
	}

	public void setFilter(String filter) {
		setAlfaOmegaDates(filter);
		this.filter = filter;
	}
	
	public boolean isPageFiltered() {
		if(getFilter().length() > 0) return true;
		return false;
	}
	
	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	
	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	private void setAlfaOmegaDates(String filter){
		if(filter != null && !"".equals(filter) && filter.indexOf("/") >=2){
			dateStart = filter + " 00:00:00";
			dateEnd = filter + " 23:59:59";
		}
	}

	@Override
	public String toString() {
		return "PageData [page="+ page +",size=" + size + ",filter=" + filter + "]";
	}
}