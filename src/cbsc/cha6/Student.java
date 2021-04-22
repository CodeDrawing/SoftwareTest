package cbsc.cha6;

import java.util.ArrayList;

public class Student{
	/*
	public final static int U_STUDENT=1;
	public final static int G_STUDENT=2;
	public final static int P_STUDENT=3;
	*/
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
	// 重构1-3
	public String returnedMessage(){
		double totalAmount = 0;
        // double finedAmount=0; refactor 3
		// int bonus = 0;  // refactor 2
		String message=new String();
        for (Rental aRental:rentals){  	
			// finedAmount = calculateFineAndBonus(aRental); // refactor 3
        	totalAmount += aRental.calculateFineAndBonus();  // refactor 4
        	while (getBonus()>=7 && totalAmount>1){
        		addBonus(-7);
        		totalAmount--;
        	}
        	message += aRental+"\n";      
        }
        message += String.format("缴纳罚金:%.2f元.\n",totalAmount);
        message += "还书奖励:"+getBonus()+"点.\n";
        
        return message;
	}
	/* after refactoring 1-3
	private double calculateFineAndBonus_1_3(Rental aRental) {
		double finedAmount;
		int bonus;
		bonus = 0;		
		finedAmount=0;
		switch(aRental.getBook().getCategory()){
		case Book.TEXT_BOOK:
			if (aRental.getDaysRented()> 30){
				finedAmount += (aRental.getDaysRented()-30)*aRental.getBook().getPrice()*0.001; 
				finedAmount += 1;
			} else {
				addBonus(1);  // refactor 2
			}			
			break;
		case Book.REFERENCE: 
			if (aRental.getDaysRented()> 30){
				finedAmount += (aRental.getDaysRented()-30)*aRental.getBook().getPrice()*0.005; 
				finedAmount += 1.5;					
			} else {
				addBonus(2);	// refactor 2
			}				
			break;
		case Book.NEW_BOOK: 
			if (aRental.getDaysRented()> 30){
				finedAmount += (aRental.getDaysRented()-30)*aRental.getBook().getPrice()*0.01; 
				finedAmount += 3;
			}else {
				addBonus(3);	// refactor 2
			}
			break;
		}
		addBonus(bonus);
		return finedAmount;
	}
	*/
	// 加上数字是为了保留重构前的代码
	public String returnedMessage_0(){
		double totalAmount = 0;
        double finedAmount=0;
		int bonus = 0;
		String message=new String();
        for (Rental aRental:rentals){  	
        	bonus = 0;		
    		finedAmount=0;
    		switch(aRental.getBook().getCategory()){
    		case Book.TEXT_BOOK:
    			if (aRental.getDaysRented()> 30){
    				finedAmount += (aRental.getDaysRented()-30)*aRental.getBook().getPrice()*0.001; 
    				finedAmount += 1;
    			} else {
    				addBonus(1);
    			}			
    			break;
    		case Book.REFERENCE: 
    			if (aRental.getDaysRented()> 30){
    				finedAmount += (aRental.getDaysRented()-30)*aRental.getBook().getPrice()*0.005; 
    				finedAmount += 1.5;					
    			} else {
    				bonus = 2;
    			}				
    			break;
    		case Book.NEW_BOOK: 
    			if (aRental.getDaysRented()> 30){
    				finedAmount += (aRental.getDaysRented()-30)*aRental.getBook().getPrice()*0.01; 
    				finedAmount += 3;
    			}else {
    				bonus = 3;
    			}
    			break;
    		}
    		addBonus(bonus);
        	totalAmount += finedAmount;
        	while (getBonus()>=7 && totalAmount>1){
        		addBonus(-7);
        		totalAmount--;
        	}
        	message += aRental+"\n";      
        }
        message += String.format("缴纳罚金:%.2f元.\n",totalAmount);
        message += "还书奖励:"+getBonus()+"点.\n";
        
        return message;
	}
	
}