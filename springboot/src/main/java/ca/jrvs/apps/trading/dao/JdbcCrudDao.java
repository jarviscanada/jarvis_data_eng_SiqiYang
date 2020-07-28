package ca.jrvs.apps.trading.dao;


import ca.jrvs.apps.trading.model.domain.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T,Integer>{

  private Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);
  abstract public JdbcTemplate getJdbcTemplate();
  abstract public SimpleJdbcInsert getSimpleJdbcInsert();
  abstract public String getTableName();
  abstract public String getIdColumnName();
  abstract Class<T> getEntityClass();


  /**
   * Saves a given entity. Use the returned instance for further operations as the save operation
   * might have changed the entity instance completely.
   *
   * @param entity must not be {@literal null}.
   * @return the saved entity will never be {@literal null}.
   */
  @Override
  public <S extends T> S save(S entity) {
    if(existsById(entity.getId())) {
      if(updateOne(entity)!= 1) {
        throw new DataRetrievalFailureException("unable to update the quote");
      }
    }else {
      addOne(entity);
    }
    return entity;
  }

  private <S extends T> void addOne(T enity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(enity);
    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    enity.setId(newId.intValue());
  }

  abstract public int updateOne(T entity);


  /**
   * Saves all given entities.
   *
   * @param entities must not be {@literal null}.
   * @return the saved entities will never be {@literal null}.
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
    List<S> entity_list = new ArrayList<>();
    entities.forEach(elements -> entity_list.add(save(elements)));
    return entity_list;
  }

  /**
   * Retrieves an entity by its id.
   *
   * @param id must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public Optional<T> findById(Integer id) {
    Optional<T> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";
    try {
      entity = Optional.ofNullable((T) getJdbcTemplate()
          .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()),id));
    }catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("can not find the trader id :" + id, e);
    }
    return entity;
  }

  /**
   * Returns whether an entity with the given id exists.
   *
   * @param id must not be {@literal null}.
   * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public boolean existsById(Integer id) {
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";
    try {
      getJdbcTemplate().queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(getEntityClass()),id);
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
  public Iterable<T> findAll() {
    List<T> allReturn;
    String selectSql = "SELECT * FROM " + getTableName();
    allReturn = getJdbcTemplate().query(selectSql,BeanPropertyRowMapper.newInstance(getEntityClass()));
    return allReturn;
  }

  /**
   * Returns all instances of the type with the given IDs.
   */
  @Override
  public Iterable<T> findAllById(Iterable<Integer> integers) {
    List<T> allValues = new ArrayList<>();
    integers.forEach(element -> allValues.add(findById(element).get()));
    return allValues;
  }

  /**
   * Returns the number of entities available.
   *
   * @return the number of entities
   */
  @Override
  public long count() {
    String query = "SELECT COUNT(*) FROM " + getTableName();
    return getJdbcTemplate().queryForObject(query,int.class);
  }

  /**
   * Deletes the entity with the given id.
   *
   * @param Id must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @Override
  public void deleteById(Integer Id) {
    if(Id == null) {
      throw new IllegalArgumentException("the given ticker is empty");
    }
    String query = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
    getJdbcTemplate().update(query,Id);
  }

  /**
   * Deletes a given entity.
   *
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public void delete(T entity) {
    deleteById(entity.getId());
  }


  /**
   * Deletes all entities managed by the repository.
   */
  @Override
  public void deleteAll() {
    String query = "DELETE FROM " + getTableName();
    getJdbcTemplate().update(query);
  }
}
