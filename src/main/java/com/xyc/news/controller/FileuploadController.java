package com.xyc.news.controller;

import cn.hutool.core.io.FileUtil;

import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.pojo.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author xuyuyu
 * @ClassName UploadController
 * @Description TODO
 * 2022/4/10  23:58
 */
@CrossOrigin
@RestController
public class FileuploadController {

    @Value("${upload.path}")
    private String parentDir;
    @PostMapping("/uploadFile")
    public Wrapper<FileInfo> upload(@RequestParam("myfile") MultipartFile file, HttpServletRequest request) throws IOException {
        String reFilePath = "";
        FileInfo fileInfo =new FileInfo();
        if(!file.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
            String dateString = dateFormat.format(new Date());
            //1.创建存上传图片的文件夹
            String childDir = "/upload/resource/"+dateString+"/";
//            String parentDir = System.getProperty("user.dir");
            //获取存放文件的路径
            String path = parentDir+childDir;
            System.out.println(path);
            File pathDir = new File(path);
            //如果文件不存在
            if(!pathDir.exists()){
                pathDir.mkdirs();
            }
            //创建文件名
            String originName =  file.getOriginalFilename();
            //获取文件后缀
            String suffix= originName.substring(originName.lastIndexOf("."),originName.length());
            //生成新的文件名
            String desName = UUID.randomUUID().toString().replaceAll("-", "")+suffix;
            fileInfo.setOldName(originName);
            fileInfo.setNewName(desName);
            double fileSizeKb = file.getSize()/1024;
            fileInfo.setSize(fileSizeKb);
            fileInfo.setResourceType(file.getContentType());
            fileInfo.setSuffix(suffix);
            File desFile = new File(path,desName);
            file.transferTo(desFile);
            //生成返回路径
            reFilePath = childDir+desName;
            fileInfo.setUrl(reFilePath);

        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,fileInfo);

    }
    /**
     * 下载文件
     */
    @GetMapping("/download")
    public void downloadFile(@RequestParam String url, HttpServletResponse response) throws Exception {
        //将文件以流的形式一次性读取到内存，通过响应输出流输出到前端
        String filePath = parentDir+url;
        File file = new File(filePath);
        // 判断下载的文件是否存在
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        String filename = file.getName();
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = null;
        // 数组是一个字节数组,也就是文件的字节流数组
        try {
            response.setCharacterEncoding("UTF-8");
             //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
             //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new Exception("下载失败");
        }
    }
}
