public class MyEnumSubclass extends MyEnum{
    public static MyEnumSubclass CHOICE_1;
    public static MyEnumSubclass CHOICE_2;

    static {
        init(MyEnumSubclass.class);
    }

    protected MyEnumSubclass(int ordinal, String name) {
        super(ordinal, name);
    }
}
