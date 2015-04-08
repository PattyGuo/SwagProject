import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
		{
			users.add(username);
			this.save();
			enc.write(msg);
		}
			
	}
	
	public List<String> getUsers()
	{
		return users;
	}
	
	public void save()
	{
		
		try {
			PrintWriter pw = new PrintWriter("users.txt");
			for (int i = 0; i < users.size(); i++)
				pw.write(users.get(i) + "\n");
				pw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
}
