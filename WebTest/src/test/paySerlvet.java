package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
			regular_non = "notUse";
		}
		String remainingDays = (String)request.getAttribute("remainingDays");
		String usedMinute = (String)request.getAttribute("usedMinute");
		String reg = request.getParameter("reg");
		if (reg == null) {
			reg = "notUse";
		}
		
		System.out.println();
		System.out.println("paySerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("regular_non " + regular_non);
		System.out.println("gate_id " + gate_id);
		System.out.println("reg " + reg);
		System.out.println("remainingDays " + remainingDays);
		System.out.println("usedMinute " + usedMinute);
		

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
		else if (reg.contentEquals("yes")) {                                    //정기 신규 등록하는사람
			String reg_price = "50000";
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/pay.jsp"); 
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("reg_price", reg_price);
			rq.forward(request,response);
			
		}
		
		else if (regular_non.contentEquals("0")) {              //일반 결제 할사람
			
			int pricePerHour = 1000;
			int time_price = (int)Math.ceil(Double.parseDouble(usedMinute)/60) * pricePerHour;
			
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/pay.jsp");
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("regular_non", "0");
			request.setAttribute("time_price", Integer.toString(time_price));
			rq.forward(request,response);

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
