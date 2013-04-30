package vmware.au.se.sqlfireweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOFactory;
import vmware.au.se.sqlfireweb.dao.members.Member;
import vmware.au.se.sqlfireweb.dao.members.MemberDAO;


@Controller
public class MemberController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/members", method = RequestMethod.GET)
    public String showDiskstores
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/isqlfire/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show members");
    	
    	MemberDAO mbrDAO = ISQLFireDAOFactory.getMemberDAO();
    	
    	List<Member> members = mbrDAO.retrieveMembers
    			((String)session.getAttribute("user_key"));
    	
    	model.addAttribute("records", members.size());
    	model.addAttribute("estimatedrecords", members.size());
    	        
    	// This will resolve to /WEB-INF/jsp/diskstores.jsp
    	return "members";
    }

}
