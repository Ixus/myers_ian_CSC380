import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 09/07/13
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 */

public class Main {
    public static void main(String[] args) throws Exception  {
        System.out.println("Server (running)");
        run();
        System.out.println("Server (ending)");
    }

    public static void run() throws Exception {

        // Listen
        ServerSocket listener = new java.net.ServerSocket(30000);
        Socket socket = listener.accept(); // returns socket

        // Read input stream (from client)
        InputStream in = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String operation = bufferedReader.readLine();
        String a = bufferedReader.readLine();
        String b = bufferedReader.readLine();

        // MATH
        int result = 0;
        MathLogic m = new MathLogic();
        if(operation.equals("add"))  result = m.add(a, b);
        else if(operation.equals("subtract")) result = m.subtract(a, b);

        // Write to output stream (to client)
        OutputStream out = socket.getOutputStream();
        byte[] sendableBytes = ("Answer: " + Integer.toString(result)).getBytes();
        out.write(sendableBytes, 0, sendableBytes.length);

        // Close
        out.flush();
        socket.close();
    }
}
