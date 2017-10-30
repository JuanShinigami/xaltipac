package org.lldm.xaltipac.service.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 
 * @author Juan Mateo.
 *
 * @param <T> Tabla a la cual se va a paginar.
 */

public class PageWrapper<T>
{

	private Page<T> page;
	private List<PageItem> items;
	private int currentNumber;
	private String url;
	private List<Integer> sizeSelector;

	/*Toma como parametros un objeto data.domain.Page y la url donde sera implementado el paginador*/
	public PageWrapper(Page<T> page, String url)
	{
		this.page = page;
		this.items = new ArrayList<PageWrapper<T>.PageItem>();
		this.sizeSelector = new ArrayList<Integer>();
		this.currentNumber = page.getNumber() + 1; // Para empezar desde 1
		this.url = url;

		for (int i = 0; i < page.getTotalPages(); i++)
		{
			items.add(new PageItem(i + 1, (i + 1) == currentNumber));
		}
		
		for(int i = 10; i < page.getTotalElements(); i += 10)
		{
			sizeSelector.add(i);
		}
		//sizeSelector.add(10);
		sizeSelector.add((int) page.getTotalElements());
		
	}

	public List<PageItem> getItems()
	{
		return items;
	}

	public int getCurrentNumber()
	{
		return currentNumber;
	}

	public List<T> getContent()
	{
		return page.getContent();
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public int getNumber()
	{
		return currentNumber;
	}
	
	public boolean getIsFirstPage()
	{
		return currentNumber == 1;	
	}
	
	public boolean getIsLastPage()
	{
		return currentNumber == page.getTotalPages();
	}
	
	public List<Integer> getSizeSelector()
	{
		return sizeSelector;
	}
	
	
	public static PageRequest  buildPageRequest(int pageIndex,int pageSize, String field){
		PageRequest request = new PageRequest(pageIndex, pageSize, Sort.Direction.ASC, field);
		return request;
	}
	
	public static PageRequest  buildPageRequest(int pageIndex,int pageSize, String field, Sort.Direction order){
		PageRequest request = new PageRequest(pageIndex, pageSize, order, field);
		return request;
	}
	
	public int getTotalPages()
	{
		return page.getTotalPages();
	}
	
	public class PageItem
	{
		private int number;
		private boolean current;

		public PageItem(int number, boolean current)
		{
			this.number = number;
			this.current = current;
		}

		public int getNumber()
		{
			return this.number;
		}

		public boolean isCurrent()
		{
			return this.current;
		}
	}

}
