package com.xyc.news.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.news.common.Page;
import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.pojo.Article;
import com.xyc.news.service.ArticleService;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * 文章控制器* @author xuyuyu
 * @ClassName CategoryController
 * @Description TODO
 * @date 2024-04-23 10:08:17
 */
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@GetMapping("/getArticleList")
	public Wrapper<PageInfo<Article>> getArticleList(Page page, Article article) {
		PageHelper.startPage((int) page.getCurrent(), (int) page.getSize());
		List<Article> articleList = articleService.getArticleList(article);
		PageInfo<Article> articlePageInfo = new PageInfo<>(articleList);
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, articlePageInfo);
	}

	@PostMapping("/articleSave")
	public Wrapper<Boolean> articleSave(@RequestBody Article article) {
		Boolean articleResult = articleService.articleSave(article);
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, articleResult);
	}

	@PostMapping("/articleUpdate")
	public Wrapper<Boolean> articleUpdate(@RequestBody Article article) {
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, articleService.articleUpdate(article));
	}

	@GetMapping("/articleDelete")
	public Wrapper<Boolean> articleDelete(Article article) {
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, articleService.articleDelete(article));
	}

	@GetMapping("/downLoadTemplate")
	public void downLoadTemplate(HttpServletResponse response) throws IOException {
		// 创建 ExcelWriter 对象
		ExcelWriter writer = ExcelUtil.getWriter(true);

		// 添加表头
		writer.merge(0, 0, 0, 7, "文章字段", true);

		// 逐行添加字段信息
		writer.getOrCreateRow(1).createCell(0).setCellValue("文章ID");
		writer.getOrCreateRow(1).createCell(1).setCellValue("文章名称");
		writer.getOrCreateRow(1).createCell(2).setCellValue("文章链接");
		writer.getOrCreateRow(1).createCell(3).setCellValue("作者");
		writer.getOrCreateRow(1).createCell(4).setCellValue("类型");
		writer.getOrCreateRow(1).createCell(5).setCellValue("类型名称");
		writer.getOrCreateRow(1).createCell(6).setCellValue("价格");
		writer.getOrCreateRow(1).createCell(7).setCellValue("数量");

		// 写出到浏览器
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=article_template.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		writer.close();
	}
	@PostMapping("/exportIn")
	public Wrapper<String> exportIn(@RequestParam("myfile") MultipartFile file, HttpServletRequest request)
			throws IOException {

		// 1. excel数据的读取
		InputStream inputStream = file.getInputStream();
		ExcelReader excelReader = ExcelUtil.getReader(inputStream);
		// 从excel的第几行开始读取
		List<List<Object>> list = excelReader.read(3); // 从第三行开始读取数据
		if (list.isEmpty()) {
			return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, "Excel中没有数据要导入");
		}

		// 2. excel数据的校验
		StringBuilder errorMessages = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			List<Object> row = list.get(i);
			// 检查每一列数据是否为空
			if (row.size() < 8) {
				errorMessages.append("第").append(i + 3).append("行数据缺失\n");
				continue;
			}
			try {
				Article article = new Article();
				article.setArticleId(row.get(0).toString());
				article.setArticleName(row.get(1).toString());
				article.setArticleUrl(row.get(2).toString());
				article.setAuthor(row.get(3).toString());
				article.setType(Integer.parseInt(row.get(4).toString()));
				article.setTypeName(row.get(5).toString());
				article.setPrice(new BigDecimal(row.get(6).toString()));
				article.setCount(Integer.parseInt(row.get(7).toString()));

				// 3. 数据的插入数据库
				if (!articleService.articleSave(article)) {
					errorMessages.append("第").append(i + 3).append("行数据插入失败\n");
				}
			} catch (Exception e) {
				errorMessages.append("第").append(i + 3).append("行数据格式错误\n");
			}
		}

		if (errorMessages.length() > 0) {
			return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, errorMessages.toString());
		} else {
			return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, "导入成功");
		}
	}

	@GetMapping("/exportOut")
	public void exportOut(Article article, HttpServletResponse response) throws IOException {
		// 查询文章数据（假设有一个 ArticleService）
		List<Article> articles = articleService.getArticleList(article);

		// 创建 ExcelWriter 对象
		ExcelWriter writer = ExcelUtil.getWriter(true);

		// 添加表头
		writer.merge(0, 0, 0, 7, "文章字段", true);

		// 添加标题行
		Row titleRow = writer.getOrCreateRow(1);
		titleRow.createCell(0).setCellValue("文章编号");
		titleRow.createCell(1).setCellValue("文章名称");
		titleRow.createCell(2).setCellValue("文章链接");
		titleRow.createCell(3).setCellValue("作者");
		titleRow.createCell(4).setCellValue("类型");
		titleRow.createCell(5).setCellValue("类型名称");
		titleRow.createCell(6).setCellValue("价格");
		titleRow.createCell(7).setCellValue("数量");

		// 逐行写入文章数据
		for (int i = 0; i < articles.size(); i++) {
			Article a = articles.get(i);
			Row row = writer.getOrCreateRow(i + 2); // 第一行是表头，所以数据行从第二行开始
			row.createCell(0).setCellValue(a.getArticleId());
			row.createCell(1).setCellValue(a.getArticleName());
			row.createCell(2).setCellValue(a.getArticleUrl());
			row.createCell(3).setCellValue(a.getAuthor());
			row.createCell(4).setCellValue(a.getType());
			row.createCell(5).setCellValue(a.getTypeName());
			row.createCell(6).setCellValue(a.getPrice().doubleValue());
			row.createCell(7).setCellValue(a.getCount());
		}

		// 写出到浏览器
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=articles.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		writer.close();
	}

}
