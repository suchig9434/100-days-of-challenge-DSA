import java.util.*;

class Solution {
    int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parseExpression(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Handles UNION: e1, e2, e3
    private Set<String> parseExpression(String s) {
        Set<String> result = new HashSet<>();

        while (index < s.length() && s.charAt(index) != '}') {

            Set<String> part = parseSequence(s);

            // Union
            result.addAll(part);

            // If comma, continue with next expression
            if (index < s.length() && s.charAt(index) == ',') {
                index++;
            } else {
                break;
            }
        }

        return result;
    }

    // Handles CONCATENATION: e1e2e3
    private Set<String> parseSequence(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            Set<String> current;

            if (s.charAt(index) == '{') {
                index++; // skip {

                current = parseExpression(s);

                index++; // skip }
            } else {
                current = new HashSet<>();
                current.add(String.valueOf(s.charAt(index)));
                index++;
            }

            result = concatenate(result, current);
        }

        return result;
    }

    // Concatenate two sets
    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}