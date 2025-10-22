package kiosk.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import kiosk.user.service.OrderPayService;
import kiosk.user.view.OrderPayDesign;
import kiosk.user.view.UseCardDesign;
import kiosk.user.view.UsePayDesign;

public class OrderPayEvent extends WindowAdapter implements ActionListener{

	public OrderPayDesign opd;
	OrderPayService ops;
	
	public OrderPayEvent() {
		super();
	}
	
	

	public OrderPayEvent(OrderPayDesign opd) {
		this.opd = opd;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		//버튼을 눌렀을 때 결제방식 checkout 업데이트
		if(e.getSource()==opd.getJbtnCard()) {//카드 버튼 
				ops.changeCheckout(null, "카드");//null매개변수에 주문번호 orderNum 들어가야함
			opd.dispose();
			new UseCardDesign();
		}//end if
		
		if(e.getSource()==opd.getJbtnPay()) {//페이 버튼
				ops.changeCheckout(null, "페이");//null매개변수에 주문번호 orderNum 들어가야함
			opd.dispose();
			new UsePayDesign();
		}//end if
		
		
		if(e.getSource()==opd.getJbtnBefore()) {//이전 버튼
			opd.dispose();
			//적립창으로 돌아가게
			
		}//end if
	}//actionPerformed



	@Override
	public void windowClosing(WindowEvent e) {
		opd.dispose();
	}//windowClosing
	
	

}//class
