package semantic;

/**
 * Created by BUSE on 2016/11/28.
 */
public class Employ extends Person {

    private String job;

    public Employ() {
    }

    public Employ(int age, String name, String job) {
        super(age, name);
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employ{age=" + getAge() +
                " name=" + getName() +
                " job=" + job +
                "}";
    }
}
