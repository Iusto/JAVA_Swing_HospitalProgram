package record;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RecordDialog extends JDialog implements ActionListener {
	// 컨테이너
	Container container = getContentPane();
	Calendar calendar = Calendar.getInstance();
	JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	String[] str = { "진료번호", "환자번호", "직원코드", "담당의학과", "진료형태", "날짜" };
	DefaultTableModel tableModel = new DefaultTableModel(str, 100);
	JTable table = new JTable(tableModel);
	JScrollPane tableoutput = new JScrollPane(table);

	// JPanel North 구성
	JPanel PanelNorth = new JPanel();
	JPanel JpanelCenter = new JPanel();

	JPanel PanelNorthWB = new JPanel();
	JLabel labelblankW = new JLabel();
	JPanel PanelNorthCW = new JPanel();
	JLabel labelnumA = new JLabel("진료 번호");
	JTextField textlnumA = new JTextField(10);
	JLabel labelnumB = new JLabel("환자 번호");
	JTextField textlnumB = new JTextField(10);
	JLabel labelnameA = new JLabel("직원 코드");
	JTextField textlnameA = new JTextField(10);

	JPanel PanelNorthCE = new JPanel();
	JLabel labelday = new JLabel("진료 날짜");
	JTextField textlday = new JTextField(10);
	JLabel labelstyle = new JLabel("진료 형태");
	JTextField textlstyle = new JTextField(10);
	JLabel labelcon = new JLabel("담당의학과");
	JTextField textlcon = new JTextField(10);
	JPanel PanelNorthEB = new JPanel();
	JLabel labelblankE = new JLabel();

	JPanel JpanelWest = new JPanel();
	JPanel JPanelSearch = new JPanel();
	JPanel JpanelWestscr = new JPanel();
	JPanel JpanelLogo = new JPanel();
	JPanel JpanelWestBlank2 = new JPanel();
	JPanel Panelnum = new JPanel();
	JPanel Panelname = new JPanel();
	JPanel titlePanel = new JPanel();

	JPanel PanelSouth = new JPanel();

	// JLabel West 구성
	JLabel labelnum = new JLabel("진료 번호");
	JLabel labelnum2 = new JLabel("환자 번호");
	JLabel labelnum3 = new JLabel("직원 코드");
	JLabel labelblank = new JLabel("");
	JLabel labelLogo = new JLabel("");
	JPanel JpanelWestCenter = new JPanel();
	JButton SearchButton = new JButton("조회");
	JButton inputButton = new JButton("입력");
	JButton outputButton = new JButton("출력");
	JButton symp = new JButton();

	// 텍스트 필드 & Area 구성
	JTextField jtextnum = new JTextField(10); // 진료번호텍스트
	JTextField jtextnum2 = new JTextField(10); // 환자번호텍스트
	JTextField jtextnum3 = new JTextField(10); // 직원코드텍스트
	JTextArea jtextArea = new JTextArea();
	JScrollPane JscrollPane = new JScrollPane(jtextArea);

	// 선언
	ImageIcon icon1 = new ImageIcon(getClass().getResource("/img/symbol.png"));
	ImageIcon icon2 = new ImageIcon(getClass().getResource("/img/symbol_icon.png"));
	RecordDAO dao = new RecordDAO();

	public RecordDialog() {
		setSize(1000, 600);
		setTitle("아이티뱅크 병원");
		setLocation(600, 150);
		// 타이틀바에 이미지 넣기
		setIconImage(icon2.getImage());
		init();
		start();
		setResizable(true); // 화면크기 변경 가능 설정
	}

	private void init() {
		// frame
		container.setLayout(new BorderLayout());
		container.add("West", JpanelWest);
		container.add("Center", JpanelCenter);
		JpanelCenter.setLayout(new BorderLayout());
		JpanelCenter.add("North", PanelNorth);
		JpanelCenter.add("Center", tableoutput);
		container.add("South", PanelSouth);
		PanelNorth.setLayout(new GridLayout(0, 4));
		PanelNorth.add(PanelNorthWB);
		PanelNorth.add(PanelNorthCW);
		PanelNorth.add(PanelNorthCE);
		PanelNorth.add(PanelNorthEB);

		PanelNorthWB.setLayout(new FlowLayout());
		PanelNorthWB.add(labelblankW);

		PanelNorthCW.setLayout(new GridLayout(4, 2));
		PanelNorthCW.add(labelnumA);
		PanelNorthCW.add(textlnumA);
		PanelNorthCW.add(labelnumB);
		PanelNorthCW.add(textlnumB);
		PanelNorthCW.add(labelnameA);
		PanelNorthCW.add(textlnameA);

		PanelNorthCE.setLayout(new GridLayout(4, 2));
		PanelNorthCE.add(labelday);
		PanelNorthCE.add(textlday);
		PanelNorthCE.add(labelstyle);
		PanelNorthCE.add(textlstyle);
		PanelNorthCE.add(labelcon);
		PanelNorthCE.add(textlcon);

		PanelNorthEB.setLayout(new FlowLayout());
		PanelNorthEB.add(labelblankE);

		// West North1 직원 검색 패널
		JpanelWest.setLayout(new GridLayout(4, 1));
		JpanelWest.add(JPanelSearch);
		JPanelSearch.setLayout(new GridLayout(4, 2));
		JPanelSearch.add(labelnum);
		JPanelSearch.add(jtextnum);
		JPanelSearch.add(labelnum2);
		JPanelSearch.add(jtextnum2);
		JPanelSearch.add(labelnum3);
		JPanelSearch.add(jtextnum3);
		JPanelSearch.add(labelblank);
		JPanelSearch.add(SearchButton);
		//
		JpanelWest.add(JpanelWestscr);
		JpanelWestscr.setLayout(new BorderLayout());
		JpanelWestscr.add("Center", JscrollPane);
		JscrollPane.setBackground(Color.lightGray);
		jtextArea.setBackground(Color.black);
		jtextArea.enable(false);

		JpanelWest.add(JpanelLogo);
		JpanelLogo.setLayout(new FlowLayout());
		int width = 100; // 너비
		int height = 100; // 높이
		icon2 = new ImageIcon(icon2.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
		JLabel labelsymbol = new JLabel(icon2);
		JpanelLogo.add(labelsymbol);

		JpanelWest.add(JpanelWestBlank2);
		JpanelWestBlank2.setLayout(new FlowLayout());

		PanelSouth.setLayout(new FlowLayout());
		PanelSouth.add(inputButton);
		PanelSouth.add(outputButton);

	}

	private void start() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		SearchButton.addActionListener(this);
		inputButton.addActionListener(this);
		outputButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SearchButton) {
			String result = "";
			if (!jtextnum.getText().equals("") && !jtextnum2.getText().equals("") && !jtextnum3.getText().equals("")) {
				int treatnum = Integer.parseInt(jtextnum.getText());
				int patientnum = Integer.parseInt(jtextnum2.getText());
				int staffnum = Integer.parseInt(jtextnum3.getText());
				RecordDTO dto = dao.search(treatnum, patientnum, staffnum);
				result = dto.printTitle() + dto.getTreatmentnum() + "\t" + dto.getPatientnum() + "\t" + dto.getStaffnum();
			} else {
				result = "데이터를 입력하세요";
			}
			jtextArea.setText(result);
			jtextnum.setText("");
			jtextnum2.setText("");
			jtextnum3.setText("");

		} else if (e.getSource() == inputButton) {
			int treatnum = Integer.parseInt(textlnumA.getText());
			int patientnum = Integer.parseInt(textlnumB.getText());
			int staffnum = Integer.parseInt(textlnameA.getText());
			String date = textlday.getText();
			String treat_style = textlstyle.getText();
			String dept = textlcon.getText();
			RecordDTO dto = new RecordDTO(treatnum, patientnum, staffnum, treat_style, dept, date);
			int result = dao.insert(dto);
			if (result > 0) {
				JOptionPane.showConfirmDialog(this, "정상적으로 입력되었습니다", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showConfirmDialog(this, "저장 실패", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == outputButton) {
			List<RecordDTO> list = dao.select();
			for (int i = 0; i < list.size(); i++) {
				RecordDTO dto = list.get(i);
				// (2, 2) 데이터 출력
				tableModel.setValueAt(dto.getTreatment(), i, 0);
				tableModel.setValueAt(dto.getPatientnum(), i, 1);
				tableModel.setValueAt(dto.getStaffnum(), i, 2);
				tableModel.setValueAt(dto.getDept(), i, 3);
				tableModel.setValueAt(dto.getTreatment(), i, 4);
				tableModel.setValueAt(dto.getVisitdate(), i, 5);
			}
		}
	}
}
