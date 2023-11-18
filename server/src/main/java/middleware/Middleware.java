package middleware;

public abstract class Middleware {
	private String name;
	private boolean active = false;
	
	public Middleware(String name, boolean active) {
		this.name = name;
		this.active = active;
	}
	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}
}
