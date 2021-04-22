import java.util.ArrayList;
import java.util.Scanner;

public class InputPhoneNumber  {

//输入电话号码,并且把它转化成数组
    public ArrayList<Character> inputNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入电话号码");
        String phoneNumber = scanner.next();
        ArrayList<Character> arrayListPhoneNumber = new ArrayList<Character>();
        for (int i = 0; i < phoneNumber.length(); i++) {
            char x =phoneNumber.charAt(i);
            arrayListPhoneNumber.add(x);
        }
//        System.out.println(arrayListPhoneNumber);
        return arrayListPhoneNumber;
    }

//    测试后四位
    public boolean lastFourNumber(ArrayList<Character> arrayListPhoneNumber){
        int numberLen = arrayListPhoneNumber.size();
        if (numberLen<4){
            System.out.println("后缀长度不能小于4");
            return false;
        }
        for (int i = numberLen-4; i <=numberLen-1 ; i++) {
            if(arrayListPhoneNumber.get(i)<'0'||arrayListPhoneNumber.get(i)>'9'){
                return false;
            }
        }
        return true;
    }
//前 缀：非'0'或'1'的三位数字；
    public boolean beforeNumber(ArrayList<Character> arrayListPhoneNumber){
        int numberLen = arrayListPhoneNumber.size();
        if(numberLen<7){
            System.out.println("前缀和后缀长度不能小于7");
            return false;
        }
        for (int i = numberLen-7; i <=numberLen-5 ; i++) {
            if (arrayListPhoneNumber.get(i) < '1' || arrayListPhoneNumber.get(i) > '9') {
                return false;
            }
        }
        return true;
    }
//    地区码：空白或三位数字；
    public boolean areaNumber(ArrayList<Character> arrayListPhoneNumber){
        int numberLen = arrayListPhoneNumber.size();
        if (numberLen > 7 && numberLen < 10) {
            System.out.println("地区码不足3位");
            return false;
        } else if (numberLen > 10) {
            System.out.println("地区码只能为空或者3位");
            return false;
        } else if (numberLen == 10) {
            for (int i = numberLen - 10; i <= numberLen - 8; i++) {
                if (arrayListPhoneNumber.get(i) < '0' || arrayListPhoneNumber.get(i) > '9') {
                    return false;
                }
            }
        }
        return true;
    }
    
//    主函数
public static void main(String[] args) {
    InputPhoneNumber inputPhoneNumber = new InputPhoneNumber();
    ArrayList<Character> xx = inputPhoneNumber.inputNumber();

//    开始验证后缀
    boolean b = inputPhoneNumber.lastFourNumber(xx);
//    System.out.println("开始验证后缀");
//    System.out.println(b);
//    开始验证前缀
    boolean b1 = inputPhoneNumber.beforeNumber(xx);
//    System.out.println("开始验证前缀");
//    System.out.println(b1);
//    开始验证地区码
    boolean b2 = inputPhoneNumber.areaNumber(xx);
//    System.out.println("开始验证地区码");
//    System.out.println(b2);
//    判断
//    System.out.println(b);
//    System.out.println(b1);
//    System.out.println(b2);
    if (b1 == false || b == false || b2 == false) {
        System.out.println("该电话号码不符合规范！");
    } else {
        System.out.println("该电话号码符合规范！");
    }
}

}
