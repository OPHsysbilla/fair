package com.mblog.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteHtml {
    /**
     * DOC 读取信息.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void read() throws FileNotFoundException, IOException {
        File file = new File("E:\\a.txt");// 指定要读取的文件
        // 获得该文件的缓冲输入流
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = "";// 用来保存每次读取一行的内容
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();// 关闭输入流
    }

    /**
     * DOC 写入信息.
     * 
     * @throws IOException
     */
    public void write(String filename,String data) throws IOException {
        File file = new File(filename);// 指定要写入的文件
        if (!file.exists()) {// 如果文件不存在则创建
            file.createNewFile();
        }
        // 获取该文件的缓冲输出流
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        // 写入信息
        bufferedWriter.write(data);
        bufferedWriter.newLine();// 表示换行
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流
    }

}
