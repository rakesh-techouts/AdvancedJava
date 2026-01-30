package com.structure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

	public void insert(Student s) {
		String sql = "INSERT INTO student (rollnumber, name, cgpa, gender) VALUES (?, ?, ?, ?)";
		try (Connection con = ConnectDatabase.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, s.getRollNumber());
			ps.setString(2, s.getName());
			ps.setDouble(3, s.getCgpa());
			ps.setString(4, s.getGender());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Insert failed", e);
		}
	}

	public List<Student> findAll() {
		String sql = "SELECT rollnumber, name, cgpa, gender FROM student ORDER BY rollnumber";
		List<Student> list = new ArrayList<>();
		try (Connection con = ConnectDatabase.getConnect();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(new Student(rs.getInt("rollnumber"), 
				rs.getString("name"), rs.getDouble("cgpa"),
				rs.getString("gender")));
			}
		} catch (SQLException e) {
			throw new RuntimeException("findAll failed", e);
		}
		return list;
	}

	public Student findByRoll(int roll) {
		String sql = "SELECT rollnumber, name, cgpa, gender FROM student WHERE rollnumber = ?";
		try (Connection con = ConnectDatabase.getConnect(); 
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, roll);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Student(rs.getInt("rollnumber"), rs.getString("name"), rs.getDouble("cgpa"),
							rs.getString("gender"));
				}
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("findByRollNumber failed", e);
		}
	}

	public void update(Student s) {
		String sql = "UPDATE student SET name = ?, cgpa = ?, gender = ? WHERE rollnumber = ?";
		try (Connection con = ConnectDatabase.getConnect(); 
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, s.getName());
			ps.setDouble(2, s.getCgpa());
			ps.setString(3, s.getGender());
			ps.setInt(4, s.getRollNumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Update failed", e);
		}
	}

	public int delete(int roll) {
		String sql = "DELETE FROM student WHERE rollnumber = ?";
		try (Connection con = ConnectDatabase.getConnect(); 
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, roll);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Delete failed", e);
		}
	}
}
