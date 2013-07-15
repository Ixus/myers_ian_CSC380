import java.io.InputStream;
import java.io.OutputStream;
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
    static Scanner serverScan;
    static Socket server;
    static OutputStream serverOut;
    static InputStream serverIn;
    static PrintWriter serverWrite;

    public static void main(String[] args) throws Exception  {
        run();
    }

    public static void run() throws Exception  {

        // ----------- Get user input -----------
        connectToServer();
        serverWrite.println("GetMathLogicMethods");
        System.out.println("Enter a method name:");
        while(serverScan.hasNext())    {
            System.out.println(serverScan.next());
        }
        String method  = scanner.next();

        System.out.print("Enter 1st number:");
        int p1 = scanner.nextInt();

        System.out.print("Enter 2nd number:");
        int p2 = scanner.nextInt();

        // Write to output stream (to the server)
        connectToServer();
        serverWrite.println(String.valueOf(method));
        serverWrite.println(String.valueOf(p1));
        serverWrite.println(String.valueOf(p2));
        server.shutdownOutput();

        // Read then print server response  (from the server)
        System.out.println(serverScan.next());

        // Continue or quit?
        System.out.print("\'C\'ontinue or \'Q\'uit");
        String input = scanner.next();
        if(input.equals("C")) run();

        // Close
        server.shutdownInput();
        server.close();
    }

    public static void connectToServer() throws Exception {
        server = new Socket(host, port);
        serverOut = server.getOutputStream();
        serverIn = server.getInputStream();
        serverScan = new Scanner(serverIn);
        serverScan.useDelimiter("$");
        serverWrite = new PrintWriter(serverOut, true);
    }
}
