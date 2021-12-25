package com.len.serializer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class to serialize objects to json and xml strings
 * Contains public static methods toXml (to convert to xml) and toJson (to convert to json)
 */
public class ObjectSerializer {

    /**
     * Takes array of ints and returns xml array as string
     * @param object array of ints
     * @return xml array as string
     */
    public static String toXml(int[] object) {
        StringBuilder sb = new StringBuilder();
        sb.append("<array>");
        for (int e : object) {
            sb.append(toXml(e));
        }
        sb.append("</array>");
        return sb.toString();
    }
    
    /**
     * Takes array of shorts and returns xml array as string
     * @param object array of shorts
     * @return xml array as string
     */
    public static String toXml(short[] object) {
        StringBuilder sb = new StringBuilder();
        sb.append("<array>");
        for (short e : object) {
            sb.append(toXml(e));
        }
        sb.append("</array>");
        return sb.toString();
    }

    /**
     * Takes array of bytes and returns xml array as string
     * @param object array of bytes
     * @return xml array as string
     */
    public static String toXml(byte[] object) {
        StringBuilder sb = new StringBuilder();
        sb.append("<array>");
        for (byte e : object) {
            sb.append(toXml(e));
        }
        sb.append("</array>");
        return sb.toString();
    }

    /**
     * Takes array of longs and returns xml array as string
     * @param object array of longs
     * @return xml array as string
     */
    public static String toXml(long[] object) {
        StringBuilder sb = new StringBuilder();
        sb.append("<array>");
        for (long e : object) {
            sb.append(toXml(e));
        }
        sb.append("</array>");
        return sb.toString();
    }

    /**
     * Takes array of chars and returns xml array as string
     * @param object array of chars
     * @return xml array as string
     */
    public static String toXml(char[] object) {
        StringBuilder sb = new StringBuilder();
        sb.append("<array>");
        for (char e : object) {
            sb.append(toXml(e));
        }
        sb.append("</array>");
        return sb.toString();
    }

    /**
     * Takes array of doubles and returns xml array as string
     * @param object array of doubles
     * @return xml array as string
     */
    public static String toXml(double[] object) {
        StringBuilder sb = new StringBuilder();
        sb.append("<array>");
        for (double e : object) {
            sb.append(toXml(e));
        }
        sb.append("</array>");
        return sb.toString();
    }

    /**
     * Takes array of floats and returns xml array as string
     * @param object array of floats
     * @return xml array as string
     */
    public static String toXml(float[] object) {
        StringBuilder sb = new StringBuilder();
        sb.append("<array>");
        for (float e : object) {
            sb.append(toXml(e));
        }
        sb.append("</array>");
        return sb.toString();
    }

    /**
     * Takes array of ints and returns json array as string
     * @param object array of ints
     * @return json array as string
     */
    public static String toJson(int[] object) {
        return Arrays.toString(object);
    }

    /**
     * Takes array of shorts and returns json array as string
     * @param object array of shorts
     * @return json array as string
     */
    public static String toJson(short[] object) {
        return Arrays.toString(object);
    }

    /**
     * Takes array of bytes and returns json array as string
     * @param object array of bytes
     * @return json array as string
     */
    public static String toJson(byte[] object) {
        return Arrays.toString(object);
    }

    /**
     * Takes array of longs and returns json array as string
     * @param object array of longs
     * @return json array as string
     */
    public static String toJson(long[] object) {
        return Arrays.toString(object);
    }

