
public class Data { // my class which holds peoples , methods to use with people and people's attributes
	public int id; 
	public String name;
	 public String gender;
	 public int weight;
	 public int height;
	 public int date;
	 public int calorie;
	 public int caloriebysport;
	 public int caloriebyfood;
	 public int caloriegap;
	 public int age; 
	 public Data(int f,String a,String b,int c,int d,int e){ // my constructor to assign all values into object
		 id = f;
		 name = a;
		 gender = b;
		 weight = c;
		 height = d;
		 date = e;
		 calorie = CalorieNeed(c,d,e,b); // my method to calculate calorie need
		 age = 2018 - e; // calculating age 
		 caloriegap = -CalorieNeed(c,d,e,b); // my calorie gap
	 }
	 public int CalorieNeed(int weight,int height,int date,String gender){ // my method to calculate calorie need
		 int result=0;	
		 if(gender.equals("male")){ // if it is male do this
		 		double 	Calculation =  66 + (13.75 * weight) + (5 * height) - (6.8 *(2018 - date));
		 		int CalorieCalculation = (int)Math.round(Calculation);
		 		result = CalorieCalculation;}// return calorie needed
		  if(gender.equals("female") ){ // if it is female do this
		 		double Calculation2 = 665 + (9.6 * weight)+(1.7 * height) - (4.7 * (2018 - date));
		 		int CalorieCalculation2 = (int ) Math.round(Calculation2) ;
		 		result = CalorieCalculation2; // return calorie needed
		 	}
		 	return result;
	 }
	 public int eatFood(int calorie,int portion){ // my method to increase calorie when food is eaten
			calorie = calorie * portion;
			return calorie;
		}
	 public int doSport(int calorie,int time){ // my method to decrease calorie when sport is done
			calorie = (int)Math.round((calorie * time )/60);
			return calorie;
		}
	 public int calorieGap(int calorie,int caloriebyfood,int caloriebysport){ // to calculate calorie gap
		 int Gap = caloriebyfood - calorie - caloriebysport;
		 return Gap;
	 }
}
