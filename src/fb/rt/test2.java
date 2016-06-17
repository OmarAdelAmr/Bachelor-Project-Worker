package fb.rt;

public class test2 extends FBInstance
{
	public EventServer ie_init_new_task = new EventInput(this);

	public test2()
	{
		super();
	}

	/** LINKING INPUT EVENTS TO THEIR NAMES */
	public EventServer eiNamed(String s)
	{
		if ("ie_init_new_task".equals(s))
			return ie_init_new_task;
		return super.eiNamed(s);
	}

	/** Defining the Methods */
	public void serviceEvent(EventServer e)
	{
		if (e == ie_init_new_task)
			service_ie_init_new_task();
	}

	private void service_ie_init_new_task()
	{
		int i = 0;
		while(true)
		{
			System.out.println("A: " + i);
			i++;
		}

	}
}
