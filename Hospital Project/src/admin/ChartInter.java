package admin;

import java.util.List;

public interface ChartInter {
	public Medical_ChartDTO searemp (int staffnum, String name,String phone);	// 직원검색
	public int empinsert(Medical_ChartDTO dto);
	public List<Medical_ChartDTO> empselect ();		// 모든 직원 확인
	public int empupdate(String position, String dept, String employment, int staffnum, String name, String id);
	
}
