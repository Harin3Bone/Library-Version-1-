package gl_Object;

import java.util.UUID;

public class Customer extends Person {
    public Customer() {
    }

    public Customer(UUID uuid, String firstname, String lastname, String identity, String password) {
        super(uuid, firstname, lastname, identity, password);
    }
}
