package backend.network.client;

import lombok.Getter;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Client {

    @Getter
    private final long id;

    public Client(long id) {
        this.id = id;
    }
}
