
public class Book {
	//Vars
	private String title;
	private String author;
	private String publisher;
	private String date;
	private String checkDate;
	private String dueDate;
	private Boolean avalability = true;
	private long borrowedDays;
	private long overdueDays;
	private double lateFee;
	
	//Constructor
	public Book(String title, String author, String publisher, String date) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.date = date;
	}
	
	public Book(String title, long borrowedDays, long overdueDays, double lateFee) {
		this.title = title;
		this.borrowedDays = borrowedDays;
		this.overdueDays = overdueDays;
		this.lateFee = lateFee;
	}
	
	//Getters and Setters
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getAvalability() {
		if(avalability == false) {
			return "no";
		}
		
		return "yes";
	}
	
	public void setAvalability(Boolean avalability) {
		this.avalability = avalability;
	}
	
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	
	public String getCheckDate() {
		return checkDate;
	}

	public long getBorrowedDays() {
		return borrowedDays;
	}

	public long getOverdueDays() {
		return overdueDays;
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setBorrowedDays(long borrowedDays) {
		this.borrowedDays = borrowedDays;
		
	}

	public void setOverdueDays(long overdueDays) {
		this.overdueDays = overdueDays;
		
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
		
	}
	

}
