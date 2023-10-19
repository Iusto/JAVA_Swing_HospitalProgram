package admin;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicHorizontalLayoutManager;
import javax.swing.table.DefaultTableModel;

public class Medical_ChartFrame extends JFrame implements ActionListener, MouseListener {
	// 컨테이너
	Container container = getContentPane();
	JOptionPane jop = new JOptionPane();
	// 이미지
	ImageIcon imageIcon = new ImageIcon("img/medi1.png");
	ImageIcon imageserch = new ImageIcon("img/ICON.png");
	ImageIcon imagebogi = new ImageIcon("img/searICON.png");
	ImageIcon symbol = new ImageIcon("img/medi2.png");
	Calendar calendar = Calendar.getInstance();
	// 콤보박스
	String[] posistr = { "사원", "대리", "과장", "부장", "레지던트", "전공의" };
	String[] deparstr = { "원무과", "전산과", "내과", "안과", "이비인후과", "산부인과", "흉부외과", "신경외과", "소아청소년과", "병리과" };
	String[] attenstr = { "Y", "N" };
	JComboBox<String> posiBox = new JComboBox<String>(posistr);
	JComboBox<String> deparBox = new JComboBox<String>(deparstr);
	JComboBox<String> attenBox = new JComboBox<String>(attenstr);

	// 탭패인 CT구성
	JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	String[] str = { "사원번호", "아이디", "비밀번호", "이름", "주민번호", "성별", "나이", "전화번호", "이메일", "직위", "부서", "재직여부(Y/N)" };
	String[] str2 = { "사원번호", "이름", "전화번호" };
	// 제목으로 str 배열 설정, 5행 설정
	DefaultTableModel tableModel = new DefaultTableModel(str, 255);
	DefaultTableModel tableModel2 = new DefaultTableModel(str2, 255);
	// JTable
	JTable table = new JTable(tableModel);
	JTable table2 = new JTable(tableModel2);
	JScrollPane scrolltabPane = new JScrollPane(table);
	JScrollPane scrolltabPane2 = new JScrollPane(table2);
	// 라디오 버튼
	JRadioButton genderbutton1 = new JRadioButton("남자", true);
	JRadioButton genderbutton2 = new JRadioButton("여자", false);
	ButtonGroup buttonGroup1 = new ButtonGroup();

	// North 패널
	JPanel jpanelNorth = new JPanel();
	JPanel jpanelNorthWest = new JPanel();
	JPanel jpanelNorthCenter = new JPanel();
	JLabel jLabelWest = new JLabel("직원검색   ");
	JLabel jLabelEast = new JLabel("직원정보   ");

	// West North 패널
	JPanel JpanelWest = new JPanel();
	JPanel JpanelWestNorth1 = new JPanel();
	JPanel JpanelWestNorth2 = new JPanel();
	JPanel JpanelWestNorth3 = new JPanel();
	JLabel labelWN = new JLabel();
	JLabel labelstaffnum = new JLabel("사원번호");
	JTextField jtextFstaffnum = new JTextField(10);
	JLabel labelname = new JLabel("이   름");
	JTextField jtextFname = new JTextField(10);
	JLabel labelphone = new JLabel("전화번호");
	JTextField jtextFphone = new JTextField(10);
	JPanel JpanelWestCenter = new JPanel();
	JButton jbuttonSear = new JButton("조회", imageserch);

	// West Center textArea
	JTextArea jtextArea = new JTextArea();
	JLabel labelName2 = new JLabel("성명");
	JLabel labelPhon2 = new JLabel("핸드폰");
	JScrollPane JscrollPane = new JScrollPane(jtextArea);
	ButtonGroup buttonGroup = new ButtonGroup();

	// Center & Center North 패널
	JPanel jpanelCenter = new JPanel();
	JPanel JpanelCenterNorth = new JPanel();
	JPanel JpanelCenterNorthN = new JPanel();
	JPanel JpanelCenterNorthW = new JPanel();
	JPanel JpanelCenterNorthWBlank = new JPanel();
	JPanel JpanelCenterNorthE = new JPanel();
	JPanel JpanelCenterNorthEBlank = new JPanel();

