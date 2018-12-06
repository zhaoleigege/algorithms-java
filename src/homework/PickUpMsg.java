package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by BUSE on 2016/12/23.
 */
public class PickUpMsg {

    public static void main(String[] args) {
        String path = args[0];
        Scanner scanner = null;
        String string = null;
        Map<String, String> messages = new HashMap<>();
        try {
            scanner = new Scanner(new FileInputStream(new File(path)));

            while (scanner.hasNext()) {
                string = scanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(string, ":");
                String key = null;
                String value = null;
                if (tokenizer.hasMoreElements())
                    key = tokenizer.nextToken().trim();
                if (tokenizer.hasMoreElements()) {
                    value = tokenizer.nextToken().trim();
                } else
                    value = "";
                messages.put(key, value);
            }
        } catch (FileNotFoundException e) {
            System.err.println("文件不存在");
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }

        scanner = new Scanner(System.in);
        System.out.println("输入要读取的信息（exit退出）");
        while ((string = scanner.nextLine()) != null) {
            if (string.equals("exit"))
                break;
            if ((string = messages.get(string.trim())) != null)
                System.out.println(string);
            else
                System.out.println("不存在这个信息");

            System.out.println("输入要读取的信息（exit退出）");
        }
    }

}
