public class MyEnumSubclass extends MyEnum{
    public static MyEnumSubclass CHOICE_1;
    public static MyEnumSubclass CHOICE_2;

    static {
        init(MyEnumSubclass.class);
    }

    public static Integer integer = 1;

    public MyEnumSubclass(int ordinal, String name) {
        super(ordinal, name);
    }
}
