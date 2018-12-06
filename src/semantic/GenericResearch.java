package semantic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by BUSE on 2016/11/29.
 */
public class GenericResearch {

    public static void main(String[] args) {
        Shape[] shapes = new Square[5];
        for (int i = 0; i < 5; i++)
            shapes[i] = new Square(i, i, 2 * i);
        System.out.println("面积和为: " + totalArea(shapes));//因为数组协变性质的原因这个调用可以成功

        Collection<Shape> shapeCollection = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            shapeCollection.add(new Square(i, i, 2 * i));
        //因为添加的是Collection的泛型限制是Shape，所以添加Square可以成功，并且调用Collection的重载方法可以成功
        System.out.println("面积和为: " + totalArea(shapeCollection));

        Collection<Square> squareCollection = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            squareCollection.add(new Square(i, i, 2 * i));
        //当方法签名为public static double totalArea(Collection<Shape> arr)
        //直接调用totalArea(squareCollection)
        //这样调用会出编译错误，因为泛型集合不具有协变性质，
        // 即Collection<Square> IS-NOT-A Collection<Shape>，即使Square IS-A Shape
        //改写方法签名为public static double totalArea(Collection<? extends Shape> arr)
        System.out.println("面积和为: " + totalArea(squareCollection));

        System.out.println("面积最大的半径为 " + findMax(shapes));

        //这句可以，因为Square父类才实现了Comparable接口
        Comparable<Shape> shapeComparable = new Square();
        //这句不行，因为泛型限定了唯一使用的类型Comparable<Square> shapeComparable = new Square();
        //虽然Square继承至Shape但是Square并没有实现Comparable接口
        //如果Comparable<Square> squareComparable = new Square();可行，
        // 那么对于另外一个继承至Shape的类Triangle来说考虑这样一种情况
//        Comparable<Square> squareComparable = new Square();
//        Comparable<Shape> shapeComparable1 = squareComparable;
//        shapeComparable1.compareTo(new Triangle());
        //这样squareComparable相当于在和Triangle比较大小了，但这是泛型所要避免的，
        //因为Square的compareTo方法只允许同样一个类的实例或者类的子类的实例来调用这个方法

    }

    public static double totalArea(Shape[] arr) {
        double area = 0;

        for (Shape shape : arr)
            area += shape.area();

        return area;
    }

    public static double totalArea(Collection<? extends Shape> arr) {
        double area = 0;

        for (Shape shape : arr)
            area += shape.area();

        return area;
    }

    public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType[] arr) {
        //因为要调用compareTo方法，所以传递进来的类型必须实现了Comparable接口
        //对于AnyType来说，假设它父类实现了Comparable接口那么AnyType对象 IS-A Comparable<父类>
        //但是 IS-NOT-A Comparable<AnyType>,所以为了使得AnyType对象可以调用此方法
        //把Comparable的泛型改为通配符并且通配符的类型界限下限为AnyType类型，包括其父类知道Object。
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i].compareTo(arr[maxIndex]) > 0)
                maxIndex = i;

        return arr[maxIndex];
    }

}
