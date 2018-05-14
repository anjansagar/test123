package learnJava;


import org.bytedeco.javacpp.opencv_core.Point;


public class CGetCoordinates extends CTemplateMatching {

    public static void main(String[] args) throws Exception {
        
       //matchImage("C:\\Users\\sagar\\Desktop\\My New_Project\\NoParams.png", "C:\\Users\\sagar\\Desktop\\My New_Project\\appet.png");
        
    	matchImage("C:\\Users\\sagar\\Desktop\\My New_Project\\device.png", "C:\\Users\\sagar\\Desktop\\My New_Project\\ph.PNG");
    	getCoordinates(0,0);
        
    }
    
    
    public static Point getCoordinates(int x_offset, int y_offset)
    {

        //finding Center point of the identified image
        @SuppressWarnings("resource")
		Point coordinates = new Point(((max.x() + (max.x() + template.cols())) / 2), ((max.y() + (max.y() + template.rows())) / 2));
    	
    	x_offset = coordinates.x()+ x_offset;
    	y_offset = coordinates.y()+ y_offset;
		
    	
    	Point newcoordinates = new Point(x_offset, y_offset);
    	System.out.println("x: "+newcoordinates.x()+ " y: "+ newcoordinates.y());
    	return newcoordinates;
    	
    }

}