	JLabel labelStaffNum = new JLabel("사원번호");
	JTextField jtextStaffNum = new JTextField(10);
	JLabel labelStaffid = new JLabel("아 이 디");
	JTextField jtextStaffid = new JTextField(20);
	JLabel labelStaffpass = new JLabel("비밀번호");
	JPasswordField jpass = new JPasswordField(20);
	JLabel labelStaffName = new JLabel("이   름");
	JTextField jtextStaffName = new JTextField(10);
	JLabel labelStaffnum = new JLabel("주민번호");
	JTextField jtextStaffnum = new JTextField(25);
	JLabel labelStaffage = new JLabel("나   이");
	JTextField jtextStaffage = new JTextField(25);
	JLabel labelStaffPhon = new JLabel("전화번호");
	JTextField jtextStaffPhon = new JTextField(20);
	JLabel labelStaffemail = new JLabel("이 메 일");
	JTextField jtextStaffemail = new JTextField(20);
	JLabel labelStaffpo = new JLabel("직   위");
	JTextField jtextStaffpo = new JTextField(25);
	JLabel labelStaffdept = new JLabel("부   서");
	JTextField jtextStaffdept = new JTextField(25);
	JLabel labelemployment = new JLabel("재직여부(Y/N)");
	JTextField jtextemployment = new JTextField(25);

	// Center Center
	JPanel JpanelCenterCenter = new JPanel();
	JButton bogi = new JButton("병원 회원관리", imagebogi);
//	ButtonGroup bg = new ButtonGroup();

	// Center South
	JPanel JpanelCenterSouth = new JPanel();
	JPanel JpanelCenterSouthC = new JPanel();
	JPanel JpanelCenterSouthW = new JPanel();
	JPanel JpanelCenterSouthW1 = new JPanel();
	JPanel JpanelCenterSouthW2 = new JPanel();
	JPanel JpanelCenterSouthW3 = new JPanel();
	JPanel JpanelCenterSouthW4 = new JPanel();
	JPanel JpanelCenterSouthE = new JPanel();
	JPanel JpanelCenterSouthN = new JPanel();
	JPanel JpanelCenterSouthS = new JPanel();

	JLabel jlanum = new JLabel("사원번호");
	JTextField jfinum = new JTextField(10);
	JLabel jlaname = new JLabel("이   름");
	JTextField jfiname = new JTextField(10);
	JLabel jlaid = new JLabel("아이디");
	JTextField jfid = new JTextField(10);
	JLabel jflaposi = new JLabel("직위변경");
	JTextField jffiposi = new JTextField(10);
	JLabel jladept = new JLabel("부서변경");
	JTextField jfidept = new JTextField(10);
	JLabel working = new JLabel("재직여부");

	// South 버튼&패널
	JPanel JpanelSouth = new JPanel();
	JPanel JpanelSouthEast = new JPanel();
	JButton jbuttoninsert = new JButton("입력");
	JButton jbuttonselect = new JButton("출력");
	JButton jbuttonupdate = new JButton("수정");
	// 데이터 처리
	ChartInter chartInter = new Medical_ChartImpl();

	JDialog dialog = new JDialog(this);
	JLabel djl = new JLabel("입력성공");
	JLabel djl2 = new JLabel("입력된 정보가 일치하지 않습니다");
	JLabel djl3 = new JLabel("입력된 정보가 누락됐습니다");

	public Medical_ChartFrame() {
		setSize(1100, 800);
		setTitle("아이티뱅크 전산팀");
		setLocation(600, 150);
		setIconImage(imageIcon.getImage());
		init();
		start();
		setVisible(true);
		setResizable(true); // 화면크기 변경 가능 설정
		
	}

