
public class pepper implements CustomerDAO{
	public int price = 1;
	public String toppings = "Pepper ";
	public pepper(CustomerDAO topping){ // sums price and toppings
		price += topping.cost();
		toppings += topping.toppings();
	}
	public pepper(){
		
	}
	public int cost() {
		return price;	
	}
	public String toppings() {
		return toppings;
	}
}
