package kiosk.user.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import kiosk.user.controller.UseCardEvent;
import kiosk.user.controller.UsePayEvent;

public class UsePayDesign extends JDialog {
	public JLabel jlblPayMsg;
	public OrderPayDesign opd;
	public JProgressBar progress;
	public  Timer timer;
	public JButton jbtnCancel;
	
	
	
	public UsePayDesign() {
		
		UsePayEvent upe=new UsePayEvent(this);
		 progress=new JProgressBar(0,100);
		 progress.setString("페이 결제 중...");
		 progress.setStringPainted(true);
		 
		 timer=new Timer(300,null);
		 timer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int percent=progress.getValue()+10;
				 progress.setValue(percent);
				 if(percent>=100) {
					 timer.stop();
					 upe.successPay();
				 }//end if
			}//actionPerformed
		});
		 
		 
		 timer.start();
		 
		 jlblPayMsg=new JLabel("<html>휴대폰의 페이 결제 바코드를 <br>스캔해주세요");
		 jbtnCancel=new JButton("취소");
		 
		 Font payFont=new Font("나눔고딕", Font.PLAIN, 20);
		 jlblPayMsg.setFont(payFont);
		 
		 setLayout(null);
			
		    add(jlblPayMsg);
			add(progress);
			add(jbtnCancel);
			
			jlblPayMsg.setBounds(50,50,300,50);
			progress.setBounds(100,300,200,50);
			jbtnCancel.setBounds(150,400,100,50);
			
			addWindowListener(upe);
			jbtnCancel.addActionListener(upe);
			
			setSize(400,600);
			setLocation(800,300);
			setVisible(true);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
	}//UseCardDesign
	
	public Timer getTimer() {
		return timer;
	}


	public UsePayDesign(OrderPayDesign opd) {
		 super(opd,"카드 결제");
		 this.opd=opd;
	}//UseCardDesign


	
   public JButton getJbtnCancel() {
		return jbtnCancel;
	}

   public static void main(String[] args) {
	  new UsePayDesign();
		
	}//main

}//class
