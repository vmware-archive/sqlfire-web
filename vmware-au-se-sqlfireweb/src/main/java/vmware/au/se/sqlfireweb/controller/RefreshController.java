package vmware.au.se.sqlfireweb.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOFactory;
import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.dao.tables.Table;
import vmware.au.se.sqlfireweb.dao.tables.TableDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.QueryUtil;

@Controller
public class RefreshController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public String showTables
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/isqlfire/login");	
    		return null;
    	}
    	
    	logger.debug("Received request refresh schema object list");
    
    	Map schemaMap = (Map) session.getAttribute("schemaMap");
    	Connection conn = AdminUtil.getConnection((String)session.getAttribute("user_key"));
    	
    	// get schema count now
    	schemaMap = QueryUtil.populateSchemaMap
    			(conn, schemaMap, (String) session.getAttribute("schema"));
    	
    	session.setAttribute("schemaMap", schemaMap);
    	
    	// This will resolve to /WEB-INF/jsp/tables.jsp
    	return "main";
    }
	

}
