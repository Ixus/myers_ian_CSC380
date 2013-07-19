import java.io.*;
import java.net.Socket;

import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 7/12/13
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyServer extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String delim = " ";
    private String ReflectionPackage = "reflection";

    public MyServer(Socket socket) throws Exception {
        // Initialize
        this.socket = socket;
        InputStream inStream = socket.getInputStream();
        OutputStream outStream = socket.getOutputStream();
        // Wrap
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream);
        // Wrap
        in = new BufferedReader(inStreamReader);
        BufferedWriter outBufferedWriter = new BufferedWriter(outStreamWriter);
        // Wrap
        out = new PrintWriter(outBufferedWriter);

        byte[] sendableBytes; // for output
    }

    public void run() {
        try {
            System.out.println("Start Thread!");

            Object[] input_temp = in.readLine().split(delim);
            Object[] input = new Object[input_temp.length];
            for(int i = 0; i < input.length; i++) input[i] = input_temp[i];

            // Run Command
            if(input[0].toString().equals("GetClasses")||input[0].toString().equals("c")) {
                getClasses(ReflectionPackage);
            }   else if(input[0].toString().equals("GetMethods")||input[0].toString().equals("m")) {
                getMethods(input[1].toString());
            }   else {
                 if(input[0].toString().length()>9) {
                     String check = input[0].toString().substring(0,10);
                     if(check.equals("reflection")){
                         runMethod(input);
                     } else {
                         out.println("Not a recognized command. Did you type in the package name?");
                     }
                 } else {
                     out.println("Not a recognized command!");
                 }
            }

            out.flush();
            socket.close();

            System.out.println("End Thread!");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println(e.getMessage());
        }
    }

    public void getClasses(String packageName) throws Exception {
        List<Class> classList = getClassesInPackage(packageName);
        for(Class c : classList) {
            out.println(c.toString());
        }
    }

    public void getMethods(String className) throws Exception {
        Class c = Class.forName(className);
        Method[] methods = c.getDeclaredMethods();
        int count = 0;
        for(Method m : methods) {
            out.println("#" + (++count) + " " + m.toString());
        }
    }

    public void runMethod(Object[] input) throws Exception {
        String className = input[0].toString();
        int methodNumber = Integer.parseInt(input[1].toString()) - 1;

        // Construct Class
        Class c = Class.forName(className);
        Object instance = c.getConstructor().newInstance();

        // Get Method
        Method method = c.getDeclaredMethods()[methodNumber];

        // Get Parameter Values
        int numberOfParams = (input.length - 2);
        String[] paramValues = new String[numberOfParams];
        for(int i = 2; i < input.length; i++) {
            paramValues[i-2] = input[i].toString();
        }

        // Convert Parameters To Correct Data Types
        Class[] paramDateTypes = method.getParameterTypes();
        Object[] paramArray = StringTypeConverter.convertToObjectArray(paramValues, paramDateTypes);

        // Invoke Method
        String result = method.invoke(instance,paramArray).toString();

        // Write to output stream (to client)
        out.print("Method returned: " + result);
    }

    public void runMethod_UnspecifiedSignature(Object[] input) throws Exception {
        String className = input[0].toString();
        String methodName = input[1].toString();

        // Check if last parameter is an array. If it is, then store an array in it.
        int indexOfLastParameter = input.length - 1;
        Object[] lastParam = input[indexOfLastParameter].toString().split(",");
        if(CheckIfParameterIsArray(input[indexOfLastParameter].toString())) {
            input[indexOfLastParameter] = lastParam;
        }

        // Construct Class
        Class c = Class.forName(className);
        Object instance = c.getConstructor().newInstance();

        // Get Parameter Data Types
        int numberOfParams = (input.length - 2);
        Class[] paramClassTypes = new Class[numberOfParams];
        for(int i = 2; i < input.length; i++) {
            paramClassTypes[i-2] = input[i].getClass();
        }

        // Get Method
        Method method = c.getDeclaredMethod(methodName, paramClassTypes);

        // Get Parameter Values
        Object[] paramValues = new Object[numberOfParams];
        for(int i = 2; i < input.length; i++) {
            paramValues[i-2] = input[i];
        }

        // Invoke Method
        String result = method.invoke(instance,paramValues).toString();

        // Write to output stream (to client)
        out.print("Method returned: " + result);
    }

    public boolean CheckIfParameterIsArray(String parameter) {
        for(int i = 0; i < parameter.length(); i++) {
            if(parameter.substring(i,i+1).equals(",")) return true;
        }
        return false;
    }

    public List<Class> getClassesInPackage(String packageName) throws Exception {
        URL packageUrl = this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));
        List allClasses = new ArrayList<>();
        if(packageUrl != null) {
            Path packagePath = Paths.get(packageUrl.toURI());
            if(Files.isDirectory(packagePath)) {
                try(DirectoryStream<Path> ds = Files.newDirectoryStream(packagePath, "*.class")) {
                    for(Path d : ds) {
                        allClasses.add(Class.forName(packageName + "." + d.getFileName().toString().replace(".class", "")));
                    }
                }
            }
            return allClasses;
        }
        return null;
    }

    public Class getClassType(String classType) {
        Class theType = null;
        if(classType.equals("String")) theType = String.class;
        else if(classType.equals("int")) theType = Integer.class;
        else if(classType.equals("double")) theType = Double.class;
        else if(classType.equals("float")) theType = Float.class;
        else if(classType.equals("array_string")) theType = String[].class;
        else if(classType.equals("array_int")) theType = Integer[].class;
        else if(classType.equals("array_double")) theType = Double[].class;
        else if(classType.equals("array_float")) theType = Float[].class;
        return theType;
    }

    public String[] getMethodSignature(Method method) {
        return null;
    }

    public void convertParametersToCorrectDataTypes(Method method, Object[] paramValues) {
        Class dataType;
        Class<?>[] paramDataTypes = method.getParameterTypes();
        for(int i = 0; i < paramValues.length; i++) {
            dataType = paramDataTypes[i];

            if(CheckIfParameterIsArray(paramValues[i].toString())) {
                convertParametersToCorrectDataTypes_Arrays(method, (Object[])paramValues[i]);
            }
            if(dataType == int.class) { paramValues[i] = Integer.parseInt(paramValues[i].toString()); }
            else if(dataType == double.class) { paramValues[i] = Double.parseDouble(paramValues[i].toString()); }
            else if(dataType == float.class) { paramValues[i] = Float.parseFloat(paramValues[i].toString()); }
        }
    }

    public void convertParametersToCorrectDataTypes_Arrays(Method method, Object[] param) {
        Class<?>[] paramDataTypes = method.getParameterTypes();
        Class dataType = paramDataTypes[paramDataTypes.length-1];
        for(int i = 0; i < param.length; i++) {
            if(dataType == int.class) { param[i] = Integer.parseInt(param[i].toString()); }
            else if(dataType == double.class) { param[i] = Double.parseDouble(param[i].toString()); }
            else if(dataType == float.class) { param[i] = Float.parseFloat(param[i].toString()); }
        }
    }
}
