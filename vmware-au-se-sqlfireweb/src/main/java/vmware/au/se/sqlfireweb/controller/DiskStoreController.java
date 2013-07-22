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

import java.util.ArrayList;
import java.util.Arrays;
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
import vmware.au.se.sqlfireweb.dao.diskstores.DiskStore;
import vmware.au.se.sqlfireweb.dao.diskstores.DiskStoreDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class DiskStoreController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/diskstores", method = RequestMethod.GET)
    public String showDiskstores
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show diskstores");

		int startAtIndex = 0, endAtIndex = 0;
		javax.servlet.jsp.jstl.sql.Result allDiskStoreInfoResult = null;
		String schema = null; 
    	
    	DiskStoreDAO dsDAO = ISQLFireDAOFactory.getDiskStoreDAO();
    	Result result = new Result();
    	
    	String dsAction = request.getParameter("dsAction");
   	
    	if (dsAction != null)
    	{
    		logger.debug("dsAction = " + dsAction);
    		
    		if (dsAction.equals("ALLDISKSTOREINFO"))
    		{
    			allDiskStoreInfoResult = dsDAO.getDiskstoreInfo
    					((String)request.getParameter("dsName"), 
					     (String)session.getAttribute("user_key"));
    			model.addAttribute("allDiskStoreInfoResult", allDiskStoreInfoResult);
    			model.addAttribute("dsName", (String)request.getParameter("dsName"));
    		}
    		else
    		{
	            result = null;
	            result =
	              dsDAO.simplediskStoreCommand
	                ((String)request.getParameter("dsName"), 
	                 dsAction, 
	                 (String)session.getAttribute("user_key"));
	              
	            model.addAttribute("result", result);
    		}
            
    	}
    	
    	List<DiskStore> dsks = dsDAO.retrieveDiskStoreList
    			("", 
    			 null, 
    			 (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("records", dsks.size());
    	model.addAttribute("estimatedrecords", dsks.size());
    	        
    	UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (dsks.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("diskstores", dsks);  
        }
        else
        {
          if (request.getParameter("startAtIndex") != null)
          {
            startAtIndex = Integer.parseInt(request.getParameter("startAtIndex"));
          }
          
          if (request.getParameter("endAtIndex") != null)
          {
            endAtIndex = Integer.parseInt(request.getParameter("endAtIndex"));
            if (endAtIndex > dsks.size())
            {
              endAtIndex = dsks.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = dsks.subList(startAtIndex, endAtIndex);
          model.addAttribute("diskstores", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
    	
    	// This will resolve to /WEB-INF/jsp/diskstores.jsp
    	return "diskstores";
    }

	@RequestMapping(value = "/diskstores", method = RequestMethod.POST)
    public String performDiskStoreAction
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}

    	int startAtIndex = 0, endAtIndex = 0;
    	Result result = new Result();
    	List<DiskStore> dsks = null;
    	
    	logger.debug("Received request to perform an action on the tables");

    	DiskStoreDAO dsDAO = ISQLFireDAOFactory.getDiskStoreDAO();

    	if (request.getParameter("search") != null)
    	{
	    	dsks = dsDAO.retrieveDiskStoreList
	    			("", 
	    			 (String)request.getParameter("search"), 
	    			 (String)session.getAttribute("user_key"));
	    	
	    	model.addAttribute("search", (String)request.getParameter("search"));
    	}
    	else
    	{
	        String[] tableList  = request.getParameterValues("selected_ds[]");
	        String   commandStr = request.getParameter("submit_mult");
	        
	        logger.debug("tableList = " + Arrays.toString(tableList));
	        logger.debug("command = " + commandStr);
	        
	        // start actions now if tableList is not null
	        
	        if (tableList != null)
	        {
	        	List al = new ArrayList<Result>();
	        	for (String table: tableList)
	        	{
	                result = null;
	                result =
	                  dsDAO.simplediskStoreCommand
	                    (table,
	                     commandStr,
	                     (String)session.getAttribute("user_key"));
	                al.add(result);
	        	}
	        	
	        	model.addAttribute("arrayresult", al);
	        }
	        
	    	dsks = dsDAO.retrieveDiskStoreList
	    			("", 
	    			 null, 
	    			 (String)session.getAttribute("user_key"));
    	}
    	
    	model.addAttribute("records", dsks.size());
    	model.addAttribute("estimatedrecords", dsks.size());
    	
    	UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (dsks.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("diskstores", dsks);  
        }
        else
        {
          if (request.getParameter("startAtIndex") != null)
          {
            startAtIndex = Integer.parseInt(request.getParameter("startAtIndex"));
          }
          
          if (request.getParameter("endAtIndex") != null)
          {
            endAtIndex = Integer.parseInt(request.getParameter("endAtIndex"));
            if (endAtIndex > dsks.size())
            {
              endAtIndex = dsks.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
            
          }
          
          List subList = dsks.subList(startAtIndex, endAtIndex);
          model.addAttribute("diskstores", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
    	
    	// This will resolve to /WEB-INF/jsp/diskstores.jsp
    	return "diskstores";
    	
    }

}
