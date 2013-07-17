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
    static Socket server;
    static Scanner serverScan;
    static PrintWriter serverWrite;
    static String delim = "|";

    public static void main(String[] args) throws Exception  {
        run();
    }

    public static void run() throws Exception  {
        getMethods();
        String method  = scanner.next().trim();
        String methodName = method.split("\\.")[1].split("\\(")[0];
        System.out.println(methodName);

        getMethodParams(method);
        String params = delim;
        String[] methodParams = serverScan.next().split("\\"+delim);
        for(String mp : methodParams) {
            System.out.println(mp);
            params += scanner.next() + delim;
        }

        runMethod(methodName, params);
        System.out.println(serverScan.next());

        // Continue or quit?
        System.out.print("\'C\'ontinue or \'Q\'uit");
        String input = scanner.next();
        if(input.equalsIgnoreCase("C")) run();
        else {
            server.shutdownInput();
            server.close();
        }
    }

    public static void connectToServer() throws Exception {
        server = new Socket(host, port);

        // Input & Output
        OutputStream serverOut = server.getOutputStream();
        InputStream serverIn = server.getInputStream();
        // Wrap
        serverScan = new Scanner(serverIn);
        serverWrite = new PrintWriter(serverOut, true);

        serverScan.useDelimiter("$");
    }

    public static void getMethods() throws Exception {
        connectToServer();
        serverWrite.println("GetClassMethods" + delim + "MathLogic");
        System.out.println("Enter a method name:");
        while(serverScan.hasNext())    {
            System.out.println(serverScan.next());
        }
    }

    public static void getMethodParams(String method) throws Exception {
        connectToServer();
        serverWrite.println("GetMethodParams" + delim + "MathLogic" + delim + method);
        if(serverScan.hasNext()) System.out.println("Method found!");
        else {
            System.out.println("Method not found!");
            run();
        }
    }

    public static void runMethod(String methodName, String params) throws Exception {
        connectToServer();
        serverWrite.println("RunMethod" + delim + "MathLogic" + delim + methodName + params);
    }
}
