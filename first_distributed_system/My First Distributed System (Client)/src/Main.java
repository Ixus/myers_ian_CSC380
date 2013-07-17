import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 09/07/13
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String host = "localhost";
    static int port = 30000;
    static Socket server;
    static Scanner serverRead;
    static PrintWriter serverWrite;

    public static void main(String[] args) throws Exception  {
        Object[] o = new Object[3];
        o[0] = "ya";
        o[1] = "meh";
        o[2] = "2,2,2";
        o[2] = o[2].toString().split(",");
        System.out.println("end");
        //run();
    }

    public static void run() throws Exception  {
        String command = "";

        // Send Commands
        while(!command.equalsIgnoreCase("q")) {
            connectToServer();
            System.out.println("Enter command:");
            command = scanner.nextLine();
            serverWrite.println(command);
            while(serverRead.hasNext())    {
                System.out.println(serverRead.next());
            }
        }

        // ex: MathLogic add 2 2
        // ex: GetMethods MathLogic

        server.shutdownInput();
        server.close();
    }

    public static void connectToServer() throws Exception {
        server = new Socket(host, port);
        serverRead = new Scanner(server.getInputStream());
        serverWrite = new PrintWriter(server.getOutputStream(), true);
        serverRead.useDelimiter("$");
    }
}
