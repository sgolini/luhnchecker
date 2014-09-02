import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/* Luhn Algorithm filter
 * 
 * reads in .txt file of ID numbers, writes IDs that don't follow the Luhn algorithm to an output file
 * 
 * @author Sam Golini
 * Fiksu, Inc.
 * July 1, 2014
 */


public class LuhnChecker {
	

	public LuhnChecker (String pathName){
	LinkedList<String> serialList = readFile(Driver.pathName); //open up and read .txt file in as linked list of strings
	FileWriter output = null;//initialize output file writer
	try {
		output = new FileWriter(Driver.pathName.substring(0,Driver.nameLength-4) + "_failed.txt");//output file name
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	while (!serialList.isEmpty()){                            //while list of serial numbers isn't empty
		//for each serial number
		String line = serialList.poll();                        //remove and select from linked list
		int numDigits = line.trim().length();                   //number of digits in ID
		int[] digitArray = stringToDigits(line, numDigits);     //create array of digits
		int[] luhnArray = new int[numDigits];                   //create luhn algorithm array of the size of how many digits there are
		Boolean flip = false;                                   //when true, multiple the digit by two
		for (int i=numDigits-1;i>=0;i--){                       //for each digit in serial no.
			if (flip && digitArray[i]<10 && digitArray[i]>-1){    //if it's a digit to be doubled and it's an integer 0-9
				int multByTwo = digitArray[i]*2;                    //mult by 2
				if (multByTwo>=10){                                 //if double has double digits, add the digits
					int first = multByTwo/10;                         //get first digit
					int second = multByTwo%10;                        //get second
					int sum = first+second;                           //add the two digits
					luhnArray[i]=sum;                                 //place in luhn array
					flip=!flip;                                       //alternate the boolean to mult by two every other time
				}
				else{
					luhnArray[i]=multByTwo;                           //just replace with double if not double digits
					flip=!flip;                                       //alternate the boolean to mult by two every other time
				}
			}
			else if (!flip && digitArray[i]<10 && digitArray[i]>-1){//if digit isn't to be doubled and is 0-9
				luhnArray[i]=digitArray[i];                           //don't double, just copy digit
				flip=!flip;                                         //alternate the boolean to mult by two every other time
			}
		}
		try {
			int luhnSum = 0;
			for (int i=0;i<luhnArray.length;i++){
				luhnSum = luhnSum+luhnArray[i];//take the sum of the digits
											//after the luhn algorithm is complete (every other is doubled and digits added
			}
			if (luhnSum%10!=0){		//if not a base 10 number	
				output.write(line + "\n");	 //must fail test, write to output file
			}
		}	
		 catch (IOException e) {
				System.out.println("input/output exception");	
		 }
	}
	
	try{
	output.close();//close output file
	}
	catch (IOException e){
		System.out.println("input/output exception");
	}
	} 
	
	//function to take a string and convert into array of digits
	//@param string and length
	public int[] stringToDigits(String n, int numDigits){
	    int[] digits= new int[numDigits];
	    String temp = n;
	    for(int i = 0; i < numDigits; i++){
	    	digits[i] = temp.charAt(i)-48;    //take the ASCII character and convert to the integer by subtracting 48 (0 is 48 in ASCII)	
	    }
	    return digits;
	}	
	  
	//reads in file of ID numbers and creates linked list of strings
	  public static LinkedList<String> readFile(String fileName) {
		    LinkedList<String> lineList = new LinkedList<String>();
		    try {
		      FileReader reader = new FileReader(fileName);
		      Scanner in = new Scanner(reader);
		      Integer i=0;
		      while (in.hasNextLine()) {
		    	String temp = in.nextLine();
		        lineList.add(i, temp); //add line to linked list as element i
		        i++;
		      }
		      in.close(); //close when no new lines
		    }
		    catch (IOException exception) {
		      System.out.println("Error processing file:" + exception);
		    }
		    
		    return lineList;
		  }
}
