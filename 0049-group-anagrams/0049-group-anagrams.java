import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // Convert string to character array
            char[] chars = s.toCharArray();

            // Sort characters
            Arrays.sort(chars);

            // Create key
            String key = new String(chars);

            // Add string to corresponding group
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(s);
        }

        // Return all groups
        return new ArrayList<>(map.values());
    }
}