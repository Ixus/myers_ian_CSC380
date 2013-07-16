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
        getMethods();
        String method  = scanner.next().trim();
        String methodName = "";

        getMethodParams(method);
        String params = "$";
        while(serverScan.hasNext())    {
            System.out.println(serverScan.next());
            params += scanner.next() + "$";
        }

        runMethod(method, params);
        System.out.println(serverScan.next());

        // Continue or quit?
        System.out.print("\'C\'ontinue or \'Q\'uit");
        String input = scanner.next();
        if(input.equalsIgnoreCase("C")) run();

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

    public static void getMethods() throws Exception {
        connectToServer();
        serverWrite.println("GetClassMethods$MathLogic");
        System.out.println("Enter a method name:");
        while(serverScan.hasNext())    {
            System.out.println(serverScan.next());
        }
    }

    public static void getMethodParams(String method) throws Exception {
        connectToServer();
        serverWrite.println("GetMethodParams$MathLogic$" + method);
        if(serverScan.hasNext()) System.out.println("Method found!");
        else {
            System.out.println("Method not found!");
            run();
        }
    }

    public static void runMethod(String method, String params) throws Exception {
        connectToServer();
        serverWrite.println("RunMethod$MathLogic$" + method + params);
        server.shutdownOutput();
        // Read then print server response  (from the server)
        System.out.println(serverScan.next());
    }

    public static String cleanMethodNameString(String method) {
        return method.split(" ")[2];
    }
}
