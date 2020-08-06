package ca.jrvs.apps.trading.dao;


import ca.jrvs.apps.trading.model.domain.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {
  private JdbcTemplate jdbcTemplate;
  private static final String TABLE_NAME = "position";
  private static final String TABLE_ACCOUNT = "account_id";
  private static final String TABLE_TICKER = "ticker";
  private Logger logger = LoggerFactory.getLogger(PositionDao.class);

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public Optional<Position> findByIdAndTicker(Integer account,String ticker) {
    Optional<Position> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE (" + TABLE_ACCOUNT + " =? "
        +"AND " + TABLE_TICKER + "=?)";
    try {
      entity = Optional.ofNullable((Position) jdbcTemplate
          .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(Position.class),account, ticker));
    }catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("can not find the trader id :" +account + ticker, e);
    }
    return entity;
  }

  public Iterable<Position> findById(Integer account) {
    List<Position> entity = new ArrayList<>();
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_ACCOUNT + "=?";
    try {
      entity = jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Position.class),account);
    }catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("can not find the trader id :" +account, e);
    }
    return entity;
  }

  public Iterable<Position> findByTicker(String ticker) {
    List<Position> entity = new ArrayList<>();
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_TICKER + "=?";
    try {
      entity = jdbcTemplate
          .query(selectSql, BeanPropertyRowMapper.newInstance(Position.class),ticker);
    }catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("can not find the trader id :" +ticker, e);
    }
    return entity;
  }


  public Iterable<Position> findAll() {
    List<Position> allReturn;
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    allReturn = jdbcTemplate.query(selectSql,BeanPropertyRowMapper.newInstance(Position.class));
    return allReturn;
  }


  public long count() {
    String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
    return jdbcTemplate.queryForObject(query,int.class);
  }

}
