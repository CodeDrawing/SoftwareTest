package cbsc.cha6.s1_polymorphy;

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
		return getBook()+"������"+daysRented+"��.";
	}
	public double calculateFine() {
		double finedAmount;
		// int bonus;
		finedAmount=0;
		// bonus = 0;
		if (getDaysRented()> 30) {
			finedAmount += (getDaysRented()-30)* getBook().getFine(); 
			finedAmount += getBook().baseFine();	// ��������
		} else {
			getStudent().addBonus(getBook().baseBonus());   // ��ǰ����Ľ�������
		}			
		
		// getStudent().addBonus(bonus);
		return finedAmount;
	}
	
}

