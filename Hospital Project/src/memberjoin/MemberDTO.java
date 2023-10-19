package memberjoin;

public class MemberDTO {
	private int staffnum;
	private String id;
	private String password;
	private String name;
	private String num;
	private String gender;
	private int age;
	private String phone;
	private String email;
	private String position;
	private String dept;
	private String employment;

	public MemberDTO() {

	}

	public MemberDTO(int staffnum, String id, String password, String name, String num, String gender, int age,
			String phone, String email, String position, String dept, String employment) {
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
		return "memberDTO [staffnum=" + staffnum + ", id=" + id + ", password=" + password + ", name=" + name + ", num="
				+ num + ", gender=" + gender + ", age=" + age + ", phone=" + phone + ", email=" + email + ", position="
				+ position + ", dept=" + dept + ", employment=" + employment + "]";
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