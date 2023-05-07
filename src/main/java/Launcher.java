import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "pass-gen", version = "pass-gen Alpha", mixinStandardHelpOptions = true)
public class Launcher implements Runnable{

	@Option(names = {"-l"}, description = "Include letters into the password")
    private boolean useLetters;

    @Option(names = {"-C"}, description = "Include capital letters into the password")
    private boolean useCapitalLetters;

    @Option(names = {"-n"}, description = "Include numbers into the password")
    private boolean useNumbers;

    @Option(names = {"-s"}, description = "Include special characters into the password")
    private boolean useSpecialCharacters;

    @Option(names = {"-A"}, description = "Include all characters into the password")
    private boolean useAll;

    @Option(names = {"-L"}, description = "Set length of password")
    private int length = 25;

    @Option(names = {"-c", "--copy"}, description = "Copy password for 60 seconds")
    private boolean copy;

    @Option(names = {"-m", "--mix"}, description = "Shuffles password 5 times")
    private boolean mix;
  

    @Override
    public void run() { 
    	Generator passGen;
    	Features perform = new Features();

    	if(useAll){
    		passGen = new Generator((byte) length, true, true, true, true);
    		
    	} else{
    		passGen = new Generator((byte) length, useLetters, useCapitalLetters, useNumbers, useSpecialCharacters); 		
    	}

    	String password = passGen.getPassword();
    	String shuffledPassword = "";

    	System.out.println("Password: " + password);

    	if(mix){
    		for(int i = 0; i < 5; i++){
    			shuffledPassword = perform.shuffle(password);
    			System.out.println("Password" + "(" + (i + 1) + "): " + shuffledPassword);
    		}

    		password = shuffledPassword;
    	}

       	if(copy){
       		if(mix){
       			System.out.println("Last password has been copied for 60 seconds");
       		} else{
       			System.out.println("Password has been copied for 60 seconds");
       		}

       		perform.executeClipboard(60, password);
       	}
    }

	public static void main(String[] args) {
		int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode); 
	}
}