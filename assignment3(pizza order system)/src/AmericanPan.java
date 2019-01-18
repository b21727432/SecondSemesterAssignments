
public class AmericanPan {
	public int price = 5;
	public String toppings="AmericanPan ";
	public int orderNo;
	public int drink;
	public AmericanPan(CustomerDAO topping){ // sums price and toppings
		price += topping.cost();
		toppings += topping.toppings();
	}
	public int cost(){
		return price;
	}
	public String toppings(){
		return toppings;
	}
}
