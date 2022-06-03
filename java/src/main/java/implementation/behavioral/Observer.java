package implementation.behavioral;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer simulate newspaper publisher operation. There're 2 main object: Subject (Observable) and Observers;
 * these two has loose coupling relation and the relationship is defined by interface.
 * Many Observers depend on Subject; when Subject change "state", it notifies to every registered Observers.
 * There're 2 ways Observers can get changes from Subject: push & pull
 */
public class Observer {
    void main() {
        IObservable weatherCentre = new WeatherData();
        IObserver monitor = new WeatherDisplay(weatherCentre); // passive registering
        IObserver chart = new WeatherStatistic(weatherCentre);
    }
}

interface IObservable {
    void register(IObserver observer);
    void unregister(IObserver observer);
    void update();
    State getState();
}

interface IObserver {
    void updated(State s);
    void unsubscribe();
}

class State {
    @Getter
    private String dynamicInfo = "I will be updated frequently";

    public State(String dynamicInfo) {
        this.dynamicInfo = dynamicInfo;
    }
}

class WeatherData implements IObservable {
    State s = new State("New state again..."); // The state will change a lots in real life
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void register(IObserver observer) {
        if(observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void unregister(IObserver observer) {
        if(observer != null) {
            observers.remove(observer);
        }
    }

    @Override
    public void update() {
        for(IObserver observer : observers) {
            observer.updated(s);
        }
    }

    @Override
    public State getState() {
        return s;
    }
}

// One of many type of observer
class WeatherDisplay implements IObserver {

    IObservable weatherData = new WeatherData();

    public WeatherDisplay(IObservable weatherData) {
        this.weatherData = weatherData;
        weatherData.register(this);
    }


    @Override
    public void updated(State s) {
        display(s);
    }

    public void display(State s) {
        System.out.println("Show the state: " + s);
    }

    @Override
    public void unsubscribe() {
        weatherData.unregister(this);
    }
}

class WeatherStatistic implements IObserver {

    IObservable weatherData = new WeatherData();

    public WeatherStatistic(IObservable weatherData) {
        this.weatherData = weatherData;
        weatherData.register(this);
    }

    @Override
    public void updated(State s) {
        showStatistic(s);
    }

    public void showStatistic(State s) {
        System.out.println("Show the statistic of state: " + s);
    }

    @Override
    public void unsubscribe() {
        weatherData.unregister(this);
    }
}