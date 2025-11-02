package cse213.midterm;

public class Student {
    private int studentId, scholarshipRate;
    private boolean hasScholarship;

    public Student(int studentId, boolean hasScholarship, int scholarshipRate) {
        this.studentId = studentId;
        this.hasScholarship = hasScholarship;
        this.scholarshipRate = scholarshipRate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getScholarshipRate() {
        return scholarshipRate;
    }

    public void setScholarshipRate(int scholarshipRate) {
        this.scholarshipRate = scholarshipRate;
    }

    public boolean isHasScholarship() {
        return hasScholarship;
    }

    public void setHasScholarship(boolean hasScholarship) {
        this.hasScholarship = hasScholarship;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", scholarshipRate=" + scholarshipRate +
                ", hasScholarship=" + hasScholarship +
                '}';
    }
}
