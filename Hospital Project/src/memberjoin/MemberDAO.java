package memberjoin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB 기능 구현
public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	// 다 null로 초기화됨

	// 1. OracleDriver 클래스가 있는 지 확인
	// => 프로젝트에 라이브러리 등록 확인
	public MemberDAO() {
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
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 사번 지정 (가입 시, 현재 있는번호의 다음번호로 지정)
	public int staffnum() {
		String sql = "select max(staffnum+1) as staffnum from staff";
		int result = 0;
		
		// (1) 오라클 db에 접속
				conn = getConnection();
				// (2) sql문 처리 요청 + 응답처리
				try {
					pstmt = conn.prepareStatement(sql);
					// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
					rs = pstmt.executeQuery();
					if (rs.next()) {
						result = rs.getInt("staffnum");
					}
				} catch (SQLException e) {
					// e.printStackTrace();
				} finally {
					// (3) 오라클 db와 접속끊기
					try {
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException e) {
						// e.printStackTrace();
					}
				}
		return result;
	}

	// 1. 입력
	public int insert(MemberDTO dto) {
		String sql = "insert into staff (staffnum, id, password, name, num, gender, age, phone,"
				+ "email, position, dept, employment) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		int result = 0;

		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getStaffnum());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPassword());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getNum());
			pstmt.setString(6, dto.getGender());
			pstmt.setInt(7, dto.getAge());
			pstmt.setString(8, dto.getPhone());
			pstmt.setString(9, dto.getEmail());
			pstmt.setString(10, dto.getPosition());
			pstmt.setString(11, dto.getDept());
			pstmt.setString(12, dto.getEmployment());
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			  e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속끊기
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				 e.printStackTrace();
			}
		}
		return result;
	}

	// 2. 아이디 중복 검사
	public MemberDTO IDdistinct(String id) {
		String sql = "select * from staff where id=?";
		MemberDTO dto = null;
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속하기
			close();
		}
		return dto;
	}
	
	// 3. 표시할 사원번호, 부서, 이름 불러오기
	public MemberDTO FromStaffinfo (String id) {
		String sql = "select * from staff where id=?";
		MemberDTO dto = null;
		// (1) 오라클 db에 접속
		conn = getConnection();	
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setDept(rs.getString("dept"));
				dto.setStaffnum(rs.getInt("staffnum"));
				dto.setName(rs.getString("name"));
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