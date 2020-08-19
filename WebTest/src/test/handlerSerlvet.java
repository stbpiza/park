package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.gateDAO;
import DAO.gateOutDAO;
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
		String car_num = request.getParameter("car_num");
		
		if (car.equals("incar")) {
			//RequestDispatcher rq = request.getRequestDispatcher("/gateSerlvet");  //입차 로그 찍기
			//request.setAttribute("car_num", car_num);
			//rq.forward(request,response);
			gateDTO gateDto = new gateDTO();
			gateDto.setCar_num(request.getParameter("car_num"));
			
			gateDAO gateDao = new gateDAO();
			gateDao.insert(gateDto);
			
			response.sendRedirect("/WebTest/gate.jsp");
			
		}
		else if (car.equals("outcar")) {
			//RequestDispatcher rq = request.getRequestDispatcher("/gateOutSerlvet");  //출차 로그 찍기
			//request.setAttribute("car_num", car_num);
			//rq.forward(request,response);
			
			gateDTO gateDto = new gateDTO();
			gateDto.setCar_num(car_num);
			
			gateOutDAO gateDao = new gateOutDAO();
			gateDao.insert(gateDto);
			
			String gate_id;
			DAO.gateIdDAO id = new DAO.gateIdDAO();
			gate_id = id.getId(car_num);
			gateDto.setGate_id(gate_id);

			
			RequestDispatcher rq = request.getRequestDispatcher("/checkRegularSerlvet");  //정기인지 체크
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			rq.forward(request,response);
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
