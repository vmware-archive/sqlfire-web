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
import vmware.au.se.sqlfireweb.dao.asyncevent.Asyncevent;
import vmware.au.se.sqlfireweb.dao.asyncevent.AsynceventDAO;
import vmware.au.se.sqlfireweb.dao.tables.Table;
import vmware.au.se.sqlfireweb.dao.tables.TableDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class AsyncController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/asyncevent", method = RequestMethod.GET)
    public String showAsyncEvents
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		int startAtIndex = 0, endAtIndex = 0;
		javax.servlet.jsp.jstl.sql.Result allAsyncEventInfoResult = null;
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/isqlfire/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show async events");
    	
    	AsynceventDAO asyncDAO = ISQLFireDAOFactory.getAsynceventDAO();
    	Result result = new Result();
    	
    	String asyncAction = request.getParameter("asyncAction");
    	
    	if (asyncAction != null)
    	{
    		logger.debug("asyncAction = " + asyncAction);
    		if (asyncAction.equals("ALLASYNCEVENTINFO"))
    		{
    			allAsyncEventInfoResult =
    					asyncDAO.getAsynEventInfo
    					  ((String)request.getParameter("asyncName"), 
    					   (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("allAsyncEventInfoResult", allAsyncEventInfoResult);
    			model.addAttribute("asyncevent", (String)request.getParameter("asyncName"));
    		}
    		else
    		{
	            result = null;
	            result =
	            	asyncDAO.simpleasynceventCommand
	                ((String)request.getParameter("asyncName"),
	                 asyncAction,
	                 (String)session.getAttribute("user_key"));
	            
	            model.addAttribute("result", result);
    		}
    	}
    	
    	List<Asyncevent> asyncevents = asyncDAO.retrieveAsynceventList
    			((String)session.getAttribute("schema"), 
    			 null, 
    			 (String)session.getAttribute("user_key"));

    	model.addAttribute("records", asyncevents.size());
    	model.addAttribute("estimatedrecords", asyncevents.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (asyncevents.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("asyncevents", asyncevents); 
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
            if (endAtIndex > asyncevents.size())
            {
              endAtIndex = asyncevents.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = asyncevents.subList(startAtIndex, endAtIndex);
          model.addAttribute("asyncevents", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        
    	// This will resolve to /WEB-INF/jsp/async.jsp
    	return "async";
    }
	
	@RequestMapping(value = "/asyncevent", method = RequestMethod.POST)
    public String performAsyncAction
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/isqlfire/login");	
    		return null;
    	}
    	
    	int startAtIndex = 0, endAtIndex = 0;
    	Result result = new Result();
    	List<Asyncevent> asyncevents = null;
    	
    	logger.debug("Received request to perform an action on the async events");

    	AsynceventDAO asyncDAO = ISQLFireDAOFactory.getAsynceventDAO();
    	
    	if (request.getParameter("search") != null)
    	{
    		asyncevents = asyncDAO.retrieveAsynceventList
				((String)session.getAttribute("schema"), 
				 (String)request.getParameter("search"), 
				 (String)session.getAttribute("user_key"));
	    	
	    	model.addAttribute("search", (String)request.getParameter("search"));
    	}
    	else
    	{
	        String[] tableList  = request.getParameterValues("selected_async[]");
	        String   commandStr = request.getParameter("submit_mult");
	        
	        logger.debug("tableList = " + Arrays.toString(tableList));
	        logger.debug("command = " + commandStr);
	        
	        // start actions now if tableList is not null
	        
	        if (tableList != null)
	        {
	        	List al = new ArrayList<Result>();
	        	for (String asyncName: tableList)
	        	{
	                result = null;
	                result =
                    	asyncDAO.simpleasynceventCommand
                        (asyncName,
                         commandStr,
                         (String)session.getAttribute("user_key"));
	                al.add(result);
	        	}
	        	
	        	model.addAttribute("arrayresult", al);
	        }
	        
	    	asyncevents = asyncDAO.retrieveAsynceventList
	    			((String)session.getAttribute("schema"), 
	    			 null, 
	    			 (String)session.getAttribute("user_key"));
    	}
    	
    	model.addAttribute("records", asyncevents.size());
    	model.addAttribute("estimatedrecords", asyncevents.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (asyncevents.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("asyncevents", asyncevents); 
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
            if (endAtIndex > asyncevents.size())
            {
              endAtIndex = asyncevents.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = asyncevents.subList(startAtIndex, endAtIndex);
          model.addAttribute("asyncevents", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
    
    	// This will resolve to /WEB-INF/jsp/async.jsp
    	return "async";
    }
}
