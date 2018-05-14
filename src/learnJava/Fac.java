package learnJava;

import java.util.Scanner;

public class Fac {

	public static void main(String[] args) {
		  int i,num,fact=1;  
		  System.out.println("Enter the number: ");
	        Scanner scanner = new Scanner(System.in);
	        num = scanner.nextInt();    
		  for(i=1;i<=num;i++){    
		      fact=fact*i;    
		  }    
		  System.out.println("Factorial of "+num+" is: "+fact);    
		 

	}

}
