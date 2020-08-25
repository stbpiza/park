package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adminSerlvet")
public class adminSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminSerlvet() {
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
		String price = (String)request.getAttribute("price");
		if (price == null) {
			price = request.getParameter("price");
		}
		String password = request.getParameter("password");
		String back = request.getParameter("back");
		if (back == null) {
			back = "notUse";
		}
		String page = request.getParameter("page");

		
		System.out.println();
		System.out.println("adminSerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("regular_non " + regular_non);
		System.out.println("gate_id " + gate_id);
		System.out.println("price " + price);
		System.out.println("password " + password);
		System.out.println("back " + back);
		System.out.println("page " + page);
		
		try {
		page.contentEquals("test"); // 비정상 접근 방지
			
			if (password == null && back.contentEquals("notUse")) {             //관리자결제로 이동(암호입력창)
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/forcedPay.jsp");  //
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", regular_non);
				request.setAttribute("price", price);
				request.setAttribute("admin", "0");
				rq.forward(request,response);
			}
			else if (back.contentEquals("back")) {              //뒤로가기
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/pay.jsp");
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", regular_non);
				request.setAttribute("price", price);
				rq.forward(request,response);
			}
			else if (page.contentEquals("force") && password.contentEquals("0000")) {          //암호맞음
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/forcedPay.jsp");
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", regular_non);
				request.setAttribute("admin", "yes");
				rq.forward(request,response);
			}
			else if (page.contentEquals("force") && !password.contentEquals("0000")){
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/forcedPay.jsp");  //암호틀림
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", regular_non);
				request.setAttribute("price", price);
				request.setAttribute("admin", "no");
				rq.forward(request,response);
			}
			else if (page.contentEquals("admin")) {                                    //관리자페이지 암호
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/adminCheck.jsp"); 
				rq.forward(request,response);
			}
			else if (page.contentEquals("check") && password.contentEquals("0000")) {      //암호맞음
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/admin.jsp"); 
				rq.forward(request,response);
			}
			else if (page.contentEquals("check")) {                                        //암호틀림
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/adminCheck.jsp"); 
				request.setAttribute("error", "1");
				rq.forward(request,response);
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
