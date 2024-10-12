package com.example.consumertasktracker.beans;

import java.sql.Date;

public class TaskBean {
	
	private Integer taskId;
	private String title;
	private Date dueDate;
	private String priority;
	private String description;
	
	public TaskBean(Integer taskId, String title, Date dueDate, String priority, String description) {
		super();
		this.taskId = taskId;
		this.title = title;
		this.dueDate = dueDate;
		this.priority = priority;
		this.description = description;
	}
	public TaskBean( String title, Date dueDate, String priority, String description) {

		this.title = title;
		this.dueDate = dueDate;
		this.priority = priority;
		this.description = description;
	}
	public TaskBean() {
		// TODO Auto-generated constructor stub
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "TaskBean [taskId=" + taskId + ", title=" + title + ", dueDate=" + dueDate + ", priority=" + priority
				+ ", description=" + description + "]";
	}
}