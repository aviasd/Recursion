public class Recursion {

	private final static int EXCLUDE_NUMBER = 0;

	public static void main(String[] args) {

		/* Test Question 1 */
		System.out.println(dec2Bin(270));
		System.out.println(bin2Dec(100001110));
		System.out.println(hex2Dec("EFD1"));
		System.out.println(dec2Hex(61393));
		System.out.println();

		/* Test Question 2 */
		int[] set = new int[] { 1, 2, 3, 4};
		int[] subset = new int[set.length];
		System.out.print("Array: ");
		printArray(set);

		System.out.println("Subsets of array: ");
		subset(set, subset, 0);
		System.out.println();

		/* Test Question 3 */
		specialPrint("Hello", '$');
	}

	/* Question 1.1 */
	//Converts from decimal to binary
	private static int dec2Bin(int num) {
		if (num == 0) {
			return 0;
		}
		int digit = num % 2;
		return (dec2Bin(num / 2) * 10 + digit);
	}

	/* Question 1.2 */
	//Converts from binary to decimal
	private static int bin2Dec(int num) {
		if (num == 0) {
			return 0;
		}
		int length = String.valueOf(num).length();
		int tenPower = (int) Math.pow(10, length - 1);
		int digit = num / tenPower;
		return (bin2Dec(num % tenPower) + digit * (int) Math.pow(2, length - 1));

	}

	/* Question 1.3 */
	//Converts from hexadecimal to decimal
	private static int hex2Dec(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		str = str.toUpperCase();
		int digit = (str.charAt(0) - '0' < 10 ? str.charAt(0) - '0' : str.charAt(0) - 'A' + 10);
		return (hex2Dec(str.substring(1)) + digit * (int) Math.pow(16, str.length() - 1));

	}

	/* Question 1.4 */
	//An helper array that helps the conversion from decimal to hexadecimal
	private static char[] hexaMap = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	//Converts from decimal to hexadecimal
	private static String dec2Hex(int num) {
		if (num < 16) {
			return "" + hexaMap[num];
		}
		int digit = num % 16;
		return (dec2Hex(num / 16) + "" + hexaMap[digit]);
	}

	/* Question 2 */

	/* Helper - prints an array except of EXCLUDE_NUMBER */
	private static void printArray(int[] arr) {
		System.out.print('{');
		printArray(arr, 0, true);
		System.out.println('}');
	}

	private static void printArray(int[] arr, int index, boolean isFirst) {
		if (index >= arr.length) {
			return;
		}
		if (arr[index] != EXCLUDE_NUMBER) {
			if (isFirst) {
				System.out.printf("%d", arr[index]);
			} else {
				System.out.printf(", %d", arr[index]);
			}
			isFirst = false;

		}
		printArray(arr, index + 1, isFirst);
	}

	//This method sends for printing all the subsets of a set
	private static void subset(int[] set, int[] subset, int idx) {
		if (idx == set.length) {
			printArray(subset);
			return;
		}

		subset[idx] = EXCLUDE_NUMBER;
		//Sends the subset in which the number in the "idx" place is not being shown
		subset(set, subset, idx + 1);

		subset[idx] = set[idx];
		//Sends the subset in which the number in the "idx" place is being shown
		subset(set, subset, idx + 1);

	}

	/* Question 3 */
	//Prints a string with the delimiter between the characters
	private static void specialPrint(String str, char delimeter) {
		if (str == null || str.length() == 0) {
			return;
		}
		String firstChar = str.substring(0, 1);
		//This "if" statement checks weather the char to printed is the last char of not
		if (str.substring(1) != null && str.substring(1).length() != 0) {
			System.out.print(firstChar + delimeter);
		} else {
			System.out.print(firstChar);
		}
		specialPrint(str.substring(1), delimeter);
	}
}
