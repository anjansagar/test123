package exception;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestException {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		
		try {
			testExceptions();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException  e) {
			e.printStackTrace();
		}

	}
public static  void testExceptions() throws IOException, FileNotFoundException{
		
	}
}