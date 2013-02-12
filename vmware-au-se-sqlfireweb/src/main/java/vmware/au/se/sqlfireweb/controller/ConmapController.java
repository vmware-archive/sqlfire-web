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
public class ConmapController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
    @RequestMapping(value = "/viewconmap", method = RequestMethod.GET)
    public String worksheet
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/isqlfire/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show connection map");
    	
    	ConnectionManager cm = ConnectionManager.getInstance();
    	
    	model.addAttribute("conmap", cm.getConnectionMap());
    	model.addAttribute("conmapsize", cm.getConnectionListSize());
    	
    	// This will resolve to /WEB-INF/jsp/conmap.jsp
    	return "conmap";
    }
}
