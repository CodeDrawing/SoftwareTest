package cbsc.cha6.s1;


public class Rental{
	private Book book;
	private int daysRented=30;
	private Student borrower;
		
	public Rental(Book aBook, int days, Student aStudent){
		book=aBook;
		daysRented=days;
		borrower=aStudent;
	}
	public Rental(Book aBook,Student aStudent){
		book=aBook;
		borrower=aStudent;
	}
	
	public Book getBook(){
		return book;		
	}
	public Student getStudent(){
		return borrower;		
	}
	public int getDaysRented(){
		return daysRented;
	}
	public void setDaysRented(int days){
		daysRented = days;
	}
	public String toString(){
		return getBook()+"½èÔÄÁË"+daysRented+"Ìì.";
	}
	public double calculateFineAndBonus() {
		double finedAmount;
		finedAmount=0;
		if (getDaysRented()> 30){
				finedAmount += (getDaysRented()-30)*getBook().getFine(); 
				finedAmount += getBook().baseFine();
			} else {
			getStudent().addBonus(getBook().baseBnous());
		}				
		return finedAmount;
	}
}
