public class Array {

    int[] a;
    int size;
    int capacity;

    Array(int capacity){
         this.capacity=capacity;
         this.size=0;
         this.a= new int[capacity];
    }

    boolean insert(int index,int element){
        if(index<0||index>size||size>=capacity){
            System.out.println("invalid insertion index");
            return false;
        }
        for(int i=size;i>index;i--){
              a[i]=a[i-1];
        }
        a[index]=element;
        size++;
        return true;
    }
    
    public void display(){
        for(int arrIndex=0;arrIndex<size;arrIndex++){
            System.out.print(a[arrIndex]+" ");
        }
         System.out.println();
    }

    boolean delete(int index){
        
        if(index<0||index>=size){
                System.out.println("invalid index to delete");
                return false;
        }
        for(int arrIndex=index;arrIndex<size-1;arrIndex++){
            a[arrIndex]=a[arrIndex+1];
        }
        size--;
        
        return true;
    }

    public static void main(String[] args){
        Array arr=new Array(5);
        arr.insert(0, 6);
        arr.insert(1, 7);
        arr.insert(2, 5);
        arr.insert(3, 1);
        arr.display();
        arr.insert(2, 3);
        System.out.println("array created.."+arr.capacity);
        arr.display();
        arr.delete(10);
        System.out.println("after delete");
        arr.display();

    }

}
