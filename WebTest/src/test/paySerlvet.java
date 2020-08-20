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
			regular_non = "2";
		}
		String reg = request.getParameter("reg");
		if (reg == null) {
			reg = "3";
		}
		System.out.println();
		System.out.println("paySerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("regular_non " + regular_non);
		System.out.println("gate_id " + gate_id);
		System.out.println("reg " + reg);
		

		if (regular_non.equals("1")) {                              //기존 정기 회원 영수증
			DTO.receiptDTO recDto = new DTO.receiptDTO();
			recDto.setPay_price("0");
			recDto.setRegular_non("1");
			recDto.setGate_id(gate_id);
			
			DAO.receiptDAO dao = new DAO.receiptDAO();
			dao.insert(recDto);
			
			
			
			//response.sendRedirect("/WebTest/bye.jsp");                //문열어주기
			
			RequestDispatcher rq = request.getRequestDispatcher("/handlerSerlvet");  //핸들러에게 결제완료 전송  
			request.setAttribute("payed", "yes");
			rq.forward(request,response);
		}
		else if (reg.equals("yes")) {                                    //정기 신규 등록하는사람
			
			RequestDispatcher rq = request.getRequestDispatcher("/pay.jsp"); 
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("reg_price", "50000");
			rq.forward(request,response);
			
			// response.sendRedirect("/WebTest/pay.jsp"); //임시
		}
		else if (regular_non.equals("0")) {                               //일반 결제 할사람
			
			
			response.sendRedirect("/WebTest/pay.jsp"); //임시

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
