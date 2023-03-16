package helpers.Observer;

import java.util.ArrayList;
import java.util.List;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Subject<T> {

    private T t;
    public List<Observer<T>> observerList;

    public Subject() {
        observerList = new ArrayList<>();
    }

    public void notifyObservers() {
        for (Observer<T> observer : observerList) {
            observer.update(t);
        }
    }

    public void addObserver(Observer<T> observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        observerList.remove(observer);
    }

    public void setT(T value) {
        t = value;
    }

    public T getT() {
        return t;
    }
}
