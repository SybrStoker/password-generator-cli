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

    @Option(names = {"-c", "--copy"}, description = "Copy password for X amount of seconds")
    private int[] copy = {0};

    @Option(names = {"-m", "--mix"}, description = "Shuffles password X amount of times")
    private int[] mix = {0};
  

    @Override
    public void run() { 
    	Generator passGen;
    	Features perform = new Features();
        boolean mixed = false;

    	if(useAll){
    		passGen = new Generator((byte) length, true, true, true, true);
    		
    	} else{
    		passGen = new Generator((byte) length, useLetters, useCapitalLetters, useNumbers, useSpecialCharacters); 		
    	}

    	String password = passGen.getPassword();
    	String shuffledPassword = "";

    	System.out.println("Password: " + password);


    	if(mix[0] != 0){
            mixed = true;
    		for(int i = 0; i < mix[0]; i++){
    			shuffledPassword = perform.shuffle(password);
    			System.out.println("Mixed password" + "(" + (i + 1) + "): " + shuffledPassword);
    		}

    		password = shuffledPassword;
    	}

       	if(copy[0] != 0){
       		if(mixed){
       			System.out.println(String.format("The last mixed password has been copied for %d seconds", copy[0]));
       		} else{
       			System.out.println(String.format("Password has been copied for %d seconds", copy[0]));
       		}

       		perform.executeClipboard(copy[0], password);
       	}
    }

	public static void main(String[] args) {
		int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode); 
	}
}