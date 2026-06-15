package cse213.demo;

public class GitHub {
    private String name;
    private int number;
    private String location, department;

    public GitHub(String name, int number, String location, String department) {
        this.name = name;
        this.number = number;
        this.location = location;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "GitHub{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", location='" + location + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
