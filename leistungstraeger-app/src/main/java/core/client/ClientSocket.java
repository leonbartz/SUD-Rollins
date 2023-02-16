package core.client;

import helpers.Socket.Socket;

public class ClientSocket extends Socket<Client> {

    public long getId() {
        if (getValue() != null) {
            return getValue().getId();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
