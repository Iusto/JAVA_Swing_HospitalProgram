package patient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import login.LoginFrame;
import memberjoin.MemberDAO;
import memberjoin.MemberDTO;
import record.RecordDialog;

public class PatientFrame extends JFrame implements ActionListener {
	Container container = getContentPane();
    RecordDialog dialog = new RecordDialog(); //(this, "회원가입");
    Container dialogContainer = dialog.getContentPane();

	// panel 구성
	JPanel panel_west = new JPanel();
	JPanel panel_center = new JPanel();
	JPanel panel_west_up = new JPanel();
	JPanel west_up = new JPanel();
	JPanel west_up_center = new JPanel();
	JPanel west_south = new JPanel();
	JPanel title_panel = new JPanel();
	JPanel date_panel = new JPanel();
	JPanel center_north = new JPanel();
	JPanel center_center = new JPanel();
	JPanel iconpanel = new JPanel();
	JPanel panelNum = new JPanel();
	JPanel panelName = new JPanel();
	JPanel panelId = new JPanel();
	JPanel panelDept = new JPanel();
	JPanel panelOutput = new JPanel();
	JPanel panel_east = new JPanel();
	JPanel patient_input_panel = new JPanel();
	JPanel patient_button_panel = new JPanel();
	JPanel patientnum_panel = new JPanel();
	JPanel patientname_panel = new JPanel();
	JPanel patientphone_panel = new JPanel();
	JPanel patientemail_panel = new JPanel();
	JPanel patientaddress_panel = new JPanel();
	JPanel patientvisit_panel = new JPanel();
	JPanel patientmemo_panel = new JPanel();

	// (1-2-1) panel_up_west 구성
	JLabel labelTitle = new JLabel("아이티뱅크 병원", JLabel.CENTER);
	JLabel labelNum = new JLabel("사원번호 : ", JLabel.LEFT);
	JLabel labelId = new JLabel("사용자 ID : ", JLabel.LEFT);
	JLabel labelName = new JLabel("사용자명 : ", JLabel.LEFT);
	JLabel labelDept1 = new JLabel("담당 부서 : ", JLabel.LEFT);
	JLabel labelNum2 = new JLabel("사원번호");
	JLabel labelId2 = new JLabel("아이디");
	JLabel labelName2 = new JLabel("비밀번호");
	JLabel labelDept2 = new JLabel("부서");
	JLabel label = new JLabel("환자 정보", JLabel.CENTER);
	JLabel blank = new JLabel("                                        ", JLabel.CENTER);
	JLabel label1 = new JLabel("기간설정", JLabel.LEFT);
	JLabel patient_num = new JLabel("환자 번호 :\t");
	JLabel patient_name = new JLabel("환자 이름 :\t");
	JLabel patient_phone = new JLabel("전화번호 :\t");
	JLabel patient_email = new JLabel("이메일 :\t");
	JLabel patient_address = new JLabel("주소 :\t");
	JLabel patient_visit = new JLabel("처음 내원날짜 :\t");

	// 텍스트 필드 구성
	JTextField textField_num = new JTextField(10);
	JTextField textField_name = new JTextField(10);
	JTextField textField_phone = new JTextField(10);
	JTextField textField_email = new JTextField(10);
	JTextField textField_address = new JTextField(10);
	JTextField textField_visit = new JTextField(10);
	JTextField textField_memo = new JTextField(100);

	JLabel arriveDateLbl = new JLabel("마지막 일");
	ImageIcon symbol = new ImageIcon(getClass().getResource("/img/symbol.png"));
	ImageIcon symbol_icon = new ImageIcon(getClass().getResource("/img/symbol_icon.png"));

	// 버튼 생성
	JButton button = new JButton("조회");
	JButton button2 = new JButton("로그아웃");
	JButton button3 = new JButton("초기화");
	JButton inputButton = new JButton("등록");
	JButton updataButton = new JButton("수정");
	JButton recordButton = new JButton("진료 기록");
	

