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

    public static void main(String[] args) throws Exception  {
        run();
    }

    public static void run() throws Exception  {
        int result = 0;

        // --------- Get user input ---------
        System.out.print("Add or subtract?");
        String operator = scanner.nextLine();

        System.out.print("Enter 1st number:");
        int a = scanner.nextInt();

        System.out.print("Enter 2nd number:");
        int b = scanner.nextInt();

        // --------- Server socket ---------
        Socket server = new Socket(host, 30000);

       // Get input & output stream
        OutputStream serverOut = server.getOutputStream();
        InputStream serverIn = server.getInputStream();

        // Write to output stream (to the server)
        PrintWriter serverWrite = new PrintWriter(serverOut, true);
        serverWrite.println(String.valueOf(operator));
        serverWrite.println(String.valueOf(a));
        serverWrite.println(String.valueOf(b));
        server.shutdownOutput();

        // Read from input stream (from the server)
        Scanner serverScan = new Scanner(serverIn);
        serverScan.useDelimiter("$");
        String resp = serverScan.next();

        // Print server response
        System.out.println(resp);

        // Close
        server.shutdownInput();
        server.close();
    }
}
