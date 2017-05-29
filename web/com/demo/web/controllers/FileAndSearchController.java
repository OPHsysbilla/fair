package com.demo.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bean.Blog;
import bean.User;
import operation.Search;

@Controller 
public class FileAndSearchController { 
	@RequestMapping(value="/search")
    public String search(Model model, 
    		@RequestParam(value="str",required=false) String str){
//		请问如果一篇都没有怎么办
        Blog blogsBylabel[] = Search.searchBlogByLabel(str);
        Blog blogsByTitle[] = Search.searchBlogByTitle(str);
        Blog blogsByUser[] = Search.searchBlogByUsername(str);
        model.addAttribute("blogsBylabel",blogsBylabel);
        model.addAttribute("blogsByTitle",blogsByTitle);
        model.addAttribute("blogsByUser",blogsByUser);
        model.addAttribute("str",str);
        return "SearchResult";
    } 
	@RequestMapping("/download")
	public ResponseEntity<byte[]> downloadFile(
			@RequestParam("filePath") String filePath,
			HttpServletRequest request) throws IOException
	{
		System.out.println(filePath);
		File file = new File(filePath);
		HttpHeaders headers = new HttpHeaders();
		String fileName = "";
		try {
			fileName = new String(filePath.getBytes("UTF-8"), "iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="/uploadfile/{userid}", method = RequestMethod.GET)
	public String filUpload(@PathVariable("userid")Integer userid) {
		
		return "upload_file";
	}
	@RequestMapping(value="/file/{userid}", method = RequestMethod.GET)
	public String fileShow(Model model,@PathVariable("userid")Integer userid) {
		User user = User.getUserByID(userid);
		File dir = new File(user.getFilesDir());
		ArrayList<String> listFilePaths = new ArrayList<String>();
		for(File tmpfile: dir.listFiles())
		{
//			System.out.println(tmpfile.getName());
//			listFilePaths.add(tmpfile.getAbsolutePath());
			listFilePaths.add(user.getFilesDir() + "/" + tmpfile.getName());
		}
		String[] paths = (String[])listFilePaths.toArray(new String[listFilePaths.size()]);
		String[] pathnames = new String[paths.length];
		for(int i=0;i<paths.length;i++)
		{
//			path = path.replace('\\', '/');
			pathnames[i] = paths[i].substring(paths[i].lastIndexOf("/")+1);
			System.out.println(pathnames[i]); 
		}
		model.addAttribute("pathnames", pathnames);  
		model.addAttribute("paths", paths);  
		return "fileshow";
	}
	
	@RequestMapping(value="/filepost/{userid}", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(
			@RequestParam("file") MultipartFile[] files, 
			@PathVariable("userid")Integer userid,
			HttpServletRequest request,
			HttpServletResponse response)
	{
		User user = User.getUserByID(userid);
		int selectFileNum = 0; //选择的文件的个数
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].getSize() > 0)
			{
				selectFileNum ++;
			}
		}
		if(selectFileNum == 0)
		{
			System.out.println("没有选择任何文件");    //说明没有选择任何文件
			return new ModelAndView("error");
		}
		
		for(MultipartFile item : files)
		{
			if(item.getSize() > 0)
			{
				String fileName = item.getOriginalFilename();
				fileName = fileName.replace(" ", "");
				File file = new File(user.getFilesDir() + "/" + fileName);
				try {
					item.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		/*
		ArrayList<String> listFilePaths = new ArrayList<String>();
		File dir = new File(user.getFilesDir());
		for(File tmpfile: dir.listFiles())
		{
//			System.out.println(tmpfile.getName());
//			listFilePaths.add(tmpfile.getAbsolutePath());
			listFilePaths.add(user.getFilesDir() + "/" + tmpfile.getName());
		}
		String[] paths = (String[])listFilePaths.toArray(new String[listFilePaths.size()]);
//		String[] pathsLeft = new String[paths.length];
		for(String path: paths)
		{
//			path = path.replace('\\', '/');
			System.out.println(path); 
		}
		request.setAttribute("paths", paths);
		*/
		return new ModelAndView("redirect:/file/"+userid);
	}
}
