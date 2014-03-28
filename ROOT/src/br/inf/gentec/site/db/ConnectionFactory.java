package br.inf.gentec.site.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionFactory
{

    private static final DataSource dataSource = build();
    private Connection connection;

    public ConnectionFactory()
    {
    }

    private static synchronized DataSource build() {
        BasicDataSource dbcp;
        try {
        	dbcp = new BasicDataSource();
            dbcp.setUrl("jdbc:mysql://gentec.inf.br:3306/gentecin_portal?autoReconnect=true");
            dbcp.setUsername("gentecin_admin");
            dbcp.setPassword("Joao&Collato2515");
            dbcp.setDefaultAutoCommit(false);
            dbcp.setDriverClassName("com.mysql.jdbc.Driver");
            dbcp.setInitialSize(2);
            dbcp.setMaxActive(25);
            dbcp.setRemoveAbandoned(true);
            dbcp.setRemoveAbandonedTimeout(300);
            dbcp.setLogAbandoned(true);
            return dbcp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public synchronized Connection getConnection()
    {
        try {
            if(connection == null) {
                connection = dataSource.getConnection();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}