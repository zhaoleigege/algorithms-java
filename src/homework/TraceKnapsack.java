package homework;

import java.util.*;

/**
 * Created by BUSE on 2016/12/5.
 */
public class TraceKnapsack {

    public static void main(String[] args) {
        int n, size;
        List<Goods> lists = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入物品的个数: ");
        n = scanner.nextInt();

//        导入数据
        for (int i = 0; i < n; i++) {
            System.out.print("请输入第" + (i + 1) + "个物品的重量和价值: ");
            Goods goods = new Goods(scanner.nextInt(), scanner.nextInt(), (i + 1));
            goods.setV_w(goods.getValue() * 1.0 / goods.getWeight());
            lists.add(goods);
        }

        System.out.print("请输入背包能承载的重量: ");
        size = scanner.nextInt();

//        对背包中的数据进行价值重量比率排序
        Collections.sort(lists);

        CurrentKnapsack currentKnapsack = new CurrentKnapsack(0, size, n);

//                开始回溯算法
        backNap(currentKnapsack, lists, -1);

//        输出结果
        System.out.print("放入背包中的物品");
        for (int i = 0; i < n; i++)
            if (currentKnapsack.getSelected()[i])
                System.out.print(" " + lists.get(i).getOrder());
        System.out.println("\n总重量: " + currentKnapsack.getOccupancyWeight() + " 总价值: " + currentKnapsack.getValue());
    }

    //    递归方法
    public static void backNap(CurrentKnapsack currentKnapsack, List<Goods> lists, int number) {
        if (number == -1) {//如果为根节点，则从list的0索引开始递归
//            左边节点入背包
            backNap(currentKnapsack, lists, 0);

//            左边节点返回到根节点后判断右边节点的价值
            int totalValue = 0;
            for (int i = 1; i < lists.size(); i++)
                totalValue += lists.get(i).getValue();
            if (totalValue > currentKnapsack.getValue()) {
                CurrentKnapsack newCurrentKnapsack = currentKnapsack;
                currentKnapsack = new CurrentKnapsack(0, newCurrentKnapsack.getWeight(), lists.size());
                backNap(currentKnapsack, lists, 1);
//                如果右边节点的价值高于左边的价值则互换
                if (newCurrentKnapsack.getValue() > currentKnapsack.getValue())
                    currentKnapsack = newCurrentKnapsack;
            }
        } else if (number < lists.size()) {//当不是根节点的递归计算
            Goods goods = lists.get(number);
            if (currentKnapsack.getOccupancyWeight() + goods.getWeight() <= currentKnapsack.getWeight()) {
                currentKnapsack.setOccupancyWeight(currentKnapsack.getOccupancyWeight() + goods.getWeight());
                currentKnapsack.setValue(currentKnapsack.getValue() + goods.getValue());
                currentKnapsack.getSelected()[number] = true;

                backNap(currentKnapsack, lists, ++number);
            } else {
                int totalValue = 0;
                for (int i = number + 1; i < lists.size(); i++)
                    totalValue += lists.get(i).getValue();
                if (totalValue > currentKnapsack.getValue())
                    backNap(currentKnapsack, lists, ++number);
            }
        }
    }

}

class CurrentKnapsack {
    private int value;
    private int weight;
    private boolean[] selected;
    private int occupancyWeight;

    public CurrentKnapsack(int value, int weight, int length) {
        this.value = value;
        this.weight = weight;
        selected = new boolean[length];
        for (int i = 0; i < length; i++)
            selected[i] = false;
        occupancyWeight = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean[] getSelected() {
        return selected;
    }

    public void setSelected(boolean[] selected) {
        this.selected = selected;
    }

    public int getOccupancyWeight() {
        return occupancyWeight;
    }

    public void setOccupancyWeight(int occupancyWeight) {
        this.occupancyWeight = occupancyWeight;
    }

    @Override
    public String toString() {
        return "CurrentKnapsack{" +
                "value=" + value +
                ", weight=" + weight +
                ", selected=" + Arrays.toString(selected) +
                ", occupancyWeight=" + occupancyWeight +
                '}';
    }
}
