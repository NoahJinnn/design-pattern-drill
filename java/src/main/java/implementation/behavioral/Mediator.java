package implementation.behavioral;

/**
 * Mediator centralizes complex communications and controls between related objects
 */
public class Mediator {
}

interface IMediator {
    void notify(ISender sender);

}

class ConcreteMediator implements IMediator {

    ISender senderA = new SenderA();
    ISender senderB = new SenderB();

    @Override
    public void notify(ISender sender) {
        sender.operate();
    }

    public void reactWithSenderA() {
        senderA.operate();
    }

    public void reactWithSenderB() {
        senderB.operate();
    }
}

interface ISender {
    void operate();

}

class SenderA implements ISender {
    IMediator mediator = new ConcreteMediator();
    @Override
    public void operate() {
        mediator.notify(this);
    }
}

class SenderB implements ISender {
    IMediator mediator = new ConcreteMediator();
    @Override
    public void operate() {
        mediator.notify(this);
    }
}
