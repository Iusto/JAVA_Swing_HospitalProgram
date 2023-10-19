package admin;

import java.util.ArrayList;
import java.util.List;

public class Medical_ChartImpl implements ChartInter {
	Medical_ChartDAO dao = new Medical_ChartDAO();
	
	@Override
	public Medical_ChartDTO searemp(int staffnum, String name,String phone) {
		Medical_ChartDTO dto = dao.search(staffnum,name,phone);
		return dto;
	}
	
	@Override
	public int empinsert(Medical_ChartDTO dto) {
		int result = dao.insert(dto);
		return result;
	}

	@Override
	public List<Medical_ChartDTO> empselect() {
		String str = "";
		List<Medical_ChartDTO> list = dao.selectAll();
		return list;
	}

	@Override
	public int empupdate(String position, String dept, String employment, int staffnum, String name, String id) {
		int result = dao.update(position, dept, employment, staffnum, name, id);
		return result;
		
	}

}
