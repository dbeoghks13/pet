package com.lec.pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.lec.pet.dto.PAdminDto;
import com.lec.pet.dto.PAdminDto;

public class PAdminDao {
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;
	private static PAdminDao instance = new PAdminDao();
	public static PAdminDao getInstance() {
		return instance;
	}
	private PAdminDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn; 
	}
	// (1) PAdmin loginCheck
	public int loginCheck(String aId, String aPw) {
		int result = LOGIN_FAIL; 
		Connection 		   conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet 		   rs 	 = null;
		String sql = "SELECT * FROM PADMIN WHERE AID=? AND APW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, aPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = LOGIN_SUCCESS;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {System.out.println(e.getMessage());}
		}
		return result;
	}
	// (2) admin aid로 dto 가져오기
	public PAdminDto getPAdmin(String aId) {
		PAdminDto admin = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM PADMIN WHERE AID=?";
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				admin = new PAdminDto();
				admin.setaId(rs.getString("aId"));
				admin.setaPw(rs.getString("aPw"));
				admin.setaName(rs.getString("aName"));
			}			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {System.out.println(e.getMessage());}
		}
		return admin;
	}
}