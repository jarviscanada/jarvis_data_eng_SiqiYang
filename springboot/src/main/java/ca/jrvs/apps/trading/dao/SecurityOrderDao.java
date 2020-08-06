package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder> {

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;
  private static final String TABLE_NAME = "security_order";
  private static final String TABLE_COLUMN = "id";

  @Autowired
  public SecurityOrderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(TABLE_COLUMN);
  }
  @Override
  public JdbcTemplate getJdbcTemplate() {
    return this.jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return this.simpleJdbcInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return TABLE_COLUMN;
  }

  @Override
  Class<SecurityOrder> getEntityClass() {
    return SecurityOrder.class;
  }

  @Override
  public int updateOne(SecurityOrder entity) {
    String query = "UPDATE security_order SET account_id=?, status=?, ticker=?, size=?, price=?, "
        + "notes=? WHERE id=?";
    return jdbcTemplate.update(query,generateParameter(entity));
  }

  private Object[] generateParameter(SecurityOrder securityOrder) {
    Object[] parameter = new Object[7];
    parameter[0] = securityOrder.getAccount_id();
    parameter[1] = securityOrder.getStatus();
    parameter[2] = securityOrder.getTicker();
    parameter[3] = securityOrder.getSize();
    parameter[4] = securityOrder.getPrice();
    parameter[5] = securityOrder.getNotes();
    parameter[6] = securityOrder.getId();
    return parameter;
  }


  /**
   * Deletes the given entities.
   *
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends SecurityOrder> entities) {
    throw new UnsupportedOperationException("this function is not implemented");
  }
}
