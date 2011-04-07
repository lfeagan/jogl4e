package net.vectorcomputing.jogl4e.demo;

public class AffinePoint {
	
	public static final AffinePoint ORIGIN = new AffinePoint(0.0d, 0.0d, 0.0d);
	public static final AffinePoint X_AXIS = new AffinePoint(1.0d, 0.0d, 0.0d);
	public static final AffinePoint Y_AXIS = new AffinePoint(0.0d, 1.0d, 0.0d);
	public static final AffinePoint Z_AXIS = new AffinePoint(0.0d, 0.0d, 1.0d);
	
	double x;
	double y;
	double z;
	
	public AffinePoint(double x, double y) {
		this(x, y, 0.0f);
	}
	
	public AffinePoint(double x, double y, double z) {
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
	
	public boolean equals(AffinePoint rhs) {
		return false;
	}

	public AffinePoint plus(AffineVector v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}
	
	public AffinePoint newPlus(AffineVector v) {
		return new AffinePoint(x + v.x, y + v.y, z + v.z);
	}
	
	public AffinePoint plus(AffinePoint p) {
		x += p.x;
		y += p.y;
		z += p.z;
		return this;
	}

	public AffinePoint newPlus(AffinePoint p) {
		return new AffinePoint(x + p.x, y + p.y, z + p.z);
	}
		
	public AffineVector newMinus(AffinePoint p) {
		return new AffineVector(x - p.x, y - p.y, z - p.z);
	}
	
	public AffinePoint mul(double multiplier) {
		x *= multiplier;
		y *= multiplier;
		z *= multiplier;
		return this;
	}

	public AffinePoint newMul(double multiplier) {
		return new AffinePoint(x * multiplier, y * multiplier, z * multiplier);
	}
	
	public AffinePoint div(double divisor) {
		x /= divisor;
		y /= divisor;
		z /= divisor;
		return this;
	}
	
	public AffinePoint newDiv(double divisor) {
		return new AffinePoint(x / divisor, y / divisor, z / divisor);
	}
}
