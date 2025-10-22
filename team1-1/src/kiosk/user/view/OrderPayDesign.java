package kiosk.user.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import kiosk.user.controller.OrderPayEvent;
import kiosk.user.service.OrderPayService;

public class OrderPayDesign extends JFrame{

	DefaultTableModel dtm;
	JTable jtOrderList;
	public JLabel jlblOrderResult2;
	public JLabel jlblChoosePayMethod;
	public JButton jbtnCard;
	public JButton jbtnPay;
	public JButton jbtnBefore;
	OrderPayService ops;
	
	
	public OrderPayDesign() {
		
    	String orderNum="";
    	ops=new OrderPayService();
	
		jtOrderList=new JTable();
		
		String[]tableColumn={"메뉴명","수량","금액"};
        dtm=new DefaultTableModel(tableColumn, 0);	
      
        //테이블에 행 추가
        for(int i=0;i<ops.showOrderDetail(orderNum).length;i++) {
        	dtm.addRow(ops.showOrderDetail(orderNum));
        	
        }//end for

        JScrollPane scroll=new JScrollPane(jtOrderList);
	
        int[]total=ops.amountPriceTotal(orderNum);
		String quantity=String.valueOf(total[0]);
		String price=String.valueOf(total[1]);
		
		String amountPrice="총 수량: "+quantity+"개 총 금액: "+price+"원";
		jlblOrderResult2=new JLabel(amountPrice);
		
		Font font=new Font("나눔고딕",Font.BOLD,20);
		
		jlblChoosePayMethod=new JLabel("결제방식을 선택해주세요!");
		
		jbtnCard=new JButton("카드");
		jbtnPay=new JButton("페이");
		jbtnBefore=new JButton("이전");
		
		OrderPayEvent ope=new OrderPayEvent(this);
		jbtnCard.addActionListener(ope);
		jbtnPay.addActionListener(ope);
		jbtnBefore.addActionListener(ope);
		
		setLayout(null);
		jlblOrderResult2.setFont(font);
		jtOrderList.setBounds(20,50,350,250);
		jlblOrderResult2.setBounds(30,300,300,50);
		jlblChoosePayMethod.setBounds(70,350,300,50);
		jbtnCard.setBounds(70,400,100,50);
		jbtnPay.setBounds(220,400,100,50);
		jbtnBefore.setBounds(150,500,100,50);
		
		
		add(scroll);
		add(jlblOrderResult2);
		add(jlblChoosePayMethod);
		add(jbtnCard);
		add(jbtnPay);
		add(jbtnBefore);
		
		
		
		setSize(400,600);
		setLocation(800,300);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}//OrderPayDesign
	
	

	public JLabel getJlblOrderResult2() {
		return jlblOrderResult2;
	}

	public JLabel getJlblChoosePayMethod() {
		return jlblChoosePayMethod;
	}

	public JButton getJbtnCard() {
		return jbtnCard;
	}

	public JButton getJbtnPay() {
		return jbtnPay;
	}

	public JButton getJbtnBefore() {
		return jbtnBefore;
	}

	public static void main(String[] args) {
		new OrderPayDesign();
	}

}//class
