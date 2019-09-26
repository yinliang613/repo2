package cn.suse.io;

import com.sun.corba.se.pept.encoding.OutputObject;

import java.io.*;
import java.util.ArrayList;

/*
    练习：序列化集合
        当我们想在文件中保存多个对象的时候
        可以把多个对象存储到一个集合中
        对集合进序列化和反序列化
    分析:
        1.定义一个存储Person对象的ArrayList集合
        2.往ArrayList集合中存储Person对象
        3.创建一个序列化流ObjectOutputStream对象
        4.使用ObjectOutputStream对象中的方法writeObject,对集合进行序列化
        5.创建一个反序列化ObjectInputStream对象
        6.使用ObjectInputStream对象中的方法readObject读取文件中保存的集合
        7.把Object类型的集合转换为ArrayList类型
        8.遍历ArrayList集合
        9.释放资源
 */

public class IO_Object {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Test2/object.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Test2/object.txt"));
        ArrayList<Persion> list = new ArrayList<>();
        list.add(new Persion("鹿晗",18));
        list.add(new Persion("吴亦凡",19));
        list.add(new Persion("蔡徐坤",20));
        oos.writeObject(list);
        Object obj =ois.readObject();
        ArrayList<Persion> al=(ArrayList<Persion>) obj;
        for (Persion p:al
             ) {
            System.out.println(p);

        }
    }
}
