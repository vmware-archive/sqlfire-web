/*
  	Copyright (c) 2013 GoPivotal, Inc. All Rights Reserved.

	This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; only version 2 of the License, and no
    later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

	The full text of the GPL is provided in the COPYING file.
*/
package vmware.au.se.sqlfireweb.controller;

import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
