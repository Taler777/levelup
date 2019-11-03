package entities;

public enum State
{
	OPEN("OPEN"),
	CLOSE("CLOSE");

	private String description;

	State(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}
}
