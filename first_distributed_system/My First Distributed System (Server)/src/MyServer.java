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
    private Class mathLogic;
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

        // Reflection
        mathLogic = Class.forName("MathLogic");
    }

    public void run() {
        try {
            String input = bufferedReader.readLine();
            if(input.equals("GetMathLogicMethods")) sendMathLogicMethods();
            else sendMathLogicResponse(input);
            out.flush();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void sendMathLogicMethods() throws Exception {
        Method[] methods = mathLogic.getDeclaredMethods();
        for(Method m : methods)                {
            sendToClient(m.toString() + String.format("%n"));
        }
    }

    public void sendMathLogicResponse(String input) throws Exception {
        String a = bufferedReader.readLine();
        String b = bufferedReader.readLine();

        Object instance = mathLogic.getConstructor().newInstance();
        Method method = mathLogic.getDeclaredMethod(input, String.class, String.class);
        String result = method.invoke(instance,a,b).toString();

        // Write to output stream (to client)
        sendToClient("Answer: " + result);
    }

    public void sendToClient(String message) throws Exception    {
        byte[] sendableBytes = message.getBytes();
        out.write(sendableBytes, 0, sendableBytes.length);
    }

    public Method[] getMathLogicMethods() {
        return mathLogic.getDeclaredMethods();
    }
}
