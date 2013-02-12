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
