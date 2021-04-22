package cbsc.cha6.refactory_2;

class Rental{
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
		return book.toString()+"½èÔÄÁË"+daysRented+"Ìì.";
	}
	public double getFine(){
		double finedAmount=0;
		if (getDaysRented() > 30){
			finedAmount += (getDaysRented()-30)*getBook().getFine(); 
			finedAmount += getBook().baseFine();
		}else{
			borrower.addBonus(getBook().getBonus());
		}
		return finedAmount;
	}
}