	private void init() {
		// frame
		container.setLayout(new BorderLayout());
		container.add("North", jpanelNorth);
		container.add("West", JpanelWest);
		container.add("Center", jpanelCenter);
		container.add("South", JpanelSouth);

		// North Label 직원검색 // 직원정보
		jpanelNorth.setLayout(new BorderLayout());
		jpanelNorth.add("West", jpanelNorthWest);
		jpanelNorth.add("Center", jpanelNorthCenter);
		jpanelNorthWest.add(jLabelWest);

		jpanelNorthCenter.add(jLabelEast);

		// West North1 직원 검색 패널
		JpanelWest.setLayout(new GridLayout(3, 1));
		JpanelWest.add(JpanelWestNorth1);
		JpanelWest.add(JpanelWestNorth2);
		JpanelWest.add(JpanelWestNorth3);
		JpanelWest.setBorder(new BevelBorder(BevelBorder.RAISED));
		JpanelWestNorth1.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		JpanelWestNorth1.setLayout(new GridLayout(4, 1));
		JpanelWestNorth1.add(labelstaffnum);
		JpanelWestNorth1.add(jtextFstaffnum);
		JpanelWestNorth1.add(labelname);
		JpanelWestNorth1.add(jtextFname);
		JpanelWestNorth1.add(labelphone);
		JpanelWestNorth1.add(jtextFphone);
		JpanelWestNorth1.add(labelWN);
		JpanelWestNorth1.add(jbuttonSear);

		// West North2 레이아웃
		JpanelWestNorth2.setLayout(new BorderLayout());
		JpanelWestNorth2.add(labelName2);
		JpanelWestNorth2.add(labelPhon2);
		JpanelWestNorth2.add(JscrollPane);
		jtextArea.setEnabled(false);
		jtextArea.setBackground(Color.GRAY);

		// West North3 레이아웃(공백)
		JpanelWestNorth3.setLayout(new BorderLayout());
		
		// 이미지 크기 조절
		int width = 150; // 너비
		int height = 150; // 높이
		symbol = new ImageIcon(symbol.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
		JLabel logo = new JLabel(symbol);
		JLabel label = new JLabel("아이티뱅크 전산팀", JLabel.CENTER);
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		label.setFont(font);
		JpanelWestNorth3.add("Center", logo);
		JpanelWestNorth3.add("South", label);
		

		// Center 패널
		jpanelCenter.setLayout(new BorderLayout());
		jpanelCenter.add("North", JpanelCenterNorth);
		jpanelCenter.add("Center", JpanelCenterCenter);
		jpanelCenter.add("South", JpanelCenterSouth);

		// Center North North 그리드 레이아웃
		JpanelCenterNorth.setLayout(new GridLayout(0, 4));
		JpanelCenterNorth.setBorder(new BevelBorder(BevelBorder.RAISED));
		JpanelCenterNorth.setBorder((new TitledBorder("직원 상세정보")));
		// Center North North North 공백패널
		JpanelCenterNorth.add(JpanelCenterNorthWBlank);
		JpanelCenterNorthWBlank.setLayout(new FlowLayout());
		// Center North 성명,주민번호,전화번호
		JpanelCenterNorth.add(JpanelCenterNorthW);
		JpanelCenterNorthW.setLayout(new GridLayout(4, 2));
		JpanelCenterNorthW.add(labelStaffNum);
		JpanelCenterNorthW.add(jtextStaffNum);
		JpanelCenterNorthW.add(labelStaffName);
		JpanelCenterNorthW.add(jtextStaffName);
		JpanelCenterNorthW.add(labelStaffPhon);
		JpanelCenterNorthW.add(jtextStaffPhon);
		JpanelCenterNorthW.add(labelStaffemail);
		JpanelCenterNorthW.add(jtextStaffemail);
		// 오른쪽 중앙 관계,생년월일,휴대폰
		JpanelCenterNorth.add(JpanelCenterNorthE);
		JpanelCenterNorthE.setLayout(new GridLayout(4, 2));
		JpanelCenterNorthE.add(labelStaffid);
		JpanelCenterNorthE.add(jtextStaffid);
		JpanelCenterNorthE.add(labelStaffpass);
		JpanelCenterNorthE.add(jpass);
		JpanelCenterNorthE.add(labelStaffpo);
		JpanelCenterNorthE.add(jtextStaffpo);
		JpanelCenterNorthE.add(labelStaffdept);
		JpanelCenterNorthE.add(jtextStaffdept);

		// Center North East 패널
		JpanelCenterNorth.add(JpanelCenterNorthEBlank);
		JpanelCenterNorthEBlank.setLayout(new GridLayout(4, 2));
		JpanelCenterNorthEBlank.add(labelStaffnum);
		JpanelCenterNorthEBlank.add(jtextStaffnum);
		JpanelCenterNorthEBlank.add(labelStaffage);
		JpanelCenterNorthEBlank.add(jtextStaffage);
		JpanelCenterNorthEBlank.add(labelemployment);
		JpanelCenterNorthEBlank.add(jtextemployment);
		JpanelCenterNorthEBlank.add(genderbutton1);
		JpanelCenterNorthEBlank.add(genderbutton2);
		buttonGroup1.add(genderbutton1);
		buttonGroup1.add(genderbutton2);

		// Center Center
		JpanelCenterCenter.setLayout(new BorderLayout());
		JpanelCenterCenter.add("Center", tabPane);

		tabPane.add("직원정보", scrolltabPane);

		bogi.setBorder(new BevelBorder(BevelBorder.RAISED));

		// Center South
		JpanelCenterSouth.setBorder((new TitledBorder("근무자 정보변경")));
		JpanelCenterSouth.setLayout(new GridLayout(0, 3));
		JpanelCenterSouth.add(JpanelCenterSouthW);
		JpanelCenterSouth.add(JpanelCenterSouthC);
		JpanelCenterSouth.add(JpanelCenterSouthE);
		JpanelCenterSouthW.setLayout(new GridLayout(4, 2));
		JpanelCenterSouthC.setLayout(new GridLayout(4, 2));
		JpanelCenterSouthE.setLayout(new GridLayout(4, 2));
		JpanelCenterSouthW.add(jlanum);
		JpanelCenterSouthW.add(jfinum);
		JpanelCenterSouthW.add(jlaname);
		JpanelCenterSouthW.add(jfiname);
		JpanelCenterSouthW.add(jlaid);
		JpanelCenterSouthW.add(jfid);
		JpanelCenterSouthC.add(jflaposi);
		JpanelCenterSouthC.add(posiBox);
		JpanelCenterSouthC.add(jladept);
		JpanelCenterSouthC.add(deparBox);
		JpanelCenterSouthC.add(working);
		JpanelCenterSouthC.add(attenBox);

		// South
		JpanelSouth.setLayout(new FlowLayout());
		JpanelSouth.setBorder(new BevelBorder(BevelBorder.RAISED));
		JpanelSouth.add(jbuttoninsert);
		JpanelSouth.add(jbuttonselect);
		JpanelSouth.add(jbuttonupdate);

	}

	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jbuttonSear.addActionListener(this);
		jbuttoninsert.addActionListener(this);
		jbuttonselect.addActionListener(this);
		jbuttonupdate.addActionListener(this);
		jpass.addActionListener(this);
		// dialog의 x버튼
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dialog.dispose(); // dialog창 없애기
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbuttonSear) {
			Medical_ChartDAO dao = new Medical_ChartDAO();
			Medical_ChartDTO dto = new Medical_ChartDTO();
			String snum = jtextFstaffnum.getText();
			String staffName = jtextFname.getText();
			String staffPhone = jtextFphone.getText();
			String result = "";
			if (!snum.equals("") && !staffName.equals("") && !staffPhone.equals("")) {
				result = dto.printTitle() + "\n" + dao.search2(Integer.parseInt(snum), staffName, staffPhone);
			} else {
				result = "데이터를 입력하세요";
			}
			jtextArea.setText(result);
			// textField 초기화
			jtextFstaffnum.setText("");
			jtextFname.setText("");
			jtextFphone.setText("");
		} else if (e.getSource() == jbuttoninsert) {
			String result = "";
			String str = null;
			String staffnum = jtextStaffNum.getText();
			String id = jtextStaffid.getText();
			String password = jpass.getText();
			String name = jtextStaffName.getText();
			String num = jtextStaffnum.getText();
			String age = jtextStaffage.getText();
			String phone = jtextStaffPhon.getText();
			String email = jtextStaffemail.getText();
			String position = jtextStaffpo.getText();
			String dept = jtextStaffdept.getText();
			String employment = jtextemployment.getText();
			String gender = ""; // 0:남자, 1:여자
			if (genderbutton1.isSelected()) { // 남자가 체크
				gender = "남자";
			} else if (genderbutton2.isSelected()) { // 여자가 체크
				gender = "여자";
			}		
			// 입력 검사
			if (!staffnum.equals("") && !id.equals("") && !password.equals("") && !name.equals("") && !num.equals("")
				&& !gender.equals("") && !age.equals("") && !phone.equals("") && !email.equals("")
				&& !position.equals("") && !dept.equals("") && !employment.equals("")) {
				// 사원번호 중복 검사
				List<Medical_ChartDTO> list = chartInter.empselect();
				for(int i=0; i<list.size(); i++) {
					Medical_ChartDTO medical_ChartDTO = list.get(i);
					if(medical_ChartDTO.getStaffnum() == Integer.parseInt(staffnum)) {
						JOptionPane.showConfirmDialog(this, "중복된 사원번호입니다", "ERROR", JOptionPane.DEFAULT_OPTION,
			                     JOptionPane.ERROR_MESSAGE);
						return;  // 함수 강제 종료
				}
			}
		}
				 
			else {
				JOptionPane.showConfirmDialog(this, "데이터를 입력해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;  // 함수 강제 종료
			}
			
			// db에 데이터 저장
			Medical_ChartDTO dto = new Medical_ChartDTO();
			dto.setStaffnum(Integer.parseInt(staffnum));
			dto.setId(id);
			dto.setPassword(password);
			dto.setName(name);
			dto.setNum(num);
			dto.setAge(Integer.parseInt(age));
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setPosition(position);
			dto.setDept(dept);
			dto.setEmployment(employment);
			dto.setGender(gender);
			int result1 = chartInter.empinsert(dto);
			if (result1 > 0) { // 저장 성공
				List<Medical_ChartDTO> list = chartInter.empselect();
				for (int i = 0; i < list.size(); i++) {
					Medical_ChartDTO dto1 = list.get(i);
					// (2, 2) 데이터 출력
					tableModel.setValueAt(dto1.getStaffnum(), i, 0);
					tableModel.setValueAt(dto1.getId(), i, 1);
					tableModel.setValueAt(dto1.getPassword(), i, 2);
					tableModel.setValueAt(dto1.getName(), i, 3);
					tableModel.setValueAt(dto1.getNum(), i, 4);
					tableModel.setValueAt(dto1.getGender(), i, 5);
					tableModel.setValueAt(dto1.getAge(), i, 6);
					tableModel.setValueAt(dto1.getPhone(), i, 7);
					tableModel.setValueAt(dto1.getEmail(), i, 8);
					tableModel.setValueAt(dto1.getPosition(), i, 9);
					tableModel.setValueAt(dto1.getDept(), i, 10);
					tableModel.setValueAt(dto1.getEmployment(), i, 11);
				}
			}

		} else if (e.getSource() == jbuttonselect) {
			Medical_ChartDTO dto = new Medical_ChartDTO();
			List<Medical_ChartDTO> list = chartInter.empselect();
			for (int j = 0; j < list.size(); j++) {
				Medical_ChartDTO dto1 = list.get(j);
				tableModel.setValueAt(dto1.getStaffnum(), j, 0);
				tableModel.setValueAt(dto1.getId(), j, 1);
				tableModel.setValueAt(dto1.getPassword(), j, 2);
				tableModel.setValueAt(dto1.getName(), j, 3);
				tableModel.setValueAt(dto1.getNum(), j, 4);
				tableModel.setValueAt(dto1.getGender(), j, 5);
				tableModel.setValueAt(dto1.getAge(), j, 6);
				tableModel.setValueAt(dto1.getPhone(), j, 7);
				tableModel.setValueAt(dto1.getEmail(), j, 8);
				tableModel.setValueAt(dto1.getPosition(), j, 9);
				tableModel.setValueAt(dto1.getDept(), j, 10);
				tableModel.setValueAt(dto1.getEmployment(), j, 11);
			}

		} else if (e.getSource() == jbuttonupdate) {
			Medical_ChartDTO dto = new Medical_ChartDTO();
			Medical_ChartDAO dao = new Medical_ChartDAO();
			int num = Integer.parseInt(jfinum.getText());
			String name = jfiname.getText();
			String id = jfid.getText();
			String position = (String) posiBox.getSelectedItem();
			String dept = (String) deparBox.getSelectedItem();
			String emp = (String) attenBox.getSelectedItem();
			System.out.println(num);
			System.out.println(name);
			System.out.println(id);
			System.out.println(position);
			System.out.println(dept);
			System.out.println(emp);
			// Medical_ChartDAO dao = new Medical_ChartDAO();
			int result = dao.update(position, dept, emp, num, name, id);
			if (result > 0) {
				JOptionPane.showConfirmDialog(this, "수정성공", "Update", JOptionPane.DEFAULT_OPTION,
	                     JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(this, "수정실패", "Update", JOptionPane.DEFAULT_OPTION,
	                     JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		}


	
	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}


}
