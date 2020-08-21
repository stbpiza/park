package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cardSerlvet")
public class cardSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public cardSerlvet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String car_num = request.getParameter("car_num");
		String gate_id = request.getParameter("gate_id");
		String regular_non = request.getParameter("regular_non");
		String price = request.getParameter("price");
		
		RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  //
		request.setAttribute("car_num", car_num);
		request.setAttribute("gate_id", gate_id);
		request.setAttribute("regular_non", regular_non);
		request.setAttribute("price", price);
		request.setAttribute("kind", "card");
		rq.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
