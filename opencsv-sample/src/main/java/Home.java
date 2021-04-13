import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVWriter;

@WebServlet("/home")
public class Home extends HttpServlet {
	 
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			CSVManager csvManager = new CSVManager();
			
			String[] colList = {"name", "age", "birthday"};
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "홍길동");
			map.put("age", "17");
			map.put("birthday", "2020/01/24");
			
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list.add(map);
			
			File file = csvManager.writerCSV("test", list, colList);
			
			resp.setContentType("text/csv"); // mime type 설정.
			resp.setHeader("Content-Disposition", "attachment;filename=\""+ file.getName() +"\"");
			resp.setHeader("Conent-Transfer-Encoding", "binary");
			
			OutputStream out = resp.getOutputStream();
			FileInputStream fis = new FileInputStream(file);
			
			FileCopyUtils.copy(fis, out); // 파일 복사
			
			out.flush(); // 전송
			
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 
	 
}