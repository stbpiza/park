package test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkRegularSerlvet")
public class checkRegularSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public checkRegularSerlvet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String car_num = request.getParameter("car_num");
		String gate_id = request.getParameter("gate_id");
		String time ;
		
		DAO.checkRegularDAO check = new DAO.checkRegularDAO();
		time = check.getTime(car_num);
		
		if (time != null) {                                             //정기로그에 있는경우
			
       Date now = new Date();
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

       try {
		Date timeDate = format.parse(time);                      
		
	       if (now.before(timeDate)) {
	    	   
	    	   //response.sendRedirect("/WebTest/bye.jsp"); // 임시
	    	   
				RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  //정기맞음
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("regular_non", "1");
				rq.forward(request,response);
	       }
	       else {
				RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  //날짜지남
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("car_num", car_num);
				request.setAttribute("regular_non", "0");
				rq.forward(request,response);

	       }
		
		
	} 
       catch (ParseException e) {
		e.printStackTrace();
	}

	}
		else {                                                                          //정기로그에 없는경우
			RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("car_num", car_num);
			request.setAttribute("regular_non", "0");
			rq.forward(request,response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}