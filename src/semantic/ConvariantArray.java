package semantic;

/**
 * Created by BUSE on 2016/11/28.
 */
public class ConvariantArray {

    public static void main(String[] args) {
        Person person = new Student(20, "赵磊", "1401030122");
        System.out.println("学生信息为 " + person);

        person = new Employ(20, "赵磊", "学生");
        System.out.println("雇员信息为 " + person);

        //协变数组类型
        //这样子写可以编译通过，但是运行时会抱java.lang.ArrayStoreException的错误
        //这是因为Employ类型不能强制类型转换为Student类型
        //为什么编译时不抱错呢？因为数组的协变性质，即允许子类数组依然是父类数组的派生类
        //但是在不是数组时转换引用可以运行成功，而在数组转换引用时却错误，因为数组记得内部元素的具体类型，并且会在运行时做类型检查
        Person[] persons = new Student[5];
        persons[0] = new Employ();
    }

}
