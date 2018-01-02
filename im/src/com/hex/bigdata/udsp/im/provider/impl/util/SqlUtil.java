package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.im.provider.impl.util.model.ValueColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-25.
 */
public class SqlUtil {

    /**
     * 查询表
     *
     * @param selectColumns
     * @param selectTableName
     * @param whereProperties
     * @return
     */
    public static String select(List<String> selectColumns,
                                String selectTableName, List<WhereProperty> whereProperties) {
        return "SELECT " + getSelectColumns(selectColumns) + "\n FROM "
                + selectTableName + getWhere(whereProperties);
    }

    /**
     * 查询SQL
     *
     * @param selectColumns
     * @param selectSql
     * @param whereProperties
     * @return
     */
    public static String select2(List<String> selectColumns,
                                 String selectSql, List<WhereProperty> whereProperties) {
        return "SELECT " + getSelectColumns2(selectColumns) + "\n FROM (\n"
                + selectSql + "\n) UDSP_VIEW " + getWhere2(whereProperties);
    }

    private static String getWhere2(List<WhereProperty> whereProperties) {
        String sql = "";
        String name = null;
        String value = null;
        DataType type = null;
        Operator operator = null;
        int count = 0;
        if (whereProperties != null && whereProperties.size() != 0) {
            for (WhereProperty whereProperty : whereProperties) {
                name = whereProperty.getName();
                value = whereProperty.getValue();
                type = whereProperty.getType();
                operator = whereProperty.getOperator();
                if (StringUtils.isBlank(name) || StringUtils.isBlank(value) || operator == null)
                    continue;
                sql += (count == 0 ? "\n WHERE " : " AND ");
                sql += "UDSP_VIEW." + name + SqlUtil.getCondition(value, type, operator);
                count++;
            }
        }
        return sql;
    }

    private static String getSelectColumns2(List<String> columns) {
        return getColumns(columns, " UDSP_VIEW.* ");
    }

    private static String getSelectColumns(List<String> columns) {
        return getColumns(columns, " * ");
    }

    private static String getColumns(List<String> columns, String sql) {
        if (columns != null && columns.size() != 0) {
            sql = "";
            for (int i = 0; i < columns.size(); i++) {
                sql += (i == 0 ? "" : ",");
                sql += columns.get(i);
            }
        }
        return sql;
    }

    /**
     * 清空表数据
     *
     * @param tableName
     * @return
     */
    public static String truncateTable(String tableName) {
        return "TRUNCATE TABLE " + tableName;
    }

    public static String getCondition(String value, DataType type, Operator operator) {
        String str = "";
        if (DataType.INT == type || DataType.TINYINT == type || DataType.DOUBLE == type
                || DataType.DECIMAL == type || DataType.BIGINT == type || DataType.FLOAT == type
                || DataType.SMALLINT == type) {
            if (Operator.EQ == operator) {
                str = " = " + value;
            } else if (Operator.NE == operator) {
                str = " != " + value;
            } else if (Operator.GE == operator) {
                str = " >= " + value;
            } else if (Operator.GT == operator) {
                str = " > " + value;
            } else if (Operator.LE == operator) {
                str = " <= " + value;
            } else if (Operator.LT == operator) {
                str = " < " + value;
            } else if (Operator.IN == operator) {
                str = " IN (" + value + ")";
            } else if (Operator.LK == operator) {
                str = " LIKE '%" + value + "%'";
            } else if (Operator.RLIKE == operator) {
                str = " LIKE '" + value + "%'";
            }
        } else {
            if (Operator.EQ == operator) {
                str = " = '" + value + "'";
            } else if (Operator.NE == operator) {
                str = " != '" + value + "'";
            } else if (Operator.GE == operator) {
                str = " >= '" + value + "'";
            } else if (Operator.GT == operator) {
                str = " > '" + value + "'";
            } else if (Operator.LE == operator) {
                str = " <= '" + value + "'";
            } else if (Operator.LT == operator) {
                str = " < '" + value + "'";
            } else if (Operator.IN == operator) {
                String[] strs = value.split(",");
                for (int j = 0; j < strs.length; j++) {
                    if (j == 0) {
                        value = "'" + strs[j] + "'";
                    } else {
                        value += ",'" + strs[j] + "'";
                    }
                }
                str = " IN (" + value + ")";
            } else if (Operator.LK == operator) {
                str = " LIKE '%" + value + "%'";
            } else if (Operator.RLIKE == operator) {
                str = " LIKE '" + value + "%'";
            }
        }
        return str;
    }

    private static String getValue(DataType dataType, String value) {
        if (DataType.STRING == dataType || DataType.VARCHAR == dataType || DataType.CHAR == dataType
                || DataType.TIMESTAMP == dataType || DataType.BOOLEAN == dataType) {
            value = "'" + value + "'";
        }
        return value;
    }

    private static String getValues(List<ValueColumn> valueColumns) {
        String sql = "";
        String colName = null;
        DataType dataType = null;
        String value = null;
        int count = 0;
        if (valueColumns != null && valueColumns.size() != 0) {
            for (ValueColumn column : valueColumns) {
                colName = column.getColName();
                dataType = column.getDataType();
                value = column.getValue();
                sql += (count == 0 ? "\n" : "\n,");
                sql += colName + " = " + getValue(dataType, value);
                count++;
            }
        }
        return sql;
    }

    public static String getSetValues(List<ValueColumn> valueColumns) {
        String sql = "";
        if (valueColumns != null && valueColumns.size() != 0) {
            sql = "\n SET " + getValues(valueColumns);
        }
        return sql;
    }

    public static String getWhere(List<WhereProperty> whereProperties) {
        String sql = "";
        String name = null;
        String value = null;
        DataType dataType = null;
        Operator operator = null;
        int count = 0;
        if (whereProperties != null && whereProperties.size() != 0) {
            for (WhereProperty whereProperty : whereProperties) {
                name = whereProperty.getName();
                value = whereProperty.getValue();
                dataType = whereProperty.getType();
                operator = whereProperty.getOperator();
                if (StringUtils.isBlank(name) || StringUtils.isBlank(value) || operator == null)
                    continue;
                sql += (count == 0 ? "\n WHERE " : " AND ");
                sql += name + SqlUtil.getCondition(value, dataType, operator);
                count++;
            }
        }
        return sql;
    }

    public static String getIntoValues(List<ValueColumn> valueColumns) {
        String sql = "";
        ValueColumn valueColumn = null;
        if (valueColumns != null && valueColumns.size() != 0) {
            sql = " (";
            for (int i = 0; i < valueColumns.size(); i++) {
                valueColumn = valueColumns.get(i);
                sql += (i == 0 ? "" : ", ");
                sql += getValue(valueColumn.getDataType(), valueColumn.getValue());
            }
            sql += ")";
        }
        return sql;
    }

    public static String getIntoNames(List<ValueColumn> valueColumns) {
        String sql = "";
        if (valueColumns != null && valueColumns.size() != 0) {
            sql = " (";
            for (int i = 0; i < valueColumns.size(); i++) {
                sql += (i == 0 ? "" : ", ");
                sql += valueColumns.get(i).getColName();
            }
            sql += ")";
        }
        return sql;
    }
}
