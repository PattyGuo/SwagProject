import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FrontEnd {

	public FrontEnd()
	{
		JFrame myFrame = new JFrame("SwagProject");
		JComboBox userList = new JComboBox();
		JPanel myPanel = new JPanel();
		JTextField userPanel = new JTextField();
		JTextField messagePanel = new JTextField();
		Box verticalBox = Box.createVerticalBox();
		Box newUserBox = Box.createHorizontalBox();
		Box existingUserBox = Box.createHorizontalBox();
		Box textBox = Box.createHorizontalBox();
		JButton newButton = new JButton("Add User");
		JButton messageButton = new JButton("Add Message");
		JLabel newLabel = new JLabel("New User:");
		JLabel existingLabel = new JLabel("Existing Users:");
		BackEnd b = new BackEnd();
		
		for (String x: b.getUsers())
		{
			userList.addItem(x);
		}
		
		newButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean error = false;
				for (int i = 0; i < userList.getItemCount(); i++)
				{
					if (userPanel.getText().equals(userList.getItemAt(i)))
					{
						JOptionPane.showMessageDialog(myFrame, "This username already exists!", "ERROR", JOptionPane.OK_OPTION);
						error = true;
					}
				}
				if (error == false)
				{
					b.addMessage(userPanel.getText(), "");
					userList.addItem(userPanel.getText());
				}
			}});
		
		newUserBox.add(newLabel);
		newUserBox.add(userPanel);
		newUserBox.add(newButton);
		
		existingUserBox.add(userList);
		
		verticalBox.add(newUserBox);
		verticalBox.add(existingUserBox);
		verticalBox.add(textBox);
		
		
		
		
		myFrame.add(verticalBox);
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

