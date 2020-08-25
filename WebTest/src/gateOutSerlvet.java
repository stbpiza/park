

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DTO.gateDTO;

@WebServlet("/gateOutSerlvet")
public class gateOutSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public gateOutSerlvet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String car_num = request.getParameter("car_num");
		
		gateDTO dto = new gateDTO();
		dto.setCar_num(car_num);
		
		DAO.gateDAO dao = new DAO.gateDAO();
		dao.inputOutCarLog(dto);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
