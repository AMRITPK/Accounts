package util;

public class UniqueNumbers {
	public static double number=1000;
	public static String getNext() {

		String str=Double.toString(number++);
		return str.substring(0, str.length() - 2);
	}
}
