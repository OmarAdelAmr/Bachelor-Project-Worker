package fb.components;

import java.util.ArrayList;

public class RobotTask extends Task
{

	private String arm;
	private long task_time;

	public RobotTask(String name, ArrayList<Task> dependencies, String arm, long task_time)
	{
		super(name, dependencies);
		this.arm = arm;
		this.task_time = task_time;
	}

	public RobotTask(String name, long task_time)
	{
		super(name);
		this.task_time = task_time;
	}

	public long getTask_time()
	{
		return task_time;
	}

	public void setTask_time(long task_time)
	{
		this.task_time = task_time;
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
