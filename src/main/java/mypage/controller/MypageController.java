package mypage.controller;

import common.Constants;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MypageController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		//로그인 되어 있지 않으면 표시 안함
		if(!super.checkLogin(request)) {
			super.setRedirect(false);
			super.setViewPage(request.getContextPath());
			return;
		}
		
		super.setRedirect(false);
		super.setViewPage(Constants.MY_PAGE);
		

	}

}
