import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;

public class GUI {
	private JFrame frame;
	ImageIcon frameIconImage;
	private final String frameIconImagePath = "icon.png"; 
	private final int screenSizeX = 1280;
	private final int screenSizeY = 720;
	private final String[] grades = {"1", "2", "3"};
	private final String[] clubs = {"ZerOpen", "RG", "IWOP", "AppplePie", "AnA",
									"EDCAN", "SIRI", "SHARC", "EVOLUTION", "자의누리",
									"MIR", "TATE", "Vfriends", "아우내"};
	private final String[] departments = {"소프트웨어과", "콘텐츠디자인과"};
	private final String[] classes = {"4", "5", "6", "10", "11", "12"};
	private final String[] interests = {"X", "게임", "음악", "개발", "영화 감상", "자전거 타기", "운동 하기",
										"독서", "TV 보기", "요리", "SNS 보기", "잠자기"};
	
	public GUI() {
		frameIconImage = new ImageIcon(frameIconImagePath);
		this.frame = new JFrame("Make Friends In Sunrin");
	}
	
	public void SetFrame() {
		this.frame.setSize(screenSizeX, screenSizeY);
		this.frame.setLayout(null);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false); 
		this.frame.setIconImage(frameIconImage.getImage());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);    
	}
	
	JPanel MakeTextField(String s, int x, int y, int sizeX, int sizeY) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(s);
		JTextField txtfield = new JTextField(10);
		panel.setBounds(x, y, sizeX, sizeY);
		panel.add(label);
		panel.add(txtfield);
		return panel;
	}
	
	JPanel MakeComboBox(String s, String[] list, int x, int y, int sizeX, int sizeY) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(s);
		JComboBox<String> combo = new JComboBox<String>(list);
		panel.setBounds(x, y, sizeX, sizeY);
		panel.add(label);
		panel.add(combo);
		return panel;
	}
	
	String GetText(JPanel panel) {
		for (Component component : panel.getComponents()) {
			if (component instanceof JTextField) {
				return ((JTextField) component).getText();
			}
			else if (component instanceof JComboBox<?>) {
				return (String) ((JComboBox<?>) component).getItemAt(((JComboBox<?>) component).getSelectedIndex());
			}
		}
		return null;
	}
	
	void CheckOverlap() {
		
	}
	
	public void AddUserScreen() {
		final int intervalX = 6;
		final int intervalY = 8;
		final int boxSizeX = 300;
		final int boxSizeY = 50;
		
		JPanel enterUserID = MakeTextField("ID 입력 : ", screenSizeX / intervalX, screenSizeY / intervalY, boxSizeX, boxSizeY);
		JPanel enterUserName = MakeTextField("이름 입력 : ", screenSizeX / intervalX, (screenSizeY / intervalY) * 2, boxSizeX, boxSizeY);
		JPanel selectUserGrade = MakeComboBox("학년 입력 : ", grades, screenSizeX / intervalX, (screenSizeY / intervalY) * 3, boxSizeX, boxSizeY);
		JPanel selectUserClub = MakeComboBox("동아리 입력 : ", clubs, screenSizeX / intervalX, (screenSizeY / intervalY) * 4, boxSizeX, boxSizeY);
		JPanel selectUserDepartment = MakeComboBox("학과 입력 : ", departments, screenSizeX / intervalX, (screenSizeY / intervalY) * 5, boxSizeX, boxSizeY);
		JPanel selectUserClass = MakeComboBox("반 입력 : ", classes, screenSizeX / intervalX, (screenSizeY / intervalY) * 6, boxSizeX, boxSizeY);
		ArrayList<JPanel> selectUserInterests = new ArrayList<JPanel>();
		for (int i = 1; i <= 5; i++) {
			selectUserInterests.add(MakeComboBox("관심사 입력 " + i + " : ", interests, screenSizeX - screenSizeX / intervalX - boxSizeX, (screenSizeY / intervalY) * i, boxSizeX, boxSizeY));
		}
		JButton createUser = new JButton("유저 생성");
		
		createUser.setBounds(screenSizeX - screenSizeX / intervalX - boxSizeX + 50, (screenSizeY / intervalY) * 6, 200, 25);
		
		createUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean exit = false;
				String userID = GetText(enterUserID);
				String userName = GetText(enterUserName);
				String userGrade = GetText(selectUserGrade);
				String userClub = GetText(selectUserClub);
				String userDepartment = GetText(selectUserDepartment);
				String userClass = GetText(selectUserClass);
				ArrayList<String> userInterests = new ArrayList<String>();
				for (int i = 0; i < 5; i++) {
					String s = GetText(selectUserInterests.get(i));
					if (userInterests.contains(s) && !s.equals("X")) {
						JOptionPane.showMessageDialog(null, "관심사를 중복 선택 하였는지 확인 해주세요");
						exit = true;
					}
					userInterests.add(s);
				}
				
				if (userID.equals("") || userName.equals("")) {
					JOptionPane.showMessageDialog(null, "모든 사항을 입력하였는지 확인 해주세요");
					exit = true;
				}
				
				if (exit) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "유저 생성 성공");
				
				Manager.AddUser(userID, userName, userGrade, userClub, userDepartment, userClass, userInterests);
				Manager.PrintAllUsers();
			}
		});

		this.frame.add(enterUserID);
		this.frame.add(enterUserName);
		this.frame.add(selectUserGrade);
		this.frame.add(selectUserClub);
		this.frame.add(selectUserDepartment);
		this.frame.add(selectUserClass);
		for (int i = 0; i < 5; i++) {
			this.frame.add(selectUserInterests.get(i));
		}
		this.frame.add(createUser);
		
	    this.SetFrame();
	}
}