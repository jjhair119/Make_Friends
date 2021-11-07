import java.util.ArrayList;

public class User {	
	private ArrayList<User> friends = new ArrayList<User>();
	private int userNumber;
	private String userID;
	private String userName;
	private String userClub;
	private String userDepartment;
	private String userClass;
	private ArrayList<String> userInterests;
	
	User(int userNumber, String userID, String userName, String userClub, String userDepartment, String userClass, ArrayList<String> userInterests) {
		this.userNumber = userNumber;
		this.userID = userID;
		this.userName = userName;
		this.userClub = userClub;
		this.userDepartment = userDepartment;
		this.userClass = userClass;
		this.userInterests = userInterests;
	}
	
	int GetUserNumber() {
		return this.userNumber;
	}
	
	ArrayList<User> GetUserFriends() {
		return this.friends;
	}
	
	String GetUserInterest(int index) {
		return this.userInterests.get(index);
	}
	
	String GetUserClass() {
		return this.userClass;
	}
	
	String GetUserDepartment() {
		return this.userDepartment;
	}
	
	String GetUserID() {
		return this.userID;
	}
	
	String GetUserClub() {
		return this.userClub;
	}
	
	String GetUserName() {
		return this.userName;
	}
	
	public void AddFriend(User u) {
		friends.add(u);
	}
	
	public void RemoveFriend(User u) {
		for (int i = 0; i < friends.size(); i++) {
			if (this.userID.equals(u.GetUserID())) {
				friends.remove(i);
				break;
			}
		}
	}
	
	public void PrintUserInfo() {
		System.out.print("���̵� : " + this.userID + " " + 
						   "�̸� : " + this.userName + " " + 
						   "���Ƹ� : " + this.userClub + " " + 
						   "�а� : " + this.userDepartment + " " + 
						   "�� : " + this.userClass);
		for (int i = 0; i < userInterests.size(); i++) {
			System.out.print(" " + "���ɻ�" + (i + 1) + " : " + userInterests.get(i));
		}
		System.out.println();
	}
	
	public double CalculateProbability(User u) {
		double prob = 0.0;
		
		// ���Ƹ�, ����ġ 35%, ������ 1, �ƴϸ� 0
		if (this.userClub.equals(u.GetUserClub())) {
			prob += 0.35;
		}
		
		int userDistance = Manager.FindFriendDistance(userNumber, u.GetUserNumber());
		
		// ģ�� �Ÿ�, ����ġ 30%, 2^-(x-1)
		if (userDistance != -1) {
			prob += 0.3 * Math.pow(2.0, -(userDistance - 2));
		}
		
		// ��, ����ġ 20%, �����а� �ٸ��� 0.6, ������ 0.3
		if (this.userClass.equals(u.GetUserClass())) {
			prob += 0.2;
		}
		else if (this.userDepartment.equals(u.GetUserDepartment())) {
			prob += (0.2 * 0.6);
		}
		else {
			prob += (0.2 * 0.3);
		}
		
		// ���ɻ�, ����ġ 15%, ���� ���ɻ� 1�� 0.2
		for (int i = 0; i < 5; i++) {
			if (this.userInterests.contains(u.GetUserInterest(i)) && !u.GetUserInterest(i).equals("X")) {
				prob += (0.15 * 0.2);
			}
		}
		return prob;
	}
	
	public boolean IsFriend(User u) {
		for (int i = 0; i < friends.size(); i++) {
			if (friends.get(i) == u) {
				return true;
			}
		}
		return false;
	}
}