package fr.ides.gestion.banque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.IColumnFilter;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;

public class CommonTest {
	
	protected static IDatabaseConnection dbUnitConnection = null;
	protected static IDataSet dataSet;
	
	
	public static IDatabaseConnection getConnection() throws ClassNotFoundException, SQLException, DatabaseUnitException    {
		Connection connexion;
		Class.forName("com.mysql.jdbc.Driver");
		connexion = DriverManager.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7149883","sql7149883","YprNGrEEG3");
		dbUnitConnection = new DatabaseConnection(connexion);

		  DatabaseConfig dBConfig = dbUnitConnection.getConfig();
		  dBConfig.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, false);
		  dBConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		  dBConfig.setProperty(DatabaseConfig.PROPERTY_PRIMARY_KEY_FILTER, new IColumnFilter() {
		    Map<String, List<String>> tablePrimaryKeyMap = new HashMap<String, List<String>>();
		
		    {
		      tablePrimaryKeyMap.put("CLIENT",
		          Arrays.asList(new String[] { "codeClient"}));
		      tablePrimaryKeyMap.put("COMPTE",
		          Arrays.asList(new String[] { "codeCompte" }));
		      tablePrimaryKeyMap.put("OPERATION",
		          Arrays.asList(new String[] { "numeroOperation"}));
		    }
		
		    public boolean accept(String tableName, Column column) {
		      if (tablePrimaryKeyMap.containsKey(tableName)) {
		        return tablePrimaryKeyMap.get(tableName).contains(column.getColumnName());
		      }
		      return false;
		    }
		  });
		  return dbUnitConnection;
	}

}
