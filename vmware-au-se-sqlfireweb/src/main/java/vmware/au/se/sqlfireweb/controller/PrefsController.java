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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.main.UserPref;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.ConnectionManager;

@Controller
public class PrefsController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/preferences", method = RequestMethod.GET)
    public String showPreferences
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show preferences");

    	model.addAttribute("userPref", (UserPref) session.getAttribute("prefs"));
    	
    	// This will resolve to /WEB-INF/jsp/preferences.jsp
    	return "preferences";
    }
	
	@RequestMapping(value = "/preferences", method = RequestMethod.POST)
    public String updatePreferences
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
		logger.debug("Received request to update preferences");

		UserPref userPrefs = (UserPref) session.getAttribute("prefs");
		
        userPrefs.setRecordsToDisplay
        	(Integer.parseInt(request.getParameter("recordsToDisplay")));
        userPrefs.setMaxRecordsinSQLQueryWindow
        	(Integer.parseInt(request.getParameter("maxRecordsinSQLQueryWindow")));
        userPrefs.setAutoCommit
    		((String)request.getParameter("autoCommit"));
        userPrefs.setHistorySize
    		(Integer.parseInt(request.getParameter("historySize")));
        
        ConnectionManager cm = ConnectionManager.getInstance();
        Connection conn = AdminUtil.getConnection((String)session.getAttribute("user_key"));
        
        if (request.getParameter("autoCommit").equals("Y"))
        {
        	conn.setAutoCommit(true);
        }
        else
        {
        	conn.setAutoCommit(false);
        }
        
        cm.updateConnection(conn, (String)session.getAttribute("user_key"));
        
		model.addAttribute("saved", "Succesfully saved application preferences");
		session.setAttribute("userPref", userPrefs);
		
    	// This will resolve to /WEB-INF/jsp/preferences.jsp
    	return "preferences";
    }
}
