package com.my.study.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.study.beans.Customer;

public class CustomerDaoImpl implements CustomerDao {
	@Override
	public void add(Customer c) {
		Connection conn = DBUtil.open();
		try {
			String sql = "insert into customer_tbl(name,email) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	@Override
	public void update(Customer c) {
		Connection conn = DBUtil.open();
		try {
			String sql = "update customer_tbl set email = ? where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getEmail());
			pstmt.setString(2, c.getName());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}

	}

	@Override
	public Customer getCustomerById(int id) {
		Connection conn = DBUtil.open();
		try {
			String sql = "select id,name,email from customer_tbl;";
			Statement stmt = conn.createStatement();
			ResultSet rst = stmt.executeQuery(sql);// 执行查询
			if (rst.next()) {// 遍历结果集
				Customer c = new Customer();
				// 根据列索引获取
				c.setId(rst.getInt(1));// 与查出来的id对应
				c.setName(rst.getString(2)); // 与查出来的name对应
				c.setEmail(rst.getString(3));// 与查出来的email对应
				return c;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Customer> queryAllCostomer() {
		Connection conn = DBUtil.open();
		try {
			List<Customer> customer_list = new ArrayList<Customer>();
			String sql = "select id,name,email from customer_tbl;";
			Statement stmt = conn.createStatement();
			ResultSet rst = stmt.executeQuery(sql);// 执行查询
			// cursor
			while (rst.next()) {// 遍历结果集
				// 根据列索引获取
				Customer c = new Customer();
				c.setId(rst.getInt(1));// 与查出来的id对应
				c.setName(rst.getString(2)); // 与查出来的name对应
				c.setEmail(rst.getString(3));// 与查出来的email对应
				customer_list.add(c);
			}
			return customer_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Customer c) {
		Connection conn = DBUtil.open();
		try {
			String sql = "delete from customer_tbl where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

}
