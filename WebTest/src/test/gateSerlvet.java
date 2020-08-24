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


import DTO.gateDTO;

@WebServlet("/gateSerlvet")
public class gateSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public gateSerlvet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String car_num = (String)request.getAttribute("car_num");
		String gate_id = (String)request.getAttribute("gate_id");
		
		System.out.println();                                      //테스트출력영역
		System.out.println("gateSerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("gate_id " + gate_id);
		
		try {
	    gate_id.contentEquals("test"); // 비정상 접근 방지
		gateDTO dto = new gateDTO();
		dto.setCar_num(car_num);
		
		DAO.gateDAO dao = new DAO.gateDAO();                                           //이용시간 계산
		String inTime = dao.getTime(dto,"1");
		String outTime = dao.getTime(dto, "0");
		
		System.out.println();
		System.out.println("gateSerlvet");
		System.out.println("inTime " + inTime);
		System.out.println("outTime " + outTime);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		try {                                                                    //date형식에 스트링넣기
			Date inTimeDate = format.parse(inTime);
			Date outTimeDate = format.parse(outTime);
			long usedTime = outTimeDate.getTime() - inTimeDate.getTime();
			int usedMinute = (int)Math.ceil((double)usedTime/(1000*60));
			
			RequestDispatcher rq = request.getRequestDispatcher("/handlerSerlvet");
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("usedMinute", Integer.toString(usedMinute));
			rq.forward(request,response);
		} 
		
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/error.jsp"); 
			rq.forward(request,response);
		}
		

		//response.sendRedirect("/WebTest/gate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
