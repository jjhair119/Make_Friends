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