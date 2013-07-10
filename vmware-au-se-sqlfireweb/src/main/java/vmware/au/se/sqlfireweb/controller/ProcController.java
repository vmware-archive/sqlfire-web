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
import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.dao.stored.ProcedureParameter;
import vmware.au.se.sqlfireweb.dao.stored.StoredProc;
import vmware.au.se.sqlfireweb.dao.stored.StoredProcDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class ProcController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/procs", method = RequestMethod.GET)
    public String showProcs
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		int startAtIndex = 0, endAtIndex = 0;
		String schema = null;
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show procs");
    	
    	StoredProcDAO procDAO = ISQLFireDAOFactory.getStoredProcDAO();
    	Result result = new Result();
    	
    	String procType  = request.getParameter("procType");
    	logger.debug("procType = " + procType);
    	String procAction = request.getParameter("procAction");
    	String selectedSchema = request.getParameter("selectedSchema");
    	logger.debug("selectedSchema = " + selectedSchema);
    	
    	if (selectedSchema != null)
    	{
    		schema = selectedSchema;
    	}
    	else
    	{
    		schema = (String)session.getAttribute("schema");
    	}
    	
    	logger.debug("schema = " + schema);
    	
    	if (procAction != null)
    	{
    		logger.debug("procAction = " + procAction);
    		if (procAction.equalsIgnoreCase("DESC"))
    		{
    			List<ProcedureParameter> procParams =
    					procDAO.describeProcedure
    					   (schema, 
    					    (String)request.getParameter("procName"),
    					    (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("procParams", procParams);
    			model.addAttribute("procName", (String)request.getParameter("procName"));
    		}
    		else
    		{
	            result = null;
	            result =
	              procDAO.simpleprocCommand
	                (schema,
	                 (String)request.getParameter("procName"),
	                 procAction,
	                 request.getParameter("procType").equals("P") ? "procedure" : "function",
	                 (String)session.getAttribute("user_key"));
	            
	            model.addAttribute("result", result);
    		}
    	}
    	
    	List<StoredProc> procs = procDAO.retrieveProcList
    			(schema, 
    			 null, 
    			 (String)request.getParameter("procType"),
    			 (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("procType", procType);
    	model.addAttribute("records", procs.size());
    	model.addAttribute("estimatedrecords", procs.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (procs.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("procs", procs);  
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
            if (endAtIndex > procs.size())
            {
              endAtIndex = procs.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = procs.subList(startAtIndex, endAtIndex);
          model.addAttribute("procs", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        model.addAttribute("schemas", 
		           ISQLFireDAOUtil.getAllSchemas
		           	((String)session.getAttribute("user_key")));

        model.addAttribute("chosenSchema", schema);
     
    	// This will resolve to /WEB-INF/jsp/procs.jsp
    	return "procs";
    }
	
	@RequestMapping(value = "/procs", method = RequestMethod.POST)
    public String performProcAction
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	int startAtIndex = 0, endAtIndex = 0;
    	String schema = null;
    	Result result = new Result();
    	List<StoredProc> procs = null;
    	
    	logger.debug("Received request to perform an action on the procs");
    	String procType  = request.getParameter("procType");
    	logger.debug("procType = " + procType);

    	String selectedSchema = request.getParameter("selectedSchema");
    	logger.debug("selectedSchema = " + selectedSchema);
    	
    	if (selectedSchema != null)
    	{
    		schema = selectedSchema;
    	}
    	else
    	{
    		schema = (String)session.getAttribute("schema");
    	}
    	
    	logger.debug("schema = " + schema);
    	
    	StoredProcDAO procDAO = ISQLFireDAOFactory.getStoredProcDAO();
    	
    	if (request.getParameter("search") != null)
    	{
	    	procs = procDAO.retrieveProcList
	    			(schema, 
	    			 (String)request.getParameter("search"), 
	    			 (String)request.getParameter("procType"),
	    			 (String)session.getAttribute("user_key"));
	    	
	    	model.addAttribute("search", (String)request.getParameter("search"));
    	}
    	else
    	{
	        String[] tableList  = request.getParameterValues("selected_proc[]");
	        String   commandStr = request.getParameter("submit_mult");
	        
	        logger.debug("tableList = " + Arrays.toString(tableList));
	        logger.debug("command = " + commandStr);
	        
	        // start actions now if tableList is not null
	        
	        if (tableList != null)
	        {
	        	List al = new ArrayList<Result>();
	        	for (String procName: tableList)
	        	{
	                result = null;
	                result =
	                 procDAO.simpleprocCommand
	                    (schema,
	                     procName,
	                     commandStr,
	                     request.getParameter("procType").equals("P") ? "procedure" : "function",
	                     (String)session.getAttribute("user_key"));
	                al.add(result);
	        	}
	        	
	        	model.addAttribute("arrayresult", al);
	        }
	        
	        procs = procDAO.retrieveProcList
	    			(schema, 
	    			 null, 
	    			 (String)request.getParameter("procType"),
	    			 (String)session.getAttribute("user_key"));
    	}
    	
    	model.addAttribute("procType", procType);
    	model.addAttribute("records", procs.size());
    	model.addAttribute("estimatedrecords", procs.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (procs.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("procs", procs);  
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
            if (endAtIndex > procs.size())
            {
              endAtIndex = procs.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = procs.subList(startAtIndex, endAtIndex);
          model.addAttribute("procs", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        
        model.addAttribute("schemas", 
		           ISQLFireDAOUtil.getAllSchemas
		           	((String)session.getAttribute("user_key")));

        model.addAttribute("chosenSchema", schema);

    	// This will resolve to /WEB-INF/jsp/procs.jsp
    	return "procs";
    }
}
