package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class ReflectionUtils {
    public static Field getFieldInClassAndSuperclasses(Class<?> clazz, String name) {
        Field field = null;
        while (clazz != null && field == null) {
            try {
                field = clazz.getDeclaredField(name);
            } catch (Exception e) {
            }
            clazz = clazz.getSuperclass();
        }
        return field;
    }

    public static boolean containsNull(Field field) throws IllegalAccessException {
        return field.get(null) == null;
    }

    public static boolean isPublicAndStatic(Field field) {
        int modifiers = field.getModifiers();
        return (modifiers & Modifier.PUBLIC) != 0 && (modifiers & Modifier.STATIC) != 0;
    }
}
