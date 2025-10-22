package kiosk.user.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kiosk.user.dao.OrderPayDAO;
import kiosk.user.dto.OrderPayDTO;

public class OrderPayService {
	
	List<String> orderList;
	List<OrderPayDTO>tempList;
	OrderPayDTO opDTO;
	OrderPayDAO opDAO;

	//메뉴명(옵션1,옵션2,옵션3)  수량  금액
	public String[] showOrderDetail(String orderNum){ 

		OrderPayDTO opDTO=new OrderPayDTO();
		String menu=
				opDTO.getMenuName()+"("+opDTO.getTempOption()+","
						+opDTO.getSizeOption()+","+opDTO.getShotOption()+")";
		
		String menuAmount=String.valueOf(opDTO.getAmount());
		
		String price=String.valueOf(opDTO.getOrderPrice());
		
		String[] orderList= {menu,menuAmount,price};
		
		
		
		return orderList;
	}//showOrderDetail
	
	
	//수량과 금액의 합계
	public int[] amountPriceTotal(String orderNum) {
	
		    tempList=null;
		OrderPayDAO opDAO=new OrderPayDAO();
		try {
			tempList=opDAO.selectOrderDetail(orderNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		int quantity=0;
		int price=0;
		
		for(OrderPayDTO opDTO: tempList) {
			quantity+=opDTO.getAmount();
			price+=opDTO.getOrderPrice();
		}//end for
		
		
		int[]total= {quantity,price};
		
		return total;
	}//quantityPrice
	
	public void changeCheckout(String orderNum,String checkout) {
		try {
			OrderPayDAO opDAO=new OrderPayDAO();
			opDAO.updateCheckout(orderNum, checkout);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}//changeCheckout
	
	public void removeOrderDetail(String orderNum) {
		try {
			OrderPayDAO opDAO=new OrderPayDAO();
			opDAO.deleteOrderDetail(orderNum);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//removeOrderDetail
	
	public void removeOrderList(String orderNum) {
		try {
			OrderPayDAO opDAO=new OrderPayDAO();
			opDAO.deleteOrderList(orderNum);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//removeOrderList
	
	
	
}//class
