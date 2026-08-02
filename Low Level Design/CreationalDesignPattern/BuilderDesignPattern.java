package CreationalDesignPattern;

class User {

    private final int id;
    private final String name;

    // Constructor

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
class UserBuilder {

    private int id;
    private String name;




    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public User build() {

        if (name == null) {
            throw new IllegalArgumentException("Name is mandatory");
        }
        return new User(id, name);
    }
}
public class BuilderDesignPattern {

    public static void main(String[] args) {
        User user = new UserBuilder()
                .setId(12)
                .setName("Saurabh")
                .build();

    }
}
