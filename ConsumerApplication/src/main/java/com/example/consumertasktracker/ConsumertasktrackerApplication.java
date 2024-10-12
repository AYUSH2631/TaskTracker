package com.example.consumertasktracker;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.example.consumertasktracker.beans.TaskBean;

@SpringBootApplication
public class ConsumertasktrackerApplication {
	
	private static final String REST_SERVICE_URI="http://localhost:8080/";
	private static final RestTemplate restTemplate=new RestTemplate();
	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ConsumertasktrackerApplication.class, args);
		getAllTask();
		System.out.println(".............................");
		addTask();
		System.out.println(".............................");
		deleteTask(2);
		System.out.println(".............................");
        getTaskByPriority("MEDIUM");
	}
	
	public static void getAllTask() throws Exception {
		try {
			Collection<TaskBean>response=restTemplate.getForObject(REST_SERVICE_URI+"getAllTask", Collection.class);
			System.out.println(response);
		}
		catch(Exception e) {
			throw new Exception("No task Found");
		}
	}
	
	public static void addTask() throws Exception {
		
		TaskBean request=new TaskBean("testing",Date.valueOf("2024-11-12"),"LOW","testing");
	
		TaskBean response=restTemplate.postForObject(REST_SERVICE_URI+"addTask", request, TaskBean.class);
		System.out.println(response);
		
		
	}
	
	public static void deleteTask(int id) {
		
		try {
		restTemplate.delete(REST_SERVICE_URI+"deleteTask/"+id);
		System.out.println("Task "+id+" Deleted Succesfully!!!");
		}
		catch(Exception e) {
			System.out.println("TASK NOT FOUND!!!"+e.getMessage());
		}
		
		
	}
	
	public static void getTaskByPriority(String priority) {
		try {
		List<TaskBean>list=restTemplate.getForObject(REST_SERVICE_URI+"getTaskByPriority/"+priority,List.class);
		System.out.println(list);
		}
		catch(Exception e) {
			System.out.println("NO TASK WITH "+priority+" PRIORITY"+e.getMessage());
		}
	}

}
