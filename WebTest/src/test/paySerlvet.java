package test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/paySerlvet")
public class paySerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public paySerlvet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String car_num = (String)request.getAttribute("car_num");
		if (car_num == null) {
		 car_num = request.getParameter("car_num");
		}
		String gate_id = (String)request.getAttribute("gate_id");
		if (gate_id == null) {
		gate_id = request.getParameter("gate_id");
		}
		String regular_non = (String)request.getAttribute("regular_non");
		if (regular_non == null) {
			regular_non = request.getParameter("regular_non");
		}
		if (regular_non == null) {
			regular_non = "notUse";
		}
		String price = (String)request.getAttribute("price");
		if (price == null) {
			price = request.getParameter("price");
		}
		String kind = (String)request.getAttribute("kind");
		if (kind == null) {
			kind = request.getParameter("kind");
		}
		if (kind == null) {
			kind = "notUse";
		}
		String remainingDays = (String)request.getAttribute("remainingDays");
		String usedMinute = (String)request.getAttribute("usedMinute");
		String coupon = request.getParameter("coupon");
		String error = request.getParameter("error");
		System.out.println();
		System.out.println("paySerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("regular_non " + regular_non);
		System.out.println("gate_id " + gate_id);
		System.out.println("price " + price);
		System.out.println("kind " + kind);
		System.out.println("remainingDays " + remainingDays);
		System.out.println("usedMinute " + usedMinute);
		System.out.println("coupon " + coupon);
		System.out.println("error " + error);
		
		try {
			gate_id.contentEquals("test"); // 비정상 접근 방지
		if (price == null){
			if (regular_non.contentEquals("1")) {                              //기존 정기 회원 영수증
				DTO.receiptDTO recDto = new DTO.receiptDTO();
				recDto.setPay_price("0");
				recDto.setRegular_non("1");
				recDto.setGate_id(gate_id);
				
				DAO.receiptDAO dao = new DAO.receiptDAO();
				dao.insert(recDto);
				
				RequestDispatcher rq = request.getRequestDispatcher("/handlerSerlvet");  //핸들러에게 결제완료 전송  
				request.setAttribute("payed", "yes");
				request.setAttribute("remainingDays", remainingDays);
				rq.forward(request,response);
			}
			else if (regular_non.contentEquals("new")) {                                    //정기 신규 등록하는사람
				HttpSession session = request.getSession();
				String reg_price = (String)session.getAttribute("setReg_price");
				System.out.println("reg_price " + reg_price);
				if (reg_price == null) {
					reg_price = "50000";
				}
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/pay.jsp"); 
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("reg_price", reg_price);
				rq.forward(request,response);
				
			}
			
			else if (regular_non.contentEquals("0")) {              //일반 결제 할사람
				
				HttpSession session = request.getSession();
				String pricePerHourString = (String)session.getAttribute("setPricePerHour");
				System.out.println("pricePerHour " + pricePerHourString);
				if (pricePerHourString == null) {
					pricePerHourString = "1000";
				}
				int priceperHour = Integer.parseInt(pricePerHourString);
				int time_price = (int)Math.ceil(Double.parseDouble(usedMinute)/60) * priceperHour;
				
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/pay.jsp");
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", "0");
				request.setAttribute("time_price", Integer.toString(time_price));
				rq.forward(request,response);
	
			}
	}
		else {
			if (kind.contentEquals("coupon")) {                     //방문증 할인 계산
				Date now = new Date();
				SimpleDateFormat format = new SimpleDateFormat("MMdd");
				String nowString = format.format(now);
				int priceInt = Integer.parseInt(price);
				if((coupon.substring(0,4)).contentEquals(nowString)) { //방문증 인식 성공
					priceInt = priceInt - 2000;
					if (priceInt < 0) {
						priceInt = 0;
					}
					RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/pay.jsp");
					request.setAttribute("car_num", car_num);
					request.setAttribute("gate_id", gate_id);
					request.setAttribute("regular_non", "0");
					request.setAttribute("price", Integer.toString(priceInt));
					rq.forward(request,response);
		
				}
				else {                                           //방문증 인식 오류
					int errorInt = Integer.parseInt(error);
					errorInt++;
					RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/pay.jsp");
					request.setAttribute("car_num", car_num);
					request.setAttribute("gate_id", gate_id);
					request.setAttribute("regular_non", "0");
					request.setAttribute("time_price", price);
					request.setAttribute("error",Integer.toString(errorInt));
					rq.forward(request,response);
				}
				
			}
			else if (kind.contentEquals("card")){          //카드계산
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/card.jsp");  //
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", regular_non);
				request.setAttribute("price", price);
				rq.forward(request,response);
			}
			else if(kind.contentEquals("cash")) {            //현금계산
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/cash.jsp");  //
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", regular_non);
				request.setAttribute("price", price);
				rq.forward(request,response);
			}
			else {                                             //결제 완료된 요청들
				DTO.receiptDTO recDto = new DTO.receiptDTO();
				recDto.setPay_price(price);
				recDto.setRegular_non(regular_non);
				recDto.setGate_id(gate_id);
				
				DAO.receiptDAO dao = new DAO.receiptDAO();
				dao.insert(recDto);
				
				if (regular_non.contentEquals("0")) {
				RequestDispatcher rq = request.getRequestDispatcher("/handlerSerlvet");  //핸들러에게 결제완료 전송  
				request.setAttribute("payed", "yes");
				rq.forward(request,response);
				}
				else {
					String rec_id = dao.checkReceipt(recDto);
					RequestDispatcher rq = request.getRequestDispatcher("/regularSerlvet");  //정기핸들러에게 가입로그 전송
					request.setAttribute("reg", "new");
					request.setAttribute("car_num", car_num);
					request.setAttribute("rec_id", rec_id);
					rq.forward(request,response);
				}
			}
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/error.jsp"); 
			rq.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
