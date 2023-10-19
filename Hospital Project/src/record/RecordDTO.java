package record;

public class RecordDTO {
	private int treatmentnum;
	private int patientnum;
	private int staffnum;
	private String treatment;
	private String dept;
	private String visitdate;

	public RecordDTO() {
		super();

	}

	public RecordDTO(int treatmentnum, int patientnum, int staffnum, String treatment, String dept, String visitdate) {
		super();
		this.treatmentnum = treatmentnum;
		this.patientnum = patientnum;
		this.staffnum = staffnum;
		this.treatment = treatment;
		this.dept = dept;
		this.visitdate = visitdate;
	}

	@Override
	public String toString() {
		return "RecordDTO [treatmentnum=" + treatmentnum + ", patientnum=" + patientnum + ", staffnum=" + staffnum
				+ ", treatment=" + treatment + ", dept=" + dept + ", visitdate=" + visitdate + "]";
	}

	public String printTitle() {
		String str = String.format("%s\t%s\t%s\n", "진료 번호", "환자 번호", "담당 직원");
		return str;
	}
	
	public String toString2() {
		String str = String.format("%s\t%s\t%s\n", treatmentnum, patientnum, staffnum);
		return str;
	}

	public int getTreatmentnum() {
		return treatmentnum;
	}

	public void setTreatmentnum(int treatmentnum) {
		this.treatmentnum = treatmentnum;
	}

	public int getPatientnum() {
		return patientnum;
	}

	public void setPatientnum(int patientnum) {
		this.patientnum = patientnum;
	}

	public int getStaffnum() {
		return staffnum;
	}

	public void setStaffnum(int staffnum) {
		this.staffnum = staffnum;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(String date) {
		this.visitdate = date;
	}

}
