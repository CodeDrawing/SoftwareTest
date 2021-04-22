package cbsc.cha6.s1;

class TextBook extends Book{
	public TextBook(String name, double aPrice) {
		super(name, aPrice);
	}
	public String getCategory(){
		return "½Ì²Ä";
	}
	public double getFine() {
		return getPrice()*0.001;
    }
	public double baseFine(){
    	return 1;
    }
	public int baseBnous() {
		return 1;
	}
}
