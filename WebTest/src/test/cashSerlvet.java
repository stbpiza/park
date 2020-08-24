package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cashSerlvet")
public class cashSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public cashSerlvet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String car_num = request.getParameter("car_num");
		String gate_id = request.getParameter("gate_id");
		String regular_non = request.getParameter("regular_non");
		String price = request.getParameter("price");
		String cash = request.getParameter("cash");
		String addCash = request.getParameter("addCash");
		String paid = request.getParameter("paid");
		
		System.out.println();
		System.out.println("cashSerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("regular_non " + regular_non);
		System.out.println("gate_id " + gate_id);
		System.out.println("price " + price);
		System.out.println("cash " + cash);
		System.out.println("addCash " + addCash);
		System.out.println("paid " + paid);

		try {
		gate_id.contentEquals("test"); // 비정상 접근 방지
		if (cash == null) {
			RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  //
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("regular_non", regular_non);
			request.setAttribute("price", price);
			request.setAttribute("kind", "cash");
			rq.forward(request,response);
		}
		else if (paid == null){
			int cashInt = Integer.parseInt(cash);
			int addCashInt = Integer.parseInt(addCash);
			cashInt = cashInt + addCashInt;
			
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/cash.jsp");  //
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("regular_non", regular_non);
			request.setAttribute("price", price);
			request.setAttribute("cash", Integer.toString(cashInt));
			rq.forward(request,response);
		}
		else {
			int cashInt = Integer.parseInt(cash);
			int priceInt = Integer.parseInt(price);
			if (cashInt==priceInt) {
				RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  //
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", regular_non);
				request.setAttribute("price", price);
				rq.forward(request,response);
			}
			else {
				int changes = cashInt - priceInt;
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/changes.jsp");  //
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", regular_non);
				request.setAttribute("price", price);
				request.setAttribute("changes", Integer.toString(changes));
				rq.forward(request,response);
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
