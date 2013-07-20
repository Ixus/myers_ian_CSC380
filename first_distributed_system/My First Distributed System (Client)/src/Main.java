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
        help();
        run();
    }

    public static void run() throws Exception  {
        String command = "";

        // Send Commands
        while(!command.equalsIgnoreCase("q")) {
            connectToServer();
            System.out.println("Enter command:");
            command = scanner.nextLine();
            serverWrite.println(command);
            System.out.println(serverRead.next());
        }

        server.shutdownInput();
        server.close();
    }

    public static void connectToServer() throws Exception {
        server = new Socket(host, port);
        serverRead = new Scanner(server.getInputStream());
        serverWrite = new PrintWriter(server.getOutputStream(), true);
        serverRead.useDelimiter("$");
    }

    public static void help() {
        System.out.println("COMMANDS");
        System.out.println("classes or c");
        System.out.println("methods or m <package>.<class>");
        //System.out.println("Run Methods Syntax:");
        //System.out.println("<class> <method> <parameters>");
       // System.out.println("Example: MathLogic add 2 2");
        //System.out.println("Array example: MathLogic add 2,4,3,7");
        System.out.println("<package>.<class> <method #> <parameters>");
        System.out.println("Ex: reflection.MathLogic 1 2 2");
        System.out.println("Ex: reflection.MathLogic 3 2,2,2");
        System.out.println("");
    }

}
