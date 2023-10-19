package record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.helpers.ParserAdapter;


//DB 기능 구현
public class RecordDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	// 다 null로 초기화됨

	// 1. OracleDriver 클래스가 있는 지 확인
	// => 프로젝트에 라이브러리 등록 확인
	public RecordDAO() {
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
	
	public RecordDTO search(int treatmentnum, int patientnum, int staffnum){
		String sql = "select * from record where treatmentnum = ? and patientnum = ? and staffnum = ?";
		RecordDTO dto = null;
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql 처리요청 + 응답
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, treatmentnum);
			pstmt.setInt(2, patientnum);
			pstmt.setInt(3, staffnum);
			// 1) 오라클 db에 szl문 전달
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new RecordDTO();
				dto.setTreatmentnum(rs.getInt(treatmentnum));
				dto.setPatientnum(rs.getInt(patientnum));
				dto.setStaffnum(rs.getInt(staffnum));
			}
		} catch (SQLException e) {
			// 리스트에 저장실패
			// e.printStackTrace();
		} finally {
			// 오라클 디비와 접속해제
			close();
		}
		return dto;
	}	
	
	public int insert (RecordDTO dto) {
		String sql = "insert into record (treatmentnum, patientnum, staffnum, treatment, dept, visitdate)"
				+ "values (?,?,?,?,?,?)";
		int result = 0;

		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getTreatmentnum());
			pstmt.setInt(2, dto.getPatientnum());
			pstmt.setInt(3, dto.getStaffnum());
			pstmt.setString(4, dto.getTreatment());
			pstmt.setString(5, dto.getDept());
			pstmt.setString(6, dto.getVisitdate());
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

	public List<RecordDTO> select() {
		String sql = "select * from record";
		List<RecordDTO> list = new ArrayList<RecordDTO>();
		int result = 0;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RecordDTO dto = new RecordDTO();
				dto.setTreatmentnum(rs.getInt("treatmentnum"));
				dto.setPatientnum(rs.getInt("patientnum"));
				dto.setStaffnum(rs.getInt("staffnum"));
				dto.setTreatment(rs.getString("treatment"));
				dto.setDept(rs.getString("dept"));
				dto.setVisitdate(rs.getString("visitdate"));
				// 리스트에 저장
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
}
