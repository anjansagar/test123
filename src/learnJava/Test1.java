package learnJava;

class Test1 {
/*	
	static int add(int a, int b)

	{
		return a+b;
	}
	static int add(int a, int b, int c)

	{
		return a+b+c;
	}
	static class overload{
		
	
		public static void main(String[] args){ 
		System.out.println(Test1.add(12,14));
		System.out.println(Test1.add(12,24,56));
	}
	}}
*/
	 
		static int add(int a,int b){return a+b;}  
		static int add(int a,int b,int c){return a+b+c;}  
		}  
		class TestOverloading1{  
		public static void main(String[] args){  
		System.out.println(Test1.add(11,11));  
		System.out.println(Test1.add(11,11,11));  
		}} 