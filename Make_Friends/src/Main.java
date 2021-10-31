import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		GUI g = new GUI();
		//g.AddUserScreen();
		Scanner scanner = new Scanner(System.in);
		
		String userID = "asdf";
		String userName = "asdf";
		String userGrade = "asdf";
		String userClub = "asdf";
		String userDepartment = "asdf";
		String userClass = "asdf";
		ArrayList<String> userInterests = new ArrayList<String>();
		userInterests.add("asdf");
		userInterests.add("asdf");
		userInterests.add("asdf");
		userInterests.add("asdf");
		userInterests.add("asdf");
		
		
		for(int i=0;i<100;i++) {
			userName = String.valueOf(i*i*i*i*i);
			Manager.AddUser(userID, userName, userGrade, userClub, userDepartment, userClass, userInterests);
		}
		
		g.MainScreen();
	}
}
