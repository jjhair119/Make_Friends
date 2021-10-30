import java.util.ArrayList;

public class User {	
	private ArrayList<User> friends = new ArrayList<User>();
	private String userID;
	private String userName;
	private String userGrade;
	private String userClub;
	private String userDepartment;
	private String userClass;
	private ArrayList<String> userInterests;
	
	User(String userID, String userName, String userGrade, String userClub, String userDepartment, String userClass, ArrayList<String> userInterests) {
		this.userID = userID;
		this.userName = userName;
		this.userGrade = userGrade;
		this.userClub = userClub;
		this.userDepartment = userDepartment;
		this.userClass = userClass;
		this.userInterests = userInterests;
	}
	
	String GetUserID() {
		return this.userID;
	}
	
	void AddFriend(User u) {
		friends.add(u);
	}
	
	void RemoveFriend(User u) {
		for (int i = 0; i < friends.size(); i++) {
			if (this.userID.equals(u.GetUserID())) {
				friends.remove(i);
				break;
			}
		}
	}
	
	void PrintUserInfo() {
		System.out.print("아이디 : " + this.userID + " " + 
						   "이름 : " + this.userName + " " + 
						   "학년 : " + this.userGrade + " " + 
						   "동아리 : " + this.userClub + " " + 
						   "학과 : " + this.userDepartment + " " + 
						   "반 : " + this.userClass);
		for (int i = 0; i < userInterests.size(); i++) {
			System.out.print(" " + "관심사" + (i + 1) + " : " + userInterests.get(i));
		}
		System.out.println();
	}
}