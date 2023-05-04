import java.util.Random;
import java.util.ArrayList;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;

public class Generator{
	private byte passLength;
	private boolean letters;
	private boolean capLetters;
	private boolean numbers;
	private boolean sChars; //speacial characters

	private char[] lettersA = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
					   				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
					   				's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	private char[] numbersA = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	private char[] sCharsA = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-',
									'_', '=', '+', '/', '\\', '|', '[', ']', '{', '}', '\'',
									'\"', ';', ':', ',', '.', '<', '>', '?', '`', '~'};

	public void setConfigurations(byte passLength, boolean letters,
			boolean capLetters, boolean numbers, boolean sChars){
		if(passLength > 25){
			throw new Error("Your password is too long. Take it easy. You've got to stop!"); 
		}

		if(letters == false && capLetters == false &&
				numbers == false && sChars == false){
			throw new Error("No options. At least one option must be chosen.");
		}
		this.passLength = passLength;
		this.letters = letters;
		this.capLetters = capLetters;
		this.numbers = numbers;
		this.sChars = sChars;
	}

	private char pickAcharacter(){
		Random genNum = new Random();
		byte choice = (byte) genNum.nextInt(4);
		char character = ' '; //cause all assingments are in switch statment compiler doesn't allow me to leave the var unsigned.

		switch(choice){
			case (byte) 0:
				character = this.lettersA[genNum.nextInt(this.lettersA.length)];
				break;
			case (byte) 1:
				character = Character.toUpperCase(character = this.lettersA[genNum.nextInt(this.lettersA.length)]);
				break;
			case (byte) 2:
				character = this.numbersA[genNum.nextInt(this.numbersA.length)];
				break;
			case (byte) 3:
				character = this.sCharsA[genNum.nextInt(this.sCharsA.length)];
				break;
			default:
				System.out.println("Failed to pick a character.");
		}

		return character;
	}


	public String getPassword(){
		StringBuilder password = new StringBuilder();

		for(int i = 0; i < passLength; i++){
			password.append(pickAcharacter());
		}

		return password.toString();
	}

	public String shufflePassword(String originalPass){
		//it shuffles already existing password
		ArrayList<Character> charsOfPass = new ArrayList<>();
		StringBuilder password = new StringBuilder();
		Random rand = new Random();
		int index;

		for(int i = 0; i < originalPass.length(); i++){
			charsOfPass.add(originalPass.charAt(i));
		}

		int times = charsOfPass.size();
		for(int i = 0; i < times; i++){
			index = rand.nextInt(charsOfPass.size());
			password.append(charsOfPass.get(index));
			charsOfPass.remove(index);
		}

		return password.toString();
	}

	private void copyTextToClipboard(String text){
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	private String copyTextFromClipboard(){
		String copiedText = "";
		try{
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			copiedText = (String) clipboard.getData(DataFlavor.stringFlavor);

		} catch (UnsupportedFlavorException | IOException e) {
        	e.printStackTrace();
    	}
		return copiedText;
	}
	



}