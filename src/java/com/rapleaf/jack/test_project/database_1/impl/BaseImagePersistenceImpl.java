
/**
 * Autogenerated by Jack
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.rapleaf.jack.test_project.database_1.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

import com.rapleaf.jack.AbstractDatabaseModel;
import com.rapleaf.jack.BaseDatabaseConnection;
import com.rapleaf.jack.ModelWithId;

import com.rapleaf.jack.test_project.database_1.models.Image;
import com.rapleaf.jack.test_project.database_1.iface.IImagePersistence;

import com.rapleaf.jack.test_project.IDatabases;

public class BaseImagePersistenceImpl extends AbstractDatabaseModel<Image> implements IImagePersistence {
  private final IDatabases databases;

  public BaseImagePersistenceImpl(BaseDatabaseConnection conn, IDatabases databases) {
    super(conn, "images", Arrays.asList("user_id"));
    this.databases = databases;
  }

  @Override
  public Image create(Map<Enum, Object> fieldsMap) throws IOException {
    Integer user_id = (Integer) fieldsMap.get(Image._Fields.user_id);
    return create(user_id);
  }


  public Image create(final Integer user_id) throws IOException {
    long __id = realCreate(new AttrSetter() {
      public void set(PreparedStatement stmt) throws SQLException {
        if (user_id == null) {
          stmt.setNull(1, java.sql.Types.INTEGER);
        } else {
          stmt.setInt(1, user_id);
        }
      }
    }, getInsertStatement(Arrays.<String>asList("user_id")));
    Image newInst = new Image(__id, user_id, databases);
    newInst.setCreated(true);
    cachedById.put(__id, newInst);
    clearForeignKeyCache();
    return newInst;
  }



  public Image create() throws IOException {
    long __id = realCreate(new AttrSetter() {
      public void set(PreparedStatement stmt) throws SQLException {
      }
    }, getInsertStatement(Arrays.<String>asList()));
    Image newInst = new Image(__id, null, databases);
    newInst.setCreated(true);
    cachedById.put(__id, newInst);
    clearForeignKeyCache();
    return newInst;
  }


  public Image createDefaultInstance() throws IOException {
    return create();
  }

  public Set<Image> find(Map<Enum, Object> fieldsMap) throws IOException {
    return find(null, fieldsMap);
  }

  public Set<Image> find(Set<Long> ids, Map<Enum, Object> fieldsMap) throws IOException {
    Set<Image> foundSet = new HashSet<Image>();
    
    if (fieldsMap == null || fieldsMap.isEmpty()) {
      return foundSet;
    }

    StringBuilder statementString = new StringBuilder();
    statementString.append("SELECT * FROM images WHERE (");
    List<Object> nonNullValues = new ArrayList<Object>();
    List<Image._Fields> nonNullValueFields = new ArrayList<Image._Fields>();

    Iterator<Map.Entry<Enum, Object>> iter = fieldsMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<Enum, Object> entry = iter.next();
      Enum field = entry.getKey();
      Object value = entry.getValue();
      
      String queryValue = value != null ? " = ? " : " IS NULL";
      if (value != null) {
        nonNullValueFields.add((Image._Fields) field);
        nonNullValues.add(value);
      }

      statementString.append(field + queryValue);
      if (iter.hasNext()) {
        statementString.append(" AND ");
      }
    }
    if (ids != null) statementString.append(" AND " + getIdSetCondition(ids));
    statementString.append(")");

    PreparedStatement preparedStatement = getPreparedStatement(statementString.toString());

    for (int i = 0; i < nonNullValues.size(); i++) {
      Image._Fields field = nonNullValueFields.get(i);
      try {
        switch (field) {
          case user_id:
            preparedStatement.setInt(i+1, (Integer) nonNullValues.get(i));
            break;
        }
      } catch (SQLException e) {
        throw new IOException(e);
      }
    }
    executeQuery(foundSet, preparedStatement);

    return foundSet;
  }

  @Override
  protected void setAttrs(Image model, PreparedStatement stmt) throws SQLException {
    if (model.getUserId() == null) {
      stmt.setNull(1, java.sql.Types.INTEGER);
    } else {
      stmt.setInt(1, model.getUserId());
    }
    stmt.setLong(2, model.getId());
  }

  @Override
  protected Image instanceFromResultSet(ResultSet rs) throws SQLException {
    return new Image(rs.getLong("id"),
      getIntOrNull(rs, "user_id"),
      databases
    );
  }

  public Set<Image> findByUserId(final Integer value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(Image._Fields.user_id, value);}});
  }
}
