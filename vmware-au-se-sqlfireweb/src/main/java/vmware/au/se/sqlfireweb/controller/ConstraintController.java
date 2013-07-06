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
import vmware.au.se.sqlfireweb.dao.constraints.Constraint;
import vmware.au.se.sqlfireweb.dao.constraints.ConstraintDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class ConstraintController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/constraints", method = RequestMethod.GET)
    public String showConstraints
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
    	
    	logger.debug("Received request to show constraints");
    	
    	ConstraintDAO conDAO = ISQLFireDAOFactory.getConstraintDAO();
    	Result result = new Result();
    	
    	String conAction = request.getParameter("conAction");
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
    	
    	if (conAction != null)
    	{
    		logger.debug("conAction = " + conAction);
            result = null;
            result =
              conDAO.simpleconstraintCommand
                (schema,
                 (String)request.getParameter("tabName"),
                 (String)request.getParameter("constraintName"),
                 conAction,
                 (String)session.getAttribute("user_key"));
            
            model.addAttribute("result", result);
    	}
    	
    	List<Constraint> cons = conDAO.retrieveConstraintList
    			(schema, 
    			 null, 
    			 (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("records", cons.size());
    	model.addAttribute("estimatedrecords", cons.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (cons.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("cons", cons);  
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
            if (endAtIndex > cons.size())
            {
              endAtIndex = cons.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = cons.subList(startAtIndex, endAtIndex);
          model.addAttribute("cons", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        model.addAttribute("schemas", 
		           ISQLFireDAOUtil.getAllSchemas
		           	((String)session.getAttribute("user_key")));

        model.addAttribute("chosenSchema", schema);

    	// This will resolve to /WEB-INF/jsp/constraints.jsp
    	return "constraints";
    }

	@RequestMapping(value = "/constraints", method = RequestMethod.POST)
    public String performConstraintAction
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		String schema = null;
		Result result = new Result();
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to perform an action on the constraints");
    	
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
    	
    	ConstraintDAO conDAO = ISQLFireDAOFactory.getConstraintDAO();

    	List<Constraint> cons = null;
    	
    	cons = conDAO.retrieveConstraintList
    			(schema, 
    			 (String)request.getParameter("search"), 
    			 (String)session.getAttribute("user_key"));
	    	
	
    	model.addAttribute("search", (String)request.getParameter("search"));
    	model.addAttribute("cons", cons);
    	model.addAttribute("records", cons.size());
        model.addAttribute("schemas", 
		           ISQLFireDAOUtil.getAllSchemas
		           	((String)session.getAttribute("user_key")));

        model.addAttribute("chosenSchema", schema);

    	// This will resolve to /WEB-INF/jsp/constraints.jsp
    	return "constraints";
    
    }

}
