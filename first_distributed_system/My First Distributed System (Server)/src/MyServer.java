import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 7/12/13
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyServer extends Thread {
    private Socket socket;

    public MyServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
