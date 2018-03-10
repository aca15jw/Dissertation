package Lsystems;

import java.util.HashMap;
import java.util.Arrays;

public class lsystemGenerator {
    public String[] getLSystem(String[] axiom, HashMap<String, String> rules, int iterations) {
        String[] result = generateLSystem(rules, axiom, iterations);
        System.out.println("The final string is: " + Arrays.toString(result));
        return result;
    }

    public static String[] generateLSystem(HashMap<String, String> rules, String[] axiom, int iterations) {
        String loopString = "";
        int counter = 0;

        for(int i = 0; i <= iterations; i++) {
            StringBuilder strBuilder = new StringBuilder();
            counter = 0;

            for(String ch: axiom) {
                if (rules.containsKey(ch)) {
                    axiom[counter] = rules.get(ch);
                }
                counter++;
            }
            for (String s: axiom) {
                strBuilder.append(s);
            }
            loopString = strBuilder.toString();
            axiom = loopString.split("(?![^{]*})");

        }
        return axiom;
    }
}
