package cbsc.cha6.s1_polymorphy;

class TextBook extends Book{
	public TextBook(String name, double aPrice) {
		super(name, aPrice);
	}
	public String getCategory(){
		return "½Ì²Ä";
	}
	double getFine() {
    	return getPrice()*0.001;
    }
	double baseFine(){
    	return 1;
    }
	int baseBonus() {
    	return 1;
    }
}
