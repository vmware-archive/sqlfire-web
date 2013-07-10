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
package vmware.au.se.sqlfireweb.utils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener
{
	  protected static Logger logger = Logger.getLogger("controller");
	  private HttpSession session = null;
	  
	  public void sessionCreated(HttpSessionEvent event)
	  {
	    // no need to do anything here as connection may not have been established yet
	    session  = event.getSession();
	    logger.debug("Session created for id " + session.getId());
	  }

	  public void sessionDestroyed(HttpSessionEvent event)
	  {
	    session  = event.getSession();
	    /*
	     * Need to ensure Connection is closed from ConnectionManager
	     */
	    ConnectionManager cm = null;

	    try
	    {
	      cm = ConnectionManager.getInstance();
	      cm.removeConnection(session.getId());
	      logger.debug("Session destroyed for id " + session.getId());
	    }
	    catch (Exception e)
	    {
	      logger.debug("SesssionListener.sessionDestroyed Unable to obtain Connection", e);
	    }
	  }
}
