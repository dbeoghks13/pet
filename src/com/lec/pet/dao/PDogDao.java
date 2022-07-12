package com.lec.pet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.pet.dto.PDogDto;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class PDogDao {
	public static final int EXISTENT    = 0;
	public static final int NONEXISTENT = 1;
	public static final int LOGIN_FAIL    =0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static PDogDao instance = new PDogDao();
	public static PDogDao getInstance() {
		return instance;
	}
	private PDogDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	//1. 강아지 등록
	public int dogInsert(PDogDto dog) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO DOG(DNO, DNAME, DSIZE, dCategory, DCONTENT, DPHOTO)" + 
				"VALUES(DOG_SEQ.NEXTVAL, ?, ?, ?, ? , ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dog.getdName());
			pstmt.setString(2, dog.getdSize());
			pstmt.setString(3, dog.getdCategory());
			pstmt.setString(4, dog.getdContent());
			pstmt.setString(5, dog.getdPhoto());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;	
	}
	//2. 강아지 list 출력
	public ArrayList<PDogDto> doglist(int startRow, int endRow, String dSize){
		ArrayList<PDogDto> dtos = new ArrayList<PDogDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"                        (SELECT * FROM DOG WHERE DSIZE= ? ORDER BY DNO DESC) A)" + 
				"        WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dSize);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int dNo = rs.getInt("dNo");
				String dName = rs.getString("dName");
				String dCategory = rs.getString("dCategory");
				String dContent = rs.getString("dContent");
				String dPhoto = rs.getString("dPhoto");
				dtos.add(new PDogDto(dNo, dName, dSize, dCategory, dContent, dPhoto));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		return dtos;
	}   
	public ArrayList<PDogDto> dogAlllist(int startRow, int endRow){
		ArrayList<PDogDto> dtos = new ArrayList<PDogDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"                        (SELECT * FROM DOG ORDER BY DNO DESC) A)" + 
				"        WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int dNo = rs.getInt("dNo");
				String dName = rs.getString("dName");
				String dSize = rs.getString("dsize");
				String dCategory = rs.getString("dCategory");
				String dContent = rs.getString("dContent");
				String dPhoto = rs.getString("dPhoto");
				dtos.add(new PDogDto(dNo, dName, dSize, dCategory, dContent, dPhoto));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		return dtos;
	}   
	
	public int getDOGTotCnt(String dSize) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) DOG FROM DOG WHERE DSIZE= ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dSize);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt("dog");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	
	public int getAllTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) DOG FROM DOG";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt("dog");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
}


























