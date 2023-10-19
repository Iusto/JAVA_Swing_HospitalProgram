package patient;

public class PatientDTO {
	private int number;		// 환자번호
	private String name;	// 이름
	private String phone;	// 전화번호
	private String email;	// 이메일
	private String address;		// 주소
	private String visitdate;	// 처음 방문일자
	private String memo;		// 메모
	
	
	public PatientDTO() {
		
	}


	public PatientDTO(int number, String name, String phone, String email, String address, String visitdate,
			String memo) {
		super();
		this.number = number;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.visitdate = visitdate;
		this.memo = memo;
	}


	@Override
	public String toString() {
		return "PatientDTO [number=" + number + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", visitdate=" + visitdate + ", memo=" + memo + "]";
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getVisitdate() {
		return visitdate;
	}


	public void setVisitdate(String visitdate) {
		this.visitdate = visitdate;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}
}
