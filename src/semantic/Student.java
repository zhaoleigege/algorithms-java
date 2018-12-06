package semantic;

/**
 * Created by BUSE on 2016/11/28.
 */
public class Student extends Person {

    private String number;

    public Student() {
    }

    public Student(int age, String name, String number) {
        super(age, name);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Employ{age=" + getAge() +
                " name=" + getName() +
                " number=" + number +
                "}";
    }
}
