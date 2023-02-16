package helpers.collections;

import java.util.ArrayList;

public class RingList<T> {

    private RingListElement<T> firstElement;
    private RingListElement<T> activeElement;

    public RingList() {}

    public RingList(RingList<T> copyList) {
        this.firstElement = copyList.firstElement;
        this.activeElement = copyList.activeElement;
    }

    public void add(T newElement) {
        RingListElement<T> newRingListElement = new RingListElement<>(newElement);
        if (firstElement == null) {
            firstElement = newRingListElement;
            activeElement = newRingListElement;
            newRingListElement.setNextElement(newRingListElement);
        } else {
            activeElement.setNextElement(newRingListElement);
            newRingListElement.setNextElement(firstElement);
            activeElement = newRingListElement;
        }
    }

    public void resetActiveElement() {
        activeElement = firstElement;
    }

    public T getElement() {
        return activeElement.getPayload();
    }

    public void next() {
        activeElement = activeElement.next();
    }

    public ArrayList<T> toList() {
        ArrayList<T> list = new ArrayList<>();
        RingListElement<T> first = firstElement;
        list.add(first.getPayload());
        RingListElement<T> next = firstElement.next();
        while(next != first) {
            list.add(next.getPayload());
            next = next.next();
        }
        return list;
    }
}
