package implementation.behavioral;

public class ChainOfResponsibility {
    void main() {
        Handler authener = new AuthenHandler();
        Handler author = new AuthorHandler();
        authener.setNextHandler(author);
    }
}

abstract class Handler {
    Handler nextHandler;
    abstract void handle();
    abstract void setNextHandler(Handler handler);
}

class AuthenHandler extends Handler {

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    void handle() {
        System.out.println("Check authentication");
        if(this.nextHandler != null) {
            this.nextHandler.handle();
        }
    }
}

class AuthorHandler extends Handler {

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    void handle() {
        System.out.println("Check authorization");
        if(this.nextHandler != null) {
            this.nextHandler.handle();
        }
    }
}