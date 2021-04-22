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
		return getBook()+"借阅了"+daysRented+"天.";
	}
	public double calculateFine() {
		double finedAmount;
		// int bonus;
		finedAmount=0;
		// bonus = 0;
		if (getDaysRented()> 30) {
			finedAmount += (getDaysRented()-30)* getBook().getFine(); 
			finedAmount += getBook().baseFine();	// 基本罚金
		} else {
			getStudent().addBonus(getBook().baseBonus());   // 提前还书的奖励分数
		}			
		
		// getStudent().addBonus(bonus);
		return finedAmount;
	}
	
}

