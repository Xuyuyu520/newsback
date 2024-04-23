package com.xyc.news.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.news.common.Page;
import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.pojo.Category;
import com.xyc.news.service. CategoryService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author xuyuyu
 * @ClassName CategoryController
 * @Description TODO
 */
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	 CategoryService categoryService;

	@GetMapping("/getCategoryList")
	public Wrapper<PageInfo<Category>> getCategoryList(Page page, Category category) {
		PageHelper.startPage((int) page.getCurrent(), (int) page.getSize());
		List<Category> categoryList = categoryService.getCategoryList(category);
		PageInfo<Category> categoryPageInfo = new PageInfo<>(categoryList);
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, categoryPageInfo);
	}

	@GetMapping("/getAllCategory")
	public Wrapper<List<Category>> getAllCategory() {
		List<Category> categoryList = categoryService.getCategoryList(new Category());
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, categoryList);
	}

	@PostMapping("/categorySave")
	public Wrapper<Boolean> categorySave(@RequestBody Category category) {
		Boolean saveResult = categoryService.categorySave(category);
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, saveResult);
	}

	@PatchMapping("/categoryUpdate")
	public Wrapper<Boolean> categoryUpdate(@RequestBody Category category, HttpServletResponse response) {
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, categoryService.categoryUpdate(category));
	}

	@DeleteMapping("/categoryDelete")
	public Wrapper<Boolean> categoryDelete(Category category) {
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, categoryService.categoryDelete(category));
	}

	@GetMapping("/exportOut")
	public void exportOut(Category category, HttpServletResponse response) throws IOException {
		List<Category> categoryList = categoryService.getCategoryList(category);
		// 在内存操作写到浏览器
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.merge(0, 0, 0, 2, "图书类型信息", true);
		writer.passCurrentRow();
		// 自定义标题别名
		writer.addHeaderAlias("typeId", "图书类型id");
		writer.addHeaderAlias("ename", "英文名");
		writer.addHeaderAlias("cname", "中文名");
		// 一次性写出list对象到Excel使用默认样式，强制输出标题
		writer.write(categoryList, true);
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		out.close();
		writer.close();
	}

	@GetMapping("/downLoadTemplate")
	public void downLoadTemplate(HttpServletResponse response) throws IOException {
		// 在内存操作写到浏览器
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.merge(0, 0, 0, 2, "图书类型", true);
		Row row1 = writer.getOrCreateRow(1);
		Cell cell11 = row1.createCell(0);
		cell11.setCellValue("图书类型id");
		Cell cell12 = row1.createCell(1);
		cell12.setCellValue("英文名");
		Cell cell13 = row1.createCell(2);
		cell13.setCellValue("中文名");
		// 设置浏览器响应的格式
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		out.close();
		writer.close();
	}

	@PostMapping("/exportIn")
	public Wrapper<String> exportIn(@RequestParam("myfile") MultipartFile file, HttpServletRequest request)
			throws IOException {
		// 1.excel数据的读取
		InputStream inputStream = file.getInputStream();
		ExcelReader excelReader = ExcelUtil.getReader(inputStream);
		// 从excel的第几行开始读取
		List<List<Object>> list = excelReader.read(2);
		if (list.size() < 1) {
			return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, "excel中没有数据要添加");
		}
		// 2.excel数据的校验
		StringJoiner joiner = new StringJoiner("/n");
		for (int i = 0; i < list.size(); i++) {
			int typeId = 0;
			List<Object> row = list.get(i);
			try {
				typeId = Integer.parseInt(row.get(0).toString());

			} catch (Exception e) {
				joiner.add("第" + (i + 1) + "行第1列格式有误");
			}
			int count = categoryService.getTypeById(typeId);
			if (count > 0) {
				joiner.add("第" + (i + 1) + "行第1列的typeId在数据库表中已经存在");
			}
		}
		String s = joiner.toString();
		if (s != null && s.trim().length() > 0) {
			return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, s);
		}
		// 3.数据的封装以及数据插入数据库
		List<Category> categoryList = new ArrayList<Category>();
		for (int i = 0; i < list.size(); i++) {
			List<Object> row = list.get(i);
			Category category = new Category();
			category.setTypeId(Integer.parseInt(row.get(0).toString()));
			category.setEname(row.get(1).toString());
			category.setCname(row.get(2).toString());
			categoryList.add(category);
		}
		if (categoryService.addBach(categoryList)) {
			return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, "添加成功");
		} else {
			return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, "添加失败");
		}
	}
}
