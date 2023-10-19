package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import admin.Medical_ChartFrame;
import memberjoin.MemberDialog;
import patient.PatientFrame;

public class LoginFrame extends JFrame implements ActionListener {
	Container container = getContentPane();
    MemberDialog dialog = new MemberDialog(); //(this, "회원가입");
    Container dialogContainer = dialog.getContentPane();
	
	// Panel
	JPanel panelSymbol = new JPanel();
	JPanel panelInput = new JPanel();
	JPanel panelIDPW = new JPanel();
	JPanel panelButton = new JPanel();
	JPanel panelWest = new JPanel();
	JPanel panelEast = new JPanel();
	JPanel Titlepanel = new JPanel();

	// Label
	JLabel labelSymbol = new JLabel("아이티뱅크병원");
	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");

	// TextField
	PlaceholderTextField textFieldID = new PlaceholderTextField();
	PlaceholderTextField2 textFieldPW = new PlaceholderTextField2();

	// Button
	JButton loginButton = new JButton("LOGIN");
	JButton joinButton = new JButton("회원가입");

	// 폰트
	Font font = new Font("맑은 고딕", Font.BOLD, 35);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 20);
	Font hint = new Font("맑은 고딕", Font.BOLD, 14);

	// 선언
	LoginDAO dao = new LoginDAO();
    ImageIcon icon1 = new ImageIcon(getClass().getResource("/img/symbol.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("/img/symbol_icon.png"));

	public LoginFrame() {
		// frame 구성
		setSize(400, 600);
		setTitle("로그인 LOGIN");
		// 타이틀바에 이미지 넣기
		setIconImage(icon2.getImage());
		// 모니터 한가운데 위치 지정
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension1 = toolkit.getScreenSize();  // 모니터의 크기
		Dimension dimension2 = getSize();	// 프레임의 크기
		int x = (int)(dimension1.getWidth() / 2 - dimension2.getWidth()/2);
		int y = (int)(dimension1.getHeight() / 2 - dimension2.getHeight()/2);
		setLocation(x, y);
		setResizable(false);
		init();
		start();
		setVisible(true);
	}

	private void init() {
		// 1) Frame 구성
		container.setLayout(new GridLayout(2, 1));
		container.add(panelSymbol);
		container.add(panelInput);

		// 이미지 크기 조절
		int width = 225; // 너비
		int height = 225; // 높이
		icon1 = new ImageIcon(icon1.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
		JLabel label = new JLabel(icon1);

		// 2) 병원 마크
		panelSymbol.setLayout(new BorderLayout());
		panelSymbol.add("Center", label);
		labelSymbol.setFont(font);
		labelSymbol.setHorizontalAlignment(SwingConstants.CENTER);

		// 3) ID/PW 입력칸, 로그인 버튼 패널
		panelInput.setLayout(new BorderLayout());
		panelInput.add("North", Titlepanel);
		Titlepanel.setLayout(new GridLayout(2, 1));
		Titlepanel.add(labelSymbol);
		panelInput.add("Center", panelIDPW);
		panelInput.add("South", panelButton);
		panelInput.add("West", panelWest);
		panelInput.add("East", panelEast);

		// 4) ID/PW 패널 구성
		panelIDPW.setLayout(new GridLayout(3, 1, 0, 10));
		panelIDPW.add(textFieldID);
		panelIDPW.add(textFieldPW);

		// 힌트 텍스트
		textFieldID.setPlaceholder("아이디(ID)");
		textFieldID.setPlaceholderFont(hint); // 폰트 설정
		textFieldPW.setPlaceholder("비밀번호(PW)");
		textFieldPW.setPlaceholderFont(hint); // 폰트 설정

		// 5) Login 버튼
		panelButton.setLayout(new GridLayout(1, 2, 5, 10));
		panelButton.add(loginButton);
		panelButton.add(joinButton);
		loginButton.setFont(font2);
		joinButton.setFont(font2);
		loginButton.setPreferredSize(new Dimension(150, 50));
	}

	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginButton.addActionListener(this);
		joinButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String id = textFieldID.getText();
			String pw = textFieldPW.getText();

			// 입력검사
			if (id.equals("")) {
				JOptionPane.showConfirmDialog(this, "아이디를 입력하세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (pw.equals("")) {
				JOptionPane.showConfirmDialog(this, "비밀번호를 입력하세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			} // 아이디, 비밀번호 모두 입력했는지 확인하고 아이디와 패스워드가 맞는 것이 있는지 확인
			if (!id.equals("") && !pw.equals("")) {
				LoginDTO admin = dao.admincheck(id, pw);	// 관리자인지 판단, 아니라면 일반 유저인지 판단
				if (admin != null) {
					// 로그인성공, 관리자 프로그램으로 실행
					dispose();
					Medical_ChartFrame medical_ChartFrame = new Medical_ChartFrame();
					return;
				}
				LoginDTO user = dao.login(id, pw);	
				if (user != null) {
					// 로그인성공, 일반유저 프로그램으로 실행
					dispose();
					PatientFrame.patient_login(id);
					PatientFrame patient = new PatientFrame();
					return;
				} else if (dao.idcheck(id) == null) {
					JOptionPane.showConfirmDialog(this, "등록된 아이디가 없습니다", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				} else {
					// System.out.println("로그인 실패"); //실패했다면 로그인실패 팝업창을 띄움
					JOptionPane.showConfirmDialog(this, "아이디나 패스워드가 틀립니다", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if (e.getSource() == joinButton) { // 회원가입 버튼
			Point point = getLocation(); // frame 위치값 읽어오기
			dialog.setLocation(point);
			dialog.setVisible(true);
		}
	}
}
