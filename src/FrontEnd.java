import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
		final JButton messageButton = new JButton("Add Message");
		final JButton readMessageButton = new JButton("Retrieve messages");
		JLabel newLabel = new JLabel("Create New User:");
		final JLabel userLabel = new JLabel();
		final JLabel readMessage = new JLabel("Retrieve a message:");
		final JLabel retrieveMessage = new JLabel("Add a message:");
		myFrame.setMinimumSize(new Dimension(600, 500));
		myFrame.setLocation(450, 250);
		
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
					readMessage.setText("Retrieve Message For: " + userPanel.getText());
					retrieveMessage.setText("Add Message For: " + userPanel.getText());
				}
			}});
		
		newUserBox.add(newLabel);
		newUserBox.add(userPanel);
		newUserBox.add(newButton);
		
		existingUserBox.add(new JLabel("Select from existing Users:"));
		existingUserBox.add(userList);
		
		userList.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					readMessage.setText("Retrieve Message For: " + userList.getSelectedItem());
					retrieveMessage.setText("Add Message For: " + userList.getSelectedItem());
				}
				
			}});
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
		
		textBox.add(retrieveMessage);
		textBox.add(Box.createHorizontalStrut(2));
		textBox.add(messagePanel);
		textBox.add(messageButton);
		textBox.setMaximumSize(new Dimension(500, 200));
		textBox.setMinimumSize(new Dimension(300, 100));
		Box messageBox = Box.createVerticalBox();
		
		messageBox.add(readMessage);
		messageBox.add(myPanel);
		messageBox.add(readMessageButton);
		
		verticalBox.add(labelBox);
		verticalBox.add(Box.createVerticalStrut(20));
		
		existingUserBox.setMaximumSize(new Dimension(400, 100));
		newUserBox.setMaximumSize(new Dimension(300, 100));
		verticalBox.add(newUserBox);
		verticalBox.add(existingUserBox);
		
		
		Box label = Box.createHorizontalBox();
		label.add(userLabel);
		
		verticalBox.add(label);
		verticalBox.add(Box.createVerticalStrut(50));
		verticalBox.add(textBox);
		verticalBox.add(Box.createVerticalStrut(50));
		verticalBox.add(messageBox);
		
		myFrame.add(verticalBox);
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
