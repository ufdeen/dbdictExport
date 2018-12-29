package club.ufdeen.DataDictExpolt.handler.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import club.ufdeen.DataDictExpolt.handler.ColumnHandler;
import club.ufdeen.DataDictExpolt.tableModel.ColumnsModel;
import club.ufdeen.DataDictExpolt.util.JdbcUtil;

public class OracleColumnResultSetHandler implements ResultSetHandler<List<ColumnsModel>>,ColumnHandler{
	
	public List<ColumnsModel> getColumn(String tableName){
		String sql = "select tc.column_name,tc.DATA_TYPE,tc.nullable,cc.comments  "
				+ " from user_tab_columns tc,user_col_comments cc "
				+ " where tc.TABLE_NAME = cc.table_name and tc.COLUMN_NAME =cc.column_name "
				+ " and cc.table_name = ? ";
		try {
			return JdbcUtil.query(sql, this, new Object[] {tableName});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ColumnsModel> handle(ResultSet rs) throws SQLException {
		List<ColumnsModel> list = new ArrayList<ColumnsModel>();
		while(rs.next()) {
			ColumnsModel cm = new ColumnsModel();
			cm.setColComment(rs.getString("COMMENTS"));
			cm.setColumnname(rs.getString("COLUMN_NAME"));
			cm.setColisNull(rs.getString("NULLABLE"));
			cm.setColType(rs.getString("DATA_TYPE"));
			list.add(cm);
		}
		
		return list;
	} 

}
