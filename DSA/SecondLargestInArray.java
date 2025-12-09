public class SecondLargestInArray {

    public static void main(String[] args) {
        int []arr = {100,8,7,100,4};

        int max = Integer.MIN_VALUE;
        int secondmax = Integer.MIN_VALUE;

        for(int num: arr){
            if(num>max){
                secondmax = max;
                max=num;
            }
            else if(num>secondmax && num!=max){
                secondmax = num;
            }
        }

        System.out.println(secondmax);
        
    }
}