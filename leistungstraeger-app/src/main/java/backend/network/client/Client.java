package backend.network.client;

import lombok.Getter;

public class Client {

    @Getter
    private final long id;

    public Client(long id) {
        this.id = id;
    }
}
