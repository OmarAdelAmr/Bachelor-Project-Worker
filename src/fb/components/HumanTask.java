package fb.components;

import java.util.ArrayList;

public class HumanTask extends Task
{
	private String associated_gesture;

	public HumanTask(String name, ArrayList<Task> dependencies, String associated_gesture)
	{
		super(name, dependencies);
		this.associated_gesture = associated_gesture;
	}

	public HumanTask(String name, String associated_gesture)
	{
		super(name);
		this.associated_gesture = associated_gesture;
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
