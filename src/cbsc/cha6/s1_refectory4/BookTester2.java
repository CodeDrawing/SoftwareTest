package cbsc.cha6.s1_refectory4;

public class BookTester2 {

	public static void main(String[] args) {
        Student student = new Student("Johson");
        test_case_one(student);
    }
	private static void test_case_one(Student student) {
		Book aBook;
		aBook = new Book("Python����",35.5); 
        student.addBook(aBook,12);
     
        aBook = new Book("Java����",37.5,1);
        student.addBook(aBook,45);
        
        aBook = new Book("C#����",41.7,3);
        student.addBook(aBook,38);
        
        aBook = new Book("�������",29.8,3);
        student.addBook(aBook,28);
        
        System.out.println("Tester2:ѧ�� "+student.getName()+" Ŀǰ����ͼ����Ϣ���£� ");
        System.out.println(student.returnedMessage());
	}
}

