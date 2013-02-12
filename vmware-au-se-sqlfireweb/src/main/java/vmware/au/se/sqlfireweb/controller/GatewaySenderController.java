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
import vmware.au.se.sqlfireweb.dao.gatewaysenders.GatewaySender;
import vmware.au.se.sqlfireweb.dao.gatewaysenders.GatewaySenderDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class GatewaySenderController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/gatewaysenders", method = RequestMethod.GET)
    public String showGatewaySenders
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		int startAtIndex = 0, endAtIndex = 0;
		javax.servlet.jsp.jstl.sql.Result allGatewaySenderInfoResult = null;
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/isqlfire/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show gateway senders");
    	
    	GatewaySenderDAO gsDAO = ISQLFireDAOFactory.getGatewaySenderDAO();
    	Result result = new Result();
    	
    	String gsAction = request.getParameter("gsAction");
    	
    	if (gsAction != null)
    	{
    		logger.debug("gsAction = " + gsAction);
    		
    		if (gsAction.equals("ALLGATEWAYSENDERINFO"))
    		{
    			allGatewaySenderInfoResult =
    					gsDAO.getGatewaySenderInfo
    					  ((String)request.getParameter("senderId"), 
    					   (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("allGatewaySenderInfoResult", allGatewaySenderInfoResult);
    			model.addAttribute("gatewaysender", (String)request.getParameter("senderId"));
    		}
    		else
    		{
	            result = null;
	            result =
	            	gsDAO.simplegatewaySenderCommand
	                ((String)request.getParameter("senderId"),
	                 gsAction,
	                 (String)session.getAttribute("user_key"));
	            
	            model.addAttribute("result", result);
    		}
    	}
    	
    	List<GatewaySender> gatewaysenders = gsDAO.retrieveGatewaySenderList
    			((String)session.getAttribute("schema"), 
    			 null, 
    			 (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("records", gatewaysenders.size());
    	model.addAttribute("estimatedrecords", gatewaysenders.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (gatewaysenders.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("gatewaysenders", gatewaysenders);  
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
            if (endAtIndex > gatewaysenders.size())
            {
              endAtIndex = gatewaysenders.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = gatewaysenders.subList(startAtIndex, endAtIndex);
          model.addAttribute("gatewaysenders", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        
    	// This will resolve to /WEB-INF/jsp/gatewaysenders.jsp
    	return "gatewaysenders";
    }
	
	@RequestMapping(value = "/gatewaysenders", method = RequestMethod.POST)
    public String performGatewaySendersAction
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
    	List<GatewaySender> gatewaysenders = null;
    	
    	logger.debug("Received request to perform an action on the gateway senders");

    	GatewaySenderDAO gsDAO = ISQLFireDAOFactory.getGatewaySenderDAO();
    	
    	if (request.getParameter("search") != null)
    	{
    		gatewaysenders = gsDAO.retrieveGatewaySenderList
				((String)session.getAttribute("schema"), 
				 (String)request.getParameter("search"), 
				 (String)session.getAttribute("user_key"));
	    	
	    	model.addAttribute("search", (String)request.getParameter("search"));
    	}
    	else
    	{
	        String[] tableList  = request.getParameterValues("selected_gatewaysenders[]");
	        String   commandStr = request.getParameter("submit_mult");
	        
	        logger.debug("tableList = " + Arrays.toString(tableList));
	        logger.debug("command = " + commandStr);
	        
	        // start actions now if tableList is not null
	        
	        if (tableList != null)
	        {
	        	List al = new ArrayList<Result>();
	        	for (String senderId: tableList)
	        	{
	                result = null;
	                result =
                    	gsDAO.simplegatewaySenderCommand
                        (senderId,
                         commandStr,
                         (String)session.getAttribute("user_key"));
	                al.add(result);
	        	}
	        	
	        	model.addAttribute("arrayresult", al);
	        }
	        
	    	gatewaysenders = gsDAO.retrieveGatewaySenderList
	    			((String)session.getAttribute("schema"), 
	    			 null, 
	    			 (String)session.getAttribute("user_key"));
    	}
    	
    	model.addAttribute("records", gatewaysenders.size());
    	model.addAttribute("estimatedrecords", gatewaysenders.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (gatewaysenders.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("gatewaysenders", gatewaysenders);  
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
            if (endAtIndex > gatewaysenders.size())
            {
              endAtIndex = gatewaysenders.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = gatewaysenders.subList(startAtIndex, endAtIndex);
          model.addAttribute("gatewaysenders", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        
    	// This will resolve to /WEB-INF/jsp/gatewaysenders.jsp
    	return "gatewaysenders";
    
    }
}
