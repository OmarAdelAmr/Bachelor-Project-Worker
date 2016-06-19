package fb.components;

import java.util.ArrayList;

public abstract class Task
{
	private String name;
	private ArrayList<Task> dependencies;

	public Task(String name, ArrayList<Task> dependencies)
	{
		this.name = name;
		this.dependencies = dependencies;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Task> getDependencies()
	{
		return dependencies;
	}

	public void setDependencies(ArrayList<Task> dependencies)
	{
		this.dependencies = dependencies;
	}
}
