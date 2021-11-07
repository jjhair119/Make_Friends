import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		String[] clubs = {"ZerOpen", "RG", "IWOP", "AppplePie", "AnA",
										"EDCAN", "SIRI", "SHARC", "EVOLUTION", "���Ǵ���",
										"MIR", "TATE", "Vfriends", "�ƿ쳻"};
		String[] departments = {"����Ʈ�����", "�����������ΰ�"};
		String[] classes = {"4", "5", "6", "10", "11", "12"};
		
		GUI g = new GUI();
		Random rand = new Random();
		
		ArrayList<String> userInterests = new ArrayList<String>();
		userInterests.add("X");
		userInterests.add("X");
		userInterests.add("X");
		userInterests.add("X");
		userInterests.add("X");
		
		for(int i=0;i<100;i++) {
			String department = departments[rand.nextInt(departments.length)];
			String club = null;
			if (department.equals(departments[0])) {
				club = clubs[rand.nextInt(9)];
			}
			else if (department.equals(departments[1])) {
				club = clubs[rand.nextInt(5) + 9];
			}
			
			String class_ = null;
			if (department.equals(departments[0])) {
				class_ = classes[rand.nextInt(3)];
			}
			else if (department.equals(departments[1])) {
				class_ = classes[rand.nextInt(3) + 3];
			}
			
			Manager.AddUser(String.valueOf(i + 1), 
					"�ƹ���", 
					club, 
					department, 
					class_, 
					userInterests);
		}
		
		g.MainScreen();
	}
}
