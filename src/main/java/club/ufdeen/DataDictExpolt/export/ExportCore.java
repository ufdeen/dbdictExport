package club.ufdeen.DataDictExpolt.export;

import java.util.ArrayList;
import java.util.List;


import club.ufdeen.DataDictExpolt.handler.ColumnHandler;
import club.ufdeen.DataDictExpolt.handler.TableHandler;
import club.ufdeen.DataDictExpolt.handler.oracle.OracleColumnResultSetHandler;
import club.ufdeen.DataDictExpolt.handler.oracle.OracleTableResultSetHandler;
import club.ufdeen.DataDictExpolt.tableModel.ColumnsModel;
import club.ufdeen.DataDictExpolt.tableModel.TableModel;
import club.ufdeen.DataDictExpolt.util.WordExportUtil;

public class ExportCore {
	
	public List<TableModel> getTableInfo(TableHandler th,ColumnHandler ch) {
		List<TableModel> allTable = th.getAllTable();
		for( TableModel tm : allTable ) {
			tm.setColumns(ch.getColumn(tm.getTableName()));
		}
		return allTable;
	}
	
	
	public void oracleExportWord() {
		List<TableModel> list = this.getTableInfo(new OracleTableResultSetHandler(),new OracleColumnResultSetHandler());
		
		WordExportUtil we = new WordExportUtil();
		String fileName = System.getProperty("user.dir")+"/WordTemp.docx";
		//String fileName = WordExportUtil.class.getResource("/WordTemp.docx").getFile();
		we.setTemplate(fileName);
		
		//封装实体详细数据
		List<String> colTitle = new ArrayList<String>();
		colTitle.add("序号");
		colTitle.add("编码");
		colTitle.add("名称");
		
		//清单列表数据
		String[][] data = new String[list.size()][colTitle.size()];
		for(int i=0; i<list.size(); i++) {
			data[i][0] = String.valueOf(i+1);
			data[i][1] = list.get(i).getTableName();
			data[i][2] = list.get(i).getComment();
		}
		
		we.createParagraph("实体列表");
		
		we.createTable(colTitle, data);
		
		we.createParagraph("------------------------");
		we.createParagraph("实体清单");
		
		//实体列表
		
		List<String> colTitleCol = new ArrayList<String>();
		colTitleCol.add("序号");
		colTitleCol.add("编码");
		colTitleCol.add("名称");
		colTitleCol.add("类型");
		colTitleCol.add("是否为空");
		for(TableModel tm : list) {
			//写入表名
			we.createParagraph(tm.getTableName());
			
			List<ColumnsModel> columns = tm.getColumns();
			String[][] colData = new String[columns.size()][colTitleCol.size()];
			for(int i=0; i<columns.size(); i++) {
				colData[i][0] = String.valueOf(i+1);
				colData[i][1] = columns.get(i).getColumnname();
				colData[i][2] = columns.get(i).getColComment();
				colData[i][3] = columns.get(i).getColType();
				colData[i][4] = columns.get(i).getColisNull();
			}
			we.createTable(colTitleCol, colData);
			
		}
		
		we.save(System.getProperty("user.dir") + "/dict_out.docx");
		System.out.println("导出成功");

	}
	
	
	public static void main(String[] args) {
		
	}
}
