package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by BUSE on 2016/11/21.
 */
public class Knapsack {

    public static void main(String[] args) {

        int number, capacity;
        double values = 0.0;
        List<Goods> lists = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入物品数量: ");
        number = scanner.nextInt();
        System.out.print("输入背包的最大承载重量: ");
        capacity = scanner.nextInt();
        for (int i = 0; i < number; i++) {
            Goods goods = new Goods();
            goods.setOrder(i + 1);
            System.out.printf("输入第%d个物品的重量和价值: ", i + 1);
            goods.setWeight(scanner.nextInt());
            goods.setValue(scanner.nextInt());
            goods.setV_w(goods.getValue() * 1.0 / goods.getWeight());
            lists.add(goods);
        }

        Collections.sort(lists);

        System.out.println("求解步骤");
        System.out.print("物品单位重量的价值: {");
        for (int i = 0; i < number; i++)
            System.out.print(lists.get(i).getV_w() + ", ");
        System.out.println("}");

        System.out.println("排序优先队列: ");
        for (int i = 0; i < number; i++)
            System.out.print(lists.get(i).getOrder() + " ");
        System.out.println();

        for (int i = 0; i < number; i++) {
            if (capacity > 0) {

                Goods goods = lists.get(i);

                if (goods.getWeight() <= capacity) {
                    capacity -= goods.getWeight();
                    values += goods.getValue();
                    goods.setPercent(100.00);
                } else {
                    double percent = capacity * 1.0 / goods.getWeight();
                    values += percent * goods.getValue();
                    goods.setPercent(percent * 100);
                    capacity = 0;
                }

                System.out.printf("将%d个物品的%.2f放入背包中，背包中剩余%d，背包中物品的价值为%.2f\n", goods.getOrder(), goods.getPercent(), capacity, values);
            } else {
                break;
            }
        }
    }

}

class Goods implements Comparable<Goods> {
    private int weight;
    private int value;
    private double v_w;
    private int order;
    private double percent;

    public Goods() {

    }

    public Goods(int weight, int value, int order) {
        this.weight = weight;
        this.value = value;
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getV_w() {
        return v_w;
    }

    public void setV_w(double v_w) {
        this.v_w = v_w;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public int compareTo(Goods o) {
        if (this.v_w - o.v_w > 0)
            return -1;
        if (this.v_w - o.v_w < 0)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "weight=" + weight +
                ", value=" + value +
                ", v_w=" + v_w +
                ", order=" + order +
                ", percent=" + percent +
                '}';
    }
}



