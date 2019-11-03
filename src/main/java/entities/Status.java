package entities;

public enum Status
{
	OPEN("open"),
	CLOSE("close");

	private String description;

	Status(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}
}
