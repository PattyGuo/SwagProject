import java.util.ArrayList;
import java.util.List;


public class BackEnd {

	private List<String> users;
	
	public BackEnd()
	{
		users = new ArrayList<String>();
	}
	public List<String> getMessages(String username)
	{
		Encoder enc = new Encoder(username + ".txt");
		return enc.read();
	}
	
	public void addMessage(String username, String msg)
	{
		boolean contains = false;
		Encoder enc = new Encoder(username + ".txt");
		for (int i = 0; i < users.size(); i++)
			if (username.equals(users.get(i)))
			{
				enc.write(msg);
				contains = true;
			}
		if (contains == false)
			users.add(username);
			
	}
	
	public List<String> getUsers()
	{
		return users;
	}
	
	
	
	
	
	
}
