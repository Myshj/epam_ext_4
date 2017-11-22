public class Main {
    public static void main(String[] args){
        System.out.println(MyEnumSubclass.CHOICE_2.toString());
        System.out.println("Values:");
        for (Object v : MyEnumSubclass.values(MyEnumSubclass.class)){
            System.out.println(v.toString());
        }
    }

}
