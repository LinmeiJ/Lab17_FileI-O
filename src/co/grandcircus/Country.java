package co.grandcircus;

public class Country {
	private String name;
	private long population;
	
	public Country() {
		super();
	}
	public Country(String name, int population) {
		super();
		this.name = name;
		this.population = population;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long l) {
		this.population = l;
	}
	@Override
	public String toString() {
		return String.format("Country: %-25s Population: %,d", getName(), getPopulation());
	}
	
	
}
