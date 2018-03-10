package Lsystems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;

public class visualOutput {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(1100,900);
        window.setTitle("L-System Output");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setSize(750, 100);
        window.add(panel);

        JPanel lsystemPanel = new JPanel();
        lsystemPanel.setSize(1000, 900);
        window.add(lsystemPanel);
        lsystemPanel.setVisible(true);

        String[] lsystems = {"Tree 1", "Tree 2", "Tree 3"};
        JComboBox<String> dropdown = new JComboBox<>(lsystems);
        panel.add(dropdown, BorderLayout.NORTH);
        dropdown.setVisible(true);

        JLabel lengthLabel = new JLabel("Line Length:");
        panel.add(lengthLabel);
        NumberFormat lengthFormat = NumberFormat.getNumberInstance();
        JTextField lineLengthInput = new JFormattedTextField(lengthFormat);
        panel.add(lineLengthInput, BorderLayout.NORTH);
        lineLengthInput.setColumns(4);
        lineLengthInput.setVisible(true);

        JLabel widthLabel = new JLabel("Line Width:");
        panel.add(widthLabel);
        NumberFormat widthFormat = NumberFormat.getNumberInstance();
        JTextField lineWidthInput = new JFormattedTextField(widthFormat);
        panel.add(lineWidthInput, BorderLayout.NORTH);
        lineWidthInput.setColumns(4);
        lineWidthInput.setVisible(true);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, BorderLayout.NORTH);
        submitButton.setVisible(true);
        panel.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] axiom = new String[1];
                HashMap<String, String> rules = new HashMap<>();
                //Number of times L-system rules are applied
                int iterations = 0;
                //Default line length of 5
                int lineLength = Integer.parseInt(lineLengthInput.getText());
                int lineWidth = Integer.parseInt(lineWidthInput.getText());
                String choice = dropdown.getSelectedItem().toString();
                switch (choice) {
                    case "Tree 1" :
                        axiom[0] = "L";
                        rules.put("L", "F[+L][-L]FL");
                        rules.put("F", "FF");
                        iterations = 6;
                        break;
                    case "Tree 2" :
                        axiom[0] = "F";
                        rules.put("F", "F[+F]F[-F]F");
                        iterations = 4;
                        break;
                    case "Tree 3" :
                        axiom[0] = "F";
                        rules.put("F", "FF-[-F+F+F]+[+F-F-F]");
                        iterations = 3;
                        break;
                }
                drawingComponent dc = new drawingComponent(axiom, rules, iterations, lineLength, lineWidth);
                lsystemPanel.removeAll();
                window.validate();
                window.repaint();
                lsystemPanel.add(dc);
                window.validate();
                window.repaint();
            }
        });


        window.setVisible(true);
    }
}