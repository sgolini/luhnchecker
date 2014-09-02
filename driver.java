import java.io.File;
import javax.swing.JFileChooser;
/* Luhn Algorithm filter
 * 
 * runs Luhn Algorithm check
 * 
 * @author Sam Golini
 * Fiksu, Inc.
 * July 1, 2014
 */

public class Driver {
	public static String pathName;
	public static int nameLength;
  public static void main(String [] args){
  	pathName=getFilePath();
  	nameLength = pathName.length();
  	LuhnChecker filter = new LuhnChecker(pathName);
  }
	 /**
  * Puts up a fileChooser and gets the path name for the file to be opened.
  * Returns an empty string if the user clicks Cancel.
  * @return path name of the file chosen
  * 
  * credit: Prof. Thomas Cormen, Dartmouth College
  */
 public static String getFilePath() {
   // Create a file chooser.
   JFileChooser fc = new JFileChooser();
    
   int returnVal = fc.showOpenDialog(null);
   if (returnVal == JFileChooser.APPROVE_OPTION) {
     File file = fc.getSelectedFile();
     String pathName = file.getAbsolutePath();
     return pathName;
   }
   else
     return "";
  }
}
