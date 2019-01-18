import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class FileIO {
	public ArrayList<customer> readFile(){
		ArrayList<customer> arraylist = new ArrayList<customer>();
		BufferedReader br = null;
		try {
	        br = new BufferedReader(new FileReader("customer.txt"));//getting people.txt
	        String line;
	        while ((line = br.readLine()) != null) { // reading line by line
                String[] parts = line.split("\\s+");
                int part1 = Integer.parseInt(parts[0]); // id	
                String part2 = parts[1]; // name
                String part3 = parts[2]; // surname 
                String part4 = (parts[3]); // telephone number
                arraylist.add(new customer(part1,part2,part3,part4));
	        }
	    } catch (IOException d) {
	        d.printStackTrace();
	    } finally {
	        try {
	            if (br != null) {
	                br.close();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
		return arraylist;
} // customer file 
}
