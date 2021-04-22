package cbsc.cha6.s1_2;

import java.util.ArrayList;

public class Student{
	
	private String name;
	private int bonus=0;
	private ArrayList<Rental> rentals = new ArrayList<Rental>(10);
	
	public Student(String aName){
		name = aName;
	}
	public String getName(){
		return name;
	}
	public ArrayList<Rental> getRentals(){
		return rentals;
	}
	public int getBonus(){
		return bonus;
	}
	public void addBonus(int reward){
		bonus += reward;
	}
	public void addBook(Book aBook, int days){
		
		rentals.add(new Rental(aBook, days, this));
	}
	public void addRental(Rental aRental){
		rentals.add(aRental);
	}
	public String returnedMessage(){
		double totalAmount = 0;
	 	double finedAmount=0;
		int bonus = 0;
		String message = new String();
	 	for (Rental aRental: rentals){
	 		
			finedAmount=0;
			bonus = 0;
			switch (aRental.getBook().getCategory()) {
			  case Book.TEXT_BOOK:
				if (aRental.getDaysRented()> 30) {
					finedAmount += (aRental.getDaysRented()-30)* aRental.getBook().getPrice()*0.001; 
					finedAmount += 1;	// ��������
				} else {
					addBonus(1);   // ��ǰ����Ľ�������
				}			
				break;
			  case Book.REFERENCE: 
				if (aRental.getDaysRented()> 30) {
					finedAmount += (aRental.getDaysRented()-30)* aRental.getBook().getPrice()*0.005; 
					finedAmount += 1.5;		// ��������			
				} else {
					addBonus(2);  // ��ǰ����Ľ�������
				}				
				break;
			  case Book.NEW_BOOK: 
				if (aRental.getDaysRented()> 30) {
					finedAmount += (aRental.getDaysRented()-30)* aRental.getBook().getPrice()*0.01; 
					finedAmount += 3;  // ��������
				}else {
					addBonus(3);  	// ��ǰ����Ľ�������
				}
				break;
			  }
			  addBonus(bonus);
			  
	     	  totalAmount += finedAmount;
			  // ��������7����Ի�1Ԫ����
	        while (getBonus()>=7 && totalAmount>1) {
	        	addBonus(-7);
	        	totalAmount--;
	        }
	        message += aRental+"\n";      
	     }
	     message += String.format("�ܹ����ɷ���: %.2fԪ.\n", totalAmount);
	     message += "���齱��: "+getBonus()+"��. \n";        
	     return message;
		}
}
