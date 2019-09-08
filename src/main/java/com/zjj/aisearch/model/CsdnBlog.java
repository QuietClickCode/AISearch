package com.zjj.aisearch.model;

public class CsdnBlog {
 
	private int key;// 编号
 
	private String title;// 标题
 
	private String date;// 日期
 
	private String tags;// 标签
 
	private String category;// 分类
 
	private int view;// 阅读人数
 
	private int comments;// 评论人数
 
	private int copyright;// 是否原创
 
	private String content; //文字内容
 
	public String getContent() {
		return content;
	}
 
	public void setContent(String content) {
		this.content = content;
	}
 
	public int getKey() {
		return key;
	}
 
	public void setKey(int key) {
		this.key = key;
	}
 
	public String getTitle() {
		return title;
	}
 
	public void setTitle(String title) {
		this.title = title;
	}
 
	public String getDate() {
		return date;
	}
 
	public void setDate(String date) {
		this.date = date;
	}
 
	public String getTags() {
		return tags;
	}
 
	public void setTags(String tags) {
		this.tags = tags;
	}
 
	public String getCategory() {
		return category;
	}
 
	public void setCategory(String category) {
		this.category = category;
	}
 
	public int getView() {
		return view;
	}
 
	public void setView(int view) {
		this.view = view;
	}
 
	public int getComments() {
		return comments;
	}
 
	public void setComments(int comments) {
		this.comments = comments;
	}
 
	public int getCopyright() {
		return copyright;
	}
 
	public void setCopyright(int copyright) {
		this.copyright = copyright;
	}
 
	@Override
	public String toString() {
		return "CsdnBlog [key=" + key + ", title=" + title + ", content=" + content + ",date=" + date + ", tags=" + tags + ", category="
				+ category + ", view=" + view + ", comments=" + comments + ", copyright=" + copyright + "]";
	}
 
}