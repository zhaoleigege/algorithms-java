package homework;

import java.io.*;

/**
 * Created by BUSE on 2016/12/23.
 */
public class CP {

    public static void main(String[] args) throws IOException {
        String srcPath = args[0];
        String targetPath = null;

        if (srcPath == null) {
            System.err.println("请输入文件源");
            System.exit(1);
        }

        try {
            targetPath = args[1];
        } catch (ArrayIndexOutOfBoundsException exception) {
            int index = srcPath.lastIndexOf("/") + 1;
            targetPath = srcPath.substring(0, index) + "copy" + srcPath.substring(index);
        }

        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(new File(srcPath)));
            outputStream = new BufferedOutputStream(new FileOutputStream(new File(targetPath), true));

            byte[] bytes = new byte[1024];
            int hasRead = -1;

            while ((hasRead = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, hasRead);
            }
        } catch (FileNotFoundException e) {
            System.err.println("文件不存在");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("文件读取权限不够");
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                inputStream.close();
            if (outputStream != null)
                outputStream.close();
        }
    }

}
