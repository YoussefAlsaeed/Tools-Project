
package entity;
import java.awt.MenuItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

@Entity
@Table(name = "ORDERS")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	  
	private LocalDateTime date= java.time.LocalDateTime.now();
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private double total;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "runnerID")
	private Runner runner;
	
	@ManyToOne
	@JoinColumn(name = "resturantID")
	private Restaurant restaurant;
	
	@Enumerated(EnumType.ORDINAL)
	private OrderStatus status;
	
	@ManyToMany
	@JoinTable(
	    name = "ORDER_MEAL",
	    joinColumns = @JoinColumn(name = "order_id"),
	    inverseJoinColumns = @JoinColumn(name = "meal_id"))
	private List<Meal> meals = new ArrayList<Meal>();
	
	public List<Meal> getItems() {
		System.out.println(java.time.LocalDateTime.now());  
		return meals;
	}

	public void setItems(List<Meal> items) {
		this.meals = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Runner getRunnerID() {
		return runner;
	}

	public void setRunnerID(Runner runnerID) {
		this.runner = runnerID;
	}

	public Restaurant getRestauarant() {
		return restaurant;
	}

	public void setRestauarant(Restaurant restauarant) {
		this.restaurant = restauarant;
	}

	public OrderStatus getStatus() {
		return status;
	}
	public String toString() {
		String output="";
		output+="\t                  Order ID = "+id+"\n";
		output+="Date = "+date+"\n";
		output+="Items ordered : \n";
		for(int i=0;i<meals.size();i++)
		{
			output+=meals.get(i).toString()+"\n";
		}
		output+="Runner fees = "+runner.getDeliveryFees()+" \n";
		output+="Total = "+total+"\n";
		output+="Order Status = "+status+"\n";
		output+="Restaurant name = "+restaurant.getName()+"\n";
		return output;
		
		
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getDeliveryFee() {
		// TODO Auto-generated method stub
		return null;
	}
}
