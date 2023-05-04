import java.util.Random;
import java.util.ArrayList;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;

public class Features{
	public String shuffle(String originalPass){
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

	private void copyToClipboard(String text){
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	private String copyFromClipboard(){
		String copiedText = "";
		try{
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			copiedText = (String) clipboard.getData(DataFlavor.stringFlavor);

		} 
		catch (UnsupportedFlavorException | IOException e){
        	e.printStackTrace();
    	}

		return copiedText;
	}
}