import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Library {
	public static final Scanner scan = new Scanner(System.in);
	
	//Building list of default books
	public static void buildDefaultBooks(ArrayList<Book> library) {
		Book b1 = new Book("Harry Potter", "J.K. Rowling", "Scholastic", "6/27/1997");
		Book b2 = new Book("MAD MAN", "Samuel R. Delany", "Open Road Media", "6/1/1994");
		Book b3 = new Book("House of Cards", "Micheal Dobbs", "HarperCollins", "7/1/1989");
		Book b4 = new Book("Diary of a Wimpy Kid", "Jeff Kinney", "Amulet Books", "4/1/2007");
		Book b5 = new Book("Harry Potter 2", "J.K. Rowling", "Scholastic", "6/27/1997");
		Book b6 = new Book("MAD MAN 2", "Samuel R. Delany", "Open Road Media", "6/1/1994");
		Book b7 = new Book("House of Cards 2", "Micheal Dobbs", "HarperCollins", "7/1/1989");
		Book b8 = new Book("Diary of a Wimpy Kid 2", "Jeff Kinney", "Amulet Books", "4/1/2007");
		
		library.add(b1);
		library.add(b2);
		library.add(b3);
		library.add(b4);
		library.add(b5);
		library.add(b6);
		library.add(b7);
		library.add(b8);
		
	}
	
	//Building list of default customers
	public static void buildDefaultCustomers(ArrayList<Customer> people) {
		Customer c1 = new Customer("Eugine", "293 Apple St", "478-902-4583", "1/28/2022");
		Customer c2 = new Customer("Jonathan", "614 Boone Dr", "563-904-6832", "2/5/2022");
		Customer c3 = new Customer("Micheal", "843 Addington Dr", "330-593-5401", "5/13/2019");
		Customer c4 = new Customer("Jeff", "956 Harper St", "475-604-2042", "10/28/2015");
		
		people.add(c1);
		people.add(c2);
		people.add(c3);
		people.add(c4);
		
	}
	
	public static void addCustomer(ArrayList<Customer> people) {
		//Adding new customer, checks date to be in correct format
		String name;
		String address;
		String number;
		String date;
		
		System.out.print("\nPlease enter the name: ");
		name = scan.nextLine();
		System.out.print("\nPlease enter the address: ");
		address = scan.nextLine();
		System.out.print("\nPlease enter the phone number: ");
		number = scan.nextLine();
		System.out.print("\nPlease enter the registered date: ");
		date = scan.nextLine();
		outerloop:
			while(true) {
				if(validateJavaDate(date) == true) {
					break outerloop;
				}
				else {
					System.out.print("\nPlease enter a valid date in the format mm/dd/yyyy: ");
					date = scan.nextLine();
				}
			}
		
		Customer newCustomer = new Customer(name, address, number, date);
		
		people.add(newCustomer);
		
		System.out.println("\nCustomer sucessfully added!");
	}
	
	public static void delCustomer(ArrayList<Customer> people) {
		//List of registered customers being displayed
		System.out.print("\nRegistered Customers");
		System.out.println("\n--------------------");
		for(Customer p : people) {
			System.out.println(p.getName());
		}
		
		//Person input
		String person;
		System.out.print("\nPlease enter the customers name: ");
		person = scan.nextLine();
		
		//Checks to see if person has any borrowed books
		outerloop:
		while(true) {
			for(Customer p : people) {
				if(person.equals(p.getName()) && p.getBooks() == 0) {
					people.remove(p);
					break outerloop;
				}
			}
			System.out.print("\nCannot remove. Please return all borrowed books or please enter name again!: ");
			person = scan.nextLine();
		}
		
		System.out.println("\nCustomer sucessfully deleted!");		
	}
	
	public static void addBook(ArrayList<Book> library) {
		//Adding new book, checks date to be in correct format
		String title;
		String author;
		String publisher;
		String date;
		
		System.out.print("\nPlease enter the title: ");
		title = scan.nextLine();
		System.out.print("\nPlease enter the author: ");
		author = scan.nextLine();
		System.out.print("\nPlease enter the publisher: ");
		publisher = scan.nextLine();
		System.out.print("\nPlease enter the release date: ");
		date = scan.nextLine();
		outerloop:
			while(true) {
				if(validateJavaDate(date) == true) {
					break outerloop;
				}
				else {
					System.out.print("\nPlease enter a valid date in the format mm/dd/yyyy: ");
					date = scan.nextLine();
				}
			}
		
		
		Book newBook = new Book(title, author, publisher, date);
		
		library.add(newBook);
		
		System.out.println("\nBook sucessfully added!");
	}
	
	public static void rentBook(HashMap<String, String> retBook, ArrayList<Customer> people, ArrayList<Book> library, ArrayList<Book> rentBook, ArrayList<Customer> rentCustom) {
		//Map vars
		Customer rentedV;
		Book rentedK;
		
		//Displaying registered customers
		System.out.print("\nRegistered Customers");
		System.out.println("\n--------------------");
		for(Customer p : people) {
			System.out.println(p.getName());
		}
		
		//Customer input
		String customer;
		System.out.print("\nPlease enter the customers name to rent book: ");
		customer = scan.nextLine();
		
		//Checking if input matches registered list, checks if person has less than 7 books while setting their book count, adds name and title to map for later use
		outerloop:
		while(true) {
			for(Customer p : people) {
				if(customer.equals(p.getName())) {
					if(p.getBooks() < 7) {
						rentCustom.add(p);
						p.setBooks();
						rentedV = p;
						break outerloop;
					}
					else {
						System.out.println("\nChoose another name! Max amount of books rented out.");
						return;
					}
				}
			}
			System.out.print("\nPlease enter a valid name!: ");
			customer = scan.nextLine();
		}
		
		    //Displaying books avilable for rent
			if(library.isEmpty()) {
				System.out.print("\nNo books avaliable for rent try again later!\n");
			}
			else {
				System.out.print("\nBook Inventory");
				System.out.println("\n-------------");
				for(Book b : library) {
					System.out.println(b.getTitle());
				}
				
				//Book input
				String book;
				System.out.print("\nPlease enter the title of the book to rent: ");
				book = scan.nextLine();
				
				//Setting avalability of book to false while removing it from the library and putting it into the map
				outerloop:
				while(true) {
					for(Book b : library) {
						if(book.equals(b.getTitle())) {
							b.setAvalability(false);
							rentBook.add(b);
							library.remove(b);
							rentedK = b;
							retBook.put(rentedK.getTitle(), rentedV.getName());
							break outerloop;
						}
					}
					System.out.print("\nPlease enter a valid title!: ");
					book = scan.nextLine();
				}
				
				//Date input
				String date;
				System.out.print("\nPlease enter the checkout date: ");
				date = scan.nextLine();
				
				//Checking format of date while setting the checkout date and due date of book
				outerloop:
				while(true) {
					if(validateJavaDate(date) == true) {//Checking ddate
						for(Book b : rentBook) {
							if(book.equals(b.getTitle())) {//Setting checkout date and due date
								b.setCheckDate(date);
								b.setDueDate(newDueDate(date));
								break outerloop;
							}
							
						}
					}
					else {
						System.out.print("\nPlease enter a valid date in the format mm/dd/yyyy: ");
						date = scan.nextLine();
					}
				}
			}
}
		
	
	public static void returnBook(HashMap<String, String> retBook, ArrayList<Customer> people, ArrayList<Book> library, ArrayList<Book> rentBook, ArrayList<Customer> rentCustom) throws ParseException {
		//Vars
		ArrayList<Book> display = new ArrayList<Book>();
		String person;
		String book;
		String ans;
		double totalLateFee = 0;
		
			//Person Input
			System.out.print("\nPlease enter the customers name: ");
			person = scan.nextLine();
			
			//Displaying customers rented books
			System.out.println("\n" + person + "'s Rented Books");
			System.out.println("-------------------");
			for(Entry<String, String> entry: retBook.entrySet()) {
				if(person.equals(entry.getValue())){
					System.out.println(entry.getKey());
				}
			}
			
			//Subtracting book count from person
			outerloop:
			while(true) {
				for(Customer p : people) {
					if(person.equals(p.getName())) {
						p.returnBooks();
						break outerloop;
					}
				}
				System.out.print("\nUser not recognized in system. Please enter name again!: ");
				person = scan.nextLine();
			}
		
		outerouterloop:
		while(true) {
			//Book Input
			System.out.print("\nPlease enter the title of the book to return: ");
			book = scan.nextLine();
			
			outerloop:
			while(true) {
				for(int i = 0; i < rentBook.size(); i++) {
					if(book.equals(rentBook.get(i).getTitle())){//Checking if book is in rented list
						break outerloop;
					}
				}
				System.out.print("\nTitle not recognized in system. Please enter name again!: ");
				book = scan.nextLine();
			}
			
			//Date Input
			long borrowedDays;
			long overdueDays = 0;
			
			String date;
			System.out.print("\nPlease enter the return date in the format mm/dd/yyyy: ");
			date = scan.nextLine();
			double lateFee = 0;
			
			//Checking if return date is in correct format, calling method to compute borrowed days, while computing overdue days and late fee
			outerloop:
			while(true) {
				for(int i = 0; i < rentBook.size(); i++) {
					if(validateJavaDate(date) == true) {
						innerloop:
						while(true) {
							borrowedDays = computeDaysBetween(rentBook.get(i).getCheckDate(), date);//Computes days between return and checkout dates
							if (borrowedDays >= 0) {
								break innerloop;
							}
							System.out.print("\nPlease enter a date past the checkout date!: ");
							date = scan.nextLine();
						}
						
						if(borrowedDays > 7) {//Calculating overdue days and late fee
							overdueDays = borrowedDays - 7;
							lateFee = overdueDays * 3;
						}
						else {	
							lateFee = 0.00; //Sets late fee to be 0 if there are no overdue days
						}
						
						//Adding total late fee from returned books
						totalLateFee += lateFee;
						
						//Adding values from map to list for display
						for(Entry<String, String> entry: retBook.entrySet()) {
							if(person.equals(entry.getValue())){//Matching person to map
								if(book.equals(entry.getKey())) {//Matching book to map
									Book b = new Book(entry.getKey(), borrowedDays, overdueDays, lateFee);
									display.add(b);
								}
							}
						}
					
						//Updating books attributes/ customer attributes
						rentBook.get(i).setAvalability(true);//Changing books availability
						library.add(rentBook.get(i));//Adding book back to library
						rentBook.remove(rentBook.get(i));//Removing from rented book list
						rentCustom.remove(rentCustom.get(i));//Removing from rented customer list
						break outerloop;
					}
						
				}
				System.out.print("\nPlease enter a valid date in the format mm/dd/yyyy: ");
				date = scan.nextLine();
			}
			
			//Prompting user for another book to be returned
			System.out.println("\nWould you like to return another book? Please enter Y or N");
			ans = scan.nextLine();
			if(ans.equals("N")|| ans.equals("n")) {
				break outerouterloop;
			}
		}
		
		//Display receipt of returned books
		System.out.print("\n");
		System.out.printf("Customer Name: %s", person);
		System.out.printf("%-30s  %-30s  %-30s %-30s","\nBook Title"," Number of days Borrowed"," No of days Overdue"," Late Fee");
		for(Book b : display) {
			System.out.printf("\n%-30s  %-30s  %-30s $%-30.2f", b.getTitle(), b.getBorrowedDays(), b.getOverdueDays(), b.getLateFee());
		}
		System.out.printf("\n\nTotal Late Fee: %.2f", totalLateFee);
		System.out.print("\n");
        System.out.println("\n");
		
	}
	
	//Displays book report
	public static void bookReport(ArrayList<Book> rentBook, ArrayList<Customer> rentCustom, ArrayList<Book> library) {
		System.out.printf("\n%-30s  %-30s  %-30s","Title","Borrowed By","Avilability");
        for(int i = 0; i < rentBook.size(); i++) {
        	System.out.printf("\n%-30s  %-30s  %-30s", rentBook.get(i).getTitle(), rentCustom.get(i).getName(), rentBook.get(i).getAvalability());
        }
        for(int i = 0; i < library.size(); i++) {
        	System.out.printf("\n%-30s  %-30s  %-30s", library.get(i).getTitle(), "-", library.get(i).getAvalability());
        }
        System.out.print("\n");
	}
	
	//Displays customer report
	public static void customerReport(ArrayList<Book> rentBook, ArrayList<Customer> rentCustom) {
		System.out.printf("\n%-30s  %-30s  %-30s","Customer","Books Borrowed","Due Date");
		 for(int i = 0; i < rentBook.size(); i++) {
	        	System.out.printf("\n%-30s  %-30s  %-30s", rentCustom.get(i).getName(), rentBook.get(i).getTitle(), rentBook.get(i).getDueDate());
	     }
		 System.out.print("\n");
	}
	
	//Verifys date is in correct format
	public static boolean validateJavaDate(String strDate)
	   {
		//Check if date is null
		if (strDate.trim().equals("")){
		    return true;
		}
		
		//Date is not null
		else
		{
		    //Set preferred date format
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    sdf.setLenient(false);
		    //Create Date object and parse string into date
		     
		    try{
		        Date javaDate = sdf.parse(strDate); 
		    }
		    
		    //Date format is invalid
		    catch (ParseException e)
		    {
		        return false;
		    }
		    
		    //Return true if date format is valid
		    return true;
		}
	}
	
	//Sets due date of book
	public static String newDueDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		
		//Setting the date to the given date
		try{
		   c.setTime(sdf.parse(date));
		}
		catch(ParseException e){
			e.printStackTrace();
		 }
		   
		//Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, 7);  
		
		//Date after adding the days to the given date
		String newDate = sdf.format(c.getTime());  	
		
		return newDate;
	}
	
	//Computing days between checkout date and return date
	public static long computeDaysBetween(String dateCheck, String dateRet) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    Date firstDate = sdf.parse(dateCheck);
	    Date secondDate = sdf.parse(dateRet);

	    long diffInMillies = (secondDate.getTime() - firstDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

	    return diff;
	}
	
	public static void main(String[] args) throws ParseException {
		//Map for late fee report
		HashMap<String, String> retBook = new HashMap<String, String>();
		
		//Creating list for books in library and registered customers
		ArrayList<Book> library = new ArrayList<Book>();
		ArrayList<Customer> people = new ArrayList<Customer>();
		
		//Creating list for rented books and customers
		ArrayList<Book> rentBook = new ArrayList<Book>();
		ArrayList<Customer> rentCustom = new ArrayList<Customer>();
		
		//Building default lists of books and customers
		buildDefaultBooks(library);
		buildDefaultCustomers(people);
		
		System.out.println("Welcome to Brandon's Book Reservation System!\n");
		
		//Menu
		Scanner scanner = new Scanner(System.in);
		String input;
		while(true) {
			 System.out.print("--------------------------------------------\n");
			 System.out.println("(1) Add Customer");
			 System.out.println("(2) Delete Customer");
			 System.out.println("(3) Add Book");
			 System.out.println("(4) Rent Book");
			 System.out.println("(5) Return Book");
			 System.out.println("(6) Book Report");
			 System.out.println("(7) Customer Report");
			 System.out.println("(8) Exit Program");
			 System.out.println("--------------------------------------------");
			 System.out.print("\nPlease enter a number 1 - 8: ");
			 input = scanner.nextLine();
		     switch (input) {
		        case "1":
		        	addCustomer(people);
		            break;
		        case "2":
		        	delCustomer(people);
		            break;
		        case "3":
		        	addBook(library);
		            break;
		        case "4":
		        	rentBook(retBook, people, library, rentBook, rentCustom);
		            break;
		        case "5":
		        	returnBook(retBook, people, library, rentBook, rentCustom);
		            break;
		        case "6":
		        	bookReport(rentBook, rentCustom, library);
		            break;
		        case "7":
		        	customerReport(rentBook, rentCustom);
		            break;
		        case "8":
		            System.out.println("\nProgram Terminated");
		            System.exit(0);
		        default:
		        	System.out.print("Please enter a valid number to execute book reservation system!\n\n ");
		        	break;
		    }
		    
		}
		
	}

}
