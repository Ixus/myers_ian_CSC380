import java.io.*;
import java.net.Socket;

import java.lang.reflect.Method;

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
    private String delim = "|";

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
            String[] input = in.readLine().split("\\"+delim);

            for(String s : input)  System.out.println(s);
            System.out.println("");

            if(input[0].equals("GetClassMethods")) sendMethods(input[1]);
            else if(input[0].equals("GetMethodParams"))  sendMethodParameters(input[1], input[2]);
            else if(input[0].equals("RunMethod")) sendMethodResponse(input);

            out.flush();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void sendMethods(String className) throws Exception {
        Class c = Class.forName(className);
        Method[] methods = c.getDeclaredMethods();
        for(Method m : methods)                {
            out.println(m.toString());
        }
    }

    public boolean sendMethodParameters(String className, String methodName) throws Exception {
        boolean foundMethod = false;
        Class c = Class.forName(className);
        Method[] methods = c.getDeclaredMethods();
        for(Method m : methods)
        {
            if(cleanMethodNameString(m.toString()).equals(methodName)) {
                foundMethod = true;
                Class<?>[] params = m.getParameterTypes();
                for(Class<?> p : params) {
                    out.print(p.toString() + delim);
                }
            }
        }
        return foundMethod;
    }

    public void sendMethodResponse(String[] input) throws Exception {
        String className = input[1];
        String methodName = input[2];
        String a = input[3];
        String b = input[4];

        Class c = Class.forName(className);
        Object instance = c.getConstructor().newInstance();

        // Get Method
        int numberOfParams = input.length - 3;
        Class[] paramClassTypes = new Class[numberOfParams];
        for(int i = 3; i < input.length; i++) {
            paramClassTypes[i-3] = input[i].getClass();
        }
        Method method = c.getDeclaredMethod(methodName, paramClassTypes);

        // Invoke Method
        Object[] paramValues = new Object[numberOfParams];
        for(int i = 3; i < input.length; i++) {
            paramValues[i-3] = input[i];
        }
        String result = method.invoke(instance,paramValues).toString();

        // Write to output stream (to client)
        out.print("Method returned: " + result);
    }

    public static String cleanMethodNameString(String method) {
        return method.split(" ")[2];
    }
}
