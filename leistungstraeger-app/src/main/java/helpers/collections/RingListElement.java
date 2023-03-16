package helpers.collections;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class RingListElement<T> {

    private final T payload;
    private RingListElement<T> nextElement;

    public RingListElement(T payload) {
        this.payload = payload;
    }

    public RingListElement<T> next() {
        return nextElement;
    }

    public void setNextElement(RingListElement<T> nextElement) {
        this.nextElement = nextElement;
    }

    public T getPayload() {
        return payload;
    }
}
