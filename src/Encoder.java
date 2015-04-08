import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Encoder {
	
	File file; 
	
	public Encoder(String filename)
	{

		file = new File(filename);	
		
	}
	
	public void write(String plaintext)
	{
		
		PrintWriter write = null;
		try {
			write = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char[] lmao = plaintext.toCharArray();
		for(int i = 0; i<lmao.length; i++)
		{
			lmao[i] = (char) ((int)(lmao[i])+1);
		}
		String encoded = "";
		for(int i = 0; i<lmao.length;i++)
		{
			encoded+= lmao[i];
		}
		write.println(encoded);
		write.close();
	}
	
	public List<String> read()
	{
		List<String> words = new ArrayList<String>();
		
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scan.hasNextLine())
		{
			String lol = scan.nextLine();
			char[] lmao = lol.toCharArray();
			for(int i = 0; i<lmao.length; i++)
			{
				lmao[i] = (char) ((int)(lmao[i])-1);
			}
			String encoded = "";
			for(int i = 0; i<lmao.length;i++)
			{
				encoded+=lmao[i];
			}
			words.add(encoded);
		}
		
		return words;
	}

}
