package org.srinivas.siteworks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.srinivas.siteworks.hibernate.Admin_Servlet_Start;
import org.srinivas.siteworks.hibernate.Admin_Servlet_ShutDown;


public class TestController extends AbstractController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		if(request.getRequestURI().equals("/StartPage.jsp")){
			Admin_Servlet_Start startServlet = new Admin_Servlet_Start();
			startServlet.doPost(request, response);		
			
		}else if(request.getRequestURI().equals("/ShutPage.jsp")){
			Admin_Servlet_ShutDown shutServlet =  new Admin_Servlet_ShutDown();
		    shutServlet.doPost(request, response);			
	    }
	   return new ModelAndView();
	}
   
}
