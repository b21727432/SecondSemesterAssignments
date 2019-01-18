
public class Neapolitan {
	public int price = 10;
	public String toppings="Neapolitan ";
	public int orderNo;
	public int drink;
	public Neapolitan(CustomerDAO topping){ // sums price and toppings
		price += topping.cost();
		toppings +=topping.toppings();
	}
	public int cost(){
		return price;
	}
	public String toppings(){
		return toppings;
	}
}
