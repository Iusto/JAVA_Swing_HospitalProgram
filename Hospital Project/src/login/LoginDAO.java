package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DB 기능 구현
public class LoginDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	// 다 null로 초기화됨

	// 1. OracleDriver 클래스가 있는 지 확인
	// => 프로젝트에 라이브러리 등록 확인
	public LoginDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 2. connection
	// => oracle db에 접속
	public Connection getConnection() {
		// Connection : oracle DB와의 접속 정보를 저장하는 클래스
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "C##hospital";
		String password = "m1234";

		try {
			// OracleDriver를 이용해서 oracle DB에 접속을 시도하고
			// => 성공하면 Connection 객체를 리턴
			// => 실패하면 예외를 발생시킴
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 3. db와 접속 끊기
	public void close() {
		try {
			if (pstmt != null) pstmt.close();
			if (rs != null)	rs.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 1. 아이디, 비밀번호 확인
	public LoginDTO login(String id, String pw) {
		String sql = "select * from staff where id=? and password=?";
		LoginDTO dto = null;
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new LoginDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속하기
			close();
		}
		return dto;
	}
	
	// 2. 아이디 확인
	public LoginDTO idcheck(String id) {
		String sql = "select * from staff where id=? and password=?";
		LoginDTO dto = null;
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new LoginDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속하기
			close();
		}
		return dto;
	}
	
	// 3. 관리자, 일반 유저 판단
	public LoginDTO admincheck(String id, String pw) {
		String sql = "select * from staff where id=? and password=? and dept = '전산과'";
		LoginDTO dto = null;
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new LoginDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속하기
			close();
		}
		return dto;
	}
}