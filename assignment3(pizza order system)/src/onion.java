
public class onion implements CustomerDAO {
	public int price = 2;
	public String toppings = "Onion ";
	public onion(CustomerDAO topping){ // sums price and toppings
		price += topping.cost();
		toppings += topping.toppings();
	}
	public onion(){
		
	}
	public int cost() {
		return price;	
	}
	public String toppings() {
		return toppings;
	}
}
