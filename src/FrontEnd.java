import java.awt.Dimension;
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
		final JFrame myFrame = new JFrame("SwagProject");
		final JComboBox <String> userList = new JComboBox<String>();
		final JTextPane myPanel = new JTextPane();
		final JTextField userPanel = new JTextField();
		final JTextField messagePanel = new JTextField();
		Box verticalBox = Box.createVerticalBox();
		Box labelBox = Box.createHorizontalBox();
		Box newUserBox = Box.createHorizontalBox();
		Box existingUserBox = Box.createHorizontalBox();
		Box textBox = Box.createHorizontalBox();
		JButton newButton = new JButton("Add User");
		JButton messageButton = new JButton("Add Message");
		JButton readMessageButton = new JButton("See all messages");
		JLabel newLabel = new JLabel("New User:");
		JLabel existingLabel = new JLabel("Existing Users:");
		
		myFrame.setMinimumSize(new Dimension(500, 300));
		
		labelBox.add(Box.createHorizontalStrut(100));
		labelBox.add(new JLabel("#SWAGPROJECT"));
		labelBox.add(Box.createHorizontalStrut(100));
		final BackEnd b = new BackEnd();
		
		for (String x: b.getUsers())
		{
			userList.addItem(x);
		}
		
		newButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				boolean error = false;
				for (int i = 0; i < userList.getItemCount(); i++)
				{
					if (userPanel.getText().equals(userList.getItemAt(i)))
					{
						JOptionPane.showMessageDialog(myFrame, "This username already exists!", "ERROR", JOptionPane.OK_OPTION);
						error = true;
					}
					if (((String)userList.getSelectedItem()).equals("users"))
					{
						JOptionPane.showMessageDialog(myFrame, "You can't name yourself as 'users'!", "ERROR", JOptionPane.OK_OPTION);
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

			public void actionPerformed(ActionEvent e) {
				if (userList.getItemCount() != 0)
				{
					if (((String)userList.getSelectedItem()).equals(""))
					{
						JOptionPane.showMessageDialog(myFrame, "You can't have a blank username!", "ERROR", JOptionPane.OK_OPTION);
					}
					else
					{
						b.addMessage((String)(userList.getSelectedItem()), messagePanel.getText());
					}
				}
				else
				{
					JOptionPane.showMessageDialog(myFrame, "You haven't selected a user!", "ERROR", JOptionPane.OK_OPTION);
				}
			}});
		
		readMessageButton.addActionListener(new ActionListener() {

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
		
		verticalBox.add(labelBox);
		verticalBox.add(newUserBox);
		verticalBox.add(existingUserBox);
		verticalBox.add(textBox);
		
		myFrame.add(verticalBox);
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
