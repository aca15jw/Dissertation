package Lsystems;

public class TurtlePosition {
    private double currentX;
    private double currentY;
    private int currentAngle;
    public TurtlePosition(double currentX, double currentY, int currentAngle) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.currentAngle = currentAngle;
    }

    public int getCurrentAngle() {
        return currentAngle;
    }

    public double getCurrentX() {
        return currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentAngle(int currentAngle) {
        this.currentAngle = currentAngle;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }
}
