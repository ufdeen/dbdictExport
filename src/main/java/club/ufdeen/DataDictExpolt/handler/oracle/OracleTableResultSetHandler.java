package club.ufdeen.DataDictExpolt.handler.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import club.ufdeen.DataDictExpolt.handler.TableHandler;
import club.ufdeen.DataDictExpolt.tableModel.TableModel;
import club.ufdeen.DataDictExpolt.util.JdbcUtil;

public class OracleTableResultSetHandler implements ResultSetHandler<List<TableModel>>,TableHandler{
	
	public List<TableModel> getAllTable(){
		String sql = "select table_name,comments from user_tab_comments where table_type = 'TABLE'";
		try {
			return JdbcUtil.query(sql, this, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TableModel> handle(ResultSet rs) throws SQLException {
		List<TableModel> list = new ArrayList<TableModel>();
		while(rs.next()) {
			TableModel tm = new TableModel();
			tm.setTableName(rs.getString("TABLE_NAME"));
			tm.setComment(rs.getString("COMMENTS"));
			list.add(tm);
		}
		
		return list;
	} 

}
