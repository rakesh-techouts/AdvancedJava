package com.structure;

public class Student {
	private int rollNumber;
	private String name;
	private double cgpa;
	private String gender;

	public Student() {
	}

	public Student(int rollNumber, String name, double cgpa, String gender) {
		this.rollNumber = rollNumber;
		this.name = name;
		this.cgpa = cgpa;
		this.gender = gender;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
