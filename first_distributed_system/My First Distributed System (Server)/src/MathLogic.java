/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 09/07/13
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */

public class MathLogic {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b)  {
        return a - b;
    }

    public int add(String a, String b) {
        return add(Integer.parseInt(a), Integer.parseInt(b));
    }

    public int subtract(String a, String b)  {
        return subtract(Integer.parseInt(a), Integer.parseInt(b));
    }
}