	// Center_center
	String[] colName = { "환자번호", "이름", "전화번호", "이메일", "주소", "처음 내원날짜", "메모사항" };
	DefaultTableModel tablemodel = new DefaultTableModel(colName, 30);
	JTable table = new JTable(tablemodel);
	JScrollPane scrollPane = new JScrollPane(table);

	// 폰트 선언
	Font fonttitle = new Font("맑은 고딕", Font.BOLD, 25);
	Font font = new Font("맑은 고딕", Font.BOLD, 20);
	Font normalFont = new Font("맑은 고딕", Font.BOLD, 14);

	Calendar calendar = Calendar.getInstance();
	// 1월 ~ 12월 설정
	String[] Month = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
	JComboBox<String> Month1comboBox = new JComboBox<String>(Month);
	String[] Year = new String[101];
	JComboBox<String> Month2comboBox = new JComboBox<String>(Month); // 월
	JComboBox<String> Year1comboBox = new JComboBox<String>(); // 첫번째 연도
	JComboBox<String> Year2comboBox = new JComboBox<String>(); // 두번째 연도
	PatientDAO dao = new PatientDAO();
	static String Num = "";
	static String ID = "";
	static String Name = "";
	static String Dept = "";

	public PatientFrame() {
		// frame 설정
		setSize(1100, 650);
		setTitle("환자 관리");
		setLocation(500, 200);
		setIconImage(symbol_icon.getImage());
		init();
		start();
		setVisible(true);
		setResizable(false);
	}

	private void init() {
		// frame 구성
		container.setLayout(new BorderLayout());
		container.add("West", panel_west);
		container.add("Center", panel_center);
		container.add("North", center_north);
		container.add("East", panel_east);
		container.add("South", patientmemo_panel);

		// 패널 서쪽
		panel_west.setLayout(new BorderLayout());
		panel_west.add("North", labelTitle);
		labelTitle.setFont(fonttitle);
		panel_west.add("Center", west_up_center);

		// 패널 남쪽
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);
		TitledBorder titledBorder2 = new TitledBorder("메모 사항");
		patientmemo_panel.setLayout(new BorderLayout());
		patientmemo_panel.add(textField_memo);
		patientmemo_panel.setBorder(new CompoundBorder(bevelBorder, titledBorder2));

