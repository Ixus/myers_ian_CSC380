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
        ServerSocket listener = new java.net.ServerSocket(30000);
        while(true)   {
            Socket socket = listener.accept(); // returns socket
            new MyServer(socket).start();
        }

    }
}
