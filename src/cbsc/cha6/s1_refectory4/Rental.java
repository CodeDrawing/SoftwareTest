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
			case Book.TEXT_BOOK: type = new String("�̲�"); break;
			case Book.REFERENCE: type = new String("�ο���"); break;
			case Book.NEW_BOOK: type = new String("����"); break;
		}
		return type+y+"(�۸�"+y.getPrice()+")"+"������"+daysRented+"��.";
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
				finedAmount += 1;	// ��������
			} else {
				getStudent().addBonus(1);   // ��ǰ����Ľ�������
			}			
			break;
		  case Book.REFERENCE: 
			if (getDaysRented()> 30) {
				finedAmount += (getDaysRented()-30)* getBook().getPrice()*0.005; 
				finedAmount += 1.5;		// ��������			
			} else {
				getStudent().addBonus(2);  // ��ǰ����Ľ�������
			}				
			break;
		  case Book.NEW_BOOK: 
			if (getDaysRented()> 30) {
				finedAmount += (getDaysRented()-30)* getBook().getPrice()*0.01; 
				finedAmount += 3;  // ��������
			}else {
				getStudent().addBonus(3);  	// ��ǰ����Ľ�������
			}
			break;
		  }
		getStudent().addBonus(bonus);
		return finedAmount;
	}
}

