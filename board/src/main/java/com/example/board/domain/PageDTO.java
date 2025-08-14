package com.example.board.domain;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class PageDTO {

	private final int BLOCK_SIZE = 10;

	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int totalPages;
	private long totalElements;
	private Page<?> page;

	public PageDTO(Page<Post> page) {
		this.page = page;
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();

		int currentPage = page.getNumber() + 1;

		this.endPage = (int) Math.ceil(currentPage / (double) BLOCK_SIZE) * BLOCK_SIZE;
		this.startPage = this.endPage - (BLOCK_SIZE - 1);
		
		if(this.totalPages < this.endPage)
			this.endPage = this.totalPages;
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.totalPages;
	}

}
