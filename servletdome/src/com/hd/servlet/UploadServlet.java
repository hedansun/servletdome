package com.hd.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(request.getSession().getAttribute("percent")+"");
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
	
		String path= request.getSession().getServletContext().getRealPath("/")+"upload/";
		
		factory.setRepository(new File(path));
		
		factory.setSizeThreshold(1024*1024) ;
	
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setProgressListener(new ProgressListener(){
			public void update(long pBytesRead, long pContentLength, int pItems) {
				int percent = (int) (((float)pBytesRead / (float)pContentLength) * 100);
				session.setAttribute("percent", percent);
			}
		});
		try {
		
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
			for(FileItem item:list){
				
				String name = item.getFieldName();
				if(item.isFormField())
				{					
					
					String value = item.getString() ;
					request.setAttribute(name, value);
				}else{
					
					String value = item.getName() ;
					int start = value.lastIndexOf("\\");
					String filename = value.substring(start+1);
					request.setAttribute(name, filename);
					try {
						item.write( new File(path,filename) );
					} catch (Exception e) {
						e.printStackTrace();
					}
					//OutputStream out = new FileOutputStream(new File(path,filename));
					//InputStream in = item.getInputStream() ;
					//int length = 0 ;
					//byte [] buf = new byte[1024] ;
					
					//System.out.println(item.getSize());

					// in.read(buf) 
					//while( (length = in.read(buf) ) != -1)
					//{
						//out.write(buf, 0, length);
					//}
					//in.close();
					//out.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
