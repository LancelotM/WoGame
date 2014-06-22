package com.unicom.wogame.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <b>Copyright (c)</b> 2013 UUCUN<br/>
 */

/**
 * @author Chen Yanqing(chenyq@uucun.com)
 * @version 1.0.0
 * @since 2013-3-4
 */
public class UniDataUtil {

    /**
     * int类型字段名
     */
    private static String[] intNames = new String[]{"outboxmax", "result",
            "totalrows", "pagenum", "score", "inboxmax", "type", "origprice",
            "infoflag", "rate", "age", "gender", "donoteisfree", "ispromotion",
            "woaccstate", "messmaxlen", "isfree", "promprice", "returnrows",
            "favoriteflag", "size", "price", "ismoresoft", "dlcount",
            "userstar", "growth"};

    private static Set<String> intNamesSet = new HashSet<String>();

    static {
        for (String intName : intNames) {
            intNamesSet.add(intName);
        }
    }

    /**
     * long类型字段名
     */
    private static String[] longNames = new String[]{"date"};

    private static Set<String> longNamesSet = new HashSet<String>();

    static {
        for (String longName : longNames) {
            longNamesSet.add(longName);
        }
    }

    protected static void writeInt(ByteArrayOutputStream baos, int value) {
        byte bytes[] = new byte[4];
        bytes[3] = (byte) value;
        bytes[2] = (byte) (value >> 8);
        bytes[1] = (byte) (value >> 16);
        bytes[0] = (byte) (value >> 24);

        try {
            baos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected static void writeShort(ByteArrayOutputStream baos, int value) {
        byte bytes[] = new byte[2];
        bytes[1] = (byte) value;
        bytes[0] = (byte) (value >> 8);

        try {
            baos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected static int readInt(ByteArrayInputStream bais) {

        byte bytes[] = new byte[4];
        bais.read(bytes, 0, 4);

        int i = 0;
        int len = bytes.length;
        for (int m = 0; m < len - 1; m++) {
            i += bytes[m] & 0xff;
            i <<= 8;
        }

        i += bytes[len - 1] & 0xff;
        return i;
    }

    protected static int bytesToInt(byte[] bytes) {
        int i = 0;
        int len = bytes.length;
        for (int m = 0; m < len - 1; m++) {
            i += bytes[m] & 0xff;
            i <<= 8;
        }

        i += bytes[len - 1] & 0xff;
        return i;
    }

    protected static long bytesToLong(byte[] bytes) {
        long i = 0;
        int len = bytes.length;
        for (int m = 0; m < len - 1; m++) {
            i += bytes[m] & 0xff;
            i <<= 8;
        }

        i += bytes[len - 1] & 0xff;
        return i;
    }

    protected static int readShort(ByteArrayInputStream bais) {

        byte bytes[] = new byte[2];
        bais.read(bytes, 0, 2);

        int i = 0;
        int len = bytes.length;
        for (int m = 0; m < len - 1; m++) {
            i += bytes[m] & 0xff;
            i <<= 8;
        }

        i += bytes[len - 1] & 0xff;
        return i;
    }

    static String ENCODING = "UTF-8";

    public static byte[] toBytes(UniData data) {
        if (data == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        List<UniData.TableData> tableList = data.getTableList();
        int tableCount = tableList == null ? 0 : tableList.size();
        writeInt(baos, tableCount);
        for (int k = 0; k < tableCount; k++) {
            UniData.TableData table = tableList.get(k);
            String tableName = table.getTableName();

            byte[] tableNameBytes = null;
            try {
                tableNameBytes = tableName.getBytes(ENCODING);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            writeInt(baos, tableNameBytes.length);
            try {
                baos.write(tableNameBytes);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            List<UniData.RowData> rowList = table.getRowList();
            int rowCount = rowList == null ? 0 : rowList.size();
            writeInt(baos, rowCount);
            for (int i = 0; i < rowCount; i++) {
                UniData.RowData rowData = rowList.get(i);
                List<UniData.ColumnData> columnList = rowData.getColumnList();
                int columnCount = columnList == null ? 0 : columnList.size();
                writeInt(baos, columnCount);
                for (int j = 0; j < columnCount; j++) {
                    UniData.ColumnData columnData = columnList.get(j);
                    String name = columnData.getName();
                    if (name == null)
                        name = "";
                    String value = columnData.getValue();
                    if (value == null)
                        value = "";

                    byte[] nameBytes = null;
                    try {
                        nameBytes = name.getBytes(ENCODING);
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    writeInt(baos, nameBytes.length);
                    try {
                        baos.write(nameBytes);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    byte[] valueBytes = null;

                    boolean isInt = false;
                    try {
                        int intValue = Integer.parseInt(value);
                        if (intValue < 999) {
                            isInt = true;
                            writeInt(baos, 4);
                            writeInt(baos, intValue);
                        }
                    } catch (Exception e) {

                    }

                    if (!isInt) {
                        try {
                            valueBytes = value.getBytes(ENCODING);
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        }
                        writeInt(baos, valueBytes.length);
                        try {
                            baos.write(valueBytes);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    byte[] resData = columnData.getResData();
                    if (resData == null) {
                        resData = new byte[0];
                    }
                    writeInt(baos, resData.length);
                    try {
                        baos.write(resData);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }

        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static UniData fromBytes(byte[] bytes) {
        UniData data = new UniData();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        int tableCount = readInt(bais);
        List<UniData.TableData> tableList = new ArrayList<UniData.TableData>(
                tableCount);
        data.setTableList(tableList);

        for (int k = 0; k < tableCount; k++) {
            UniData.TableData table = new UniData.TableData();
            tableList.add(table);
            int tableNamesize = readInt(bais);
            byte[] tableNameBytes = new byte[tableNamesize];
            bais.read(tableNameBytes, 0, tableNamesize);
            try {
                table.setTableName(new String(tableNameBytes, ENCODING));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            int rowCount = readInt(bais);

            List<UniData.RowData> rowList = new ArrayList<UniData.RowData>(
                    rowCount);
            table.setRowList(rowList);
            for (int i = 0; i < rowCount; i++) {
                UniData.RowData rowData = new UniData.RowData();
                rowList.add(rowData);

                int columnCount = readInt(bais);
                List<UniData.ColumnData> columnList = new ArrayList<UniData.ColumnData>(
                        columnCount);
                rowData.setColumnList(columnList);

                for (int j = 0; j < columnCount; j++) {
                    UniData.ColumnData columnData = new UniData.ColumnData();
                    columnList.add(columnData);

                    int nameLen = readInt(bais);
                    byte[] nameBytes = new byte[nameLen];
                    bais.read(nameBytes, 0, nameLen);

                    String colName = "";
                    try {
                        colName = new String(nameBytes, ENCODING);
                        columnData.setName(colName);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    int valueLen = readInt(bais);
                    byte[] valueBytes = new byte[valueLen];
                    bais.read(valueBytes, 0, valueLen);
                    String value = "";

                    try {
                        // value = new String(valueBytes, ENCODING);
                        // if (valueLen == 4) {
                        // int intValue = bytesToInt(valueBytes);
                        // value = intValue + "";
                        // } else if (valueLen == 8) {
                        // long longValue = bytesToLong(valueBytes);
                        // value = longValue + "";
                        // }

                        value = new String(valueBytes, ENCODING);
                        switch (valueLen) {
                            case 4:
                                if (isIntValue(colName)) {
                                    value = bytesToInt(valueBytes) + "";
                                }
                                break;
                            case 8:
                                if (isLongValue(colName)) {
                                    value = bytesToLong(valueBytes) + "";
                                }
                                break;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    columnData.setValue(value);

                    int resDataLen = readInt(bais);
                    byte[] resDataBytes = new byte[resDataLen];
                    bais.read(resDataBytes, 0, resDataLen);

                    columnData.setResData(resDataBytes);
                }
            }
        }

        return data;
    }

    /**
     * 根据字段名确定是否为int
     *
     * @param colName
     * @return
     */
    private static boolean isIntValue(String colName) {
        return intNamesSet.contains(colName);
    }

    /**
     * 根据字段名确定是否为long
     *
     * @param colName
     * @return
     */
    private static boolean isLongValue(String colName) {
        return longNamesSet.contains(colName);
    }

}
