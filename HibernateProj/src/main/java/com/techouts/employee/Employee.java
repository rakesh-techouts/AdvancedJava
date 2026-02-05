package com.techouts.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private int id;
	private String name;
    private double salary;

    public Employee() {}

    public Employee(int id , String name,double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
    @Override
    public String toString() {
        return "Employee [Id: "+this.id+" Name: "+this.name+" Salary: "+this.salary+" ]";
    }
}
