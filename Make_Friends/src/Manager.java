import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Manager {
	private static ArrayList<User> users = new ArrayList<User>();
	
	public static void AddUser(String userID, String userName, String userGrade, String userClub, String userDepartment, String userClass, ArrayList<String> userInterests) {
		users.add(new User(users.size(), userID, userName, userGrade, userClub, userDepartment, userClass, userInterests));
	}
	
	public static void PrintAllUsers() {
		for (int i = 0; i < users.size(); i++) {
			users.get(i).PrintUserInfo();
		}
	}
	
	public static int FindFriendDistance(int start, int end) {
		int[] dist = new int[users.size()];
		Queue<Integer> q = new LinkedList<>();

		q.add(start);
		dist[0] = 0;
		for (int i = 1; i < dist.length; i++) {
			dist[i] = -1;
		}
		
		while(!q.isEmpty()) {
			int here = q.poll();
			ArrayList<User> friends = users.get(here).GetUserFriends();
			for (int i = 0; i < friends.size(); i++) {
				int next = friends.get(i).GetUserNumber();
				if (dist[next] == -1) {
					dist[next] = dist[here] + 1;
					q.add(next);
				}
			}
		}
		
		return dist[end];
	}
	
	public static int GetUsersSize() {
		return users.size();
	}
	
	public static User GetUserByUserID(String userID) {
		for (int i = 0; i < users.size(); i++) {
			if (userID.equals(users.get(i).GetUserID())) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public static User GetUserByIndex(int index) {
		return users.get(index);
	}
	
	public static boolean CheckUserIDOverlap(String s) {
		for (int i = 0; i < users.size(); i++) {
			if (s.equals(users.get(i).GetUserID())) {
				return true;
			}
		}
		return false;
	}
}
