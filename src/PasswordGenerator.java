import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PasswordGenerator {

	static boolean proceed = false;
	static Scanner params = new Scanner(System.in);
	static boolean[]specials = new boolean [3]; //[0] - uppercase [2]- special chars [1]- numbers
	static int [][]specialsRef = {{65,90}, {48,57}, {33,47}};
	static int minChars;
	
	public static void main(String[] args) throws IOException {
		
		File pssFile = new File ("psswrd.txt");
		PrintWriter print1 = new PrintWriter (new FileWriter(pssFile, true));
		String password = "";
		int count = 0;
		
		System.out.println("Welcome to Password Generator v1.2!\nBy Vidith Madhu");
		System.out.println("\nBegin by giving me some information: \n");
		
		while(!proceed) {
		System.out.print("How many characters does your password need?(Minimum 6) ");
		
		try {
			
			minChars = params.nextInt();
			if(minChars >=6) {
				
			proceed = true;
			
			} else {
			System.out.println("At least 6 characters required!");	
			params.nextLine();
			}
			
		} catch (InputMismatchException a) {
			
			System.out.println("Invalid input");
			params.nextLine();
			
			
		}
		
	}
	
	System.out.println();
	proceed = false;
	params.nextLine();
	specialsPutter("Does your password need uppercase characters? (y/n) ", 0);
	
	
	System.out.println();
	proceed = false;
	params.nextLine();
	specialsPutter("Does your password need special characters? (y/n) ", 2);
	
	System.out.println();
	proceed = false;
	params.nextLine();
	specialsPutter("Does your password need numbers? (y/n) ", 1);
	
	
	while(count < minChars) {
		
		int rand = ((int)(Math.random() * 3));
		//int startRand = ((int)(Math.random() * 2));
			
			if(specials[rand]){
				
				if(count < minChars) {
					
					password+= Character.toString((char)((int)(Math.random() * ((specialsRef[rand][1] - specialsRef[rand][0]) + 1)) + specialsRef[rand][0]));
					count++;
				
				} else {
					
					break;
				}
				
			}
			
		
		
		if(count < minChars) {
			
			password += Character.toString((char)((int)(Math.random() * 26) + 97));
			count++;
		}
				
	}

	System.out.println("\nPassword: " + password);
	params.nextLine();
	System.out.print("Is this password satisfactory? (y/n) ");
	
	
	if(params.next().equals("y")) {
		
		System.out.println();
		System.out.print("Would you like the password to be saved on the included password file? (y/n) ");
		params.nextLine();
		
		if(params.next().equals("y")){
			
			System.out.println();
			System.out.print("Enter a title for this password: ");
			params.nextLine();
			print1.println("\n" + params.nextLine());
			print1.println(password);
			print1.close();
			System.out.println("Your password has been saved!");
			
		} else {
			
			System.out.println("Ok, bye!");
		}
			
		
	} else {
		
		System.out.println("Sorry to hear that, please try again");
	}
	
}
	
	private static void specialsPutter(String message, int index) {
		
		while(!proceed) {
			
			System.out.print(message);
			String response = params.next();
			if(response.equals("y")) {
				
				specials[index] = true;
				proceed = true;
				
			} else if(response.equals("n")) {
				
				specials[index] = false;
				proceed = true;
				
			} else {
				
				System.out.println("Invalid input (answer with y/n)");
			}
			
		}
		
	}

}
