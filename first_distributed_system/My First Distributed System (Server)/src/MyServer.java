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
    private InputStream in;
    private OutputStream out;
    BufferedReader bufferedReader;

    public MyServer(Socket socket) throws Exception {
        // Initialize
        this.socket = socket;
        in = socket.getInputStream();
        out = socket.getOutputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(in));
        byte[] sendableBytes; // for output
    }

    public void run() {
        try {
            String input[] = bufferedReader.readLine().split("\\$");

            for(String s : input)  System.out.println(s);
            System.out.println("");

            if(input[0].equals("GetClassMethods")) sendMethods(input[1]);
            else if(input[0].equals("GetMethodParams"))  sendMethodParameters(input[1], input[2]);
            else if(input[0].equals("RunMethod")) sendMethodResponse(input[1], input[2]);

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
            sendToClient(m.toString() + String.format("%n"));
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
                    sendToClient(p.toString());
                }
            }
        }
        return foundMethod;
    }

    public void sendMethodResponse(String className, String methodName) throws Exception {
        String a = bufferedReader.readLine();
        String b = bufferedReader.readLine();

        Class c = Class.forName(className);
        Object instance = c.getConstructor().newInstance();
        Method method = c.getDeclaredMethod(methodName, String.class, String.class);
        String result = method.invoke(instance,a,b).toString();

        // Write to output stream (to client)
        sendToClient("Method returned: " + result);
    }

    public void sendToClient(String message) throws Exception    {
        byte[] sendableBytes = message.getBytes();
        out.write(sendableBytes, 0, sendableBytes.length);
    }

    public static String cleanMethodNameString(String method) {
        return method.split(" ")[2];
    }
}
