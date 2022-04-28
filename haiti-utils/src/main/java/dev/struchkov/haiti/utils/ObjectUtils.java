package dev.struchkov.haiti.utils;

import dev.struchkov.haiti.context.exception.ConvertException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;
import static dev.struchkov.haiti.utils.Inspector.isNotNull;

/**
 * Утилитарный класс для работы с классами и объектами.
 *
 * @author upagge 07.03.2021
 */
public class ObjectUtils {

    private ObjectUtils() {
        utilityClass();
    }

    /**
     * <p>Позволяет получить значение вложенного в объект поля по названию поля.</p>
     * <p></p>
     * <p><b>При этом у поля должен быть доступен get-метод</b></p>
     * <p></p>
     * <p>Поддерживаются воженные объекты, чтобы задать такой объект, необходимо прописать путь до него от переданного объекта через '.'. У всех объектов на пути должны быть get-методы.</p>
     *
     * @param fieldName Имя поля в объекте или цепь имен
     * @param object    Объект
     * @return Значение поля из объекта
     */
    public static Object getFieldValue(Object object, String fieldName) {
        isNotNull(object, fieldName);
        final int firstNameIndex = fieldName.indexOf(".");
        String firstName;
        if (firstNameIndex != -1) {
            firstName = fieldName.substring(0, firstNameIndex);
        } else {
            firstName = fieldName;
        }
        for (Method method : object.getClass().getMethods()) {
            if (isGetMethod(firstName, method)) {
                try {
                    final Object invoke = method.invoke(object);
                    if (firstNameIndex == -1) {
                        return invoke;
                    } else {
                        return getFieldValue(invoke, fieldName.substring(firstNameIndex + 1));
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new ConvertException("Не удалось определить метод " + method.getName());
                }
            }
        }
        throw new ConvertException("Метод у объекта не найден");
    }

    public static void setFieldValue(Object object, String fieldName, Object fieldValue) {
        isNotNull(object, fieldName);
        final int firstNameIndex = fieldName.indexOf(".");
        if (firstNameIndex != -1) {
            final String[] split = fieldName.split("\\.");
            final String fieldNameToObject = String.join(".", Arrays.copyOf(split, split.length - 1));
            fieldName = split[split.length - 1];
            object = getFieldValue(object, fieldNameToObject);
        }

        boolean flag = true;
        for (Method method : object.getClass().getMethods()) {
            if (isSetMethod(fieldName, method)) {
                try {
                    Class<?> parameterType = method.getParameterTypes()[0];
                    method.invoke(object, convertFieldValue(fieldValue, parameterType));
                    flag = false;
                    break;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new ConvertException("Не удалось определить метод " + method.getName());
                }
            }
        }
        Inspector.isFalse(flag, () -> new ConvertException("Метод у объекта не найден"));
    }

    private static Object convertFieldValue(Object fieldValue, Class<?> parameterType) {
        if (String.class.equals(fieldValue.getClass())) {
            final String value = (String) fieldValue;
            if ("null".equalsIgnoreCase(value)) {
                return null;
            }
            if (Long.class.equals(parameterType)) {
                return Long.valueOf(value);
            }
            if (Integer.class.equals(parameterType)) {
                return Integer.valueOf(value);
            }
            if (Double.class.equals(parameterType)) {
                return Double.valueOf(value);
            }
            if (Float.class.equals(parameterType)) {
                return Float.valueOf(value);
            }
            if (BigDecimal.class.equals(parameterType)) {
                return BigDecimal.valueOf(Long.parseLong(value));
            }
            if (BigInteger.class.equals(parameterType)) {
                return BigInteger.valueOf(Long.parseLong(value));
            }
        }
        return fieldValue;
    }

    /**
     * Определяет что метод является геттером поля.
     *
     * @param fieldName Поле
     * @param method    Метод
     * @return true если метод является геттером для fieldName, false в противном случае
     */
    public static boolean isGetMethod(String fieldName, Method method) {
        isNotNull(fieldName);
        return (method.getName().startsWith("get"))
                && (method.getName().length() == (fieldName.length() + 3))
                && method.getName().toLowerCase().endsWith(fieldName.toLowerCase());
    }

    /**
     * Определяет что метод является сеттером поля.
     *
     * @param fieldName Поле
     * @param method    Метод
     * @return true если метод является сеттером для fieldName, false в противном случае
     */
    public static boolean isSetMethod(String fieldName, Method method) {
        isNotNull(fieldName);
        return (method.getName().startsWith("set"))
                && (method.getName().length() == (fieldName.length() + 3))
                && method.getName().toLowerCase().endsWith(fieldName.toLowerCase());
    }

    public static <T> List<T> toList(T... t) {
        return Arrays.stream(t).collect(Collectors.toList());
    }

    public static <T> Collection<T> toCollect(T... t) {
        return Arrays.stream(t).collect(Collectors.toList());
    }

    public static <T> Collection<T> toCollect(Collection<T> collection, T... t) {
        return Stream.concat(
                collection.stream(), Arrays.stream(t)
        ).collect(Collectors.toList());
    }

    public static <T> Collection<T> toCollect(Collection<T> collectionOne, Collection<T> collectionTwo) {
        return Stream.concat(
                collectionOne.stream(), collectionTwo.stream()
        ).collect(Collectors.toList());
    }

}
