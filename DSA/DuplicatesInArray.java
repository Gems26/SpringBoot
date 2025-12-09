import java.util.HashSet;

public class DuplicatesInArray {
    public static void main(String[] args) {
        int []arr={5,10,12,5,7,8,10,4};

        HashSet set1 = new HashSet<>();

        for(int num : arr){
            if(!set1.add(num)){
                System.out.println("duplicate.."+num);
            }
        }
    }
}
