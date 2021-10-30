import java.util.ArrayList;

public class Manager {
	private static ArrayList<User> users = new ArrayList<User>();
	
	public static void AddUser(String userID, String userName, String userGrade, String userClub, String userDepartment, String userClass, ArrayList<String> userInterests) {
		users.add(new User(userID, userName, userGrade, userClub, userDepartment, userClass, userInterests));
	}
	
	public static void PrintAllUsers() {
		for (int i = 0; i < users.size(); i++) {
			users.get(i).PrintUserInfo();
		}
	}
}
