package com.example.tasktracker.beans;

import java.sql.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TaskBean {

	private Integer taskId;
	@NotEmpty
	private String title;
	@Future(message="Please Enter Future Date")
	private Date dueDate;
	@NotEmpty
	private String priority;
	@NotEmpty
	private String description;
	
	public TaskBean(Integer taskId, String title, Date dueDate, String priority, String description) {
		super();
		this.taskId = taskId;
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
