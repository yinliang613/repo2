package cn.susu.filtration;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class TestFileFiltration {
    public static void main(String[] args) {
        serch(new File("E:\\learn\\2019最新黑马JAVAEE 57期基础班+就业班实战开发视频教程"),0);

    }
    public static void serch(File f,int i){
        if(f.isFile()){
            for (int j=0;j<i;j++){
                System.out.print("-");
            }
            System.out.println(f.getAbsoluteFile());
        }else{
            //使用匿名内部类构建过滤器
            File[] l = f.listFiles(new FileFilter(){

                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory()||pathname.getName().toLowerCase().endsWith(".java");
                }
            });
            for (File f1:l) {
                serch(f1,i+1);
            }
        }
    }
}
