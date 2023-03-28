package backend.network.client.socket;

import backend.network.client.Client;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ClientSocket extends Socket<Client> {

    public long getId() {
        if (getValue() != null) {
            return getValue().getId();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
