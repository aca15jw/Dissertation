package Lsystems;

import javafx.scene.shape.Line;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Stack;

public class drawingComponent extends JComponent {
    private String[] axiom;
    private int iterations;
    private int lineLength;
    private int lineWidth;
    private HashMap<String, String> rules;


    public drawingComponent(String[] axiom, HashMap<String, String> rules, int iterations, int lineLength, int lineWidth) {
        this.axiom = axiom;
        this.rules = rules;
        this.iterations = iterations;
        this.lineLength = lineLength;
        this.lineWidth = lineWidth;
        setPreferredSize(new Dimension(700, 700));
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        lsystemGenerator generator = new lsystemGenerator();
        //L and R are equivalent to F(subscript)l and F(subscript)r


        //String[] lsystem = generator.getLSystem(axiom, rules, iterations);
        String[] lsystem = {"F", "F", "F", "#", "{1}", "F", "F", "F", "#", "{}","F", "F", "F", "#", "{1}"};


        //Directions can be Up, Down, Left, Right
        TurtlePosition turtlePosition = new TurtlePosition(150, 600, 270);
        //Angle of 0 is the right direction, rotates counter-clockwise (so a value of 90 would be downwards)
        int angleChange = 20;
        double endXLocation;
        double endYLocation;
        Stack positionStack = new Stack();
        g2.setStroke(new BasicStroke(lineWidth));
        int indexCounter = 0;
        int defaultLineWidthIncrement = 4;


        //F = move forward 10px
        //+ = turn left by the value of angle
        //- = turn right by the value of angle

        for (String value : lsystem) {
            if (value.equals("F") || value.equals("R") || value.equals("L")) {
                endXLocation = turtlePosition.getCurrentX() +  Math.cos(Math.toRadians(turtlePosition.getCurrentAngle())) * lineLength;
                endYLocation = turtlePosition.getCurrentY() + Math.sin(Math.toRadians(turtlePosition.getCurrentAngle())) * lineLength;
                Shape l = new Line2D.Double(turtlePosition.getCurrentX(), turtlePosition.getCurrentY(), endXLocation, endYLocation);
                g2.draw(l);
                turtlePosition.setCurrentX(endXLocation);
                turtlePosition.setCurrentY(endYLocation);
            } else if (value.equals("f")) {
                double currentX = turtlePosition.getCurrentX();
                double currentY = turtlePosition.getCurrentY();
                turtlePosition.setCurrentX(currentX + Math.cos(Math.toRadians(turtlePosition.getCurrentAngle())) * lineLength);
                turtlePosition.setCurrentY(currentY + Math.sin(Math.toRadians(turtlePosition.getCurrentAngle())) * lineLength);
            } else if (value.equals("+")) {
                int angle = turtlePosition.getCurrentAngle();
                turtlePosition.setCurrentAngle(angle - angleChange);
            } else if (value.equals("-")) {
                int angle = turtlePosition.getCurrentAngle();
                turtlePosition.setCurrentAngle(angle + angleChange);
            } else if (value.equals("[")) {
                TurtlePosition stackTurtle = new TurtlePosition(turtlePosition.getCurrentX(), turtlePosition.getCurrentY(), turtlePosition.getCurrentAngle());
                positionStack.push(stackTurtle);
            } else if (value.equals("]")) {
                TurtlePosition tempPosition = (TurtlePosition)positionStack.pop();
                turtlePosition.setCurrentAngle(tempPosition.getCurrentAngle());
                turtlePosition.setCurrentX(tempPosition.getCurrentX());
                turtlePosition.setCurrentY(tempPosition.getCurrentY());
            } else if (value.length() > 1) {
                String associatedSymbol = lsystem[indexCounter - 1];
                if (associatedSymbol.equals("#")) {
                    String lineWidthString = value.substring(1, value.length() - 1);
                    if (isNumber(lineWidthString)) {
                        int lineWidthIncrement = Integer.parseInt(lineWidthString);
                        lineWidth += lineWidthIncrement;
                    } else {
                        lineWidth += defaultLineWidthIncrement;
                    }
                    g2.setStroke(new BasicStroke(lineWidth));
                }
            }
            indexCounter ++;
        }
    }

    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
