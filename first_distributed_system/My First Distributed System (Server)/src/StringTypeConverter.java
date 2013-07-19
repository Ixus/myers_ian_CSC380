/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 7/18/13
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */

public class StringTypeConverter {

    /********** The Guts *********/

    private static int convertToInt(String x) {
        return Integer.parseInt(x);
    }

    private static double convertToDouble(String x) {
        return Double.parseDouble(x);
    }

    private static float convertToFloat(String x) {
        return Float.parseFloat(x);
    }

    private static Integer[] convertToIntArray(String[] x) {
        Integer[] array = new Integer[x.length];
        for(int i = 0; i < x.length; i++) array[i] = convertToInt(x[i]);
        return array;
    }

    private static Double[] convertToDoubleArray(String[] x) {
        Double[] array = new Double[x.length];
        for(int i = 0; i < x.length; i++) array[i] = convertToDouble(x[i]);
        return array;
    }

    private static Float[] convertToFloatArray(String[] x) {
        Float[] array = new Float[x.length];
        for(int i = 0; i < x.length; i++) array[i] = convertToFloat(x[i]);
        return array;
    }

    private static boolean isArray(String x, String arrayDeliminator) {
        boolean result = false;
        if(x.split(arrayDeliminator).length > 1 ) result = true;
        return result;
    }

    /********** The Conversion Methods *********/

    public static Object convertTo(String x, Class dataType) {
        Object result = x;
        if(dataType == int.class) result = convertToInt(x);
        if(dataType == double.class) result = convertToDouble(x);
        if(dataType == float.class) result = convertToFloat(x);
        return result;
    }

    public static Object[] convertTo(String[] x, Class dataType) {
        Object[] result = x;
        if(dataType == int[].class) result = convertToIntArray(x);
        if(dataType == double[].class) result = convertToDoubleArray(x);
        if(dataType == float[].class) result = convertToFloatArray(x);
        return result;
    }

    /********** YES! *********/

    public static Object[] convertToObjectArray(String[] valueArray, Class[] datatypeArray, String arrayDeliminator) {
            Object[] newArray = new Object[valueArray.length];
            for(int i = 0; i < valueArray.length; i++) {
                if(isArray(valueArray[i], arrayDeliminator)){
                    newArray[i] = valueArray[i].split(arrayDeliminator);
                    newArray[i] = convertTo((String[])newArray[i], datatypeArray[i]);
                }
                else {
                    newArray[i] = valueArray[i];
                    newArray[i] = convertTo((String)newArray[i], datatypeArray[i]);
                }
            }
            return newArray;
    }

    public static Object[] convertToObjectArray(String[] valueArray,  Class[] datatypeArray) {
        return convertToObjectArray(valueArray, datatypeArray, ",");
    }
}
