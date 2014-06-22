package com.unicom.wogame.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Copyright (c)</b> 2013 UUCUN<br/>
 */

/**
 * 联通UniStore的通用协议数据结构
 *
 * @author Chen Yanqing(chenyq@uucun.com)
 * @version 1.0.0
 * @since 2013-3-4
 */
public class UniData {

    public static class ColumnData {
        // int namesize = 8;
        // string name = "password";
        // int valuesize = 6;
        // bytes value = "123456";
        // int ressize = 0;
        // bytes resdata = 0;
        public String name;
        public String value;
        public byte[] resData;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public byte[] getResData() {
            return resData;
        }

        public void setResData(byte[] resData) {
            this.resData = resData;
        }

        public String toString() {
            return "{name=" + name + ",value=" + value + ",resData=" + resData
                    + "}";
        }

    }

    public static class RowData {
        List<ColumnData> columnList;

        private Map<String, ColumnData> map;

        public List<ColumnData> getColumnList() {
            return columnList;
        }

        public void setColumnList(List<ColumnData> columnList) {
            this.columnList = columnList;

        }

        private Map<String, ColumnData> getMap() {
            if (map != null) {
                return map;
            }
            map = new HashMap<String, ColumnData>();
            for (ColumnData cd : columnList) {
                map.put(cd.getName(), cd);
            }
            return map;
        }

        public ColumnData getColumnData(String key) {
            if (key == null || getMap() == null) {
                return null;
            }
            return getMap().get(key);
        }

        public String toString() {
            return "columnList:" + columnList;
        }

    }

    public static class TableData {
        String tableName;
        List<RowData> rowDataList;

        public List<RowData> getRowList() {
            return rowDataList;
        }

        public void setRowList(List<RowData> rowDataList) {
            this.rowDataList = rowDataList;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public RowData getRowData(int index) {
            int size = this.rowDataList == null ? -1 : this.rowDataList.size();
            if (index < 0 || index >= size) {
                return null;
            }
            return rowDataList.get(index);
        }

        public String toString() {
            return "tableName：" + tableName + ",rowDataList:" + rowDataList;
        }

    }

    List<TableData> tableList;

    public List<TableData> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableData> tableList) {
        this.tableList = tableList;
    }

    /**
     * 根据表名返回表
     *
     * @param tableName
     * @return
     */
    public TableData getTable(String tableName) {
        if (tableList == null || tableName == null) {
            return null;
        }
        for (TableData td : tableList) {
            if (td.getTableName().equalsIgnoreCase(tableName)) {
                return td;
            }
        }
        return null;
    }

    public String toString() {
        return "UniData:" + tableList;
    }

}
