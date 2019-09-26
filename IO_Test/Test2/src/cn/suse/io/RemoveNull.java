
package cn.suse.io;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 尹亮
 */

public class RemoveNull {
    private static List<File> list = new ArrayList<File>();
    public static void visitAllFile(File file){
        File[] files = file.listFiles();

        if (files  != null ){
            for (File file1 : files) {
                list.add(file1);
                //System.out.println("name"+file1.getPath());
                if (file1.isDirectory()){
                    visitAllFile(file1);
                }
            }
        }
    }

    public static void removeNullDirctory(File file){
        //File file = new File("E:\\Desktop\\PanDownload_v2.1.2");
        visitAllFile(file);
        for (File file1 : list) {
            if(file1.isDirectory() && file1.listFiles().length<=0){
                System.out.println("删除文件夹为"+file1.getPath());
                file1.delete();
            }
        }
    }
    public static void main(String[] args) {
        //指定文件夹，删除其中的空目录
        File file = new File("E:\\Desktop\\新建文件夹 (2)");
        RemoveNull.removeNullDirctory(file);
    }
}
