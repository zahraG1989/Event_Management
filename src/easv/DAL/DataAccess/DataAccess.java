package easv.DAL.DataAccess;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DataAccess {

        private final SQLServerDataSource dataSource ;

        public DataAccess() {
            dataSource = new SQLServerDataSource();
            dataSource.setServerName("10.176.111.31");
            dataSource.setUser("CSe21B_24");
            dataSource.setPassword("CSe21B_24");
            dataSource.setDatabaseName("MovieManager");  // create new database name called what ever


            System.out.println("you are connected ");
        }

        public Connection getConnection() throws SQLServerException {

            return dataSource.getConnection();
        }
}
