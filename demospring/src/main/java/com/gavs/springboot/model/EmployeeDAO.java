package com.gavs.springboot.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class EmployeeDAO {
	public static Connection MySQLConnection() {
	
	String MYSQL_DRIVERNAME = "com.mysql.cj.jdbc.Driver";
	String MYSQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
	String strUserName = "root";
	String strPassword = "admin";
		
	try {
			Class.forName(MYSQL_DRIVERNAME);
			return DriverManager.getConnection(MYSQL_CONNECTION_URL,strUserName, strPassword);
//			Statement stmt = conn.createStatement();) {
//			String sql="CREATE DATABASE EMPLOYEE";
//			String sql = "CREATE TABLE EMPLOYEE_DETAILS" + "(EMP_ID INT NOT NULL," + "EMP_NAME NOT NULL,"
//			+ " PRIMARY KEY (EMP_ID))";
//			stmt.executeUpdate(sql);
//			System.out.println("Created table in given database...");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int addEmployee(Employee emp) {
		try {
			String sql = "INSERT INTO EMPLOYEE_DETAILS VALUES (?,?)";
			PreparedStatement pstmt = MySQLConnection().prepareStatement(sql);
			pstmt.setInt(1, emp.getId());
			pstmt.setString(2, emp.getName());
			return pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			if(MySQLConnection()!=null) {
				try {
					MySQLConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	@SuppressWarnings("null")
	public Employee findEmployeeById(Employee emp1) throws SQLException {
		ResultSet rs=null;
		Employee emp=null;
			try {
				String sql = "SELECT NAME FROM EMPLOYEE_DETAILS WHERE ID=?";
                PreparedStatement pstmt = MySQLConnection().prepareStatement(sql);
                pstmt.setInt(1, emp.getId());
                rs = pstmt.executeQuery();
                
				if(rs.next()) {
					emp=new Employee();
					emp.setId(rs.getInt(1));
					emp.setName(rs.getString(2));
				}
				return emp;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				rs.close();
				if(MySQLConnection()!=null)
					try {
						MySQLConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			
		}
	public List<Employee> displayEmployee() throws SQLException{
		ResultSet rs = null;
		List<Employee> employeeList = null;
		Employee emp = null;
		try {
			String sql = "SELECT ID,NAME FROM EMPLOYEE_DETAILS";
			PreparedStatement ps = MySQLConnection().prepareStatement(sql);
			rs = ps.executeQuery(sql);
			employeeList = new ArrayList<Employee>();
			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				employeeList.add(emp);
			}
			return employeeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (MySQLConnection() != null) {
				MySQLConnection().close();
			}
		}
		
	}
	public int modifyEmployee(Employee emp) {
		try {
			String sql = "UPDATE `EMPLOYEE_DETAILS` SET `NAME` = ? WHERE (`ID` = ?);";
			PreparedStatement ps = MySQLConnection().prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getId());
			int res = ps.executeUpdate();
			return res;
		} catch (Exception e) {
			return 0;
		}finally {
			if (MySQLConnection() != null) {
				try {
					MySQLConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public int deleteEmployee(Employee emp) {
		try {
			String sql="DELETE FROM EMPLOYEE_DETAILS WHERE ID=?";
			PreparedStatement ps = MySQLConnection().prepareStatement(sql);
			ps.setString(1, emp.getName());
			return ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			if(MySQLConnection()!=null)
				try {
					MySQLConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
}
		
	



