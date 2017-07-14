package classes;

public class DefaultSymbol {
	private static final long serialVersionUID = 1L;
	private int[] color;
	private String type;
	private String style;
	private double width;
	private Outline outline;
	
	public int[] getColor() {
		return this.color;
	}
	
	public void setColor (int[] color) {
		this.color =color;
	}
	
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStyle () {
		return this.style;
	}
	
	public void setStyle(String style) {
		this.style= style;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public void setWidth (double width) {
		this.width=width;
	}
	
	public Outline getOutline () {
		return this.outline;
	}
	
	public void setOutline(Outline outline) {
		this.outline= outline;
	}

}
