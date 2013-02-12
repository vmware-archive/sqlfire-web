package vmware.au.se.sqlfireweb.controller;

import java.sql.Connection;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.beans.Login;
import vmware.au.se.sqlfireweb.main.UserPref;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.ConnectionManager;
import vmware.au.se.sqlfireweb.utils.QueryUtil;
import vmware.au.se.sqlfireweb.utils.SQLFireJDBCConnection;

@Controller
public class LoginController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) 
    {
    	logger.debug("Received request to show login page");
    	// Create new QueryWindow and add to model
    	// This is the formBackingObject
    	model.addAttribute("loginAttribute", new Login());
    	// This will resolve to /WEB-INF/jsp/loginpage.jsp
    	return "loginpage";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login
    (@ModelAttribute("loginAttribute") Login loginAttribute, 
     Model model, 
     HttpSession session) throws Exception
    {
    	logger.debug("Received request to login");
    	ConnectionManager cm = ConnectionManager.getInstance();
    	Connection conn;
    	
    	try
    	{
	    	if (loginAttribute.getUsername().trim().equals(""))
	    	{
	    		conn = AdminUtil.getNewConnection(loginAttribute.getUrl());
	    	}
	    	else
	    	{
	    		conn = AdminUtil.getNewConnection
	    		  (loginAttribute.getUrl(), 
	    		   loginAttribute.getUsername(),
	    		   loginAttribute.getPassword());
	    	}
	    	
	    	SQLFireJDBCConnection newConn = 
	    			new SQLFireJDBCConnection
	    			  (conn,
	    			   loginAttribute.getUrl(),
	    			   new java.util.Date().toString(),
	    			   loginAttribute.getUsername().trim().equals("") ? "APP" : loginAttribute.getUsername().toUpperCase());
	    	
	    	cm.addConnection(newConn, session.getId());
	    	
	    	session.setAttribute("user_key", session.getId());
	    	session.setAttribute("schema", loginAttribute.getUsername().trim().equals("") ? "APP" : loginAttribute.getUsername().toUpperCase());
	    	session.setAttribute("url", loginAttribute.getUrl());
	    	session.setAttribute("prefs", new UserPref());
	    	
	    	Map<String, String> schemaMap = AdminUtil.getSchemaMap();
	    	
	    	// get schema count now
	    	schemaMap = QueryUtil.populateSchemaMap
	    			(conn, schemaMap, loginAttribute.getUsername().trim().equals("") ? "APP" : loginAttribute.getUsername().toUpperCase());
	    	
	    	session.setAttribute("schemaMap", schemaMap);
	    	
	    	logger.info(loginAttribute);
	    	
	    	// This will resolve to /WEB-INF/jsp/main.jsp
	    	return "main";
    	}
    	catch (Exception ex)
    	{
    		model.addAttribute("error", ex.getMessage());
    		// This will resolve to /WEB-INF/jsp/loginpage.jsp
    		return "loginpage";
    	}
    }
}