    /**
     * Takes array of chars and returns json array as string
     * @param object array of chars
     * @return json array as string
     */
    public static String toJson(char[] object) {
        StringBuilder sb = new StringBuilder("[");
        if (object.length > 0) {
            for (char c : object) {
                sb.append('"');
                sb.append(c);
                sb.append('"');
                if (c != object[object.length - 1]) sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * Takes array of doubles and returns json array as string
     * @param object array of doubles
     * @return json array as string
     */
    public static String toJson(double[] object) {
        return Arrays.toString(object);
    }

    /**
     * Takes array of floats and returns json array as string
     * @param object array of floats
     * @return json array as string
     */
    public static String toJson(float[] object) {
        return Arrays.toString(object);
    }

    /**
     * Takes an object with fields marked with @MyXML and returns xml string
     * @param object object to serialize
     * @return xml string
     * @see com.len.serializer.MyXML
     */
    public static <T, V> String toXml(T object) {

        if (isPrimitive(object)) {
            return '<' +
                    object.getClass().getSimpleName().toLowerCase() +
                    '>' +
                    object +
                    "</" +
                    object.getClass().getSimpleName().toLowerCase() +
                    '>';
        }

        if (object.getClass().isArray()) return toXmlArray(Arrays.stream((V[]) object).collect(Collectors.toList()));
        if (object instanceof Iterable) return toXmlArray((Iterable<V>) object);

        Field[] fields = object.getClass().getDeclaredFields();

        StringBuilder sb = new StringBuilder();

        sb.append('<');
        sb.append(object.getClass().getSimpleName().toLowerCase());
        sb.append('>');

        for (Field f : fields) {
            if (!f.isAnnotationPresent(MyXML.class)) continue;

            f.setAccessible(true);

            try {
                sb.append('<');
                sb.append(f.getAnnotation(MyXML.class).value());
                sb.append('>');

                if (f.getType().isPrimitive() || f.getType().equals(String.class)) sb.append(f.get(object));
                else if (int[].class.equals(f.getType())) {
                    sb.append(toXml((int[]) f.get(object)));
                } else if (char[].class.equals(f.getType())) {
                    sb.append(toXml((char[]) f.get(object)));
                } else if (byte[].class.equals(f.getType())) {
                    sb.append(toXml((byte[]) f.get(object)));
                } else if (short[].class.equals(f.getType())) {
                    sb.append(toXml((short[]) f.get(object)));
                } else if (long[].class.equals(f.getType())) {
                    sb.append(toXml((long[]) f.get(object)));
                } else if (double[].class.equals(f.getType())) {
                    sb.append(toXml((double[]) f.get(object)));
                } else if (float[].class.equals(f.getType())) {
                    sb.append(toXml((float[]) f.get(object)));
                } else sb.append(toXml(f.get(object)));


                sb.append("</");
                sb.append(f.getAnnotation(MyXML.class).value());
                sb.append('>');
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        sb.append("</");
        sb.append(object.getClass().getSimpleName().toLowerCase());
        sb.append('>');

        return sb.toString();
    }

    /**
     * Takes an object with fields marked with @MyJSON and returns json string
     * @param object object to serialize
     * @return xml string
     * @see com.len.serializer.MyJSON
     */
    public static <T, V> String toJson(T object) {

        if (isPrimitive(object)) {
            if (object instanceof Character || object instanceof String)
                return "{\"" + object.getClass().getSimpleName().toLowerCase() + "\": \"" + object + "\"}";
            else
                return "{\"" + object.getClass().getSimpleName().toLowerCase() + "\": " + object + '}';
        }

        if (object instanceof Iterable) return toJsonArray((Iterable<V>) object);

        if (object.getClass().isArray()) return toJsonArray(Arrays.stream((V[]) object).collect(Collectors.toList()));

        Field[] fields = object.getClass().getDeclaredFields();

        StringBuilder sb = new StringBuilder();

        sb.append('{');

        for (Field f : fields) {
            if (!f.isAnnotationPresent(MyJSON.class)) continue;

            f.setAccessible(true);

            try {
                sb.append('"');
                sb.append(f.getAnnotation(MyJSON.class).value());
                sb.append("\": ");

                if (f.getType().isPrimitive() || f.getType().equals(String.class)) {
                    if (f.getType().equals(String.class) || f.getType().equals(char.class)) {
                        sb.append('"');
                        sb.append(f.get(object));
                        sb.append('"');
                    } else {
                        sb.append(f.get(object));
                    }
                } else if (int[].class.equals(f.getType())) {
                    sb.append(toJson((int[]) f.get(object)));
                } else if (char[].class.equals(f.getType())) {
                    sb.append(toJson((char[]) f.get(object)));
                } else if (byte[].class.equals(f.getType())) {
                    sb.append(toJson((byte[]) f.get(object)));
                } else if (short[].class.equals(f.getType())) {
                    sb.append(toJson((short[]) f.get(object)));
                } else if (long[].class.equals(f.getType())) {
                    sb.append(toJson((long[]) f.get(object)));
                } else if (double[].class.equals(f.getType())) {
                    sb.append(toJson((double[]) f.get(object)));
                } else if (float[].class.equals(f.getType())) {
                    sb.append(toJson((float[]) f.get(object)));
                } else sb.append(toJson(f.get(object)));

                if (!f.equals(fields[fields.length - 1])) sb.append(", ");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        sb.append('}');

        return sb.toString();
    }

    /**
     * Checks if object type is primitive type
     * @param object object to check
     * @return true if type is primitive, false if no
     */
    private static <T> boolean isPrimitive(T object) {
        return object instanceof Boolean ||
                object instanceof String ||
                object instanceof Character ||
                object instanceof Integer ||
                object instanceof Short ||
                object instanceof Long ||
                object instanceof Byte ||
                object instanceof Float ||
                object instanceof Double;
    }

    /**
     * Takes Iterable and returns xml array as string
     * @param itr Iterable to iterate
     * @return xml array as string
     */
    private static <V> String toXmlArray(Iterable<V> itr) {
        StringBuilder sb = new StringBuilder();
        sb.append("<array>");
        for (V e : itr) {
            sb.append(toXml(e));
        }
        sb.append("</array>");
        return sb.toString();
    }

    /**
     * Takes Iterable and returns json array as string
     * @param itr Iterable to iterate
     * @return json array as string
     */
    private static <V> String toJsonArray(Iterable<V> itr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean flag = false;
        for (V e : itr) {
            flag = true;
            if (isPrimitive(e)) {
                if (e instanceof String || e instanceof Character) {
                    sb.append('"');
                    sb.append(e);
                    sb.append('"');
                } else sb.append(e);
            } else sb.append(toJson(e));

            sb.append(", ");
        }
        if (flag) sb.replace(sb.length() - 2, sb.length(), "");
        sb.append(']');
        return sb.toString();
    }
}
