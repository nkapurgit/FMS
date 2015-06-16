package org.samvitticapital.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


@Component
@Entity
public class Employee {
	
	@Id
	String name;
	int age;
	String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	

}
