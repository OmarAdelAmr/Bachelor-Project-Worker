package fb.components;

import java.util.ArrayList;

public class Product
{

	private String name;
	private ArrayList<Task> steps = new ArrayList<Task>();

	public Product(String name, ArrayList<Task> steps)
	{
		this.name = name;
		this.steps = steps;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Task> getSteps()
	{
		return steps;
	}

	public void setSteps(ArrayList<Task> steps)
	{
		this.steps = steps;
	}

}
