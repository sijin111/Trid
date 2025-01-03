package admin.controller.member;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Constants;
import common.component.PagingComponent;
import common.controller.AbstractController;
import common.domain.PagingDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberDAO_imple;

/*
 * 로그인 내역을 조회하는 컨트롤러
 */
public class MemberLoginHistoryController extends AbstractController {
	
	private final MemberDAO memberDAO = new MemberDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod(); // HTTP 메소드
		
		// 관리자 권한 검증
		if(!super.checkAdminLogin(request)) {
			return;
		}
		
		// POST 요청인 경우
		if("POST".equalsIgnoreCase(method)) {
			super.setRedirect(true);
			super.setViewPage(Constants.ADMIN_LOGIN_HISTORY_URL);
		}
		// GET 요청인 경우
		else {
			int curPage = 1; // 현재 페이지 초기화

			// curPage가 정수인지 예외처리
			try {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			} catch (NumberFormatException e) {}
			
			try {
				Map<String, Object> paraMap = new HashMap<>();
				
				String memberNo = request.getParameter("memberNo"); // 사용자 일련번호
				
				int totalRowCount = memberDAO.selectLoginHistoryTotalRowCount(memberNo); // 전체 행 개수 조회
				
				PagingDTO pagingDTO = PagingComponent.createPaging(curPage, totalRowCount); // 페이징 관련 정보가 저장된 DTO 생성
				
				paraMap.put("pagingDTO", pagingDTO);
				paraMap.put("memberNo", memberNo);
				
				List<Map<String, String>> hisotryList = memberDAO.selectLoginHistoryByAdmin(paraMap); // 사용자 로그인 기록 조회
				
				request.setAttribute("historyList", hisotryList);
				request.setAttribute("pagingDTO", pagingDTO);
				
				super.setRedirect(false);
				super.setViewPage(Constants.ADMIN_LOGIN_HISTORY_JSP);
				
			} catch (SQLException e) {
				e.printStackTrace();
				super.handleServerError();
			}
			
		}
		
	}

}