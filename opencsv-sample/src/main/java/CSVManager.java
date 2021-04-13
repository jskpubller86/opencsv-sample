import java.io.File;
import java.io.FileWriter;
import java.util.*;
import au.com.bytecode.opencsv.CSVWriter;


public class CSVManager {
	
	public File writerCSV(String fileName, List<Map<String, String>> list, String[] colList){
		File file = new File(fileName); // file 생성자
		FileWriter fileWriter = null;
		CSVWriter writer = null;
		
		try {
			file.createNewFile(); // file 생성
			fileWriter = new FileWriter(file); // file에 데이터를 작성하기 위한 fileWriter 생성
			writer = new CSVWriter(fileWriter); // csvWriter 생성
			String[] data = new String[colList.length];
			
			writer.writeNext(colList); // 첫 행 데이터  buffer에 추가
			
			for(Map<String, String> map : list){
				for(int i=0; i < colList.length; i++){
					data[i] = map.get(colList[i]);
				}
				
				writer.writeNext(data); // 데이터  buffer에 추가
			}
			
			writer.flush(); // buffer에 있는 데이터를 실제 파일에 작성.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer != null) writer.close();
				if(fileWriter != null) fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return file;
	}
}
