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
import vmware.au.se.sqlfireweb.dao.tables.Table;
import vmware.au.se.sqlfireweb.dao.tables.TableDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class TableController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/tables", method = RequestMethod.GET)
    public String showTables
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		int startAtIndex = 0, endAtIndex = 0;
		javax.servlet.jsp.jstl.sql.Result dataLocationResult, memoryUsageResult, expirationEvictionResult, allTableInfoResult, tableStructure = null;
		String schema = null; 
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show tables");
    	
    	TableDAO tableDAO = ISQLFireDAOFactory.getTableDAO();
    	Result result = new Result();
    	
    	String tabAction = request.getParameter("tabAction");
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
    	
    	if (tabAction != null)
    	{
    		logger.debug("tabAction = " + tabAction);
    		
    		if (tabAction.equalsIgnoreCase("LOADSCRIPT"))
    		{
    			model.addAttribute("loadscriptsql", 
    					tableDAO.generateLoadScript
    						(schema,
    						 (String)request.getParameter("tabName")));
    			model.addAttribute("tablename", (String)request.getParameter("tabName"));
    		}
    		else if (tabAction.equalsIgnoreCase("DATALOCATIONS"))
    		{
    			dataLocationResult = 
    					tableDAO.getDataLocations
    					   (schema,
    						(String)request.getParameter("tabName"),
    					    (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("dataLocationResults", dataLocationResult);
    			model.addAttribute("tablename", (String)request.getParameter("tabName"));
    		}
    		else if (tabAction.equalsIgnoreCase("MEMORYUSAGE"))
    		{
    			memoryUsageResult = 
    					tableDAO.getMemoryUsage
    					   (schema,
    						(String)request.getParameter("tabName"),
    					    (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("tableMemoryUsage", memoryUsageResult);
    			model.addAttribute("tablename", (String)request.getParameter("tabName"));
    		}
    		else if (tabAction.equalsIgnoreCase("EXPIRATION-EVICTION"))
    		{
    			expirationEvictionResult = 
    					tableDAO.getExpirationEvictionAttrs
    					   (schema,
    						(String)request.getParameter("tabName"),
    					    (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("expirationEvictionResult", expirationEvictionResult);
    			model.addAttribute("tablename", (String)request.getParameter("tabName"));
    		}
    		else if (tabAction.equalsIgnoreCase("ALLTABLEINFO"))
    		{
    			allTableInfoResult = 
    					tableDAO.getAllTableInfo
    					   (schema,
    						(String)request.getParameter("tabName"),
    					    (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("allTableInfoResult", allTableInfoResult);
    			model.addAttribute("tablename", (String)request.getParameter("tabName"));
    		}
    		else if (tabAction.equalsIgnoreCase("STRUCTURE"))
    		{
    			tableStructure = 
    					tableDAO.getTableStructure
    					   (schema,
    						(String)request.getParameter("tabName"),
    					    (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("tableStructure", tableStructure);
    			model.addAttribute("tablename", (String)request.getParameter("tabName"));
    		}
    		else if (tabAction.equalsIgnoreCase("PARTITIONATTRS"))
    		{
    			String parAttrResult = 
    					tableDAO.viewPartitionAttrs
    					   (schema,
    						(String)request.getParameter("tabName"),
    					    (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("parAttrResult", parAttrResult);
    			model.addAttribute("tablename", (String)request.getParameter("tabName"));
    		}
    		else
    		{
	            result = null;
	            result =
	              tableDAO.simpletableCommand
	                (schema,
	                 (String)request.getParameter("tabName"),
	                 tabAction,
	                 (String)session.getAttribute("user_key"));
	            model.addAttribute("result", result);
    		}   
            
    	}
    	
    	List<Table> tbls = tableDAO.retrieveTableList
    			(schema, 
    			 null, 
    			 (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("records", tbls.size());
    	model.addAttribute("estimatedrecords", tbls.size());
    	        
    	UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (tbls.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("tables", tbls);  
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
            if (endAtIndex > tbls.size())
            {
              endAtIndex = tbls.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = tbls.subList(startAtIndex, endAtIndex);
          model.addAttribute("tables", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        model.addAttribute("schemas", 
        		           ISQLFireDAOUtil.getAllSchemas
        		           	((String)session.getAttribute("user_key")));

        model.addAttribute("chosenSchema", schema);
        
    	// This will resolve to /WEB-INF/jsp/tables.jsp
    	return "tables";
    }
	
	@RequestMapping(value = "/tables", method = RequestMethod.POST)
    public String performTableAction
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
    	List<Table> tbls = null;
    	
    	logger.debug("Received request to perform an action on the tables");

    	TableDAO tableDAO = ISQLFireDAOFactory.getTableDAO();

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
	    	tbls = tableDAO.retrieveTableList
	    			(schema, 
	    			 (String)request.getParameter("search"), 
	    			 (String)session.getAttribute("user_key"));
	    	
	    	model.addAttribute("search", (String)request.getParameter("search"));
    	}
    	else
    	{
	        String[] tableList  = request.getParameterValues("selected_tbl[]");
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
	                  tableDAO.simpletableCommand
	                    (schema,
	                     table,
	                     commandStr,
	                     (String)session.getAttribute("user_key"));
	                al.add(result);
	        	}
	        	
	        	model.addAttribute("arrayresult", al);
	        }
	        
	    	tbls = tableDAO.retrieveTableList
	    			(schema, 
	    			 null, 
	    			 (String)session.getAttribute("user_key"));
    	}
    	
    	model.addAttribute("records", tbls.size());
    	model.addAttribute("estimatedrecords", tbls.size());
    	
    	UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (tbls.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("tables", tbls);  
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
            if (endAtIndex > tbls.size())
            {
              endAtIndex = tbls.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
            
          }
          
          List subList = tbls.subList(startAtIndex, endAtIndex);
          model.addAttribute("tables", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        model.addAttribute("schemas", 
		           ISQLFireDAOUtil.getAllSchemas
		           	((String)session.getAttribute("user_key")));
        
        model.addAttribute("chosenSchema", schema);
        
    	// This will resolve to /WEB-INF/jsp/tables.jsp
    	return "tables";
    }
}
