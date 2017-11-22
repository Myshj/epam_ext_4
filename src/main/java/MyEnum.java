import utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class MyEnum {

    private final int ordinal;
    private final String name;
    private static HashMap<
            Class<? extends MyEnum>,
            ArrayList<Object>
            > values = new HashMap<>();

    protected MyEnum(
            int ordinal,
            String name
    ) {
        this.ordinal = ordinal;
        this.name = name;
    }

    public static void init(Class<? extends MyEnum> clazz) {
        values.put(
                clazz,
                new ArrayList<>()
        );
        Field[] fields = clazz.getFields();
        int currentOrdinal = 0;
        for (Field field : fields) {
            if (!isEnumFieldOfClass(field, clazz)) {
                continue;
            }

            try {
                if (!ReflectionUtils.containsNull(field)) {
                    continue;
                }
                initEnumField(field, clazz, currentOrdinal++);
                values.get(clazz).add(field.get(null));

            } catch (IllegalAccessException | NoSuchMethodException |
                    InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initEnumField(
            Field field,
            Class<? extends MyEnum> clazz,
            int ordinal
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        field.set(
                null,
                clazz.getConstructor(int.class, String.class).newInstance(ordinal, field.getName())
        );
    }

    private static boolean isEnumFieldOfClass(Field field, Class<? extends MyEnum> clazz) {
        return field.getGenericType() == clazz && ReflectionUtils.isPublicAndStatic(field);
    }

    public final int getOrdinal() {
        return ordinal;
    }

    public final String getName() {
        return name;
    }

    public static final ArrayList<Object> values(
            Class<? extends MyEnum> clazz
    ){
        return new ArrayList<>(values.get(clazz));
    }

    @Override
    public String toString() {
        return "MyEnum{" +
                "ordinal=" + ordinal +
                ", name='" + name + '\'' +
                '}';
    }
}
