package fb.rt;

import fb.datatype.ANY;
import fb.datatype.WSTRING;
import fb.mas.WorkerAgent;

// This class receives data and events from other function blocks and 
// is responsible for sending data to the MAS node connected to the robot

public class MASConnector extends FBInstance
{
	/** MAS VARIABLES */
	private WorkerAgent worker_agent;
	/** END OF MAS VARIABLES */

	/** INPUT EVENTS */
	public EventServer ie_init_MAS = new EventInput(this);
	public EventServer ie_new_task_start_stop = new EventInput(this);
	public EventServer ie_execute_products_order = new EventInput(this);
	/** END OF INPUT EVENTS */

	/** INPUT VARIABLES */
	public WSTRING iv_new_task_name = new WSTRING(); // INPUT
	public WSTRING iv_products_order = new WSTRING(); // INPUT

	/** END OF INPUT VARIABLES */

	public MASConnector()
	{
		super();
	}

	/** LINKING INPUT EVENTS TO THEIR NAMES */
	public EventServer eiNamed(String s)
	{
		if ("ie_init_MAS".equals(s))
			return ie_init_MAS;
		if ("ie_new_task_start_stop".equals(s))
			return ie_new_task_start_stop;
		if ("ie_execute_products_order".equals(s))
			return ie_execute_products_order;
		return super.eiNamed(s);
	}

	/** LINKING INPUT VARIABLES TO THEIR NAMES */
	public ANY ivNamed(String s) throws FBRManagementException
	{
		if ("iv_new_task_name".equals(s))
			return iv_new_task_name;
		if ("iv_products_order".equals(s))
			return iv_products_order;
		return super.ivNamed(s);
	}

	/** LINKING INPUT VARIABLES TO THEIR VALUES */
	public void connectIV(String ivName, ANY newIV) throws FBRManagementException
	{

		if ("iv_new_task_name".equals(ivName))
		{
			connect_iv_new_task_name((WSTRING) newIV);
			return;
		}
		if ("iv_products_order".equals(ivName))
		{
			connect_iv_products_order((WSTRING) newIV);
			return;
		}
		super.connectIV(ivName, newIV);
	}

	public void connect_iv_new_task_name(WSTRING newIV) throws FBRManagementException
	{
		iv_new_task_name = newIV;
	}

	private void connect_iv_products_order(WSTRING newIV)
	{
		iv_products_order = newIV;
	}

	/** Defining the Methods */
	public void serviceEvent(EventServer e)
	{
		if (e == ie_init_MAS)
			service_ie_init_MAS();
		else if (e == ie_new_task_start_stop)
			service_ie_new_task_start_stop();
		else if (e == ie_execute_products_order)
			service_ie_execute_products_order();
	}

	private void service_ie_init_MAS()
	{
		worker_agent = new WorkerAgent();
		worker_agent.start();
	}

	private void service_ie_new_task_start_stop()
	{
		String temp_input = iv_new_task_name.value;
		worker_agent.send_inform_message("new_task", temp_input);
	}

	private void service_ie_execute_products_order()
	{
		String temp_input = iv_products_order.value;
		worker_agent.send_inform_message("execute_task", temp_input);
	}

}
