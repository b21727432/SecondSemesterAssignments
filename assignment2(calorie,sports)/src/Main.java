import java.io.*;
import java.util.Arrays;
public class Main {
public static void main(String[] args) throws IOException  {
	Spors[] sportArray = new Spors[50]; // my array to store sports
	Data[] peopleArray = new Data[50]; // my array to store people
	Foods[] FoodsArray = new Foods[100]; // my array to store foods
	int[] IslemSýra = new int[50]; // to use in printList I store id's in order to command list
	int [] NoDupArray = new int[50];
	BufferedReader br = null;// to read i/o
	BufferedWriter writer = null;// to write i/o
	FileWriter fw = null;// to write i/o
	int sayac=0,sayac2=0,sayac3=0,sayac4=0,h=0,g=0; // my variables
	try { // opening a new file to write something into it
		fw = new FileWriter("monitoring.txt"); // opens
		writer = new BufferedWriter(fw); // my shortcut to write
        try {
            br = new BufferedReader(new FileReader("people.txt"));//getting people.txt
            String line;
            while ((line = br.readLine()) != null) { // reading line by line
                String[] parts = line.split("\t");
                int part1 = Integer.parseInt(parts[0]);
                String part2 = parts[1];
                String part3 = parts[2];
                int part4 = Integer.parseInt(parts[3]);
                int part5 = Integer.parseInt(parts[4]);
                int part6 = Integer.parseInt(parts[5]);
                peopleArray[sayac++]=new Data(part1,part2,part3,part4,part5,part6); // assigning my objects into array
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
        try { // block to read sports
            br = new BufferedReader(new FileReader("sport.txt")); // reading file
            String line2;          
            while ((line2 = br.readLine()) != null) { // reading line by line
                String[] parts2 = line2.split("\t");
                int part21 = Integer.parseInt(parts2[0]);
                String part22 = parts2[1];              
                int part23 = Integer.parseInt(parts2[2]);              
                sportArray[sayac2++]=new Spors(part21,part22,part23); // assigning objects into sport array
            }
        } catch (IOException f) {
            f.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try { // reading foods
            br = new BufferedReader(new FileReader("food.txt"));
            String line3;            
            while ((line3 = br.readLine()) != null) { // reading line by line
                String[] parts3 = line3.split("\t");
                int part31 = Integer.parseInt(parts3[0]);
                String part32 = parts3[1];              
                int part33 = Integer.parseInt(parts3[2]);              
                FoodsArray[sayac3++]=new Foods(part31,part32,part33); //assigning objects into food array               
            }
        } catch (IOException q) {
            q.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try { // reading commands
            br = new BufferedReader(new FileReader(args[0])); // reading commands
            String line4;
            int sayar = countLines(args[0]);
            
            int sayar2=0;
            while ((line4 = br.readLine()) != null) { // reading commands line by line
     //           System.out.println(line4);
            	sayar2++;
               if(line4.equals("printList")){ // if command is printList
            	   NoDupArray=eleminateDuplicates(IslemSýra);// removing duplicate elements in IslemSýra array
            	   for(g=0;g<NoDupArray.length;g++){          // to get the order just like command   		
            		   for(sayac4=0;sayac4<(sayac);sayac4++){
                		if(NoDupArray[g]==peopleArray[sayac4].id){ // if they are equal print the equal one
                			if(peopleArray[sayac4].caloriebyfood!=0 || peopleArray[sayac4].caloriebysport!=0){// if people eat anything or done anything print him. if he does nothing don't print him
                				writer.write(peopleArray[sayac4].name +"\t"+ peopleArray[sayac4].age +"\t"+ peopleArray[sayac4].calorie +"kcal\t"+ peopleArray[sayac4].caloriebyfood +"kcal\t"+ peopleArray[sayac4].caloriebysport+"kcal\t");// printing the attributes
                				if(peopleArray[sayac4].caloriegap>0){ // to print + sign if calorie gap > 0
                					writer.write("+"+peopleArray[sayac4].caloriegap+"kcal\n");
                				}
                				else{
                					writer.write(peopleArray[sayac4].caloriegap+"kcal\n"); // if gap < 0 it automatically prints - so nothing to do
                				}
                	}
                		}
                	} // for ends
                	}
                }
               else if(line4.contains("p")){ // if command is print a specific person 
      //          	System.out.println(line4.substring(6, line4.length()-1));// it works
                	int x = Integer.parseInt(line4.substring(6, line4.length()-1)); // translate the string into integer(specific string)
                	for(int i = 0;i<sayac;i++){
                		if(peopleArray[i].id==x){
                			writer.write(peopleArray[i].name +"\t"+ peopleArray[i].age +"\t"+ peopleArray[i].calorie +"kcal\t"+ peopleArray[i].caloriebyfood +"kcal\t"+ peopleArray[i].caloriebysport +"kcal\t"+peopleArray[i].caloriegap+"kcal\n");// write the person's attributes if we found the person with the given id
                		//	writer.write("***************\n");
                		}
                	}
            }
               else{ // if it is not print list or print(x) than person eats or does sports
            	   String[] parts5 = line4.split("\t");        	   
                   int part51 = Integer.parseInt(parts5[0]); // getting the person's id
                   int part52 = Integer.parseInt(parts5[1]); // this is sport or food we must determine what is this
                   int part53 = Integer.parseInt(parts5[2]); // if food portion if sport time
                   IslemSýra[h]=part51;
                   h++;
                   if(part52<=1999){ // food id' start with 10 11 12 so if number is less than 1999 it must be food             	   
                	   for(int y = 0; y < sayac3 ; y++){
                		   if(FoodsArray[y].id==part52){
                			   for(int t=0;t<sayac;t++){
                			   	   if(peopleArray[t].id==part51){
                			   		   peopleArray[t].caloriebyfood=peopleArray[t].caloriebyfood+(FoodsArray[y].calories)*part53; // increasing the calorie taken by food
                			   		   writer.write(peopleArray[t].id+"\thas\ttaken\t"+ FoodsArray[y].calories*part53+ "kcal\tfrom\t"+FoodsArray[y].name+"\n");// writing it
                			   //		   writer.write("***************\n");
                			   		   peopleArray[t].caloriegap=peopleArray[t].caloriegap+(FoodsArray[y].calories*part53);          // updating our caloriegap      				   
                			   	   }
                			   }
                		   }
                	   }
                   }
                   if(part52>=2000){ // sports start with 20  so if it is more than 2000 it must be sport 
                	   for(int z=0;z < sayac2;z++){
                		   if(sportArray[z].id==part52){
                			   for(int v = 0;v < sayac ; v++){
                				   if(peopleArray[v].id==part51){
                					   peopleArray[v].caloriebysport=peopleArray[v].caloriebysport+(sportArray[z].calories)*part53/60; // increasing the calorie burned by sport
                					   writer.write(peopleArray[v].id+"\thas\tburned\t"+(sportArray[z].calories)*part53/60+"kcal\t" + "thanks\tto\t"+ sportArray[z].name+"\n");// writing calorieburned and sport id
                					//   writer.write("***************\n");
                					   peopleArray[v].caloriegap=peopleArray[v].caloriegap-sportArray[z].calories*part53/60; // updating our caloriegap
                				   }
                			   }
                		   }
                	   }
                   }
               }
               if (sayar2>sayar){
               }
               else{
            	   writer.write("***************\n");
               }
            }// while ends
        } // try ends
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (writer != null)
				writer.close();
			if (fw != null)
				fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
	 // main end
public	static int[] eleminateDuplicates(int[] arrayWithDuplicates){ // our method to remove duplicate elements in array
		int noOfUniqueElements = arrayWithDuplicates.length;
		int size = arrayWithDuplicates.length;
		for (int i = 0; i < size; i++) {
			for (int j = i+1; j < size; j++) {
			 	if(arrayWithDuplicates[i] == arrayWithDuplicates[j]) {
			 		arrayWithDuplicates[j] = arrayWithDuplicates[size-1];		
			 		size--;
			 		j--;
			 		noOfUniqueElements--;
			 	}
			}
		}
		int[] arrayWithoutDuplicates = Arrays.copyOf(arrayWithDuplicates, noOfUniqueElements);
		return arrayWithoutDuplicates;
		} // function ends
public static void printStar(){/*
	for(dint i=0;i<15;i++){
		System.out.print("*");
	}
	System.out.print("*\n");*/
	System.out.println('*'*15);
}			
public static int countLines(String filename) throws IOException { // my method to calculate number of lines so i can print stars and avoid printing the last star
    InputStream is = new BufferedInputStream(new FileInputStream(filename));
    try {
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        boolean empty = true;
        while ((readChars = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n') {
                    ++count;
                }
            }
        }
        return (count == 0 && !empty) ? 1 : count;
    } finally {
        is.close();
    }
}
} // class ends 
	


