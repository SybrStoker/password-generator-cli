import java.util.Random;
import java.util.ArrayList;

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

	Generator(byte passLength, boolean letters,
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
		byte choice;
		char character = ' '; //cause all assingments are in switch statment compiler doesn't allow me to leave the var unsigned.

		while(character == ' '){
			choice = (byte) genNum.nextInt(4);
					
			switch(choice){
				case (byte) 0:
					if(letters){
						character = this.lettersA[genNum.nextInt(this.lettersA.length)];
					}
					break;
				case (byte) 1:
					if(capLetters){
						character = Character.toUpperCase(character = this.lettersA[genNum.nextInt(this.lettersA.length)]);
					}
					break;
				case (byte) 2:
					if(numbers){
						character = this.numbersA[genNum.nextInt(this.numbersA.length)];
					}
					break;
				case (byte) 3:
					if(sChars){
						character = this.sCharsA[genNum.nextInt(this.sCharsA.length)];
					}
					break;
				default:
					System.out.println("Failed to pick a character.");
		}
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
}