package club.ufdeen.DataDictExpolt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;



/**
 * 使用POI,进行Word相关的操作
 * @author  ufdeen
 * 
 */
public class WordExportUtil {

	private XWPFDocument document;


	public void setTemplate(String templatePath) {
		try {
			this.document = new XWPFDocument(POIXMLDocument.openPackage(templatePath));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save(String fileName) {
		File newFile = new File(fileName);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(newFile);
			this.document.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null){
					fos.flush();
				}
				if (fos != null){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 创建段落
	 * */
	public void createParagraph(String text) {
		XWPFParagraph paragraph = this.document.createParagraph();
		paragraph.createRun().setText(text);
	}
	
	
	/**
	 * 新建表格
	 * @param colTitle 表头列表
	 * @param data 表数据
	 */
	public void createTable(List<String> colTitle,String[][] data) {
		XmlCursor cursor = document.createParagraph().getCTP().newCursor();
		XWPFTable table = document.insertNewTbl(cursor);
		XWPFTableRow tableRowTitle = table.getRow(0);
		//构建列标题
		for(int i=0;i<colTitle.size();i++) {
			if(i==0) {
			//第一行第一列
				tableRowTitle.getCell(0).setText(colTitle.get(i));
			}else {
				tableRowTitle.addNewTableCell().setText(colTitle.get(i));
			}
		}
		for(int j=0;j<data.length; j++) {
			XWPFTableRow createRow = table.createRow();
			for(int k=0;k<data[j].length;k++) {
				createRow.getCell(k).setText(data[j][k]);
			}
		}
		
	}

	

}
