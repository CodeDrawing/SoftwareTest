package cbsc.cha6.s1_2;

class Rental{
	private Book y;
	private int daysRented=30;
	private Student borrower;
		
	public Rental(Book aBook, int days, Student aStudent){
		y=aBook;
		daysRented=days;
		borrower=aStudent;
	}
	public Rental(Book aBook,Student aStudent){
		y=aBook;
		borrower=aStudent;
	}
	public Book getBook(){
		return y;		
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
		String type=new String();
		switch(y.getCategory()){
			case Book.TEXT_BOOK: type = new String("�̲�"); break;
			case Book.REFERENCE: type = new String("�ο���"); break;
			case Book.NEW_BOOK: type = new String("����"); break;
		}
		return type+y+"(�۸�"+y.getPrice()+")"+"������"+daysRented+"��.";
	}
}

