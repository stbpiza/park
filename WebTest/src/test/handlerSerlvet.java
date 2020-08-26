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
		String in_out;
		String car = request.getParameter("car");                      // incar outcar로 입차 출차 구분
		if (car == null) {
			car = "notUse";
		}
		String car_num = request.getParameter("car_num");
		String gate_id = request.getParameter("gate_id");
		String remainingDays = (String)request.getAttribute("remainingDays");
		String usedMinute = (String)request.getAttribute("usedMinute");

		String payed = request.getParameter("payed");                   //yes면 결제완료 no면 결제해야됨
		if (payed == null) {
		 payed = (String)request.getAttribute("payed");
		}
		String reg = request.getParameter("reg");
		if (reg == null) {
			 reg = (String)request.getAttribute("reg");
			}
		if (reg == null) {
			 reg = "notUse";
		}
		
		System.out.println();                                      //테스트출력영역
		System.out.println("handlerSerlvet");
		System.out.println("car_num " + car_num);
		System.out.println("car " + car);
		System.out.println("gate_id " + gate_id);
		System.out.println("payed " + payed);
		System.out.println("reg " + reg);
		System.out.println("remainingDays " + remainingDays);
		System.out.println("usedMinute " + usedMinute);
		
		try {
			//gate_id.contentEquals("test"); // 비정상 접근 방지
		if (car.contentEquals("incar") && payed == null) {                                       //입차 로그 찍기

			gateDTO gateDto = new gateDTO();
			gateDto.setCar_num(car_num);
			
			gateDAO gateDao = new gateDAO();
			in_out = gateDao.getIn_out(gateDto);
			
			if (in_out == null) {  //정상입차 - 처음와본차량
				System.out.println("첫입차");
				gateDao.inputInCarLog(gateDto);
				
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/hi.jsp");
				rq.forward(request,response);	
			}
			
			else if (in_out.contentEquals("0")) {  //출차로그 찍힌 후 계산유무 확인
				System.out.println("출차로그는찍힌상태");
				gate_id = gateDao.getId(gateDto);
				
				DTO.receiptDTO recDto = new DTO.receiptDTO();
				recDto.setGate_id(gate_id);
				
				DAO.receiptDAO recDao = new DAO.receiptDAO();
				String rec_id = recDao.checkReceipt(recDto);
				
				if (rec_id == null) {                           //계산 안된차
					System.out.println("출차로그찍고계산안했음");
					in_out="1";
					RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/error.jsp"); 
					request.setAttribute("in_out", in_out);
					rq.forward(request,response);	
				}
				
				else {                                     //정상입차
					System.out.println("출차로그찍고계산함 정상입차");
					gateDao.inputInCarLog(gateDto);
					
					RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/hi.jsp");
					rq.forward(request,response);
				}
			}
			else {
			System.out.println("출차로그없는상황");
			in_out="1";
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/error.jsp");  //이미 입차된 차
			request.setAttribute("in_out", in_out);
			rq.forward(request,response);	
			}
			
			
		}
		else if (car.contentEquals("outcar") && payed == null) {                            //출차 로그 찍기
			
			gateDTO gateDto = new gateDTO();
			gateDto.setCar_num(car_num);
			
			gateDAO gateDao = new gateDAO();
			in_out = gateDao.getIn_out(gateDto);
			if (in_out == null) {
				in_out = "first";
			}
			System.out.println("inout 추출 " + in_out);
			
			if (in_out.contentEquals("1")) {
				System.out.println("입차로그 있는상황");
				gateDao.inputOutCarLog(gateDto);
				
				gate_id = gateDao.getId(gateDto);
				gateDto.setGate_id(gate_id);
	
				RequestDispatcher rq = request.getRequestDispatcher("/regularSerlvet");  //정기인지 체크
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				rq.forward(request,response);
			}
			else if (in_out.contentEquals("first")) {
				System.out.println("아무로그없이 출차시도");
				in_out = "0";
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/error.jsp");  
				request.setAttribute("in_out", in_out);
				rq.forward(request,response);	
			}
			else {
				System.out.println("출차로그찍혀있는데 재출차");
				gate_id = gateDao.getId(gateDto);
				gateDto.setGate_id(gate_id);
				
				DTO.receiptDTO recDto = new DTO.receiptDTO();
				recDto.setGate_id(gate_id);
				
				DAO.receiptDAO recDao = new DAO.receiptDAO();
				String rec_id = recDao.checkReceipt(recDto);
				
				if (rec_id == null) {                           //로그만 찍히고 계산안됨
					System.out.println("계산안한상태(실제출차못함)");
					gateDao.inputOutCarLog(gateDto);
					gate_id = gateDao.getId(gateDto);
					
					RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/repay.jsp"); 
					request.setAttribute("car_num", car_num);
					request.setAttribute("gate_id", gate_id);
					rq.forward(request,response);
				}
				
				else {                                             //계산하고 나갔는데 또 출차?
					System.out.println("정상출차 차량");
					in_out = "0";
					RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/error.jsp");  
					request.setAttribute("in_out", in_out);
					rq.forward(request,response);	
				}
			}
		}
		else if (payed.contentEquals("no") && usedMinute == null) {
			System.out.println("이용시간계산요청");
			RequestDispatcher rq = request.getRequestDispatcher("/gateSerlvet");  //이용시간계산
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("reg", reg);
			rq.forward(request,response);
		}
		else if (payed.contentEquals("no") && reg.contentEquals("no")) {
			System.out.println("일반결제로 보내기");
			RequestDispatcher rq = request.getRequestDispatcher("/paySerlvet");  //일반결제
			request.setAttribute("car_num", car_num);
			request.setAttribute("gate_id", gate_id);
			request.setAttribute("usedMinute", usedMinute);
			request.setAttribute("regular_non", "0");
			rq.forward(request,response);
		}
		else if (payed.contentEquals("no") && reg.contentEquals("yes")) {
			System.out.println("정기가입요청자 이용시간분석");
			int usedMinuteInt = Integer.parseInt(usedMinute);
			if (usedMinuteInt >= 1440) { //24시간 초과 이용자는 정기가입 안됨
				System.out.println("24시간 초과 가입거부");
				RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/error.jsp");  
				request.setAttribute("in_out", "2");
				rq.forward(request,response);
			}
			else { //무사정기가입
				System.out.println("가입통과");
				RequestDispatcher rq = request.getRequestDispatcher("/regularSerlvet");  
				request.setAttribute("car_num", car_num);
				request.setAttribute("gate_id", gate_id);
				request.setAttribute("reg", "yes");
				rq.forward(request,response);
			}
		}
		else if (payed.contentEquals("yes")) {
			System.out.println("출차 문열어주기");
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/bye.jsp");  //문열어주기
			request.setAttribute("remainingDays", remainingDays);
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
