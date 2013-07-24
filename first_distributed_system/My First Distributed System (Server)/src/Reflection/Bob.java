package reflection;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 7/16/13
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bob {
    String hello = "Hello human.";

    public Bob() {}

    public Bob(String hello) {
            this.hello = hello;
    }

    public String hello()  {
        return hello;
    }

    public String echo(String message)  {
           return message;
    }
}
