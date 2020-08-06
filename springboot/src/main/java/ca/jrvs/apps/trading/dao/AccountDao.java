package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


@Repository
public class AccountDao extends JdbcCrudDao<Account>{
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;
  private static final String TABLE_NAME = "account";
  private static final String ID_COLUMN_NAME = "id";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);

  @Autowired
  public AccountDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN_NAME);
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
    return this.ID_COLUMN_NAME;
  }

  @Override
  Class<Account> getEntityClass() {
    return Account.class;
  }

  @Override
  public int updateOne(Account entity) {
    String query = "UPDATE account SET trader_id=?, amount=? WHERE id=?";
    return jdbcTemplate.update(query,generateEntity(entity));

  }

  private Object[] generateEntity(Account account) {
    Object[] accountInfo = new Object[3];
    accountInfo[0] = account.getTrader_id();
    accountInfo[1] = account.getAmount();
    accountInfo[2] = account.getId();
    return accountInfo;
  }

  /**
   * Deletes the given entities.
   *
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends Account> entities) {
    throw new UnsupportedOperationException("this function is not implemented");
  }
}
