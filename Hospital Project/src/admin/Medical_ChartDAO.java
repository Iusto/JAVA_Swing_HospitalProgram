package admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Medical_ChartDAO {
	Connection conn;
	PreparedStatement pstt;
	ResultSet rs;
	
	public Medical_ChartDAO() {
		// 오라클 드라이버 검사
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("오라클 드라이버 확인");
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 확인불가");
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		// DB와 연결
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "C##hospital";
		String userpass = "m1234";
		
		try {
			conn = DriverManager.getConnection(url,userid,userpass);
			System.out.println("커넥션 성공");
		} catch (SQLException e) {
			System.out.println("커넥션 실패");
			e.printStackTrace();
		}
		return conn;
	
	}
	public void Close() {
		// DB와 연결 해제
		try {
			if(rs != null) rs.close();
			if(conn != null) conn.close();
			if(pstt != null) pstt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 서치버튼
	public Medical_ChartDTO search(int staffnum, String name,String phone){
		String sql = "select * from staff where staffnum = ? and name = ? and phone = ?";
		Medical_ChartDTO dto = null;
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql 처리요청 + 응답
		try {
			pstt = conn.prepareStatement(sql);
			pstt.setInt(1, staffnum);
			pstt.setString(2, name);
			pstt.setString(3, phone);
			// 1) 오라클 db에 szl문 전달
			rs = pstt.executeQuery();
			if(rs.next()) {
				dto = new Medical_ChartDTO();
				dto.setStaffnum(rs.getInt("staffnum"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// 리스트에 저장실패
			// e.printStackTrace();
		} finally {
			// 오라클 디비와 접속해제
			Close();
		}
		return dto;
	}
	
	// 서치버튼
		public String search2(int staffnum, String name,String phone){
			String sql = "select * from staff where staffnum = ? and name = ? and phone = ?";
			Medical_ChartDTO dto = null;
			String result = "";
			// (1) 오라클 db에 접속
			conn = getConnection();
			// (2) sql 처리요청 + 응답
			try {
				pstt = conn.prepareStatement(sql);
				pstt.setInt(1, staffnum);
				pstt.setString(2, name);
				pstt.setString(3, phone);
				// 1) 오라클 db에 szl문 전달
				rs = pstt.executeQuery();
				if(rs.next()) {
					dto = new Medical_ChartDTO();
					dto.setStaffnum(rs.getInt("staffnum"));
					dto.setName(rs.getString("name"));
					dto.setPhone(rs.getString("phone"));
					result = dto.toString2();
				}
			} catch (SQLException e) {
				// 리스트에 저장실패
				System.out.println("리스트에 저장실패");
				e.printStackTrace();
			} finally {
				// 오라클 디비와 접속해제
				Close();
			}
			return result;
		}
	
	
	// 입력버튼
	public int insert(Medical_ChartDTO dto) {
		String sql = "insert into staff (staffnum,id,password,name,num,gender,age,phone,email,position,dept,employment) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		int result = 0;
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstt = conn.prepareStatement(sql);
			pstt.setInt(1, dto.getStaffnum());
			pstt.setString(2, dto.getId());
			pstt.setString(3, dto.getPassword());
			pstt.setString(4, dto.getName());
			pstt.setString(5, dto.getNum());
			pstt.setString(6, dto.getGender());
			pstt.setInt(7, dto.getAge());
			pstt.setString(8, dto.getPhone());
			pstt.setString(9, dto.getEmail());
			pstt.setString(10, dto.getPosition());
			pstt.setString(11,  dto.getDept());
			pstt.setString(12, dto.getEmployment());
			
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			result = pstt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속끊기
			Close();
		}
		return result;
	}
	
	// 출력버튼
	public List<Medical_ChartDTO> selectAll() {
		String sql = "select * from staff";
		List<Medical_ChartDTO> list = new ArrayList<Medical_ChartDTO>();
		int num = 0;
		// (1) 오라클 db에 접속
		conn = getConnection();
		// (2) sql문 처리 요청 + 응답처리
		try {
			pstt = conn.prepareStatement(sql);
			
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			rs = pstt.executeQuery();
			
			while (rs.next()) {
				Medical_ChartDTO dto = new Medical_ChartDTO();
				dto.setStaffnum(rs.getInt("staffnum"));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getString("num"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setPosition(rs.getString("position"));
				dto.setDept(rs.getString("dept"));
				dto.setEmployment(rs.getString("employment"));

				// 리스트에 저장
				list.add(dto);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// (3) 오라클 db와 접속끊기
			Close();
		}
		return list;
	}
	
	// 직원 삭제
	public int update(String position, String dept, String employment, int staffnum, String name, String id) {
		String sql = "update staff set position=?, dept=?, employment=? where staffnum=? and name=? and id=?";
		int result = 0;
		// 1) 오라클 db에 접속
		conn = getConnection();
		// 2) sql문 처리 요청 + 응답처리
		try {
			pstt = conn.prepareStatement(sql);
			pstt.setString(1, position);
			pstt.setString(2, dept);
			pstt.setString(3, employment);
			pstt.setInt(4, staffnum);
			pstt.setString(5, name);
			pstt.setString(6, id);
			// 1) oracle db에 sql문 전달 2) 응답을 기다림 3) 응답결과를 리턴함
			result = pstt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3) 오라클 db와 접속끊기
			Close();
		}
		if (result > 0) {
			System.out.println(result);
		}
		return result;
	}
	
	
	
}


















