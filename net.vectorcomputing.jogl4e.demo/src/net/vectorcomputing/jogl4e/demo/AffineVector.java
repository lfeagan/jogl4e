package net.vectorcomputing.jogl4e.demo;

public class AffineVector {

	public static final AffineVector XAXIS = new AffineVector(1.0f, 0.0f, 0.0f);
	public static final AffineVector YAXIS = new AffineVector(0.0f, 1.0f, 0.0f);
	public static final AffineVector ZAXIS = new AffineVector(0.0f, 0.0f, 1.0f);
	
	double x;
	double y;
	double z;
	
	public AffineVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY()  {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}

	public AffineVector plus(AffineVector other) {
		return new AffineVector(x + other.x, y + other.y, z + other.z);
	}

	public AffineVector minus(AffineVector subtrahend) {
		return new AffineVector(x - subtrahend.x, y - subtrahend.y, z - subtrahend.z);
	}
	
	public AffineVector scale(double scale) {
		return new AffineVector(x * scale, y * scale, z * scale);
	}
	
	public double length() {
		return (double) Math.sqrt(x*x + y * y + z * z);
	}
	
	public AffineVector normalize() {
		return this.scale(1.0f / this.length());
	}
	
	public double dot(AffineVector b) {
		return x * b.x + y * b.y + z * b.z;
	}
}
