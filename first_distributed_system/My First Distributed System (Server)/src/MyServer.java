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
    private String delim = " ";

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
            Object[] input = in.readLine().split(delim);

            for(Object s : input)  System.out.println(s);

            if(input[0].equals("GetMethods")) getMethods(input[1].toString());
            else if(input[0].equals("MathLogic")) runMethod(input);

            out.flush();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void getMethods(String className) throws Exception {
        Class c = Class.forName(className);
        Method[] methods = c.getDeclaredMethods();
        for(Method m : methods)                {
            out.println(m.toString());
        }
    }

    public void runMethod(Object[] input) throws Exception {
        String className = input[0].toString();
        String methodName = input[1].toString();

        int indexOfLastParameter = input.length - 1;
        Object[] lastParam = input[indexOfLastParameter].toString().split(",");
        input[indexOfLastParameter] = lastParam;

        Class c = Class.forName(className);
        Object instance = c.getConstructor().newInstance();

        // Get Method
        int numberOfParams = input.length - 2;
        Class[] paramClassTypes = new Class[numberOfParams];
        for(int i = 2; i < input.length; i++) {
            paramClassTypes[i-2] = input[i].getClass();
        }
        Method method = c.getDeclaredMethod(methodName, paramClassTypes);

        // Invoke Method
        Object[] paramValues = new Object[numberOfParams];
        for(int i = 2; i < input.length; i++) {
            paramValues[i-2] = input[i];
        }
        String result = method.invoke(instance,paramValues).toString();

        // Write to output stream (to client)
        out.print("Method returned: " + result);
    }

    public static String cleanMethodNameString(String method) {
        return method.split(" ")[2];
    }
}
