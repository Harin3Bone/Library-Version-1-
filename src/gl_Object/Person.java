package gl_Object;

import java.util.UUID;

public class Person {
    // Create attribute variable
    private UUID uuid = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String identity;
    private String password;

    // Getter & Setter
    public UUID getUuid() {

        return uuid;
    }

    public void setUuid(UUID uuid) {

        this.uuid = uuid;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getIdentity() {

        return identity;
    }

    public void setIdentity(String identity) {

        this.identity = identity;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    // Constructor
    public Person() {

    }

    public Person(UUID uuid, String firstName, String lastName, String identity, String password) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identity = identity;
        this.password = password;
    }

    // toString
    @Override
    public String toString() {
        return "firstName = " + firstName +
                " | lastName = " + lastName +
                " | identity = " + identity +
                " | password = " + password;
    }
}
