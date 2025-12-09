public class Palindrome {
    public static void main(String[] args) {
        String name="ala";
        String revName="";

        for(int i=name.length()-1;i>=0;i--){
            revName+=name.charAt(i);
        }

        System.out.println(name.equals(revName)?"palindrome":"not a palindrome");
    }
}
