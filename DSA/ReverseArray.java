public class ReverseArray {

    public static void main(String[] args){
        int []arr={4,5,10,8,6,4};
        
        //using two pointer technique
        int i=0;
        int j= arr.length-1;

        while(i<j){
            int temp = arr[i];
            arr[i]=arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        for(int num:arr){
            System.err.print(num+"");
        }
    }

}
