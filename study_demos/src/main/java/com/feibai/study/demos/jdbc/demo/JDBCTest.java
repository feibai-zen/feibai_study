package com.feibai.study.demos.jdbc.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.feibai.study.demos.jdbc.dao.DBUtil;

public class JDBCTest {

	public static void main(String[] args) {
		// createTable();
		// query_statement();
		// insert_statement();
		// update_statement();
		// query_preparedStatement(2);//
		// update_preparedStatement("liyuanlong","liyuanlong@qq.com");
		// delete_preparedStatement("liyuanlong");
		simple_callableStatement();
	}

	/////////////////////////////////// Statement////////////////////////////////
	// 创建表-Statement
	static void createTable() {
		Connection conn = DBUtil.open();
		try {
			Statement stmt = conn.createStatement();
			String sql = "create table customer_tbl(" + "id int(10) unsigned not null auto_increment primary key,"
					+ "name varchar(30) not null," + "email varchar(50) not null" + ")";
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// 查询表-Statement
	static void query_statement() {
		Connection conn = DBUtil.open();
		try {
			String sql = "select id,name,email from customer_tbl;";
			Statement stmt = conn.createStatement();
			ResultSet rst = stmt.executeQuery(sql);// 执行查询
			// cursor
			while (rst.next()) {// 遍历结果集
				// 根据列索引获取
				int id = rst.getInt(1);// 与查出来的id对应
				String name = rst.getString(2); // 与查出来的name对应
				String email = rst.getString(3);// 与查出来的email对应

				/*
				 * 根据列名称获取 rst.getInt("id"); rst.getString("name"); rst.getString("email");
				 */

				System.out.println(id);
				System.out.println(name);
				System.out.println(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}

	}

	// 插入表-Statement
	static void insert_statement() {
		Connection conn = DBUtil.open();
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into customer_tbl(name,email) values('somebody','somebody@qq.com')";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// 修改表-Statement
	static void update_statement() {
		Connection conn = DBUtil.open();
		try {
			Statement stmt = conn.createStatement();
			String sql = "update customer_tbl set email='big_somebody@qq.com' where name='somebody';";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// 删除表中数据-Statement
	static void delete_statement() {
		Connection conn = DBUtil.open();
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete from customer_tbl where name='somebody'";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	/////////////////////////////////////////// PreparedStatement///////////////////////////////////////////////////
	// 查询数据-PreparedStatement
	static void query_preparedStatement(int id) {
		Connection conn = DBUtil.open();
		try {
			String sql = "select id,name,email from customer_tbl where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");

				System.out.println(name);
				System.out.println(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// 插入数据-PreparedStatement
	static void insert_preparedStatement(String name, String email) {
		Connection conn = DBUtil.open();
		try {
			String sql = "insert into customer_tbl(name,email) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// 修改数据-PreparedStatement
	static void update_preparedStatement(String name, String email) {
		Connection conn = DBUtil.open();
		try {
			String sql = "update customer_tbl set email = ? where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// 删除-PreparedStatement
	static void delete_preparedStatement(String name) {
		Connection conn = DBUtil.open();
		try {
			String sql = "delete from customer_tbl where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	//////////////////////////////////// CallableStatement//////////////////////////////
	// 查询-简单存储过程
	static void simple_callableStatement() {
		Connection conn = DBUtil.open();
		try {
			String sql = "{call all_customers()}";
			CallableStatement callstmt = conn.prepareCall(sql);
			ResultSet rs = callstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// 有输入参数存储过程
	static void inparameter_callableStatement(String name, String email) {
		Connection conn = DBUtil.open();
		try {
			String sql = "{call insert_customer(?,?)}";
			CallableStatement callstmt = conn.prepareCall(sql);
			callstmt.setString(1, name);
			callstmt.setString(2, email);

			callstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// 查询-有输入输出参数存储过程
	// procedure:create procedure getnamebyid(IN id int, OUT name varchar(30))
	static void outparameter_callableStatement(int id) {
		Connection conn = DBUtil.open();
		try {
			String sql = "{call getnamebyid(?,?)}";
			CallableStatement callstmt = conn.prepareCall(sql);
			callstmt.setInt(1, id);
			callstmt.registerOutParameter(2, Types.CHAR);
			callstmt.execute();
			String name = callstmt.getString(2);
			callstmt.executeQuery();
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	// INOUT参数
	static void inoutparameter_callableStatement(int id) {
		Connection conn = DBUtil.open();
		try {
			String sql = "CALL ALTOBJ ('something', ?, ?)"; // 第一个参数为INOUT参数, 第二个为OUT
			CallableStatement proc = conn.prepareCall(sql);
			proc.setInt(1, -1); // 作为IN时, 提供Value
			proc.registerOutParameter(1, Types.INTEGER); // 作为OUT时 注册输出参数
			proc.registerOutParameter(2, Types.VARCHAR); // 第二个参数为OUT参数, 仅注册.
			proc.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

}
