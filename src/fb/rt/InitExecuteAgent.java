package fb.rt;

import fb.mas.ExecuteAgent;

public class InitExecuteAgent extends FBInstance
{
	private static ExecuteAgent execute_agent = new ExecuteAgent("ExecuteAgentLocal", 0, "MainAppAgent");

	// INPUT EVENTS
	public EventServer ie_init_execute_agent = new EventInput(this);
	// END OF INPUT EVENTS

	public InitExecuteAgent()
	{
		super();

	}

	/** LINKING INPUT EVENTS TO THEIR NAMES */
	public EventServer eiNamed(String s)
	{
		if ("ie_init_execute_agent".equals(s))
			return ie_init_execute_agent;
		return super.eiNamed(s);
	}

	/** Defining the Methods */
	public void serviceEvent(EventServer e)
	{
		service_ie_init_execute_agent();
	}

	private void service_ie_init_execute_agent()
	{
		execute_agent.start();
	}

}
