package com.xyc.news.controller;

import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
public class UploadController {
	@PostMapping("/upload")
	public Wrapper<String> upload(@RequestParam("myfile") MultipartFile file, HttpServletRequest request) throws IOException {
		String reFilePath = "";
		if (!file.isEmpty()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
			String dateString = dateFormat.format(new Date());
			// 1.创建存上传图片的文件夹
			String childDir = "/upload/" + dateString + "/";
			String parentDir = System.getProperty("user.dir");
			System.out.println(parentDir);
			// 获取存放文件的路径
			String path = parentDir + childDir;
			System.out.println(path);
			File pathDir = new File(path);
			// 如果文件不存在
			if (!pathDir.exists()) {
				pathDir.mkdirs();
			}
			// 创建文件名
			String originName = file.getOriginalFilename();
			// 获取文件后缀
			String suffix = originName.substring(originName.lastIndexOf("."), originName.length());
			// 生成新的文件名
			String desName = UUID.randomUUID() + suffix;
			File desFile = new File(path, desName);
			file.transferTo(desFile);
			// 生成返回路径
			reFilePath = childDir + desName;
		}

		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, reFilePath);

	}

		/* public Wrapper<String> upload(@RequestParam("myfile") MultipartFile file, HttpServletRequest request) throws IOException {
		String reFilePath = "";
		if (!file.isEmpty()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
			String dateString = dateFormat.format(new Date());
			// 1.创建存上传图片的文件夹
			String childDir = "/upload/" + dateString + "/";
			String parentDir = System.getProperty("user.dir");
			System.out.println(parentDir);
			// 获取存放文件的路径
			String path = parentDir + childDir;
			System.out.println(path);
			File pathDir = new File(path);
			// 如果文件不存在
			if (!pathDir.exists()) {
				pathDir.mkdirs();
			}
			// 创建文件名
			String originName = file.getOriginalFilename();
			// 获取文件后缀
			String suffix = originName.substring(originName.lastIndexOf("."), originName.length());
			// 生成新的文件名
			String desName = UUID.randomUUID() + suffix;
			File desFile = new File(path, desName);
			file.transferTo(desFile);
			// 生成返回路径
			reFilePath = childDir + desName;
		}

		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, reFilePath);

	} */
}
