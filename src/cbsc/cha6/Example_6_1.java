package cbsc.cha6;
/**
 * @(#)Refactorying Example 6.1
 * ѧУͼ��ݼ���ѧ������ķ��
 * 				�̲ġ��ο�������
 * ��ѽ���������50   40    30  
 * ÿ����һ�췣���� 0.001,0.005Ԫ��0.01Ԫ��
 * ��ʱ�Ļ������1��1.5��3
 * ��������ǰ���齱������ 1, 2, 3; 7��ɵ���1Ԫ����
 *
 * ��չ����ѧ����ѽ������� 30   40   50
 * @author 
 * @version 1.00 2015/3/14
 */
public class Example_6_1 {
	
	public static void main(String[] args) {
        Student student = new Student("zhangsan");
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
        
        System.out.println(student.returnedMessage());
	}

}
