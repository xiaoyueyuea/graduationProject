package com.yuelei.entity.bean;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

	private List<T> content;

	private Integer page;

	private Integer size;

	private Integer total;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
