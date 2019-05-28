package throwable;


public class try_catch {
    public void tests() throws Throwable {
        MyException e = new MyException();
        throw e;

    }
}

class MyException extends Throwable{

}
