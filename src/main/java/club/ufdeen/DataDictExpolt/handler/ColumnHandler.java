package club.ufdeen.DataDictExpolt.handler;

import java.util.List;

import club.ufdeen.DataDictExpolt.tableModel.ColumnsModel;

public interface ColumnHandler {

	public List<ColumnsModel> getColumn(String tableName);
}
