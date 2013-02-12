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
import vmware.au.se.sqlfireweb.dao.indexes.Index;
import vmware.au.se.sqlfireweb.dao.indexes.IndexDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class IndexController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/indexes", method = RequestMethod.GET)
    public String showInexes
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		int startAtIndex = 0, endAtIndex = 0;
		String schema = null;
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/isqlfire/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show indexes");
    	
    	IndexDAO indexDAO = ISQLFireDAOFactory.getIndexDAO();
    	Result result = new Result();
    	
    	String idxAction = request.getParameter("idxAction");
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
    	
    	if (idxAction != null)
    	{
    		logger.debug("idxAction = " + idxAction);
            result = null;
            result =
              indexDAO.simpleindexCommand
                (schema,
                 (String)request.getParameter("indexName"),
                 idxAction,
                 (String)session.getAttribute("user_key"));
            
            model.addAttribute("result", result);
    	}
    	
    	List<Index> indexes = indexDAO.retrieveIndexList
    			(schema, 
    			 null, 
    			 (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("records", indexes.size());
    	model.addAttribute("estimatedrecords", indexes.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (indexes.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("indexes", indexes);  
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
            if (endAtIndex > indexes.size())
            {
              endAtIndex = indexes.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = indexes.subList(startAtIndex, endAtIndex);
          model.addAttribute("indexes", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        model.addAttribute("schemas", 
		           ISQLFireDAOUtil.getAllSchemas
		           	((String)session.getAttribute("user_key")));

        model.addAttribute("chosenSchema", schema);

    	// This will resolve to /WEB-INF/jsp/indexes.jsp
    	return "indexes";
    }
	
	@RequestMapping(value = "/indexes", method = RequestMethod.POST)
    public String performIndexAction
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		String schema = null;
		int startAtIndex = 0, endAtIndex = 0;
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/isqlfire/login");	
    		return null;
    	}
    	
    	Result result = new Result();
    	List<Index> indexes = null;
    	
    	logger.debug("Received request to perform an action on the indexes");

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
    	
    	IndexDAO indexDAO = ISQLFireDAOFactory.getIndexDAO();
    	
    	if (request.getParameter("search") != null)
    	{
	    	indexes = indexDAO.retrieveIndexList
	    			(schema, 
	    			 (String)request.getParameter("search"), 
	    			 (String)session.getAttribute("user_key"));
	    	
	    	model.addAttribute("search", (String)request.getParameter("search"));
    	}
    	else
    	{
	        String[] tableList  = request.getParameterValues("selected_idx[]");
	        String   commandStr = request.getParameter("submit_mult");
	        
	        logger.debug("tableList = " + Arrays.toString(tableList));
	        logger.debug("command = " + commandStr);
	        
	        // start actions now if tableList is not null
	        
	        if (tableList != null)
	        {
	        	List al = new ArrayList<Result>();
	        	for (String index: tableList)
	        	{
	                result = null;
	                result =
	                 indexDAO.simpleindexCommand
	                    (schema,
	                     index,
	                     commandStr,
	                     (String)session.getAttribute("user_key"));
	                al.add(result);
	        	}
	        	
	        	model.addAttribute("arrayresult", al);
	        }
	        
	    	indexes = indexDAO.retrieveIndexList
	    			(schema, 
	    			 null, 
	    			 (String)session.getAttribute("user_key"));
    	}

    	model.addAttribute("records", indexes.size());
    	model.addAttribute("estimatedrecords", indexes.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (indexes.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("indexes", indexes);  
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
            if (endAtIndex > indexes.size())
            {
              endAtIndex = indexes.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = indexes.subList(startAtIndex, endAtIndex);
          model.addAttribute("indexes", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        
        model.addAttribute("schemas", 
		           ISQLFireDAOUtil.getAllSchemas
		           	((String)session.getAttribute("user_key")));
     
        model.addAttribute("chosenSchema", schema);
     
    	// This will resolve to /WEB-INF/jsp/indexes.jsp
    	return "indexes";
    }
}
