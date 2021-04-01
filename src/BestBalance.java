import java.util.Scanner;

import static sun.util.calendar.CalendarUtils.mod;

/**
 * @Author: CodeDrawing
 * @Date: 2021/4/1 8:45
 * @Package: PACKAGE_NAME
 * 功能：
 * 细节：
 * 注意：
 */
public class BestBalance {
    public static void main(String[] args) {
//        定义50元10元5元1元的张数
        int x=0,y=0,m=0,n=0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入购买的金额：");
        int paymentMoney=scanner.nextInt();
//找50的
        x=paymentMoney/50;
        paymentMoney=paymentMoney%50;
//        找10元的
        y=paymentMoney/10;
        paymentMoney=paymentMoney%10;
//        找5元的
        m=paymentMoney/5;
        paymentMoney=paymentMoney%5;
//        找1元的
        n=paymentMoney/1;
        System.out.println("最好的方式是：找"+x+"张50的，找"+y+"张10元的,找"+m+"张5元的，找"+n+"张1元的");
    }
}
