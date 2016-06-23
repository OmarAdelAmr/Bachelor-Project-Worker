package fb.components;

import java.util.ArrayList;

public class RobotTask extends Task
{

	private String arm;

	public RobotTask(String name, ArrayList<Task> dependencies, String arm)
	{
		super(name, dependencies);
		this.arm = arm;
		// TODO Auto-generated constructor stub
	}

	public RobotTask(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public String getArm()
	{
		return arm;
	}

	public void setArm(String arm)
	{
		this.arm = arm;
	}

}
