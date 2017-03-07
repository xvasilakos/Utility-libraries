package utilities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author Xenofon Vasilakos xvas@aueb.gr
 */
public class CommonFunctions {

    public static final double PHI = (1 + Math.sqrt(5)) / 2;

    public static Logger getLoggerFor(Class theClass, String... strLst) {
        StringBuilder sb = new StringBuilder();
        for (String nxt : strLst) {
            sb.append(":");
            sb.append(nxt);
        }

        sb.append(theClass.getCanonicalName());

        return Logger.getLogger(sb.toString());
    }

    public static double[] doubleArray(Double[] array) {
        double[] _doubleArray = new double[array.length];
        for (int i = 0; i < _doubleArray.length; i++) {
            _doubleArray[i] = array[i];
        }
        return _doubleArray;
    }

    public static String toString(Collection objs) {
        return toString("", objs);
    }

    public static String toString(Map<Object, Object> map) {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            s.append("\n Key: " + toString(key));
            s.append("\n\t Value(s) mapped: " + toString(value));
        }
        return s.toString();
    }

    public static String toString(Object o) {
        if (o instanceof Collection) {
            return toString((Collection) o);
        } else if (o instanceof Map) {
            return toString((Map) o);
        } else {
            return o.toString();
        }
    }

    public static String toString(String nesting, Collection objs) {
        if (objs==null) return "null";
        return toString(nesting, "\n\t{\n\t", "\n\t}", ",\n\t", objs.toArray(new Object[objs.size()]));
    }

    public static String toString(Object... objs) {
        return toString("", objs);
    }

    public static String toString(String nesting, Object... objs) {
        return toString(nesting, "\n\t{\n\t", "\n\t}", ",\n\t", objs);
    }

    public static String toString(String nesting, String prefix, String suffix, String separator, Object... objs) {
        if (objs.length == 0) {
            return prefix + suffix;
        }
        StringBuilder _toString = new StringBuilder(nesting + prefix);

        int i = 0;
        for (Object nxtObj : objs) {

            _toString.append(nesting).append(nesting);
            _toString.append(nxtObj == null ? "null" : nxtObj.toString());

            _toString.append(nesting);
            if (++i < objs.length) {// if it has next, add the separator
                _toString.append(separator);
            }
        }
        return _toString.append(nesting).append(suffix).toString();
    }

    public static String toSynopsis(String nesting, String prefix, String suffix, String separator, Iterable objs) {
        if (!objs.iterator().hasNext()) {
            return prefix + suffix;
        }
        StringBuilder _toString = new StringBuilder(nesting + prefix);

        int i = 0;
        for (Iterator it = objs.iterator(); it.hasNext();) {
            Object nxtObj = it.next();
            _toString.append(nesting).append(nesting);
            _toString.append(nxtObj == null ? "null" : nxtObj.toString());
            _toString.append(nesting);
            if (it.hasNext()) {// if it has next, add the separator
                _toString.append(separator);
            }
        }
        return _toString.append(nesting).append(suffix).toString();
    }

    public static String toString(double[] dbls) {
        return toString("", "{", "}", ", ", dbls);
    }

    public static String toString(String nesting, String prefix, String suffix, String separator, double... doubles) {
        if (doubles.length == 0) {
            return prefix + suffix;
        }
        StringBuilder _toString = new StringBuilder(nesting + prefix);
        for (int i = 0; i < doubles.length; i++) {
            Object nxt_obj = doubles[i];
            _toString.append(nesting).append(nesting).append(nxt_obj.toString()).append(nesting);
            if (i + 1 < doubles.length) {// if it has next, add the separator
                _toString.append(separator);
            }
        }
        return _toString.append(nesting).append(suffix).toString();
    }

    public static String toString(String nesting, String prefix, String suffix, String separator, Collection objs) {
        if (objs.isEmpty()) {
            return prefix + suffix;
        }
        StringBuilder _toString = new StringBuilder(nesting + prefix);
        for (Iterator it = objs.iterator(); it.hasNext();) {
            Object nxt_obj = it.next();
            _toString.append(nesting).append(nesting).append(nxt_obj.toString()).append(nesting);
            if (it.hasNext()) {
                _toString.append(separator);
            }
        }
        return _toString.append(nesting).append(suffix).toString();
    }

    public static Set<String> extractKeys(Set<Map.Entry<String, String>> entries) {
        Set<String> _extractKeys = new HashSet<>();
        for (Map.Entry<String, String> entry : entries) {
            _extractKeys.add(entry.getKey());
        }
        return _extractKeys;
    }

    public static Set<String> extractValues(Set<Map.Entry<String, String>> entries) {
        Set<String> _extractValues = new HashSet<>();
        for (Map.Entry<String, String> entry : entries) {
            _extractValues.add(entry.getValue());
        }
        return _extractValues;
    }

    /**
     * If the value is float or double, then it is rounded to the roundDecimal.
     * Otherwise the value is returned intact
     *
     * @param n
     * @param roundDecimal
     * @return the value rounded to a decimal
     */
    public static Number roundNumber(Number n, int roundDecimal) {
        if (n instanceof Double) {
            Double d = (Double) n;
            int rounded = (int) (d.doubleValue() * Math.pow(10, roundDecimal));
            return rounded / Math.pow(10, roundDecimal);
        }
        if (n instanceof Float) {
            Float d = (Float) n;
            int rounded = (int) (d.doubleValue() * Math.pow(10, roundDecimal));
            return rounded / Math.pow(10, roundDecimal);
        }

        return n;
    }

    public static int countOccurrences(String word, char occurChar) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == occurChar) {
                count++;
            }
        }
        return count;
    }

    /**
     * *
     * Parses and returns the number of corresponding bytes
     *
     * @param str
     * @return
     */
    public static long parseSizeToBytes(String str) {
        if (str.endsWith("KB") || str.endsWith("kb")) {
            String substring = str.substring(0, str.length() - 2);
            Double tmp = Double.parseDouble(substring);//for numbers in scientific notation like 1e123
            return (long) (1024 * tmp);
        }

        if (str.endsWith("MB") || str.endsWith("mb")) {
            String substring = str.substring(0, str.length() - 2);
            Double tmp = Double.parseDouble(substring);//for numbers in scientific notation like 1e123
            return (long) (Math.pow(1024, 2) * tmp);
        }

        if (str.endsWith("GB") || str.endsWith("gb")) {
            String substring = str.substring(0, str.length() - 2);
            Double tmp = Double.parseDouble(substring);//for numbers in scientific notation like 1e123
            return (long) (Math.pow(1024, 3) * tmp);
        }

        if (str.endsWith("TB") || str.endsWith("tb")) {
            String substring = str.substring(0, str.length() - 2);
            Double tmp = Double.parseDouble(substring);//for numbers in scientific notation like 1e123
            return (long) (Math.pow(1024, 4) * tmp);
        }

        return (int) Double.parseDouble(str);
    }

    /**
     *
     * Parses the size along with a measure unit (e.g. 30KB) and returns a
     * number measured in megabytes. Passing no measurement unit is assumed to
     * refer to a number of bytes.
     *
     * @param str
     * @return
     */
    public static double parseSizetoMBs(String str) {
        if (str.endsWith("KB") || str.endsWith("kb")) {
            String substring = str.substring(0, str.length() - 2);
            Double tmp = Double.parseDouble(substring);//for numbers in scientific notation like 1e123
            return (tmp / 1024);
        }

        if (str.endsWith("MB") || str.endsWith("mb")) {
            String substring = str.substring(0, str.length() - 2);
            return Double.parseDouble(substring);//for numbers in scientific notation like 1e123

        }

        if (str.endsWith("GB") || str.endsWith("gb")) {
            String substring = str.substring(0, str.length() - 2);
            return 1024 * Double.parseDouble(substring);
        }

        if (str.endsWith("TB") || str.endsWith("tb")) {
            String substring = str.substring(0, str.length() - 2);
            Double tmp = Double.parseDouble(substring);//for numbers in scientific notation like 1e123
            return Math.pow(1024, 2) * tmp;
        }

        //if none of the above, then it was passed measured in bytes
        String substring = str.substring(0, str.length() - 2);
        return Double.parseDouble(substring) / Math.pow(1024, 2);
    }

    protected CommonFunctions() {
    }

}
