import java.util.HashMap;

public class CountCharInString {
    public static void main(String[] args) {
        String name="Balasabari";

        HashMap<Character,Integer> frequencyMap = new HashMap<>();
        
        for(char ch: name.toCharArray()){
            frequencyMap.put(Character.toLowerCase(ch),frequencyMap.getOrDefault(ch, 0)+1);
        }

        System.out.println(frequencyMap);
    }
}
