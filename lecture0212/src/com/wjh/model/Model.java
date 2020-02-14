package com.wjh.model;

public class Model {
	
	public Model () {
		
	}
	
	private int idx;
	private int num;
	private String subject;
	private String writer;
	private String contents;
	private String ip;
	private int hit = 0 ;
	private String reg_date;
	private String mod_date;
	
	private String pageNum = "1";
	private String searchType = "";
	private String searchText = "";
	private int list_count = 10;
	private int page_per_block = 10;
	
	// 목록 페이지 네비게이터 블록 수 // 페이지 번호를 몇개나 보여줄 건지
	
	public int get_idx () {
		 return idx;
	}
	
	public void set_idx (int idx) {
		this.idx = idx;
	}
	
	public int get_num () {
		 return num;
	}
	
	public void set_num(int num) {
		this.num = num;
	}

	public String get_subject () {
		 return subject;
	}
	
	public void set_subject(String subject ) {
		this.subject = subject;
	}
	
	public String get_writer () {
		 return writer ;
	}
	
	public void set_writer(String writer) {
		this.writer = writer;
	}
	
	public String get_contents () {
		 return contents;
	}
	
	public void set_contents(String contents) {
		this.contents = contents;
	}
	public String get_reg_date () {
		 return reg_date ;
	}
	
	public void set_reg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String get_mod_date () {
		 return mod_date;
	}
	
	public void set_mod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public String get_ip () {
		 return ip;
	}
	
	public void set_ip(String ip ) {
		this.ip = ip;
	}
	public int get_hit () {
		 return hit;
	}
	
	public void set_hit(int hit) {
		this.hit = hit;
	}
	public String get_pageNum () {
		 return pageNum ;
	}
	
	public void set_pageNum(String page_num ) {
		this.pageNum  = page_num;
	}
	
	public String get_searchType () {
		 return searchType;
	}
	
	public void set_searchType(String search_type) {
		this.searchType= search_type;
	}
	
	public String get_searchText () {
		 return searchText;
	}
	
	public void set_searchText(String search_text) {
		this.searchText = search_text;
	}
	
	public int get_list_count () {
		 return list_count;
	}
	
	public void set_list_count (int list_count) {
		this.list_count = list_count;
	}
	
	public int get_page_per_block () {
		 return page_per_block;
	}
	
	public void set_page_per_block(int page_per_block) {
		this.page_per_block = page_per_block;
	}
}
