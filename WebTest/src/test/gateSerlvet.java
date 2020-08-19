package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.gateDAO;
import DTO.gateDTO;

@WebServlet("/gateSerlvet")
public class gateSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public gateSerlvet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		gateDTO dto = new gateDTO();
		dto.setCar_num(request.getParameter("car_num"));
		
		gateDAO dao = new gateDAO();
		dao.insert(dto);
		
		response.sendRedirect("/WebTest/gate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
