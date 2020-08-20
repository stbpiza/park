package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.gateDAO;
import DTO.gateDTO;


@WebServlet("/handlerSerlvet")
public class handlerSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public handlerSerlvet() {
        super();
            }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String car = request.getParameter("car");
		if (car == null) {
			car = "0";
		}
		String car_num = request.getParameter("car_num");
		String gate_id = request.getParameter("gate_id");
		String payed = request.getParameter("payed");
		if (payed == null) {
		 payed = (String)request.getAttribute("payed");
		}
		System.out.println();

		System.out.println("handlerSerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("car " + car);
		System.out.println("gate_id " + gate_id);
		System.out.println("payed " + payed);
		if (car.equals("incar") && payed == null) {
			//RequestDispatcher rq = request.getRequestDispatcher("/gateSerlvet");  //입차 로그 찍기
			//request.setAttribute("car_num", car_num);
			//rq.forward(request,response);
			
			gateDTO gateDto = new gateDTO();
			gateDto.setCar_num(car_num);
			
			gateDAO gateDao = new gateDAO();
			gateDao.inputInCarLog(gateDto);
			
			response.sendRedirect("/WebTest/gate.jsp");
			
		}
		else if (car.equals("outcar") && payed == null) {
			//RequestDispatcher rq = request.getRequestDispatcher("/gateOutSerlvet");  //출차 로그 찍기
			//request.setAttribute("car_num", car_num);
			//rq.forward(request,response);
			
			gateDTO gateDto = new gateDTO();
			gateDto.setCar_num(car_num);
			
			gateDAO gateDao = new gateDAO();
			gateDao.inputOutCarLog(gateDto);
			
			
			gate_id = gateDao.getId(gateDto);
			gateDto.setGate_id(gate_id);

			
			RequestDispatcher rq = request.getRequestDispatcher("/checkRegularSerlvet");  //정기인지 체크
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			rq.forward(request,response);
			
		}
		else if (payed.equals("no")) {
			RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  //일반결제
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("regular_non", "0");
			rq.forward(request,response);
		}
		else if (payed.equals("yes")) {
			response.sendRedirect("/WebTest/bye.jsp");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
