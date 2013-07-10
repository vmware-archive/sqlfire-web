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
package vmware.au.se.sqlfireweb.beans;

public class CommandResult 
{
    private String command;
    private String message;
    private int rows;
    private String elapsedTime;
    
    public void setCommand(String command)
    {
    	this.command = command;
    }

    public String getCommand()
    {
    	return command;
    }

    public void setMessage(String message)
    {
    	this.message = message;
    }

    public String getMessage()
    {
    	return message;
    }

    
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	
	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Override
	public String toString() {
		return "CommandResult [command=" + command + ", message=" + message
				+ ", rows=" + rows + ", elapsedTime=" + elapsedTime + "]";
	}
	  
}
