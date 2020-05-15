package com.fabric.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fabric.common.CommonResult;
import com.fabric.dao.FileRepository;
import com.fabric.entity.File;
import com.fabric.pojo.Chaincode;
import com.fabric.service.CodeService;
import com.fabric.service.impl.ChainCodeService;

@Controller
@RequestMapping("/")
public class fileController {
	private static final Logger log = (Logger) LoggerFactory.getLogger(fileController.class);

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private ChainCodeService chainCodeService;
	
	@Autowired
	private CodeService codeService;
	
	@RequestMapping(value = "/upload", method = POST)
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {// 上传文件
																													
		// 未规定路径，默认保存在项目根路径下
		System.out.println(request.getSession().getAttribute("isLogin"));
		System.out.println(request.getSession().getAttribute("orgname"));
		String orgname = (String) request.getSession().getAttribute("orgname");
		if (request.getSession().getAttribute("isLogin") == null) {
			log.info("未登录状态，不可以上传！");
			return "redirect:login";
		}
		
		Chaincode code = codeService.queryChaincodeIsExist(orgname, "mychannel");
		if(code==null) {
			request.setAttribute("msg", "您还未安装链码！");
			return "fabricnetwork";
		}
		if((code.getIsInstantiated()).equals("false")) {
			request.setAttribute("msg", "您还未实例化链码！");
			return "fabricnetwork";
		}
		if (file.isEmpty()) {
			request.setAttribute("msg", "您还未选择上传文件!");
			return "fabricnetwork";
		}
		String name = file.getOriginalFilename();
		long size = file.getSize();
		// Date date = new Date();
		// Timestamp createTime = new Timestamp(date.getTime());
		// Calendar calendar = Calendar.getInstance();
		// DateTime createTime = new DateTime();
		// Timestamp createTime2 = new Timestamp();
		// long time1=20200227;
		// log.info(String.valueOf(createTime));
		File file1 = new File();
		// file1.setCreateTime(time1);
		file1.setName(name);
		int i = 1;
		//
		for (; i < 100; i++) {
			List<File> files = fileRepository.findById(i);
			if (files == null)
				break;
		}
		file1.setId(i);
		file1.setSize(size);
		fileRepository.save(file1);
		log.info(file.toString() + " 保存文件信息");
		String filepath = "/home/ycy/myprojects/repository/" + file.getOriginalFilename();
		String[] args = {orgname};
		String[] codeArgs = {name,orgname,filepath};
		CommonResult result = chainCodeService.invokeChaincode(orgname, args, "mychannel",  "addyifile", codeArgs);
		if(result.getCode()==200) {
			System.out.println("数据记录成功");
		}
		// String filepath=file.getOriginalFilename();
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filepath));
		outputStream.write(file.getBytes());
		outputStream.flush();
		outputStream.close();
		return "file";
	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public ResponseEntity download(@RequestBody String name) throws Exception {// 下载文件

		log.info("download");
		// String name=file1.getName()+"";
		//System.out.println("data!");
		System.out.println(name);
		//JSONObject object = JSON.parseObject(name);
		//System.out.println(object);
		//System.out.println(object.getString("name"));
		//Object object = json.get("name");
		log.info(name);
		FileSystemResource file = new FileSystemResource("/home/ycy/myprojects/repository/" + name);
		log.info(file.toString());
		// 根据情况换成本地仓库文件夹路径或者云路径
		HttpHeaders headers = new HttpHeaders();
		// 在响应头中添加值，设置下载文件默认名称
		headers.add("Content-Disposition", "attachment;filename=" + name);
		log.info(headers.toString());
		return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
	}

	@GetMapping(path = "/file")
	public String getAllFiles(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("isLogin") == null) {
			log.info("未登录状态，不可以查看文件列表和下载文件！");
			return "redirect:login";
		}
		Iterable<File> files = fileRepository.findAll();
		model.addAttribute("index", files);
		return "file";
	}

	@GetMapping(path = "/delete")
	public String deletefile(@RequestBody String name) {
		log.info(name);
		return "file";
	}
}
