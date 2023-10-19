package memberjoin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MemberDialog extends JDialog implements ActionListener {
	Container container = getContentPane();

	// Panel
	JPanel panelTitle = new JPanel();
	JPanel panelMain = new JPanel();
	JPanel panelID = new JPanel();
	JPanel panelEmail = new JPanel();
	JPanel panelWest = new JPanel();
	JPanel panelEast = new JPanel();
	JPanel info1 = new JPanel();
	JPanel info2 = new JPanel();
	JPanel info3 = new JPanel();
	JPanel panelPW1 = new JPanel();
	JPanel panelPW2 = new JPanel();
	JPanel paneldept = new JPanel();
	JPanel panelnum = new JPanel();

	// Label
	JLabel labelSymbol = new JLabel("회원가입");
	JLabel LabelInfo1 = new JLabel("개인 정보 수집 및 이용에 대한 동의");
	JLabel must1 = new JLabel("(필수)");
	JLabel LabelInfo2 = new JLabel("병원 직원만 가입이 가능합니다");
	JLabel must2 = new JLabel("(필수)");
	JLabel LabelInfo3 = new JLabel("개인 정보 제3자 제공 동의");
	JLabel notmust3 = new JLabel("(선택)");
	JLabel Labeldept = new JLabel("담당 부서  ");

	// TextField
	PlaceholderTextField textFieldID = new PlaceholderTextField(); // 아이디
	PlaceholderTextField2 textFieldPW1 = new PlaceholderTextField2(); // 비밀번호
	PlaceholderTextField2 textFieldPW2 = new PlaceholderTextField2(); // 비밀번호 재입력
	PlaceholderTextField textFieldNum = new PlaceholderTextField(); // 주민번호
	PlaceholderTextField2 textFieldNum2 = new PlaceholderTextField2(); // 주민번호 뒷자리
	PlaceholderTextField textFieldName = new PlaceholderTextField(); // 이름
	PlaceholderTextField textFieldPhone = new PlaceholderTextField(); // 전화번호
	PlaceholderTextField textFieldEmail = new PlaceholderTextField(); // 이메일

	// Button
	JButton joinButton = new JButton("동의하고 가입하기");
	JButton distinctButton = new JButton("중복 확인");

	// 폰트
	Font font1 = new Font("맑은 고딕", Font.BOLD, 35);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 18);

	// 체크박스
	JCheckBox box1 = new JCheckBox();
	JCheckBox box2 = new JCheckBox();
	JCheckBox box3 = new JCheckBox();

	// 선언
	MemberDAO dao = new MemberDAO();
	Font font = new Font("맑은 고딕", Font.BOLD, 20);
	boolean pwflag = false; // 비밀번호, 비밀번호 재사용이 서로 같은지 판단
	boolean idflag = false; // 아이디 중복검사 판단
	String[] strs = { "원무과", "전산과", "내과", "안과", "이비인후과", "산부인과", "흉부외과", "신경외과", "소아청소년과", "병리과" };
	JComboBox<String> comboBox = new JComboBox<String>(strs);
	ImageIcon icon1 = new ImageIcon(getClass().getResource("/img/symbol.png"));
	ImageIcon icon2 = new ImageIcon(getClass().getResource("/img/symbol_icon.png"));

	public MemberDialog() {
		// frame 구성
		setSize(500, 850);
		setTitle("로그인 LOGIN");
		setResizable(false);
		// 타이틀바에 이미지 넣기
		setIconImage(icon1.getImage());
		init();
		start();
	}

	public void initialize() {
		textFieldID.setText("");
		textFieldPW1.setText("");
		textFieldPW2.setText("");
		textFieldName.setText("");
		textFieldNum.setText("");
		textFieldNum2.setText("");
		textFieldPhone.setText("");
		textFieldEmail.setText("");
		box1.setSelected(false);
		box2.setSelected(false);
		box3.setSelected(false);
		comboBox.setSelectedIndex(0);
		distinctButton.setEnabled(true);
		textFieldID.setEditable(true);
		textFieldID.setFocusable(true);
		boolean pwflag = false; // 비밀번호, 비밀번호 재사용이 서로 같은지 판단
		boolean idflag = false; // 아이디 중복검사 판단
	}

	private void init() {
		// 1) Frame 구성

		container.setLayout(new BorderLayout());
		container.add("North", panelTitle);
		container.add("West", panelWest);
		container.add("East", panelEast);
		container.add("Center", panelMain);

		// 이미지 크기 조절
		int width = 80; // 너비
		int height = 80; // 높이
		icon1 = new ImageIcon(icon1.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
		JLabel label = new JLabel(icon1);

		// 회원가입 타이틀 & 병원마크
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelTitle.add(labelSymbol);
		panelTitle.add(label);
		labelSymbol.setFont(font1);

		panelMain.setLayout(new GridLayout(12, 1, 0, 10));
		panelMain.add(panelID);
		panelID.setLayout(new BorderLayout());

		panelID.add("Center", textFieldID);
		panelID.add("East", distinctButton);

		panelMain.add(panelPW1);
		panelMain.add(panelPW2);
		panelPW1.setLayout(new BorderLayout());
		panelPW2.setLayout(new BorderLayout());
		panelPW1.add(textFieldPW1);
		panelPW2.add(textFieldPW2);

		panelMain.add(textFieldName);
		panelMain.add(panelnum);
		panelnum.setLayout(new GridLayout(1, 2, 10, 0));
		panelnum.add(textFieldNum);
		panelnum.add(textFieldNum2);
		panelMain.add(textFieldPhone);
		panelMain.add(textFieldEmail);
		panelMain.add(paneldept);
		paneldept.setLayout(new BorderLayout());
		paneldept.add("West", Labeldept);
		paneldept.add("Center", comboBox);
		Labeldept.setFont(font2);
		Labeldept.setForeground(Color.GRAY);
		comboBox.setFont(font2);

		panelMain.add(info1);
		panelMain.add(info2);
		panelMain.add(info3);
		panelMain.add(joinButton);

		info1.setLayout(new FlowLayout(FlowLayout.LEFT));
		info2.setLayout(new FlowLayout(FlowLayout.LEFT));
		info3.setLayout(new FlowLayout(FlowLayout.LEFT));

		info1.add("West", box1);
		info2.add("West", box2);
		info3.add("West", box3);

		info1.add(LabelInfo1);
		info2.add(LabelInfo2);
		info3.add(LabelInfo3);

		info1.add(must1);
		must1.setForeground(Color.red);
		info2.add(must2);
		must2.setForeground(Color.red);
		info3.add(notmust3);
		notmust3.setForeground(Color.cyan);

		LabelInfo1.setFont(font2);
		LabelInfo2.setFont(font2);
		LabelInfo3.setFont(font2);
		must1.setFont(font2);
		must2.setFont(font2);
		notmust3.setFont(font2);
		distinctButton.setFont(font2);
		joinButton.setFont(font2);

		// 힌트 텍스트
		textFieldID.setPlaceholder("아이디(필수) 최소 5자리이상");
		textFieldID.setPlaceholderFont(font); // 폰트 설정
		textFieldPW1.setPlaceholder("비밀번호(필수)");
		textFieldPW1.setPlaceholderFont(font); // 폰트 설정
		textFieldPW2.setPlaceholder("비밀번호 재입력");
		textFieldPW2.setPlaceholderFont(font); // 폰트 설정
		textFieldName.setPlaceholder("이름 (필수)");
		textFieldName.setPlaceholderFont(font); // 폰트 설정
		textFieldNum.setPlaceholder("주민번호 앞자리");
		textFieldNum.setPlaceholderFont(font); // 폰트 설정
		textFieldNum2.setPlaceholder("주민번호 뒷자리");
		textFieldNum2.setPlaceholderFont(font); // 폰트 설정
		textFieldPhone.setPlaceholder("휴대폰번호(필수) Ex)010-1234-5678");
		textFieldPhone.setPlaceholderFont(font); // 폰트 설정
		textFieldEmail.setPlaceholder("이메일주소(필수, 아이디 찾기에 사용됩니다)");
		textFieldEmail.setPlaceholderFont(font); // 폰트 설정
	}

	private void start() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		distinctButton.addActionListener(this);
		joinButton.addActionListener(this);
		textFieldID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldID.getText().equals("아이디를 입력하세요")) {
					textFieldID.setText("");
					textFieldID.setForeground(Color.BLACK);
				}
				;
			}
		});
		textFieldName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldName.getText().equals("이름을 입력하세요")) {
					textFieldName.setText("");
					textFieldName.setForeground(Color.BLACK);
				}
				;
			}
		});
		textFieldPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldPhone.getText().equals("휴대폰 번호를 입력하세요")) {
					textFieldPhone.setText("");
					textFieldPhone.setForeground(Color.BLACK);
				}
				;
			}
		});
		textFieldEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldEmail.getText().equals("이메일을 입력하세요")) {
					textFieldEmail.setText("");
					textFieldEmail.setForeground(Color.BLACK);
				}
				;
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == distinctButton) { // 아이디 중복 검사 버튼
			String id = textFieldID.getText();
			if (id.equals("") || id.equals("아이디를 입력하세요")) {
				textFieldID.setText("아이디를 입력하세요");
				textFieldID.setForeground(Color.red);
				return;
			}
			if (id.length() < 5) { // 아이디가 너무 짧은 경우
				JOptionPane.showConfirmDialog(this, "최소 5자리이상이여야 합니다", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 아이디 중복 검사
			MemberDTO dto = dao.IDdistinct(id);
			if (dto != null) { // 아이디 중복 시 다이얼로그 띄움
				JOptionPane.showConfirmDialog(this, id + "는 중복된 아이디 입니다", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			else { // 사용 가능한 아이디 (중복 X)
				JOptionPane.showConfirmDialog(this, id + "는 사용 가능한 아이디 입니다", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				textFieldID.setEditable(false);
				textFieldID.setFocusable(false);
				distinctButton.setEnabled(false);
				idflag = true;
				// 중복검사를 하고 더이상 데이터를 바꾸지 않도록 비활성화
			}
		}

		if (e.getSource() == joinButton) { // 동의하고 가입하기 버튼

			String id = textFieldID.getText();
			String pw1 = textFieldPW1.getText();
			String pw2 = textFieldPW2.getText();
			String name = textFieldName.getText();
			String num = textFieldNum.getText() + "-" + textFieldNum2.getText(); // 주민번호
			String gender = "";
			int age = 0;
			String Phone = textFieldPhone.getText();
			String email = textFieldEmail.getText();
			String dept = (String) comboBox.getSelectedItem();

			if (id.equals("") || id.equals("아이디를 입력하세요")) {
				textFieldID.setText("아이디를 입력하세요");
				textFieldID.setForeground(Color.red);
				return;
			}
			if (!idflag) { // 중복검사를 안하고 가입버튼 눌렀을 때
				JOptionPane.showConfirmDialog(this, "아이디 중복 검사를 해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (pw1.equals("") || pw1.equals("비밀번호(필수)")) { // 비밀번호칸에 아무것도 입력되지 않았을 때
				JOptionPane.showConfirmDialog(this, "비밀번호를 입력해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (pw1.length() < 8) { // 비밀번호가 너무 짧을 때
				JOptionPane.showConfirmDialog(this, "비밀번호를 최소 8자리 이상 입력해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (pw2.equals("") || pw2.equals("비밀번호 재입력")) { // 비밀번호 재입력칸에 아무것도 입력되지 않았을 때
				JOptionPane.showConfirmDialog(this, "비밀번호 재입력을 입력해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!pw1.equals(pw2)) { // 비밀번호와 비밀번호 재입력의 값이 서로 같지 않을 때
				JOptionPane.showConfirmDialog(this, "비밀번호가 서로 다릅니다", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 여기까지오면 비밀번호는 완벽하게 입력된걸로 판단
			pwflag = true;

			if (name.equals("") || name.equals("이름을 입력하세요")) { // 이름칸 안적었을 때
				textFieldName.setText("이름을 입력하세요");
				textFieldName.setForeground(Color.red);
				return;
			}

			if (textFieldNum.getText().equals("")) { // 주민번호 앞자리를 적지 않았을 시
				JOptionPane.showConfirmDialog(this, "주민번호 앞자리를 입력해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (textFieldNum.getText().length() != 6) { // 잘못된 주민번호 앞자리 시
				JOptionPane.showConfirmDialog(this, "잘못된 주민번호 앞자리 입니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (textFieldNum2.getText().equals("")) { // 주민번호 뒷자리를 적지 않았을 시
				JOptionPane.showConfirmDialog(this, "주민번호 뒷자리를 입력해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (textFieldNum2.getText().length() != 7) { // 잘못된 주민번호 뒷자리 시
				// 잘못된 주민번호 뒷자리 시
				JOptionPane.showConfirmDialog(this, "잘못된 주민번호 뒷자리 입니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (Phone.equals("") || Phone.equals("휴대폰 번호를 입력하세요")) { // 전화번호 안적었을 때
				textFieldPhone.setText("휴대폰 번호를 입력하세요");
				textFieldPhone.setForeground(Color.red);
				return;
			}

			if (email.equals("") || email.equals("이메일을 입력하세요")) { // 이메일 안적었을 때
				textFieldEmail.setText("이메일을 입력하세요");
				textFieldEmail.setForeground(Color.red);
				return;
			}

			if (!box1.isSelected()) {
				JOptionPane.showConfirmDialog(this, "개인 정보 수집 및 동의에 체크해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!box2.isSelected()) {
				JOptionPane.showConfirmDialog(this, "병원 직원인지 체크해주세요", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (idflag && pwflag) {
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				int staffnum = dao.staffnum();
				gender = (Integer.parseInt(textFieldNum2.getText().substring(0, 1)) == 1
						|| Integer.parseInt(textFieldNum2.getText().substring(0, 1)) == 3) ? "남자" : "여자";
				age = (Integer.parseInt(textFieldNum2.getText().substring(0, 1)) <= 2)
						? year - (1900 + Integer.parseInt(textFieldNum.getText().substring(0, 2)))
						: year - (2000 + Integer.parseInt(textFieldNum.getText().substring(0, 2)));
				MemberDTO dto = new MemberDTO(staffnum, id, pw2, name, num, gender, age, Phone, email, "사원", dept, "Y");
				int result = dao.insert(dto);
				if (result > 0) {
					JOptionPane.showConfirmDialog(this, "정상적으로 회원가입 되었습니다.", "확인", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					initialize();
					dispose();
				} else {
					JOptionPane.showConfirmDialog(this, "알 수 없는 오류가 발생했습니다", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}