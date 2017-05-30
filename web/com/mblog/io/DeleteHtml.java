package com.mblog.io;

import java.io.File;

public class DeleteHtml {
	public void delete(String filename){
		try{
            File file = new File(filename);
            if(file.delete()){
                System.out.println(file.getName() + " 文件已被删除！");
            }else{
                System.out.println("文件删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
}
