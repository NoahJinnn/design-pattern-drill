package implementation.behavioral;

import lombok.Getter;
import lombok.Setter;

/**
 * State encapsulates subject's internal state, state object reflects subject behavior and allows subject to change its
 * behavior
 */
public class StatePattern {
    void main() {
        StateMachine stateMachine = new StateMachine();
        while(true) {
            stateMachine.process();
        }
    }
}

@Getter
@Setter
class StateMachine {
    private IState startS = new Start();
    private IState workS = new Working();
    private IState stopS = new Stop();
    private IState currentState = startS;
    private int workDays = 0;

    public int getWorkDays() {
        return workDays;
    }

    public void process() {
        this.currentState.transit(this);
    }

}

interface IState {
    void affect(); // Do some side effects
    void transit(StateMachine stateMachine);
}

class Start implements IState {

    @Override
    public void affect() {
        System.out.println("Always an init state");
    }

    @Override
    public void transit(StateMachine stateMachine) {
        // I just work 5 days a week
        if(stateMachine.getWorkDays() == 5) {
            stateMachine.setWorkDays(0);
            stateMachine.setCurrentState(stateMachine.getStopS());
        } else {
            stateMachine.setCurrentState(stateMachine.getWorkS());
        }
    }
}

class Working implements IState {

    @Override
    public void affect() {
        System.out.println("Doing my job");
    }

    @Override
    public void transit(StateMachine stateMachine) {
        stateMachine.setWorkDays(stateMachine.getWorkDays() + 1);
        stateMachine.setCurrentState(stateMachine.getStopS());
    }
}

class Stop implements IState {

    @Override
    public void affect() {
        System.out.println("Let's go home");
    }

    @Override
    public void transit(StateMachine stateMachine) {
        stateMachine.setCurrentState(stateMachine.getStartS());
    }
}