import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		GUI g = new GUI();
		
		ArrayList<String> userInterests = new ArrayList<String>();
		userInterests.add("X");
		userInterests.add("X");
		userInterests.add("X");
		userInterests.add("X");
		userInterests.add("X");
		
		for(int i=0;i<100;i++) {
			Manager.AddUser(String.valueOf(i + 1), "�ƹ���", "2", "SHARC", "����Ʈ�����", "5", userInterests);
		}
		
		g.MainScreen();
	}
}
