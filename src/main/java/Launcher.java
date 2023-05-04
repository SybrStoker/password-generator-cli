public class Launcher{
	public static void main(String[] args) {
		Generator passGen = new Generator();
		Features toDo = new Features();
		String password;

		passGen.setConfigurations((byte)10, true, true, true, true);
		password = passGen.getPassword();
		System.out.println(password);
		System.out.println(toDo.shuffle(password));
	}
}