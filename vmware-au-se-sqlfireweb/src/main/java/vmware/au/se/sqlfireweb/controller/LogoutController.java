package vmware.au.se.sqlfireweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.utils.ConnectionManager;

@Controller
public class LogoutController 
{
	protected static Logger logger = Logger.getLogger("controller");

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout
      (Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception 
    {
    	logger.debug("Received request to logout of SQLFire*Web");

    	// remove connection from list
    	ConnectionManager cm = ConnectionManager.getInstance();
    	cm.removeConnection(session.getId());
    	
    	session.invalidate();
    	
    	response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");
    	
    	return null;
    }
}
