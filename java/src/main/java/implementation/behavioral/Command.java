package implementation.behavioral;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Command encapsulates requests of client as object
 * Let us parameterize different requests and build request log, undoable operations
 * Command patterns comprise from 3 element: Command - request object, Invoker - invoke request, Receiver - request executor
 */
public class Command {
    void main() {
        TaskDistributor taskDistributor = new TaskDistributor();
        CustomerStatistic customerStatistic = new CustomerStatistic();
        ICommand paperCmd = new PaperTask(customerStatistic);
        ICommand defaultCmd = new DefaultTask();
        taskDistributor.setCommand(paperCmd);
        taskDistributor.setCommand(defaultCmd);
        while(!taskDistributor.cmdQueue.isEmpty()) {
            ICommand cmd = taskDistributor.cmdQueue.poll();
            cmd.execute();
        }
    }
}

interface ICommand {
    void execute();
    void undo();
}

abstract class Invoker {
    Stack<ICommand> cmdLogs; // Implement cmd history or log
    abstract void setCommand(ICommand cmd);
    abstract void undoCommand();
}

class TaskDistributor extends Invoker {
    Queue<ICommand> cmdQueue = new LinkedList<>();

    public TaskDistributor() {
        this.cmdLogs = new Stack<>();
    }

    @Override
    public void setCommand(ICommand cmd) {
        cmdQueue.add(cmd);
        cmdLogs.push(cmd);
    }

    @Override
    void undoCommand() {
        ICommand cmd = this.cmdLogs.pop();
        cmd.undo();
    }
}

class PaperTask implements ICommand {

    CustomerStatistic customerStatistic;
    int prevAction = 0;

    public PaperTask(CustomerStatistic customerStatistic) {
        this.customerStatistic = customerStatistic;
    }

    @Override
    public void execute() {
        if(prevAction == 0) {
            prevAction = this.customerStatistic.drawChart();
        } else if(prevAction == 1) {
            prevAction = this.customerStatistic.delete();
        } else {
            prevAction = this.customerStatistic.collect();
        }
    }

    @Override
    public void undo() {
        if(prevAction == 0) {
            prevAction = this.customerStatistic.collect(); // exec previous action
        } else if(prevAction == 1) {
            prevAction = this.customerStatistic.drawChart();
        } else {
            prevAction = this.customerStatistic.drawChart();
        }
    }
}

// Receiver
class CustomerStatistic {
    int collect() {
        System.out.println("Collect customer data");
        return 0;
    }
    int drawChart() {
        System.out.println("Draw data");
        return 1;
    }
    int delete() {
        System.out.println("Delete data");
        return 2;
    }
}

class DefaultTask implements ICommand {

    @Override
    public void execute() {
        System.out.println("Do nothing, I'm a Null Object");
    }

    @Override
    public void undo() {
        System.out.println("Undo nothing");
    }
}