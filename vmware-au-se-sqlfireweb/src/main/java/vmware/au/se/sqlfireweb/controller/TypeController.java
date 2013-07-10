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
import vmware.au.se.sqlfireweb.dao.types.Type;
import vmware.au.se.sqlfireweb.dao.types.TypeDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class TypeController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/types", method = RequestMethod.GET)
    public String showTypes
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		int startAtIndex = 0, endAtIndex = 0;
		javax.servlet.jsp.jstl.sql.Result dataLocationResult = null;
		String schema = null;
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show types");
    	
    	TypeDAO typeDAO = ISQLFireDAOFactory.getTypeDAO();
    	Result result = new Result();
    	
    	String typeAction = request.getParameter("typeAction");
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
    	
    	if (typeAction != null)
    	{
    		logger.debug("typeAction = " + typeAction);
    		
            result = null;
            result =
              typeDAO.simpletypeCommand
                (schema,
                 (String)request.getParameter("typeName"),
                 typeAction,
                 (String)session.getAttribute("user_key"));
            model.addAttribute("result", result);
            
    	}
    	
    	List<Type> types = typeDAO.retrieveTypeList
    			(schema, 
    			 null, 
    			 (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("records", types.size());
    	model.addAttribute("estimatedrecords", types.size());
    	        
    	UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (types.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("types", types);  
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
            if (endAtIndex > types.size())
            {
              endAtIndex = types.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = types.subList(startAtIndex, endAtIndex);
          model.addAttribute("types", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        model.addAttribute("schemas", 
        		           ISQLFireDAOUtil.getAllSchemas
        		           	((String)session.getAttribute("user_key")));

        model.addAttribute("chosenSchema", schema);
        
    	// This will resolve to /WEB-INF/jsp/types.jsp
    	return "types";
    }
	
	@RequestMapping(value = "/types", method = RequestMethod.POST)
    public String performTypeAction
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
    	List<Type> types = null;
    	
    	logger.debug("Received request to perform an action on the types");

    	TypeDAO typeDAO = ISQLFireDAOFactory.getTypeDAO();

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
    	
    	if (request.getParameter("search") != null)
    	{
	    	types = typeDAO.retrieveTypeList
	    			(schema, 
	    			 (String)request.getParameter("search"), 
	    			 (String)session.getAttribute("user_key"));
	    	
	    	model.addAttribute("search", (String)request.getParameter("search"));
    	}
    	else
    	{
	        String[] tableList  = request.getParameterValues("selected_type[]");
	        String   commandStr = request.getParameter("submit_mult");
	        
	        logger.debug("tableList = " + Arrays.toString(tableList));
	        logger.debug("command = " + commandStr);
	        
	        // start actions now if tableList is not null
	        
	        if (tableList != null)
	        {
	        	List al = new ArrayList<Result>();
	        	for (String type: tableList)
	        	{
	                result = null;
	                result =
	                  typeDAO.simpletypeCommand
	                    (schema,
	                     type,
	                     commandStr,
	                     (String)session.getAttribute("user_key"));
	                al.add(result);
	        	}
	        	
	        	model.addAttribute("arrayresult", al);
	        }
	        
	    	types = typeDAO.retrieveTypeList
	    			(schema, 
	    			 null, 
	    			 (String)session.getAttribute("user_key"));
    	}
    	
    	model.addAttribute("records", types.size());
    	model.addAttribute("estimatedrecords", types.size());
    	        
    	UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (types.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("types", types);  
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
            if (endAtIndex > types.size())
            {
              endAtIndex = types.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = types.subList(startAtIndex, endAtIndex);
          model.addAttribute("types", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        
        model.addAttribute("schemas", 
		           ISQLFireDAOUtil.getAllSchemas
		           	((String)session.getAttribute("user_key")));
        
        model.addAttribute("chosenSchema", schema);
        
    	// This will resolve to /WEB-INF/jsp/types.jsp
    	return "types";
    }
}
