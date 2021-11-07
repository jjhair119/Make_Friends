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
		System.out.print("아이디 : " + this.userID + " " + 
						   "이름 : " + this.userName + " " + 
						   "동아리 : " + this.userClub + " " + 
						   "학과 : " + this.userDepartment + " " + 
						   "반 : " + this.userClass);
		for (int i = 0; i < userInterests.size(); i++) {
			System.out.print(" " + "관심사" + (i + 1) + " : " + userInterests.get(i));
		}
		System.out.println();
	}
	
	public double CalculateProbability(User u) {
		double prob = 0.0;
		
		// 동아리, 가중치 35%, 같으면 1, 아니면 0
		if (this.userClub.equals(u.GetUserClub())) {
			prob += 0.35;
		}
		
		int userDistance = Manager.FindFriendDistance(userNumber, u.GetUserNumber());
		
		// 친구 거리, 가중치 30%, 2^-(x-1)
		if (userDistance != -1) {
			prob += 0.3 * Math.pow(2.0, -(userDistance - 2));
		}
		
		// 반, 가중치 20%, 같은학과 다른반 0.6, 나머지 0.3
		if (this.userClass.equals(u.GetUserClass())) {
			prob += 0.2;
		}
		else if (this.userDepartment.equals(u.GetUserDepartment())) {
			prob += (0.2 * 0.6);
		}
		else {
			prob += (0.2 * 0.3);
		}
		
		// 관심사, 가중치 15%, 같은 관심사 1당 0.2
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