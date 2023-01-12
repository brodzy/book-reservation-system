
public class Customer {
	//Vars
	private String name;
	private String address;
	private String phone;
	private String date;
	private int days;
	private int books = 0;
	Book book;
	
	//Constructor
	public Customer(String name, String address, String phone, String date) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.date = date;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getDays() {
		return days;
	}
	
	public int getBooks() {
		return books;
	}
	
	public int setBooks() {
		books += 1;
		return books;
	}
	
	public int returnBooks() {
		books -= 1;
		return books;
	}
	
}
