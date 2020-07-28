package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Trader;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public class TraderDao extends JdbcCrudDao<Trader> {
  private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
  private final String TABLE_NAME = "trader";
  private final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;



  @Autowired
  public TraderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
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
    return this.TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return this.ID_COLUMN;
  }

  @Override
  Class<Trader> getEntityClass() {
    return Trader.class;
  }

  public int updateOne(Trader trader) {
    String update_sql = "UPDATE trader SET first_name=?, last_name=?, "
        + "dob=?, country=?, email=? WHERE id=?";
    return jdbcTemplate.update(update_sql,makeUpdateValues(trader));
  }


  private Object[] makeUpdateValues(Trader trader) {
    Object[] answer = new Object[6];
    answer[0] = trader.getFirst_name();
    answer[1] = trader.getLast_name();
    answer[2] = trader.getDob();
    answer[3] = trader.getCountry();
    answer[4] = trader.getEmail();
    answer[5] = trader.getId();
    return answer;
  }



  /**
   * Deletes the given entities.
   *
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends Trader> entities) {
    throw new UnsupportedOperationException("this function is not implemented");
  }
}
