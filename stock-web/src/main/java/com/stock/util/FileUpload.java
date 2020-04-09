package com.stock.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

/**
 * @Description: 文件上传
 * @Author: weiguodong
 * @Create:2019/11/15 16:50
 */
public class FileUpload {

    public static void fileUpload(String path, String fileNameString, CommonsMultipartFile file) throws IOException {
        File reaPath = new File(path);
        if (!reaPath.exists()) {
            reaPath.mkdirs();
        }
        File file1 = new File(path,fileNameString); //新建一个文件

        byte[] b = file.getBytes();//将上传的文件写入新建的文件中
        FileOutputStream fos = new FileOutputStream(file1);
        fos.write(b);
        fos.flush();
        fos.close();
    }

    public static Boolean checkPic(String typename){
        Boolean flag = false;
        if("jpg".equalsIgnoreCase(typename)||"gif".equalsIgnoreCase(typename)||"png".equalsIgnoreCase(typename) || "bmp".equalsIgnoreCase(typename) ||"jpeg".equalsIgnoreCase(typename)){
            flag= true;
        }
        return flag;
    }
}
