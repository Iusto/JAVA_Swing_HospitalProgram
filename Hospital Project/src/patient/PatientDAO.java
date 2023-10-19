package patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// DB 기능 구현
public class PatientDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	// 다 null로 초기화됨

	// 1. OracleDriver 클래스가 있는 지 확인
	// => 프로젝트에 라이브러리 등록 확인
	public PatientDAO() {
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

	public int insert(PatientDTO dto) {
		String sql = "insert into patient (patientnum, name, phone, email, address, visitdate, memo)"
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		int result = 0;

		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNumber());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getVisitdate());
			pstmt.setString(7, dto.getMemo());

			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속끊기
			close();
		}
		return result;
	}

	// 전체 조회
	public int update(PatientDTO dto) {
		String sql = "update patient set name=?, phone=?, email=?, address=?, visitdate=?, memo=? where patientnum=?";
		int result = 0;
		// 1) 오라클 db에 접속
		conn = getConnection();
		// 2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getVisitdate());
			pstmt.setString(6, dto.getMemo());
			pstmt.setInt(7, dto.getNumber());
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속 끊기
			close();
		}
		return result;
	}

	// 기간 조회
	public List<PatientDTO> selectAll2(String month1, String month2, String year1, String year2) {
		String sql = "SELECT * FROM patient WHERE EXTRACT(MONTH FROM visitdate) BETWEEN ? AND ?"
				+ "AND EXTRACT(YEAR FROM visitdate) BETWEEN ? AND ?";
		List<PatientDTO> list = new ArrayList<PatientDTO>();
		// 1) 오라클 db에 접속
		conn = getConnection();
		// 2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, month1);
			pstmt.setString(2, month2);
			pstmt.setString(3, year1);
			pstmt.setString(4, year2);
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			rs = pstmt.executeQuery();

			while (rs.next()) { // 1줄 데이터 읽어오기
				PatientDTO dto = new PatientDTO();
				dto.setNumber(rs.getInt("patientnum"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setVisitdate(rs.getString("visitdate"));
				dto.setMemo(rs.getString("memo"));
				// 리스트에 저장
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 3) 오라클 db와 접속끊기
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// (3) 오라클 db와 접속 끊기
				close();
			}
		}
		return list;
	}


	// 3. 환자번호 중복검사
	public int NumCheck(int number) {
		String sql = "select * from patient where patientnum = ?";
		//boolean result = false;  // true : 일치하는 사람이 있음
		int num = 0;//	사람없음
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("patientnum");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속하기
			close();
		}
		return num;
	}
}
