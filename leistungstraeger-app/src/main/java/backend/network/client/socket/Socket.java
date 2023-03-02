package backend.network.client.socket;

import lombok.Getter;
import lombok.Setter;

public class Socket<T> {

    @Getter
    @Setter
    private T value;

}
