package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote,String>{

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;



  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }


  /**
   * Saves a given entity. Use the returned instance for further operations as the save operation
   * might have changed the entity instance completely.
   *
   * @param quote must not be {@literal null}.
   * @return the saved entity will never be {@literal null}.
   */
  @Override
  public Quote save(Quote quote) {
    if(existsById(quote.getId())) {
      int updateRowNo = updateOne(quote);
      if(updateRowNo !=1) {
        throw new DataRetrievalFailureException("unable to update the quote");
      }
    }else {
      addOne(quote);
    }
    return quote;
  }


  /**
   * helper method that saves one quote.
   * @param quote
   */
  public void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
//    Map<String, Object> parameters = new HashMap<String, Object>(7);
//    parameters.put("getTicker", quote.getId());
//    parameters.put("last_price", quote.getLastPrice());
//    parameters.put("bid_price", quote.getBidPrice());
//    parameters.put("bid_size", quote.getBidSize());
//    parameters.put("ask_price", quote.getAskPrice());
//    parameters.put("ask_size", quote.getAskSize());
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert",1,row);
    }
  }

  /**
   * helper method that updates one quote.
   * @param quote
   * @return
   */
  public int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, "
        + "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql,makeUpdateValues(quote));
  }


  private Object[] makeUpdateValues(Quote quote) {
    Object[] answer = new Object[6];
    answer[0] = quote.getLastPrice();
    answer[1] = quote.getBidPrice();
    answer[2] = quote.getBidSize();
    answer[3] = quote.getAskPrice();
    answer[4] = quote.getAskSize();
    answer[5] = quote.getId();
    return answer;
  }

  /**
   * Saves all given entities.
   *
   * @param quotes must not be {@literal null}.
   * @return the saved entities will never be {@literal null}.
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public <S extends Quote> List<S> saveAll(Iterable<S> quotes) {
    List quoteList = new ArrayList<Quote>();
    quotes.forEach(element -> quoteList.add(save(element)));
    return quoteList;
  }

  /**
   * Retrieves an entity by its id.
   *
   * @param s must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public Optional<Quote> findById(String s) {
    Quote quoteResult = new Quote();
    String query = "SELECT * FROM " + TABLE_NAME + " WHERE ticker=?";
    try {
      quoteResult=jdbcTemplate.queryForObject(query,(resultSet, i) -> {
        Quote quote = new Quote();
        quote.setId(resultSet.getString("ticker"));
        quote.setAskPrice(resultSet.getDouble("ask_price"));
        quote.setAskSize(resultSet.getInt("ask_size"));
        quote.setBidPrice(resultSet.getDouble("bid_price"));
        quote.setBidSize(resultSet.getInt("bid_size"));
        quote.setLastPrice(resultSet.getDouble("last_price"));
        return quote;
      },s);
      return Optional.of(quoteResult);

    }catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("can not verify this ticker");
    }
  }

  /**
   * Returns whether an entity with the given id exists.
   *
   * @param s must not be {@literal null}.
   * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public boolean existsById(String s) {
    try{
      String query = "SELECT * FROM " + TABLE_NAME + " WHERE ticker=?";
      jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Quote.class), s);
      return true;
    }catch (DataRetrievalFailureException e) {
      return false;
    }
  }

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  @Override
  public Iterable<Quote> findAll() {
    try{
      List<Quote> quotes = jdbcTemplate.query(
          "select * from " + TABLE_NAME,
          (resultSet, rowNum) -> {
            Quote quote = new Quote();
            quote.setId(resultSet.getString("ticker"));
            quote.setAskPrice(resultSet.getDouble("ask_price"));
            quote.setAskSize(resultSet.getInt("ask_size"));
            quote.setBidPrice(resultSet.getDouble("bid_price"));
            quote.setBidSize(resultSet.getInt("bid_size"));
            quote.setLastPrice(resultSet.getDouble("last_price"));
            return quote;
          });
          return quotes;
    }catch (DataAccessException e) {
      throw new DataRetrievalFailureException("can not get the value from database",e);
    }
  }



  /**
   * Returns all instances of the type with the given IDs.
   */
  @Override
  public Iterable<Quote> findAllById(Iterable<String> strings) {
    throw new UnsupportedOperationException("unimplemented method");
  }

  /**
   * Returns the number of entities available.
   *
   * @return the number of entities
   */
  @Override
  public long count() {
    String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
    return jdbcTemplate.queryForObject(query,int.class);
  }

  /**
   * Deletes the entity with the given id.
   *
   * @param s must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @Override
  public void deleteById(String s) {
    if(s == null) {
      throw new IllegalArgumentException("the given ticker is empty");
    }
    String query = "DELETE FROM " + TABLE_NAME + " WHERE ticker=?";
    jdbcTemplate.update(query,s);

  }

  /**
   * Deletes a given entity.
   *
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public void delete(Quote entity) {
    throw new UnsupportedOperationException("unimplemented method");
  }

  /**
   * Deletes the given entities.
   *
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends Quote> entities) {
    throw new UnsupportedOperationException("unimplemented method");
  }

  /**
   * Deletes all entities managed by the repository.
   */
  @Override
  public void deleteAll() {
    String query = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(query);
  }
}
