package CreationalDesignPattern;

// Prototype Class
class Student implements Cloneable {

    int id;
    String name;
    Address address;

    // Override clone() to make it public
    @Override
    public Student clone() throws CloneNotSupportedException {

        // Creates a shallow copy
        Student clonedStudent = (Student) super.clone();

        // Deep copy of Address object
        clonedStudent.address = new Address();
        clonedStudent.address.address = this.address.address;

        return clonedStudent;
    }
}

// Nested Object
class Address {
    String address;
}

public class ProtoTypeDesignPattern {

    public static void main(String[] args) throws CloneNotSupportedException {

        // Original Object
        Student s1 = new Student();
        s1.id = 1;
        s1.name = "Saurabh";

        s1.address = new Address();
        s1.address.address = "Delhi";

        // Clone the object
        Student s2 = s1.clone();

        // Modify only cloned object
        s2.id = 2;
        s2.name = "Rahul";
        s2.address.address = "Mumbai";

        System.out.println("Original Student");
        System.out.println(s1.id + " " + s1.name + " " + s1.address.address);

        System.out.println();

        System.out.println("Cloned Student");
        System.out.println(s2.id + " " + s2.name + " " + s2.address.address);

        System.out.println();

        // Verify they are different objects
        System.out.println(s1 == s2);                 // false
        System.out.println(s1.address == s2.address); // false
    }
}