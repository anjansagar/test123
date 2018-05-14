package learnJava;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.opencv_core.Point;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import java.util.concurrent.ThreadLocalRandom;
public class CTemplateMatching {

    public static Point min;
	public static Point max;
	public static Mat template;
    
    public static void matchImage(String src,String templ){
    	
        //read in image default colors
       Mat sourceColor = imread(src);
       Mat sourceGrey = new Mat(sourceColor.size(), CV_8UC1);
       cvtColor(sourceColor, sourceGrey, COLOR_BGR2GRAY);
       
       //load in template in grey 
       template = imread(templ,CV_LOAD_IMAGE_GRAYSCALE);//int = 0
       
       //Size for the result image
       Size size = new Size(sourceGrey.cols()-template.cols()+1, sourceGrey.rows()-template.rows()+1);
       Mat result = new Mat(size, CV_32FC1);
       
       //Using matchTemplate method to compare images
       matchTemplate(sourceGrey, template, result, TM_CCOEFF_NORMED);
       
       DoublePointer minVal= new DoublePointer();
       DoublePointer maxVal= new DoublePointer();
       min = new Point();
       max = new Point();
       
       //localize the minimum and maximum values in the result matrix  by using minMaxLoc
       //The functions minMaxLoc find the minimum and maximum element values and their positions
       minMaxLoc(result, minVal, maxVal, min, max, null);
       
       //Draw a rectangle around the highest possible matching area
       rectangle(sourceColor,new Rect(max.x(),max.y(),template.cols(),template.rows()), randColor(), 2, 0, 0);
       imwrite("blah2.png", sourceColor);
       //waitKey(0);    
       
    }
    
    public Point getMin() {
    	return min;
    }
    
    public Point getMax() {
    	return max;
    }
    
    public Mat getTemplate() {
    	return template;
    }
    
    public static Scalar randColor(){
        int b,g,r;
        b= ThreadLocalRandom.current().nextInt(0, 255 + 1);
        g= ThreadLocalRandom.current().nextInt(0, 255 + 1);
        r= ThreadLocalRandom.current().nextInt(0, 255 + 1);
        return new Scalar (b,g,r,0);
     }}
    
    
    /*

    public static String readImageText(String file){
		TessBaseAPI api = new TessBaseAPI();
		if (api.Init("", "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }	
		PIX image = lept.pixRead(file);
		
        api.SetImage(image);
        
       BytePointer outText = api.GetUTF8Text();
        String string = outText.getString();
        assertTrue(!string.isEmpty());
        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        lept.pixDestroy(image);
        return string;
	}
    // some usefull things.

    
    public static List<Point> getPointsFromMatAboveThreshold(Mat m, float t){
       List<Point> matches = new ArrayList<Point>();
       FloatIndexer indexer = m.createIndexer();
       for (int y = 0; y < m.rows(); y++) {
            for (int x = 0; x < m.cols(); x++) {
               if (indexer.get(y,x)>t) {
              System.out.println("(" + x + "," + y +") = "+ indexer.get(y,x));
              matches.add(new Point(x, y));
          }
         }
       }
       return matches;
    }
    public static byte[] readFile(String filename){
    	
    	try {
			return Files.readAllBytes(new File(filename).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    }
    public static void oldStyle(String[] args){
        //get color source image to draw red rect on later
        IplImage srcColor = cvLoadImage(args[0]);
        //create blank 1 channel image same size as the source 
        IplImage src = cvCreateImage(cvGetSize(srcColor), IPL_DEPTH_8U, 1);
        //convert source to grey and copy to src
        cvCvtColor(srcColor, src, CV_BGR2GRAY);
        //get the image to match loaded in greyscale. 
        IplImage tmp = cvLoadImage(args[1], 0);
        //this image will hold the strength of the match
        //as the template is translated across the image 
        IplImage result = cvCreateImage(
                        cvSize(src.width() - tmp.width() + 1,
                        src.height() - tmp.height() + 1), IPL_DEPTH_32F, src.nChannels());

        cvZero(result);

        // Match Template Function from OpenCV
        cvMatchTemplate(src, tmp, result, CV_TM_CCORR_NORMED);

        // double[] min_val = new double[2];
        // double[] max_val = new double[2];
        DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();

        CvPoint minLoc = new CvPoint();
        CvPoint maxLoc = new CvPoint();

        cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);

        // Get the Max or Min Correlation Value
        // System.out.println(Arrays.toString(min_val));
        // System.out.println(Arrays.toString(max_val));

        CvPoint point = new CvPoint();
        point.x(maxLoc.x() + tmp.width());
        point.y(maxLoc.y() + tmp.height());
        // cvMinMaxLoc(src, min_val, max_val,0,0,result);

        cvRectangle(srcColor, maxLoc, point, CvScalar.RED, 2, 8, 0); // Draw a
                                                                // Rectangle for
                                                                // Matched
                                                                // Region

        cvShowImage("Lena Image", srcColor);
        //cvWaitKey(0);
        cvReleaseImage(srcColor);
        cvReleaseImage(src);
        cvReleaseImage(tmp);
        cvReleaseImage(result);
    }
    
    //chandrakanth -  histogram check
    public static double HistMatch(String filePath, String compareFilePath) {
		IplImage baseImage = cvLoadImage(filePath);
		CvHistogram hist = getHueHistogram(baseImage);

		IplImage contrastImage = cvLoadImage(compareFilePath);
		CvHistogram hist1 = getHueHistogram(contrastImage);
		//CV_COMP_CHISQR
		double matchValue = cvCompareHist(hist, hist1, CV_COMP_CHISQR);
		baseImage.release();
		contrastImage.release();
		hist.release();
		hist1.release();
		cvReleaseImage(baseImage);
		cvReleaseImage(contrastImage);
		baseImage = null;
		contrastImage = null;
		hist = null;
		hist1 = null;
		System.out.println(matchValue);
		return matchValue;
	}
    public static CvHistogram getHueHistogram(IplImage image) {
		if (image == null || image.nChannels() < 1)
			new Exception("Error!");

		IplImage greyImage = cvCreateImage(image.cvSize(), image.depth(), 1);
		cvCvtColor(image, greyImage, CV_RGB2GRAY);

		// bins and value-range
		int numberOfBins = 256;
		float minRange = 0f;
		float maxRange = 255f;
		// Allocate histogram object
		int dims = 1;
		int[] sizes = new int[] {numberOfBins };
		int histType = CV_HIST_ARRAY;
		float[] minMax = new float[] {minRange, maxRange };
		float[][] ranges = new float[][] {minMax };
		int uniform = 1;
		CvHistogram hist = cvCreateHist(dims, sizes, histType, ranges, uniform);
		// Compute histogram
		int accumulate = 0;
		IplImage mask = null;
		IplImage[] aux = new IplImage[] {greyImage };

		cvCalcHist(aux, hist, accumulate, null);
		cvNormalizeHist(hist, 1);

		cvGetMinMaxHistValue(hist, minMax, minMax, sizes, sizes);
		greyImage.release();
		cvRelease(greyImage);
		greyImage = null;
		return hist;
	}*/
