package lab03;

// What imports do you need to include? Put them here.
import java.util.GregorianCalendar;

public class LibraryBook extends Book { 

	// A LibraryBook contains a holder (a String) and due date represented by
	// a GregorianCalendar
	private String holder;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title) {
		// FILL IN
		super(isbn,author,title);
		this.holder = null;
		this.dueDate = null;
		
	}

	public String getHolder() {
		// FILL IN
		return this.holder; // placeholder
	}
	
	public GregorianCalendar getDueDate() {
		// FILL IN
		return this.dueDate; // placeholder
	}
	
	public void checkin() {
		// FILL IN
		this.holder = null;
		this.dueDate = null;
	}
	
	public void checkout(String holder, GregorianCalendar dueDate){
		// FILL IN
		this.holder = holder;
		this.dueDate = new GregorianCalendar(
				dueDate.get(GregorianCalendar.YEAR), 
				dueDate.get(GregorianCalendar.MONTH), 
				dueDate.get(GregorianCalendar.DATE));
		
	}	

	// Do not override the equals method in Book

}