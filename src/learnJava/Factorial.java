package learnJava;

public class Factorial {

	public static void main(String[] args) {
		int i,fact=1,n=5;
		for (i=1;i<=n;i++)
			fact=fact*i;
		System.out.println(fact);
	}

}
