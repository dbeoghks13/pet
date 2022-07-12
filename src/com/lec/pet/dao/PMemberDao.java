package com.lec.pet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.pet.dto.PMemberDto;

public class PMemberDao {
	public static final int EXISTENT    = 0;
	public static final int NONEXISTENT = 1;
	public static final int LOGIN_FAIL    =0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static PMemberDao instance = new PMemberDao();
	public static PMemberDao getInstance() {
		return instance;
	}
	private PMemberDao() {}
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
	// (1) 로그인 체크
	public int loginCheck(String mId, String mPw) {
		int result = LOGIN_FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM PMEMBER WHERE mID=? and mPW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = LOGIN_SUCCESS;
			}else {
				result = LOGIN_FAIL;
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
		return result;
	}
	// (2) mid로 dto가져오기(로그인 성공시 session에 넣기 위함)
	public PMemberDto getMember(String mId) {
		PMemberDto member = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM PMEMBER WHERE mId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mPw = rs.getString("mPw");
				String mName = rs.getString("mName");
				String mEmail = rs.getString("mEmail");
				String mPhoto = rs.getString("mPhoto");
				Date   mBirth = rs.getDate("mBirth");
				String mAddress=rs.getString("mAddress");
				Timestamp mRdate = rs.getTimestamp("mRdate");
				member = new PMemberDto(mId, mPw, mName, mEmail, mPhoto, mBirth, 
																mAddress, mRdate);
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
		return member;
	}
	// (3) 회원id 중복체크 
	public int mIdConfirm(String mId) {
		int result = EXISTENT;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM PMEMBER WHERE mId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
			}else {
				result = NONEXISTENT;
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
		return result;
	}
	// (4) 회원가입 
	public int joinMember(PMemberDto member) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PMEMBER "
					+ "(mID, mPw, mName, mEmail, mPhoto, mBirth, mAddress) "
				+ "VALUES (?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getmPw());
			pstmt.setString(3, member.getmName());
			pstmt.setString(4, member.getmEmail());
			pstmt.setString(5, member.getmPhoto());
			pstmt.setDate(6, member.getmBirth());
			pstmt.setString(7, member.getmAddress());
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
	private String getmAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	private Date getmBirth() {
		// TODO Auto-generated method stub
		return null;
	}
	private String getmPhoto() {
		// TODO Auto-generated method stub
		return null;
	}
	private String getmEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	private String getmName() {
		// TODO Auto-generated method stub
		return null;
	}
	private String getmPw() {
		// TODO Auto-generated method stub
		return null;
	}
	private String getmId() {
		// TODO Auto-generated method stub
		return null;
	}
	// (5) 회원정보 수정 
	public int modifyMember(PMemberDto member) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PMEMBER SET mPw = ?," + 
				"                    mName = ?," + 
				"                    mEmail = ?," + 
				"                    mPhoto = ?," + 
				"                    mBirth = ?," + 
				"                    mAddress = ?" + 
				"        WHERE mId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getmPw());
			pstmt.setString(2, member.getmName());
			pstmt.setString(3, member.getmEmail());
			pstmt.setString(4, member.getmPhoto());
			pstmt.setDate(5, member.getmBirth());
			pstmt.setString(6, member.getmAddress());
			pstmt.setString(7, member.getmId());
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
	// (6) 회원리스트(top-N구문)
	public ArrayList<PMemberDto> allMember(int startRow, int endRow){
		ArrayList<PMemberDto> members = new ArrayList<PMemberDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " 
				 +     "         (SELECT * FROM PMEMBER ORDER BY mRDATE DESC) A)" 
				 +     "  WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId = rs.getString("mId");
				String mPw = rs.getString("mPw");
				String mName = rs.getString("mName");
				String mEmail = rs.getString("mEmail");
				String mPhoto = rs.getString("mPhoto");
				Date   mBirth = rs.getDate("mBirth");
				String mAddress=rs.getString("mAddress");
				Timestamp mRdate = rs.getTimestamp("mRdate");
				members.add(new PMemberDto(mId, mPw, mName, mEmail, mPhoto, mBirth, 
																mAddress, mRdate));
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
		return members;
	}
	// (7) 회원수
	public int getPMemberTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM PMEMBER";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt("cnt");
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
	// (8) 회원탈퇴
	public int withdrawal(String mId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PMEMBER WHERE MID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
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
	// (9) 이메일 중복체크
	public int emailConfirm(String mEmail) {
		int result = EXISTENT;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM PMEMBER WHERE mEMAIL = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
			}else {
				result = NONEXISTENT;
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
		return result;
	}
}