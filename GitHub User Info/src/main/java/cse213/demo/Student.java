package cse213.demo;

public class Student {
    private String name, major, email;
    private int id, age;

    public Student() {
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setId(int id) {
        this.id = id;
        return this;
    }

    public static void main(String[] args) {
        Student st = new Student().setName("Rhythm").setId(2431079);

        Student st1 = new Student();
        st1.setName("ABC");
        st1.setId(123);
    }
}
