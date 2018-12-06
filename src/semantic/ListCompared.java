package semantic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by BUSE on 2016/11/29.
 */
public class ListCompared {

    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
//        List<Integer> list = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 400_0000; i++) {
            list.add((int) (1_000_000 * Math.random()));
        }

        //  removeEvenVer(list);

        for (int i = 0; i < list.size(); )
            if (++i % 1000 == 0)
                System.out.println(i);

        long timeEnd = System.currentTimeMillis();

        System.out.println("用时 -> " + (timeEnd - timeStart));
    }

    public static void removeEvenVer(List<Integer> list) {
        Iterator<Integer> integerIterator = list.iterator();

        while (integerIterator.hasNext())
            if (integerIterator.next() % 2 == 0)
                integerIterator.remove();
    }
}
