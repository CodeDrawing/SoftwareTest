package cbsc.cha6.s1_refectory4;

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
			case Book.TEXT_BOOK: type = new String("教材"); break;
			case Book.REFERENCE: type = new String("参考书"); break;
			case Book.NEW_BOOK: type = new String("新书"); break;
		}
		return type+y+"(价格"+y.getPrice()+")"+"借阅了"+daysRented+"天.";
	}
	public double calculateFineAndBonus() {
		double finedAmount;
		int bonus;
		finedAmount=0;
		bonus = 0;
		switch (getBook().getCategory()) {
		  case Book.TEXT_BOOK:
			if (getDaysRented()> 30) {
				finedAmount += (getDaysRented()-30)* getBook().getPrice()*0.001; 
				finedAmount += 1;	// 基本罚金
			} else {
				getStudent().addBonus(1);   // 提前还书的奖励分数
			}			
			break;
		  case Book.REFERENCE: 
			if (getDaysRented()> 30) {
				finedAmount += (getDaysRented()-30)* getBook().getPrice()*0.005; 
				finedAmount += 1.5;		// 基本罚金			
			} else {
				getStudent().addBonus(2);  // 提前还书的奖励分数
			}				
			break;
		  case Book.NEW_BOOK: 
			if (getDaysRented()> 30) {
				finedAmount += (getDaysRented()-30)* getBook().getPrice()*0.01; 
				finedAmount += 3;  // 基本罚金
			}else {
				getStudent().addBonus(3);  	// 提前还书的奖励分数
			}
			break;
		  }
		getStudent().addBonus(bonus);
		return finedAmount;
	}
}