		west_up_center.setLayout(new GridLayout(3, 1));
		// 이미지 크기 조절
		int width = 130; // 너비
		int height = 130; // 높이
		symbol = new ImageIcon(symbol.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
		JLabel labelsymbol = new JLabel(symbol);
		west_up_center.add(labelsymbol);
		west_up_center.add(panelOutput);
		west_up_center.add(button2);
		panelOutput.setLayout(new GridLayout(4, 1));
		panelOutput.add(panelNum);
		panelOutput.add(panelId);
		panelOutput.add(panelName);
		panelOutput.add(panelDept);

		// 사원번호
		panelNum.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNum.add(labelNum);
		panelNum.add(labelNum2);
		// 사용자 ID
		panelId.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelId.add(labelId);
		panelId.add(labelId2);
		// 사용자 명
		panelName.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelName.add(labelName);
		panelName.add(labelName2);
		// 부서 명
		panelDept.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDept.add(labelDept1);
		panelDept.add(labelDept2);

		labelNum2.setText(Num);
		labelId2.setText(ID);
		labelName2.setText(Name);
		labelDept2.setText(Dept);

		labelNum.setFont(font);
		labelId.setFont(font);
		labelName.setFont(font);
		labelDept1.setFont(font);
		labelNum2.setFont(normalFont);
		labelId2.setFont(normalFont);
		labelName2.setFont(normalFont);
		labelDept2.setFont(normalFont);

		// 패널 동쪽
		panel_east.setLayout(new BorderLayout());
		panel_east.add("Center", patient_input_panel);
		panel_east.add("South", patient_button_panel);
		patient_button_panel.setLayout(new FlowLayout());
		patient_button_panel.add(inputButton);
		patient_button_panel.add(updataButton);
		patient_button_panel.add(recordButton);
		patient_button_panel.add(button2);		// 로그아웃
		TitledBorder titledBorder = new TitledBorder("환자 등록");
		patient_input_panel.setBorder(new CompoundBorder(bevelBorder, titledBorder));
		patient_input_panel.setLayout(new GridLayout(6, 1));
		patient_input_panel.add(patientnum_panel);
		patient_input_panel.add(patientname_panel);
		patient_input_panel.add(patientphone_panel);
		patient_input_panel.add(patientemail_panel);
		patient_input_panel.add(patientaddress_panel);
		patient_input_panel.add(patientvisit_panel);

		patientnum_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		patientnum_panel.add(patient_num);
		patientnum_panel.add(textField_num);
		patient_num.setFont(normalFont);

		patientname_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		patientname_panel.add(patient_name);
		patientname_panel.add(textField_name);
		patientname_panel.setFont(normalFont);

		patientphone_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		patientphone_panel.add(patient_phone);
		patientphone_panel.add(textField_phone);
		patientphone_panel.setFont(normalFont);

		patientemail_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		patientemail_panel.add(patient_email);
		patientemail_panel.add(textField_email);
		patientemail_panel.setFont(normalFont);

		patientaddress_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		patientaddress_panel.add(patient_address);
		patientaddress_panel.add(textField_address);
		patientaddress_panel.setFont(normalFont);

		patientvisit_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		patientvisit_panel.add(patient_visit);
		patientvisit_panel.add(textField_visit);
		patientvisit_panel.setFont(normalFont);

		int year = calendar.getWeekYear() - 100;
		for (int i = 0; i < Year.length; i++) {
			Year1comboBox.insertItemAt(String.valueOf(year) + "년", i);
			Year2comboBox.insertItemAt(String.valueOf(year) + "월", i);
			year++;
		}

		// panel_North
		panel_center.setLayout(new BorderLayout());
		panel_center.add("North", title_panel);
		title_panel.setLayout(new GridLayout(2, 1));
		center_center.setLayout(new BorderLayout());
		center_north.add("North", date_panel);
		date_panel.setLayout(new GridLayout(4, 1));

		title_panel.setLayout(new BorderLayout());
		title_panel.add("North", label);

		title_panel.add("Center", iconpanel);
		iconpanel.setLayout(new FlowLayout());
		// 년
		iconpanel.add(Year1comboBox);
		// 월
		iconpanel.add(Month1comboBox);

		// comboBox 크기 설정
		Dimension boxsize = new Dimension(65, 20); // 년
		Year1comboBox.setPreferredSize(boxsize);
		Month1comboBox.setPreferredSize(boxsize);

		iconpanel.add(blank);

		// 년
		iconpanel.add(Year2comboBox);
		// 월
		iconpanel.add(Month2comboBox);

		Year2comboBox.setPreferredSize(boxsize);
		Month2comboBox.setPreferredSize(boxsize);
		
		
		iconpanel.add(button); // 조회 버튼
		iconpanel.add(button3); // 초기화
		label.setFont(fonttitle);

		// panel_Center_North
		center_north.setLayout(new BorderLayout());
		date_panel.setLayout(new GridLayout(2, 1));
		date_panel.add(label1);

		// panel_Center_Center
		center_center.setLayout(new BorderLayout());
		center_center.setBorder(new LineBorder(Color.GRAY, 3));
		panel_center.add("Center", scrollPane);

		Year1comboBox.setSelectedIndex(0);
		Month1comboBox.setSelectedIndex(0);
		Year2comboBox.setSelectedIndex(100);
		Month2comboBox.setSelectedIndex(11);
	}

	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		button.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		inputButton.addActionListener(this);
		updataButton.addActionListener(this);
		recordButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button2) { // 로그아웃
			new LoginFrame();
			dispose();
		} else if(e.getSource() == button3) {		// 테이블 초기화
			
			for (int i = 0; i < table.getRowCount(); i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
					table.setValueAt("", i, j);
				}
			}
		} else if (e.getSource() == button) {
			List<PatientDTO> list = new ArrayList<PatientDTO>();
			String year1 = (String) Year1comboBox.getSelectedItem();
			year1 = year1.substring(0, year1.length()-1);
			String year2 = (String) Year2comboBox.getSelectedItem();
			year2 = year2.substring(0, year2.length()-1);
			String month1 = (String) Month1comboBox.getSelectedItem();
			month1 = month1.substring(0, month1.length()-1);
			String month2 = (String) Month2comboBox.getSelectedItem();
			month2 = month2.substring(0, month2.length()-1);
			list = dao.selectAll2(month1, month2, year1, year2);
			for (int i = 0; i < list.size(); i++) {
				PatientDTO dto1 = list.get(i);
				// (2, 2) 데이터 출력
				tablemodel.setValueAt(dto1.getNumber(), i, 0);
				tablemodel.setValueAt(dto1.getName(), i, 1);
				tablemodel.setValueAt(dto1.getPhone(), i, 2);
				tablemodel.setValueAt(dto1.getEmail(), i, 3);
				tablemodel.setValueAt(dto1.getAddress(), i, 4);
				tablemodel.setValueAt(dto1.getVisitdate(), i, 5);
				tablemodel.setValueAt(dto1.getMemo(), i, 6);
			}
		} else if (e.getSource() == inputButton) {
			PatientDAO dao = new PatientDAO();
			int number = Integer.parseInt(textField_num.getText());
			String name = textField_name.getText();
			String phone = textField_phone.getText();
			String email = textField_email.getText();
			String address = textField_address.getText();
			String visitdate = textField_visit.getText();
			String memo = textField_memo.getText();
			PatientDTO dto = new PatientDTO(number, name, phone, email, address, visitdate, memo);
			int result1 = 0;
			if (dao.NumCheck(number) != 0) {	// 환자 번호 중복검사
				JOptionPane.showConfirmDialog(this, "중복된 환자번호입니다", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return; // 중복 시 강제 종료
			}
			int result = dao.insert(dto);
			if (result > 0) {
				JOptionPane.showConfirmDialog(this, "정상적으로 입력되었습니다", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(this, "알 수 없는 오류입니다", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == updataButton) {
			PatientDAO dao = new PatientDAO();
			int patientnum = Integer.parseInt(textField_num.getText());
			String name = textField_name.getText();
			String phone = textField_phone.getText();
			String email = textField_email.getText();
			String address = textField_address.getText();
			String visitdate = textField_visit.getText();
			String memo = textField_memo.getText();
			PatientDTO dto = new PatientDTO(patientnum,name,phone,email,address,visitdate,memo);
			int result = dao.update(dto);
			if (patientnum != dto.getNumber()) {
				JOptionPane.showConfirmDialog(this, "환자번호는 변경할 수 없습니다", "Update", JOptionPane.DEFAULT_OPTION,
	                     JOptionPane.ERROR_MESSAGE);
			}
			if (result > 0) {
				JOptionPane.showConfirmDialog(this, "수정성공", "Update", JOptionPane.DEFAULT_OPTION,
	                     JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(this, "수정실패", "Update", JOptionPane.DEFAULT_OPTION,
	                     JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == recordButton) {
			Point point = getLocation(); // frame 위치값 읽어오기
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension dimension1 = toolkit.getScreenSize();  // 모니터의 크기
			Dimension dimension2 = getSize();	// 프레임의 크기
			int x = (int)(dimension1.getWidth() / 2 - dimension2.getWidth()/2);
			int y = (int)(dimension1.getHeight() / 2 - dimension2.getHeight()/2);
			dialog.setLocation(point.x, point.y);
			dialog.setVisible(true);
		}
	}

	public static void patient_login(String username) {
		ID = username;
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		dto = dao.FromStaffinfo(username);
		Num = String.valueOf(dto.getStaffnum());
		Dept = dto.getDept();
		Name = dto.getName();
	}
}
