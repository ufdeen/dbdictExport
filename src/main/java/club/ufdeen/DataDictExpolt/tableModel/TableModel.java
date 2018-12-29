package club.ufdeen.DataDictExpolt.tableModel;

import java.util.List;

public class TableModel {

	private String tableName;
	private String comment;
	
	private List<ColumnsModel> columns;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<ColumnsModel> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnsModel> columns) {
		this.columns = columns;
	}
	
	
	
}
