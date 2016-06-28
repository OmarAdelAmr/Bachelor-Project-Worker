package fb.components;

import java.util.ArrayList;

public class Task
{
	private String name;
	private ArrayList<Task> dependencies;

	public Task(String name, ArrayList<Task> dependencies)
	{
		this.name = name;
		this.dependencies = dependencies;
	}

	public Task(String name)
	{
		this.name = name;
		this.dependencies = null;
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

	public String toString()
	{
		String result = this.name + "~";
		for (int i = 0; i < this.dependencies.size(); i++)
		{
			result += this.dependencies.get(i).getName();
			if (i < this.dependencies.size() - 1)
			{
				result += "~";
			}

		}
		return result;
	}
}
