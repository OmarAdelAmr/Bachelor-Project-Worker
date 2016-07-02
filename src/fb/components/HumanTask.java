package fb.components;

import java.util.ArrayList;

public class HumanTask extends Task
{
	private String associated_gesture;

	public HumanTask(String name, ArrayList<Task> dependencies)
	{
		super(name, dependencies);
		// TODO Auto-generated constructor stub
	}

	public HumanTask(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public String getAssociated_gesture()
	{
		return associated_gesture;
	}

	public void setAssociated_gesture(String associated_gesture)
	{
		this.associated_gesture = associated_gesture;
	}

}
