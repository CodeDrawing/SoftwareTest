package cbsc.cha6.delivery;

/*
��ʾ������ָ����BMI���ǹ���ͨ�õĺ��������صķ�����
����ķ����ǣ�BMI=���أ����������ߣ��ף�ƽ����
�ҹ�ר����Ϊ���й������������֣����ֵӦ����20-22��
BMI����22.6Ϊ���أ�BMI����30Ϊ���֡�
����ָ��, ����, Ů��
���᣺����20������19
���У�20-25�� 19-24
���أ�25-30�� 24-29
���֣�30-35�� 29-34
�ǳ�����, ����35, ����34
ר��ָ�������������ָ����22��
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import java.awt.event.*;
@SuppressWarnings("serial")
public class HealthCalculator extends JFrame implements ActionListener{ 
JTextField height,weight;
 JTextArea result;
 JButton calculate, clear;
 ButtonActionListener listener;
 public HealthCalculator() {
	 setLayout(new FlowLayout());
     height = new JTextField(10); 
     weight = new JTextField(10);
     weight.setText("�������أ����");
	 height.setText("������ߣ�cm��");
     add(height);
     add(weight);
     calculate = new JButton("����");
     clear = new JButton("����");
     add(calculate);
     add(clear);
     result = new JTextArea(6,24);	
     add(result);
     calculate.addActionListener(this);
     clear.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e) {
    	JButton button=(JButton)e.getSource();   //
    	if (button.getText() == "����"){
    		try{
    			double h = Double.parseDouble(height.getText());
    			double w = Double.parseDouble(weight.getText());
    			int r=0;
    			// String showText;
    			r = (int) (100*100*w/h/h);
    			if(r<20){
    				result.append("BMI="+r+"\n"+"̫���ˣ�Ҫ����Ӫ����");
    			}else if(r<=25){
    				result.append("BMI="+r+"\n"+"��ϲ�㣬��׼���Σ�");
    			}else {
    				result.append("BMI="+r+"\n"+"���ڷ��֣�Ҫ���ء���ǿ������");
    			}
    			result.append("\n");
    		}catch(Exception exp){
    			result.setText("��ߺ�����ֻ�������֣�");
    		}
    		 
    	 }else {  
    		    weight.setText("�������أ����");
    		 	height.setText("������ߣ�cm��");
    	}
  	}	
	public static void main(String[] args)
	{
		HealthCalculator win= new HealthCalculator();
		win.setTitle("����������");
		win.setSize(300,180);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	}
}