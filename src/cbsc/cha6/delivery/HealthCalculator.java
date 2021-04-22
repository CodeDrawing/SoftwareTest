package cbsc.cha6.delivery;

/*
提示：体重指数（BMI）是国际通用的衡量人体重的方法，
计算的方法是：BMI=体重（公斤）除以身高（米）平方。
我国专家认为，中国人属亚洲人种，最佳值应该是20-22，
BMI大于22.6为超重，BMI大于30为肥胖。
体重指数, 男性, 女性
过轻：低于20；低于19
适中：20-25； 19-24
过重：25-30； 24-29
肥胖：30-35； 29-34
非常肥胖, 高于35, 高于34
专家指出最理想的体重指数是22。
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
     weight.setText("输入体重（公斤）");
	 height.setText("输入身高（cm）");
     add(height);
     add(weight);
     calculate = new JButton("计算");
     clear = new JButton("清零");
     add(calculate);
     add(clear);
     result = new JTextArea(6,24);	
     add(result);
     calculate.addActionListener(this);
     clear.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e) {
    	JButton button=(JButton)e.getSource();   //
    	if (button.getText() == "计算"){
    		try{
    			double h = Double.parseDouble(height.getText());
    			double w = Double.parseDouble(weight.getText());
    			int r=0;
    			// String showText;
    			r = (int) (100*100*w/h/h);
    			if(r<20){
    				result.append("BMI="+r+"\n"+"太轻了，要补充营养。");
    			}else if(r<=25){
    				result.append("BMI="+r+"\n"+"恭喜你，标准体形！");
    			}else {
    				result.append("BMI="+r+"\n"+"过于肥胖，要减重、加强锻炼。");
    			}
    			result.append("\n");
    		}catch(Exception exp){
    			result.setText("身高和体重只能是数字！");
    		}
    		 
    	 }else {  
    		    weight.setText("输入体重（公斤）");
    		 	height.setText("输入身高（cm）");
    	}
  	}	
	public static void main(String[] args)
	{
		HealthCalculator win= new HealthCalculator();
		win.setTitle("健康计算器");
		win.setSize(300,180);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	}
}