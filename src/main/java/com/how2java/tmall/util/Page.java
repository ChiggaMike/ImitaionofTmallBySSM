package com.how2java.tmall.util;

public class Page {
	
	private int start;
	private int count;
	private int total;
	private String param;
	
	private static final int defaultCount = 5;
	
	public int getStart(){
		return start;
	}
	
	public void setStart(int start){
		this.start = start;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public Page(){
		count = defaultCount;
	}
	
	public Page(int start, int count){
		this();
		this.start = start;
		this.count = count;
	}
	
	public boolean isHasPrevious(){
		
		return start != 0;
	}
	
	public boolean isHasNext(){
		
		return start != getLast();
	}
	
	public int getTotalPage(){
		int totalPage;
		if (total % count == 0)
			totalPage = total / count;
		else
			totalPage = total / count + 1;
		if (totalPage == 0)
			totalPage = 1;
		return totalPage;
	}
	
	public int getLast(){
		int last;
		if (total % count == 0)
			last = total - count;
		else
			last = total - total % count;
		last = last<0?0:last;
		return last;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void setTotal(int total){
		this.total = total;
	}
	
	public String getParam(){
		return param;
	}
	
	public void setParam(String param){
		this.param = param;
	}
	
	public String toString(){
		return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
		+ ", getCount()=" + getCount() + ", isHasPrevious()=" + isHasPrevious() + ", isHasNext()=" + isHasNext()
		+ ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
	}

}
