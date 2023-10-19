package admin;

public class Medical_ChartDTO {
	int staffnum;		// 사원번호
	String id;			// 아이디
	String password;	// 비밀번호
	String name;		// 이름
	String num;			// 주민번호
	String gender;		// 성별
	int age;			// 나이
	String phone;		// 전화번호
	String email;		// 이메일
	String position;	// 직위
	String dept;		// 담당부서
	String employment;	// 재직여부
	public Medical_ChartDTO() {
		super();
	}
	public Medical_ChartDTO(int staffnum, String id, String password, String name, String num, String gender,
			int age, String phone, String email, String position, String dept, String employment) {
		super();
		this.staffnum = staffnum;
		this.id = id;
		this.password = password;
		this.name = name;
		this.num = num;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.position = position;
		this.dept = dept;
		this.employment = employment;
	}
	@Override
	public String toString() {
		String str = String.format("%d\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s",
		staffnum, id, password, name, num, gender, age, phone, email, position, dept, employment);
		return str;
	}
	
	public String printTitle() {
		String str = String.format("%s\t%s\t%s", "사번", "이름", "전화번호");
		return str;
	}
	
	
	public String toString2() {
		String str = String.format("%s\t%s\t%s", staffnum, name, phone);
		return str;
	}
	
	public int getStaffnum() {
		return staffnum;
	}
	public void setStaffnum(int staffnum) {
		this.staffnum = staffnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}

}