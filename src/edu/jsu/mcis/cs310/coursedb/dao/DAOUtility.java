package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

		// Retrieve metadata for ResultSet
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columnCount = rsmd.getColumnCount();
		
		// Iterate over each row
		while (rs.next()) {
			JsonObject row = new JsonObject();
			// Loop for column data
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				String columnValue = rs.getString(columnName);
				// Store column data in JsonObject
				row.put(columnName, columnValue);
			}
			records.add(row);
		}
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
