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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GUI {
	private JFrame frame;
	ImageIcon mainImage;
	ImageIcon friendImage;
	ImageIcon probImage;
	private final String mainImagePath = "Main_Image.png"; 
	private final String friendImagePath = "Friend_Image.png"; 
	private final String probImagePath = "Prob_Image.png"; 
	private final int screenSizeX = 1280;
	private final int screenSizeY = 720;
	private final String[] clubs = {"ZerOpen", "RG", "IWOP", "AppplePie", "AnA",
									"EDCAN", "SIRI", "SHARC", "EVOLUTION", "���Ǵ���",
									"MIR", "TATE", "Vfriends", "�ƿ쳻"};
	private final String[] departments = {"����Ʈ�����", "�����������ΰ�"};
	private final String[] classes = {"4", "5", "6", "10", "11", "12"};
	private final String[] interests = {"X", "����", "����", "����", "��ȭ ����", "������ Ÿ��", "� �ϱ�",
										"����", "TV ����", "�丮", "SNS ����", "���ڱ�"};
	
	class Pair implements Comparable<Pair>{
		String userID;
		double prob;
		
		Pair(String userID, double prob){
			this.userID = userID;
			this.prob = prob;
		}

		@Override
		public int compareTo(GUI.Pair o) {
			if (o.prob - this.prob > 0)
				return 1;
			else if (o.prob - this.prob< 0)
				return -1;
			else
				return 0;
		}
		
	}
	
	public GUI() {
		mainImage = new ImageIcon(mainImagePath);
		friendImage = new ImageIcon(friendImagePath);
		probImage = new ImageIcon(probImagePath);
		this.frame = new JFrame("Make Friends In Sunrin");
	}
	
	public void SetFrame() {
		this.frame.setSize(screenSizeX, screenSizeY);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false); 
		this.frame.setIconImage(this.mainImage.getImage());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);    
	}
	
	JPanel MakeTextField(String s, int x, int y, int sizeX, int sizeY) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(s);
		JTextField txtfield = new JTextField(10);
		panel.setBackground(Color.WHITE);
		panel.setBounds(x, y, sizeX, sizeY);
		panel.add(label);
		panel.add(txtfield);
		return panel;
	}
	
	JPanel MakeComboBox(String s, String[] list, int x, int y, int sizeX, int sizeY) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(s);
		JComboBox<String> combo = new JComboBox<String>(list);
		panel.setBackground(Color.WHITE);
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
	
	public void AddUserScreen() {
		final int intervalX = 6;
		final int intervalY = 8;
		final int boxSizeX = 300;
		final int boxSizeY = 50;
		
		JPanel mainPanel = new JPanel();
		JPanel enterUserID = MakeTextField("ID �Է� : ", screenSizeX / intervalX, screenSizeY / intervalY, boxSizeX, boxSizeY);
		JPanel enterUserName = MakeTextField("�̸� �Է� : ", screenSizeX / intervalX, (screenSizeY / intervalY) * 2, boxSizeX, boxSizeY);
		JPanel selectUserClub = MakeComboBox("���Ƹ� �Է� : ", clubs, screenSizeX / intervalX, (screenSizeY / intervalY) * 3, boxSizeX, boxSizeY);
		JPanel selectUserDepartment = MakeComboBox("�а� �Է� : ", departments, screenSizeX / intervalX, (screenSizeY / intervalY) * 4, boxSizeX, boxSizeY);
		JPanel selectUserClass = MakeComboBox("�� �Է� : ", classes, screenSizeX / intervalX, (screenSizeY / intervalY) * 5, boxSizeX, boxSizeY);
		ArrayList<JPanel> selectUserInterests = new ArrayList<JPanel>();
		for (int i = 1; i <= 5; i++) {
			selectUserInterests.add(MakeComboBox("���ɻ� �Է� " + i + " : ", interests, screenSizeX - screenSizeX / intervalX - boxSizeX, (screenSizeY / intervalY) * i, boxSizeX, boxSizeY));
		}
		JButton createUser = new JButton("���� ����");
		JButton back = new JButton("�ڷ� ����");
	
		createUser.setBounds(screenSizeX - screenSizeX / intervalX - boxSizeX + 50, (screenSizeY / intervalY) * 6, 200, 25);
		back.setBounds(screenSizeX - screenSizeX / intervalX - boxSizeX + 50, (screenSizeY / intervalY) * 6 + 50, 200, 25);
		
		createUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean exit = false;
				String userID = GetText(enterUserID);
				String userName = GetText(enterUserName);
				String userClub = GetText(selectUserClub);
				String userDepartment = GetText(selectUserDepartment);
				String userClass = GetText(selectUserClass);
				ArrayList<String> userInterests = new ArrayList<String>();
				for (int i = 0; i < 5; i++) {
					String s = GetText(selectUserInterests.get(i));
					if (userInterests.contains(s) && !s.equals("X")) {
						JOptionPane.showMessageDialog(null, "���ɻ縦 �ߺ� ���� �Ͽ����� Ȯ�� ���ּ���");
						exit = true;
						break;
					}
					userInterests.add(s);
				}
				
				if (userID.equals("") || userName.equals("")) {
					JOptionPane.showMessageDialog(null, "��� ������ �Է��Ͽ����� Ȯ�� ���ּ���");
					exit = true;
				}
				
				if (Manager.CheckUserIDOverlap(userID)) {
					JOptionPane.showMessageDialog(null, "���� ���̵� �ߺ� �Ǿ����ϴ�");
					exit = true;
				}
				
				if (exit) {
					return;
				}
				
				JOptionPane.showMessageDialog(null, "���� ���� ����");
				
				Manager.AddUser(userID, userName, userClub, userDepartment, userClass, userInterests);
				Manager.PrintAllUsers();
				mainPanel.setVisible(false);
				MainScreen();
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				MainScreen();
			}
		});

		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(enterUserID);
		mainPanel.add(enterUserName);
		mainPanel.add(selectUserClub);
		mainPanel.add(selectUserDepartment);
		mainPanel.add(selectUserClass);
		for (int i = 0; i < 5; i++) {
			mainPanel.add(selectUserInterests.get(i));
		}
		mainPanel.add(createUser);
		mainPanel.add(back);
		
		this.frame.add(mainPanel);
	    this.SetFrame();
	}
	
	JTable MakeTable(ArrayList<User> userFriends) {
		String[] headings = new String[] {"ID", "�̸�", "���Ƹ�", "�а�", "��", "���ɻ�1", "���ɻ�2", "���ɻ�3", "���ɻ�4", "���ɻ�5"};
		String[][] data = new String[userFriends.size()][10];
		
		for(int i = 0; i < userFriends.size(); i++) {
			User u = Manager.GetUserByUserID(userFriends.get(i).GetUserID());
			data[i][0] = u.GetUserID();
			data[i][1] = u.GetUserName();
			data[i][2] = u.GetUserClub();
			data[i][3] = u.GetUserDepartment();
			data[i][4] = u.GetUserClass();
			data[i][5] = u.GetUserInterest(0);
			data[i][6] = u.GetUserInterest(1);
			data[i][7] = u.GetUserInterest(2);
			data[i][8] = u.GetUserInterest(3);
			data[i][9] = u.GetUserInterest(4);
		}
		
		JTable table = new JTable(data, headings);
		table.setPreferredScrollableViewportSize(new Dimension(700,600));
		table.setFillsViewportHeight(true);
		
		return table;
	}
	
	public void AddFriendsScreen(User user) {
		JPanel mainPanel = new JPanel();
		
		JPanel nowUserPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		
		JLabel textLabel = new JLabel("���� ���� ���̵�", SwingConstants.CENTER);
		JLabel userIdLabel = new JLabel(user.GetUserID(), SwingConstants.CENTER);
		JTextField userIdTf = new JTextField();
		JButton AddButton = new JButton("�߰�");
		JButton backButton = new JButton("�ڷ� ����");
		
		ArrayList<User> userFriends = user.GetUserFriends();
		
		backButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			           	mainPanel.setVisible(false);
			            MainScreen();
			        }  
			    });  
		
		JLabel imgLabel = new JLabel(friendImage);
		
		mainPanel.setBackground(Color.WHITE);
		nowUserPanel.setBackground(Color.WHITE);
		tablePanel.setBackground(Color.WHITE);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		nowUserPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		textLabel.setFont(nowUserPanel.getFont().deriveFont(17.0f));
		userIdLabel.setFont(nowUserPanel.getFont().deriveFont(17.0f));
		
		mainPanel.setLayout(null);
		nowUserPanel.setLayout(null);
		
		nowUserPanel.add(textLabel);
		nowUserPanel.add(userIdLabel);
		nowUserPanel.add(userIdTf);
		nowUserPanel.add(AddButton);
		nowUserPanel.add(backButton);
		
		tablePanel.add(new JScrollPane(MakeTable(userFriends)));
		
		mainPanel.add(imgLabel);
		mainPanel.add(nowUserPanel);
		mainPanel.add(tablePanel);
		
		imgLabel.setBounds(175, 75, 150, 150);
		nowUserPanel.setBounds(60, 300, 400, 330);
		tablePanel.setBounds(470, 25, 750, 650);
		
		textLabel.setBounds(50, 55, 300, 20);
		userIdLabel.setBounds(50, 75, 300, 20);
		userIdTf.setBounds(50, 130, 300, 35);
		AddButton.setBounds(50, 195, 300, 35);
		backButton.setBounds(50, 260, 300, 35);
		
		AddButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
						User u = Manager.GetUserByUserID(userIdTf.getText());

						for (int i = 0; i < userFriends.size(); i++) {
							if (userFriends.get(i) == u) {
								JOptionPane.showMessageDialog(null, "�̹� ģ���� �����Դϴ�");
								return;
							}
						}
						
						if (user == u) {
							JOptionPane.showMessageDialog(null, "������ ���̵� �Դϴ�");
							return;
						}
						
						if (u != null) {
							user.AddFriend(u);
							u.AddFriend(user);
							
							tablePanel.setVisible(false);
							tablePanel.removeAll();
							tablePanel.add(new JScrollPane(MakeTable(userFriends)));
							tablePanel.setVisible(true);
							
							userIdTf.setText("");
							JOptionPane.showMessageDialog(null, "ģ�� �߰��� �����߽��ϴ�");
						}
						else {
							JOptionPane.showMessageDialog(null, "���� ���� ���̵� �Դϴ�");
						}
			        }  
			    });  
		
		this.frame.add(mainPanel);
		this.SetFrame();
	}
	
	public void MainScreen() {
		JPanel mainPanel = new JPanel();
		String[] headings = new String[] {"ID", "�̸�", "���Ƹ�", "�а�", "��", "���ɻ�1", "���ɻ�2", "���ɻ�3", "���ɻ�4", "���ɻ�5"};
		String[][] data = new String[Manager.GetUsersSize()][10];
		
		mainPanel.setLayout(null);
		
		JButton newUserButton = new JButton("���ο� ���� ����");
		
		newUserButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			           	mainPanel.setVisible(false);
			            AddUserScreen();
			        }  
			    });  
		
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
		
		FriendsButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){   	
			           	User user = Manager.GetUserByUserID(userIDtf.getText());
			           	if (user != null) {
			           		mainPanel.setVisible(false);
			           		AddFriendsScreen(user);
			           	}
			           	else {
			           		JOptionPane.showMessageDialog(null, "���� ���� ���̵� �Դϴ�");
			           	}
			        }  
			    });  
		
		PBButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			           	User user = Manager.GetUserByUserID(userIDtf.getText());
			           	if (user != null) {
			           		mainPanel.setVisible(false);
			           		ProbabilityCalculationScreen(user);
			           	}
			           	else {
			           		JOptionPane.showMessageDialog(null, "���� ���� ���̵� �Դϴ�");
			           	}
			        }  
			    });  
		
		JLabel imgLabel = new JLabel(mainImage);
		
		imgLabel.setBounds(175,75,150,150);
		
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
			data[i][2] = u.GetUserClub();
			data[i][3] = u.GetUserDepartment();
			data[i][4] = u.GetUserClass();
			data[i][5] = u.GetUserInterest(0);
			data[i][6] = u.GetUserInterest(1);
			data[i][7] = u.GetUserInterest(2);
			data[i][8] = u.GetUserInterest(3);
			data[i][9] = u.GetUserInterest(4);
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
		
		ListSelectionModel select= table.getSelectionModel();  
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        select.addListSelectionListener(new ListSelectionListener() {  
          public void valueChanged(ListSelectionEvent e) {  
            String data = null;  
            int row = table.getSelectedRow();   
            data = (String) table.getValueAt(row, 0);
            userIDtf.setText(data);    
          }       
        });  
		
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		newUserPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		tablePanel.setBackground(Color.WHITE);
		tablePanel.add(new JScrollPane(table));
		
		mainPanel.add(tablePanel);
		this.frame.add(mainPanel);
		this.SetFrame();
	}
	
	public void ProbabilityCalculationScreen(User user) {
		JPanel mainPanel = new JPanel();
		
		JPanel tablePanel = new JPanel();
		
		JLabel textLabel = new JLabel("���� ���� ���̵�", SwingConstants.CENTER);
		JLabel userIdLabel = new JLabel(user.GetUserID(), SwingConstants.CENTER);
		JButton backButton = new JButton("�ڷ� ����");
		
		backButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
						mainPanel.setVisible(false);
						MainScreen();
			        }  
			    });  
		
		JLabel imgLabel = new JLabel(probImage);
		
		mainPanel.setLayout(null);
		
		mainPanel.setBackground(Color.WHITE);
		tablePanel.setBackground(Color.WHITE);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		textLabel.setFont(mainPanel.getFont().deriveFont(17.0f));
		userIdLabel.setFont(mainPanel.getFont().deriveFont(17.0f));
		
		String[] headings = new String[] {"����", "ID", "�̸�", "���Ƹ�", "�а�", "��", "���ɻ�1", "���ɻ�2", "���ɻ�3", "���ɻ�4", "���ɻ�5", "Ȯ��"};
		String[][] data = new String[5][12];
		
		ArrayList<Pair> p = new ArrayList<Pair>();
		
		for (int i = 0; i < Manager.GetUsersSize(); i++) {
			User u = Manager.GetUserByIndex(i);
			
			if (user != u && !user.IsFriend(u)) {
				p.add(new Pair(u.GetUserID(), user.CalculateProbability(u)));
			}
		}
		
		Collections.sort(p);
		
		for(int i = 0; i < 5; i++) {
			User u = Manager.GetUserByUserID(p.get(i).userID);
			data[i][0] = String.valueOf(i+1);
			data[i][1] = u.GetUserID();
			data[i][2] = u.GetUserName();
			data[i][3] = u.GetUserClub();
			data[i][4] = u.GetUserDepartment();
			data[i][5] = u.GetUserClass();
			data[i][6] = u.GetUserInterest(0);
			data[i][7] = u.GetUserInterest(1);
			data[i][8] = u.GetUserInterest(2);
			data[i][9] = u.GetUserInterest(3);
			data[i][10] = u.GetUserInterest(4);
			data[i][11] = String.valueOf((int)(p.get(i).prob * 100)) + "%";
		}
		
		JTable frendTable = new JTable(data, headings);
		frendTable.setPreferredScrollableViewportSize(new Dimension(800,80));
		frendTable.setFillsViewportHeight(true);
		
		imgLabel.setBounds(screenSizeX / 2 - 75, 75,150,150);
		tablePanel.setBounds(screenSizeX / 2 - 425, 305, 850, 150);
		textLabel.setBounds(screenSizeX / 2 - 150, 230, 300, 20);
		userIdLabel.setBounds(screenSizeX / 2 - 150, 255, 300, 20);
		backButton.setBounds(screenSizeX / 2 - 150, 460, 300, 40);
		
		tablePanel.add(new JScrollPane(frendTable));
		
		mainPanel.add(textLabel);
		mainPanel.add(userIdLabel);
		
		mainPanel.add(imgLabel);
		mainPanel.add(tablePanel);
		mainPanel.add(backButton);
		
		mainPanel.add(tablePanel);
		this.frame.add(mainPanel);
		this.SetFrame();
	}
}