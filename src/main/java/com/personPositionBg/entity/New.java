package com.personPositionBg.entity;

public class New {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimeYmd() {
		return createTimeYmd;
	}
	public void setCreateTimeYmd(String createTimeYmd) {
		this.createTimeYmd = createTimeYmd;
	}
	private String title;
	private String content;
	private String createTime;
	private String createTimeYmd;
}
