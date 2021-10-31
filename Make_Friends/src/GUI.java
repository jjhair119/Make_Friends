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
									"EDCAN", "SIRI", "SHARC", "EVOLUTION", "���Ǵ���",
									"MIR", "TATE", "Vfriends", "�ƿ쳻"};
	private final String[] departments = {"����Ʈ�����", "�����������ΰ�"};
	private final String[] classes = {"4", "5", "6", "10", "11", "12"};
	private final String[] interests = {"X", "����", "����", "����", "��ȭ ����", "������ Ÿ��", "� �ϱ�",
										"����", "TV ����", "�丮", "SNS ����", "���ڱ�"};
	
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
		JPanel enterUserID = MakeTextField("ID �Է� : ", screenSizeX / intervalX, screenSizeY / intervalY, boxSizeX, boxSizeY);
		JPanel enterUserName = MakeTextField("�̸� �Է� : ", screenSizeX / intervalX, (screenSizeY / intervalY) * 2, boxSizeX, boxSizeY);
		JPanel selectUserGrade = MakeComboBox("�г� �Է� : ", grades, screenSizeX / intervalX, (screenSizeY / intervalY) * 3, boxSizeX, boxSizeY);
		JPanel selectUserClub = MakeComboBox("���Ƹ� �Է� : ", clubs, screenSizeX / intervalX, (screenSizeY / intervalY) * 4, boxSizeX, boxSizeY);
		JPanel selectUserDepartment = MakeComboBox("�а� �Է� : ", departments, screenSizeX / intervalX, (screenSizeY / intervalY) * 5, boxSizeX, boxSizeY);
		JPanel selectUserClass = MakeComboBox("�� �Է� : ", classes, screenSizeX / intervalX, (screenSizeY / intervalY) * 6, boxSizeX, boxSizeY);
		ArrayList<JPanel> selectUserInterests = new ArrayList<JPanel>();
		for (int i = 1; i <= 5; i++) {
			selectUserInterests.add(MakeComboBox("���ɻ� �Է� " + i + " : ", interests, screenSizeX - screenSizeX / intervalX - boxSizeX, (screenSizeY / intervalY) * i, boxSizeX, boxSizeY));
		}
		JButton createUser = new JButton("���� ����");
		
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
						JOptionPane.showMessageDialog(null, "���ɻ縦 �ߺ� ���� �Ͽ����� Ȯ�� ���ּ���");
						exit = true;
					}
					userInterests.add(s);
				}
				
				if (userID.equals("") || userName.equals("")) {
					JOptionPane.showMessageDialog(null, "��� ������ �Է��Ͽ����� Ȯ�� ���ּ���");
					exit = true;
				}
				
				if (exit) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "���� ���� ����");
				
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
		String[] headings = new String[] {"ID", "�̸�", "�г�", "���Ƹ�", "�а�", "��", "���ɻ�1", "���ɻ�2", "���ɻ�3", "���ɻ�4", "���ɻ�5"};
		String[][] data = new String[Manager.GetUsersSize()][11];
		
		mainPanel.setLayout(null);
		
		JButton newUserButton = new JButton("���ο� ���� ����");
		
		mainPanel.setBackground(Color.WHITE);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(470, 25, 750, 650);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel buttonPanel = new JPanel();	
		JPanel newUserPanel = new JPanel();
		
		buttonPanel.setLayout(null);
		newUserPanel.setLayout(null);
		
		JLabel textLabel = new JLabel("���� ���̵� : ");
		JTextField userIDtf = new JTextField(10);
		
		JButton FriendsButton = new JButton("ģ�� �߰�");
		JButton PBButton = new JButton("���ɼ� ���");
		
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