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

@WebServlet("/regularSerlvet")
public class RegularSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegularSerlvet() {
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
		String reg = (String)request.getAttribute("reg");
		if (reg == null) {
			reg = request.getParameter("reg");
		}
		String rec_id = (String)request.getAttribute("rec_id");
		
		System.out.println();
		System.out.println("regularSerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("gate_id " + gate_id);
		System.out.println("reg " + reg);
		System.out.println("rec_id " + rec_id);
		
		try {
		gate_id.contentEquals("test"); // 비정상 접근 방지
		if (reg == null) { // 정기유무 확인파트
			System.out.println("정기회원인지 확인");
			String time ;
			
			DTO.regularDTO regDto = new DTO.regularDTO();
			regDto.setCar_num(car_num);
			
			DAO.RegularDAO checkReg = new DAO.RegularDAO();
			time = checkReg.getTime(regDto);
			System.out.println("time " + time);
			
			if (time != null) {                                             //정기로그에 있는경우
			
	       Date now = new Date();
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	       try { //시간계산 트라이캐치
			Date timeDate = format.parse(time);                      
			long remainingTime = timeDate.getTime() - now.getTime();
			System.out.println("remainingTime " + remainingTime);
			int remainingDays = (int)(remainingTime/(1000*60*60*24));
			System.out.println("remainingDays " + remainingDays);
		       if (remainingDays > 0) {
		    	   System.out.println("정기맞음");
		    	   	RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  //정기맞음
					request.setAttribute("gate_id", gate_id);
					request.setAttribute("regular_non", "1");
					request.setAttribute("remainingDays", Integer.toString(remainingDays));
					rq.forward(request,response);
		       }
		       else {
		    	   System.out.println("날짜지남");
					RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/signUp.jsp");  //날짜지남
					request.setAttribute("gate_id", gate_id);
					request.setAttribute("car_num", car_num);
					rq.forward(request,response);
		       }
	       } 
	       catch (ParseException e) {
			e.printStackTrace();
	       }
		}
			else {                                                                          //정기로그에 없는경우
				System.out.println("정기로그에없음");
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/signUp.jsp");  
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("car_num", car_num);
				rq.forward(request,response);
			}
	}
		else if (reg.contentEquals("yes")) {                                           //정기 신규등록 파트
			System.out.println("정기가입파트");
			RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("car_num", car_num);
			request.setAttribute("regular_non", "new");
			rq.forward(request,response);
		}
		
		else {                                                     //계산 완료한 신규가입자 로그등록
			System.out.println("정기계산자 로그찍기");
			DTO.regularDTO regDto = new DTO.regularDTO();
			regDto.setCar_num(car_num);
			regDto.setRec_id(rec_id);
			
			DAO.RegularDAO regDao = new DAO.RegularDAO();
			regDao.insert(regDto);
			
			RequestDispatcher rq = request.getRequestDispatcher("/handlerSerlvet");  
			request.setAttribute("payed", "yes");
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
