package com._3e.ADInterface;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import pl.x3E.adInterface.DataRow;

public interface ADInfo {
  public void setParameters( PreparedStatement pstmt, boolean forCount) throws SQLException;
  //public String getSQLWhere();
  public String getSQL();
  public String getSQLCount();
  
  public DataRow getColumns();
  
}
