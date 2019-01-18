import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
	FileIO x = new FileIO();
	ArrayList<customer> customerfile = x.readFile(); // my customer array
	ArrayList<AmericanPan> americanorder = new ArrayList<AmericanPan>(); // my american pizza orders
	ArrayList<Neapolitan> neapolitanorder = new ArrayList<Neapolitan>(); // my neapolitan pizza orders
	BufferedReader br = null;
	BufferedWriter writer = null;// to write i/o
	FileWriter fw = null;// to write i/o
	int j=0;
	try { // opening a new file to write something into it
		fw = new FileWriter("output.txt"); // opens
		writer = new BufferedWriter(fw); // my shortcut to write
	try {
	        br = new BufferedReader(new FileReader(args[0]));//getting people.txt
	        String line;
	        while ((line = br.readLine()) != null) { // reading line by line
                String[] parts = line.split("\\s+");               
                if(parts[0].equals("AddCustomer")){  
                    int part1 = Integer.parseInt(parts[1]); // id	
                    String part2 = parts[2]; // name
                    String part3 = parts[3]; // surname 
                    String part4 = (parts[4]); // telephone number
                	customerfile.add(new customer(part1,part2,part3,part4)); // creating a new object
          //      	System.out.println("Customer "+part1+" "+part2+" added");
                	writer.write("Customer "+part1+" "+part2+" added\n");
                }
                if(parts[0].equals("RemoveCustomer")){ // removing customer
                    int part5 = Integer.parseInt(parts[1]); // id	
                	for(int i=0;i<customerfile.size();i++){
                		if(customerfile.get(i).customerID==part5){                			
                //			System.out.println("Customer "+customerfile.get(i).customerID+" "+customerfile.get(i).customerName+" removed");
                			writer.write("Customer "+customerfile.get(i).customerID+" "+customerfile.get(i).customerName+" removed\n");
                			customerfile.remove(i); // removing
                			for(int a=0;a<americanorder.size();a++){ // removing removed customers order
                				if(americanorder.get(a).orderNo==(customerfile.get(i).orderNo)){
                					americanorder.remove(a);
                				}
                				else if(neapolitanorder.get(a).orderNo==(customerfile.get(i).orderNo)){
                					neapolitanorder.remove(a);
                				}
                			}
                			
                		}
                	}
                }
               if(parts[0].equals("CreateOrder")){ // creating order
                	int part=Integer.parseInt(parts[2]);
                	int part1=Integer.parseInt(parts[1]);
                	for(j=0;j<customerfile.size();j++){
                		if(customerfile.get(j).customerID==part){
                			customerfile.get(j).orderNo=part1;
                		//	System.out.println("Order "+part1+" created");
                			writer.write("Order "+part1+" created\n");

                			break;
                		}
                	}
              //  	System.out.println("Order "+customerfile.get(j).orderNo+" created"); // order created
                }
                if(parts[0].equals("AddPizza")){ // creating new pizza
                	int part=Integer.parseInt(parts[1]);
                	if(parts[2].equals("AmericanPan")){ // new american pizza
                    	String line2="AmericanPan ";
                		int count=0;
                	      for(int q=3;q<parts.length;q++){
                	    	  line2 +=parts[q]+" ";
                	      }
                	      Scanner scanner = new Scanner(line2);
                  		Constructor<?> c = Class.forName(parts[2]).getConstructor(CustomerDAO.class);
                		scanner.next();
                		Constructor<?> d = Class.forName(parts[3]).getConstructor(CustomerDAO.class); // first topping
                		scanner.next();
                		if(scanner.hasNext()){ // if there is another topping
                    		Constructor<?> e = Class.forName(parts[4]).getConstructor();
                    		scanner.next();
                    		if(scanner.hasNext()){ // if there is 3re topiing
                        		Constructor<?> f = Class.forName(parts[5]).getConstructor();
                        		americanorder.add((AmericanPan)c.newInstance(d.newInstance(e.newInstance(f.newInstance())))); // create pizza with 3
                        		americanorder.get(count).orderNo=part;
              //          		System.out.println("AmericanPan pizza added to order "+americanorder.get(count).orderNo);
                        		writer.write("AmericanPan pizza added to order "+americanorder.get(count).orderNo+"\n");

                        		count +=1;
                        		

                    		}
                    		americanorder.add((AmericanPan)c.newInstance(d.newInstance(e.newInstance()))); // create pizza with 2
                    		americanorder.get(count).orderNo=part;
                 //   		System.out.println("AmericanPan pizza added to order "+americanorder.get(count).orderNo);
                    		writer.write("AmericanPan pizza added to order "+americanorder.get(count).orderNo+"\n");


                    		count +=1;
                		}
                		else{
                    		americanorder.add((AmericanPan)c.newInstance(d.newInstance())); // create pizza with 1
                    		americanorder.get(count).orderNo=part;
                    	//	System.out.println("AmericanPan pizza added to order "+americanorder.get(count).orderNo);
                    		writer.write("AmericanPan pizza added to order "+americanorder.get(count).orderNo+"\n");

                    		count +=1;
                		}
                }
	        } // add pizza ends
                if(parts[0].equals("AddPizza")){ // same part but for   t he neapolitan
                	int part=Integer.parseInt(parts[1]);

                	if(parts[2].equals("Neapolitan")){
                    	String line3="Neapolitan ";

                		int count2=0;
              	      for(int q=3;q<parts.length;q++){
              	    	  line3 +=parts[q]+" ";
              	      }
              	      Scanner scanner = new Scanner(line3);
                		Constructor<?> c = Class.forName(parts[2]).getConstructor(CustomerDAO.class);
              		scanner.next();
              		Constructor<?> d = Class.forName(parts[3]).getConstructor(CustomerDAO.class);
              		scanner.next();
              		if(scanner.hasNext()){
                  		Constructor<?> e = Class.forName(parts[4]).getConstructor();
                  		scanner.next();
                  		if(scanner.hasNext()){
                      		Constructor<?> f = Class.forName(parts[5]).getConstructor();
                      		neapolitanorder.add((Neapolitan)c.newInstance(d.newInstance(e.newInstance(f.newInstance()))));// with 3 topping
                      		neapolitanorder.get(count2).orderNo=part;
                   //   		System.out.println("Neapolitan pizza added to order "+neapolitanorder.get(count2).orderNo);
                      		writer.write("Neapolitan pizza added to order "+neapolitanorder.get(count2).orderNo+"\n");
                      		count2 +=1;
                  		}
                  		neapolitanorder.add((Neapolitan)c.newInstance(d.newInstance(e.newInstance()))); // with 2 toppng
                  		neapolitanorder.get(count2).orderNo=part;
                //  		System.out.println("Neapolitan pizza added to order "+neapolitanorder.get(count2).orderNo);
                  		writer.write("Neapolitan pizza added to order "+neapolitanorder.get(count2).orderNo+"\n");


                  		count2 +=1;
              		}
              		else{
                  		neapolitanorder.add((Neapolitan)c.newInstance(d.newInstance())); //one topping
                  		neapolitanorder.get(count2).orderNo=part;
            //      		System.out.println("Neapolitan pizza added to order "+neapolitanorder.get(count2).orderNo);
                  		writer.write("Neapolitan pizza added to order "+neapolitanorder.get(count2).orderNo+"\n");

                  		count2 +=1;
              		}
                }
	        } // add piazza ends*/
                if(parts[0].equals("AddDrink")){ // add drinks
            		int part=Integer.parseInt(parts[1]);
            		for(int k=0;k<americanorder.size();k++){
            			if(americanorder.get(k).orderNo==part){
            				americanorder.get(k).drink=1;

            			}
            		}
            		for(int f=0;f<neapolitanorder.size();f++){
            			if(neapolitanorder.get(f).orderNo==part){
            				neapolitanorder.get(f).drink=1;

            			}
            		}
         //   		System.out.println("Drink Added to order "+part); // add drinks
            		writer.write("Drink Added to order "+part+"\n");


                } // add drink ends
                if(parts[0].equals("PayCheck")){ // calculates all price and prints
                	int total = 0;
                	int part=Integer.parseInt(parts[1]);
              //  	System.out.println("PayCheck For Order "+part);
                	writer.write("PayCheck For Order "+part+"\n");

                	for(int l=0;l<americanorder.size();l++){
                		if(americanorder.get(l).orderNo==part){
             //   			System.out.print("\t"+americanorder.get(l).toppings()+americanorder.get(l).cost()+"$");
                			writer.write("\t"+americanorder.get(l).toppings()+americanorder.get(l).cost()+"$\n");

                			total +=americanorder.get(l).cost();
                			
                			if(americanorder.get(l).drink==1){
                //				System.out.println(americanorder.get(l).cost()+1);
                			}
                			else{
              //  				System.out.println(americanorder.get(l).cost());  
                				}
                		}
                	}
                	for(int m=0;m<neapolitanorder.size();m++){ // if it is neapolitan pizza
                		if(neapolitanorder.get(m).orderNo==part){
                	//		System.out.print("\n\t"+neapolitanorder.get(m).toppings()+neapolitanorder.get(m).cost()+"$");
                			writer.write("\t"+neapolitanorder.get(m).toppings()+neapolitanorder.get(m).cost()+"$\n");

                			total +=neapolitanorder.get(m).cost();
                			if(neapolitanorder.get(m).drink==1){
                		//		System.out.println(neapolitanorder.get(m).cost()+1);
                		//		System.out.println("\n\t"+"SoftDrink 1$");
                				writer.write("\t"+"SoftDrink 1$");

                				total += 1;
                    			

                			}
                			else{
                	//			System.out.println(neapolitanorder.get(m).cost());
                				writer.write(neapolitanorder.get(m).cost()+"\n");

                			}
                		}
                	}
            //    	System.out.println("\t"+"Total :"+total+"$");
                	writer.write("\n\t"+"Total :"+total+"$\n");

                }
                if(parts[0].equals("List")){ // prints all customers
                	writer.write("Customer List:\n");
                	Collections.sort(customerfile, new Comparator<customer>() { // my sort function to sort names

                        public int compare(customer o1, customer o2) {
                            return o1.customerName.compareTo(o2.customerName);
                        }
                    });
             //   	System.out.println(customerfile.get(0).customerName);
                	for(int g=0;g<customerfile.size();g++){
                	//	System.out.println(customerfile.get(g).customerID+" "+customerfile.get(g).customerName+" "+customerfile.get(g).customerSurname+" "+customerfile.get(g).phone_number);
                		writer.write(customerfile.get(g).customerID+" "+customerfile.get(g).customerName+" "+customerfile.get(g).customerSurname+" "+customerfile.get(g).phone_number+"\n");

                	}
                }
                
	        } // while ends
	        
	        } // try ends inner try
	catch (IOException d) {
	        d.printStackTrace();
	    } finally {
	        try {
	            if (br != null) {
	                br.close();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }} // finally ends

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
	} // static main
 // main ends class
