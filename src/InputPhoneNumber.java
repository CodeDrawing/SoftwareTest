import java.util.Scanner;

public class InputPhoneNumber  {

//输入电话号码
    public String inputNumber(){
        Scanner scanner = new Scanner(System.in);
        String phoneNumber = scanner.next();
        return phoneNumber;
    }
//    测试后四位
    public boolean lastFourNumber(String number){
        int numberLen = number.length();
        for (int i = numberLen-1; i <numberLen-5 ; i--) {

        }
    }

}
