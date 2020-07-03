package ca.jrvs.apps.jdbc;

import com.sun.org.apache.xpath.internal.operations.Or;
import sun.print.CUPSPrinter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExecutor {
  static final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

    public static void main(String[] args) {
        DataBaseConnectionManager dcm = new DataBaseConnectionManager("localhost",
                "hplussport", "postgres","12345");
        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1000);
           logger.info(String.valueOf(order));
        }catch(SQLException e) {
            logger.error("can no get the correct order");
        }
    }
}
