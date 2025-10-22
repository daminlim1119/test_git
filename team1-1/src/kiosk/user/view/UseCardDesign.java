package kiosk.user.view;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import kiosk.user.controller.UseCardEvent;

public class UseCardDesign extends JDialog {
	
	public JLabel jlblCardMsg;
	public OrderPayDesign opd;
	public JProgressBar progress;
	public  Timer timer;
	public JButton jbtnCancel;
	
	
	public UseCardDesign() {
		
		UseCardEvent uce=new UseCardEvent(this);
		 progress=new JProgressBar(0,100);
		 progress.setString("카드 결제 중...");
		 progress.setStringPainted(true);
		 
		 timer=new Timer(300,null);
		 timer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int percent=progress.getValue()+10;
				 progress.setValue(percent);
				 if(percent>=100) {
					 timer.stop();
					 uce.successCard();
				 }//end if
			}//actionPerformed
		});
		 
		 
		 timer.start();
		 
		 
		 jlblCardMsg=new JLabel("휴대폰의 결제 바코드를 스캔해주세요");
		 jbtnCancel=new JButton("취소");
		 
		 setLayout(new FlowLayout());
			
		 
		 jlblCardMsg=new JLabel("신용카드를 끝까지 넣어주세요");
		 jbtnCancel=new JButton("취소");
		 
		 Font payFont=new Font("나눔고딕", Font.PLAIN, 20);
		 jlblCardMsg.setFont(payFont);
		 
		    setLayout(null);
			
		    add(jlblCardMsg);
			add(progress);
			add(jbtnCancel);
			
			jlblCardMsg.setBounds(50,50,300,50);
			progress.setBounds(100,300,200,50);
			jbtnCancel.setBounds(150,400,100,50);
			
			addWindowListener(uce);
			jbtnCancel.addActionListener(uce);
			
			setSize(400,600);
			setLocation(800,300);
			setVisible(true);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
	}//UseCardDesign
	
	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public Timer getTimer() {
		return timer;
	}


	public UseCardDesign(OrderPayDesign opd) {
		 super(opd,"카드 결제");
		 this.opd=opd;
	}//UseCardDesign


	
   public static void main(String[] args) {
	  new UseCardDesign();
		
	}//main

}
