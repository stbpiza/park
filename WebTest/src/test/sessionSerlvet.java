package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionSerlvet")
public class sessionSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public sessionSerlvet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		if (id == null) {
			id = "notUse";
		}
		String password = request.getParameter("password");
		if (password == null) {
			password = "notUse";
		}
		String errorCheck = request.getParameter("errorCheck");
		String type = request.getParameter("type");
		String setPricePerHour = request.getParameter("setPricePerHour");
		String setReg_price = request.getParameter("setReg_price");
		
		System.out.println();
		System.out.println("sessionSerlvet");
		System.out.println("id " + id);
		System.out.println("password " + password);
		System.out.println("errorCheck " + errorCheck);
		System.out.println("type " + type);
		
		
		try {
			errorCheck.contentEquals("test"); //주소로 들어오는거 방지
			if (type == null) {
				if (id.contentEquals("admin") && password.contentEquals("0000")) {
					System.out.println("로그인성공");
					HttpSession session = request.getSession(true);
					session.setMaxInactiveInterval(86400);
					session.setAttribute("setPricePerHour","1000");
					session.setAttribute("setReg_price","50000");
					session.setAttribute("changeBox", "100000");
					RequestDispatcher rq = request.getRequestDispatcher("/gate.jsp");  //로그인 성공
					rq.forward(request,response);
				}
				else if (!id.contentEquals("admin") || !password.contentEquals("0000")) {
					System.out.println("로그인실패");
					RequestDispatcher rq = request.getRequestDispatcher("/start.jsp");  //로그인 실패
					request.setAttribute("idCheck", "no");
					rq.forward(request,response);
				}
			}
			else if (type.contentEquals("change")) {
				System.out.println("잔돈추가");
				HttpSession ss = request.getSession();
				String changeBox = (String)ss.getAttribute("changeBox");
				if (changeBox==null) {                                       //null에러 방지
					changeBox="0";
				}
				int changeBoxInt = Integer.parseInt(changeBox);             //잔돈추가
				changeBoxInt = changeBoxInt + 100000;
				if (changeBoxInt>300000) {
					changeBoxInt = 300000;
				}
				ss.setAttribute("changeBox", Integer.toString(changeBoxInt));
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/admin.jsp");  
				request.setAttribute("set", "yes");
				rq.forward(request,response);
			}
			else if (setPricePerHour != null) {
				System.out.println("시간당 금액 수정" + setPricePerHour);
				HttpSession session = request.getSession();
				session.setAttribute("setPricePerHour",setPricePerHour);
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/admin.jsp");  //시간당 금액 수정
				request.setAttribute("set", "yes");
				rq.forward(request,response);
			}
			else if (setReg_price != null) {
				System.out.println("정기 금액 수정" + setReg_price);
				HttpSession session = request.getSession();
				session.setAttribute("setReg_price",setReg_price);
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/admin.jsp");  //정기 금액 수정
				request.setAttribute("set", "yes");
				rq.forward(request,response);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			RequestDispatcher rq = request.getRequestDispatcher("/start.jsp"); //주소로 들어오면 초기화면으로 보냄
			rq.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
