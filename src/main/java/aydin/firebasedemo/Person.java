package aydin.firebasedemo;

public class Person {
    private String name;
    private int age;
    private String phoneNumber;

    // No-argument constructor
    public Person() { }

    // Constructor with name, age, and phone number
    public Person(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
