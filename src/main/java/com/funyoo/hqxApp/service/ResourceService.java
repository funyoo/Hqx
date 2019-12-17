package com.funyoo.hqxApp.service;

import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ResourceService {



    /**
     * 保存字节流至文件
     * @param filePath 文件
     * @param bytes   字节流
     * @return        文件全路径
     */
    public String saveFileByBytes(String filePath, byte[] bytes) {
        File targetFile = new File(filePath);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFile);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return filePath;
    }

    /**
     * 删除指定URL的文件
     * @param filePath 文件路径
     * @return
     */
    public boolean removeFile(String filePath) {
        File targetFile = new File((filePath));
        if (targetFile.exists()) {
            targetFile.delete();
            return true;
        }
        return false;
    }
}
