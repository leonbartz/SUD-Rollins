package backend.network.client.socket;

import backend.network.client.Client;

public class ClientSocket extends Socket<Client> {

    public long getId() {
        if (getValue() != null) {
            return getValue().getId();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
