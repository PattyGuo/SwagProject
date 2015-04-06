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
import javax.swing.JTextPane;


public class FrontEnd {

	public FrontEnd()
	{
		JFrame myFrame = new JFrame("SwagProject");
		JComboBox <String> userList = new JComboBox<String>();
		JTextPane myPanel = new JTextPane();
		JTextField userPanel = new JTextField();
		JTextField messagePanel = new JTextField();
		Box verticalBox = Box.createVerticalBox();
		Box newUserBox = Box.createHorizontalBox();
		Box existingUserBox = Box.createHorizontalBox();
		Box textBox = Box.createHorizontalBox();
		JButton newButton = new JButton("Add User");
		JButton messageButton = new JButton("Add Message");
		JButton readMessageButton = new JButton("See all messages");
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
		
		messageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (userList.getItemCount() != 0)
				{
					b.addMessage((String)(userList.getSelectedItem()), messagePanel.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(myFrame, "You haven't selected a user!", "ERROR", JOptionPane.OK_OPTION);
				}
			}});
		
		readMessageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = "";
				for (String x : b.getMessages((String)userList.getSelectedItem()))
				{
					temp += x + "\n";
				}
				myPanel.setText(temp);
			}});
		
		textBox.add(messageButton);
		textBox.add(messagePanel);
		textBox.add(myPanel);
		textBox.add(readMessageButton);		
		
		verticalBox.add(newUserBox);
		verticalBox.add(existingUserBox);
		verticalBox.add(textBox);
		
		
		
		
		myFrame.add(verticalBox);
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
