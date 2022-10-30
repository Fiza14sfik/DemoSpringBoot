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
			return DriverManager.getConnection(MYSQL_CONNECTION_URL,strUserName, strPassword);
//			Statement stmt = conn.createStatement();) {
//			String sql="CREATE DATABASE EMPLOYEE";
//			String sql = "CREATE TABLE EMPLOYEE_DETAILS" + "(EMP_ID INT NOT NULL," + "EMP_NAME NOT NULL,"
//			+ " PRIMARY KEY (EMP_ID))";
//			stmt.executeUpdate(sql);
//			System.out.println("Created table in given database...");
		}catch(Exception e) {
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
	public Employee findEmployeeById(Employee em) throws SQLException {
		 ResultSet rs = null;
		    Employee Details=null;
		    try {
		        String sql = "SELECT ID,NAME FROM EMPLOYEE_DETAILS WHERE ID=?";
		        PreparedStatement ps = MySQLConnection().prepareStatement(sql);
		        ps.setInt(1, em.getId());
		        rs = ps.executeQuery();
		        while(rs.next()) {
		            Details = new Employee();
		            Details.setId(rs.getInt(1));
		            Details.setName(rs.getString(2));
		        }
		        
		        return Details;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    } finally {
		            try {
		                MySQLConnection().close();
		            } catch (SQLException e) {
		                e.printStackTrace();
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
		        String sql = "DELETE FROM EMPLOYEE_DETAILS WHERE ID=?";
		        PreparedStatement ps = MySQLConnection().prepareStatement(sql);
		        ps.setInt(1, emp.getId());
		        return ps.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		        return 0;
		    } finally {
		            try {
		                MySQLConnection().close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
	           }
             }
   	
		
	



