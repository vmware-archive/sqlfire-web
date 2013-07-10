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
package vmware.au.se.sqlfireweb.dao.triggers;

public class Trigger 
{
	private String triggerId;
	private String schemaName;
	private String triggerName;
	private String created;
	private String event;
	private String firingTime;
	private String type;
	private String state;
	private String triggerDefinition;
	
	public Trigger ()
	{
	}

	public Trigger(String triggerId, String schemaName, String triggerName, String created, String event,
			String firingTime, String type, String state,
			String triggerDefinition) 
	{
		super();
		this.triggerId = triggerId;
		this.schemaName = schemaName;
		this.triggerName = triggerName;
		this.created = created;
		this.event = event;
		this.firingTime = firingTime;
		this.type = type;
		this.state = state;
		this.triggerDefinition = triggerDefinition;
	}

	
	public String getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getFiringTime() {
		return firingTime;
	}

	public void setFiringTime(String firingTime) {
		this.firingTime = firingTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTriggerDefinition() {
		return triggerDefinition;
	}

	public void setTriggerDefinition(String triggerDefinition) {
		this.triggerDefinition = triggerDefinition;
	}

	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "Trigger [triggerId=" + triggerId + ", schemaName=" + schemaName
				+ ", triggerName=" + triggerName + ", created=" + created
				+ ", event=" + event + ", firingTime=" + firingTime + ", type="
				+ type + ", state=" + state + ", triggerDefinition="
				+ triggerDefinition + "]";
	}
	
	
}
