import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.JTable;
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
		
		JPanel mainPanel = new JPanel();
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

		mainPanel.setLayout(null);
		mainPanel.add(enterUserID);
		mainPanel.add(enterUserName);
		mainPanel.add(selectUserGrade);
		mainPanel.add(selectUserClub);
		mainPanel.add(selectUserDepartment);
		mainPanel.add(selectUserClass);
		for (int i = 0; i < 5; i++) {
			mainPanel.add(selectUserInterests.get(i));
		}
		mainPanel.add(createUser);
		
		this.frame.add(mainPanel);
	    this.SetFrame();
	}
	
	public void AddFriends(String userID) {
		JLabel IdLabel = new JLabel(userID);
	}
	
	public void MainScreen() {
		JPanel mainPanel = new JPanel();
		String[] headings = new String[] {"ID", "이름", "학년", "동아리", "학과", "반", "관심사1", "관심사2", "관심사3", "관심사4", "관심사5"};
		String[][] data = new String[Manager.GetUsersSize()][11];
		
		mainPanel.setLayout(null);
		
		JButton newUserButton = new JButton("새로운 유저 생성");
		
		mainPanel.setBackground(Color.WHITE);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(470, 25, 750, 650);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel buttonPanel = new JPanel();	
		JPanel newUserPanel = new JPanel();
		
		buttonPanel.setLayout(null);
		newUserPanel.setLayout(null);
		
		JLabel textLabel = new JLabel("유저 아이디 : ");
		JTextField userIDtf = new JTextField(10);
		
		JButton FriendsButton = new JButton("친구 추가");
		JButton PBButton = new JButton("가능성 계산");
		
		ImageIcon img = new ImageIcon(frameIconImagePath);
		JLabel imgLabel = new JLabel(img);
		
		imgLabel.setBounds(200,100,100,100);
		
		buttonPanel.add(newUserButton);
		newUserPanel.add(textLabel);
		newUserPanel.add(userIDtf);
		newUserPanel.add(FriendsButton);
		newUserPanel.add(PBButton);
		
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setSize(30, 30);
		buttonPanel.add(newUserPanel);
		
		mainPanel.add(buttonPanel);
		mainPanel.add(imgLabel);
		
		for(int i=0;i<Manager.GetUsersSize();i++) {
			User u = Manager.GetUserByIndex(i);
			data[i][0] = u.GetUserID();
			data[i][1] = u.GetUserName();
			data[i][2] = u.GetUserGrade();
			data[i][3] = u.GetUserClub();
			data[i][4] = u.GetUserDepartment();
			data[i][5] = u.GetUserClass();
			data[i][6] = u.GetUserInterest(0);
			data[i][7] = u.GetUserInterest(1);
			data[i][8] = u.GetUserInterest(2);
			data[i][9] = u.GetUserInterest(3);
			data[i][10] = u.GetUserInterest(4);
		}
		
		buttonPanel.setBounds(60, 300, 400, 330);
		newUserButton.setBounds(50, 20, 300, 40);
		
		newUserPanel.setBounds(50, 80, 300, 230);
		newUserPanel.setBackground(Color.WHITE);
		
		textLabel.setBounds(30, 35, 80, 30);
		userIDtf.setBounds(105, 35, 165, 30);
		
		FriendsButton.setBounds(30, 100, 240, 30);
		PBButton.setBounds(30, 165, 240, 30);
		
		JTable table = new JTable(data, headings);
		table.setPreferredScrollableViewportSize(new Dimension(700,600));
		table.setFillsViewportHeight(true);
		
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		newUserPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		tablePanel.setBackground(Color.WHITE);
		tablePanel.add(new JScrollPane(table));
		
		mainPanel.add(tablePanel);
		this.frame.add(mainPanel);
		this.SetFrame();
	}
}