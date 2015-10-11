/*
 * blancoCommons Copyright (C) 2005 Tosiki Iga
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 */
package blanco.commons.sql.format;

import junit.framework.TestCase;

/**
 * BlancoSqlFormatter: SQL整形ツール. SQL文を決められたルールに従い整形します。 <br>
 * SQL文として正しいことが前提条件です。
 * http://homepage2.nifty.com/igat/igapyon/diary/2005/ig050613.html <br>
 * 追加のSQL整形コーディングルールに従い、単体試験を実施します。
 * 
 * @author iga
 */
public class BlancoSqlFormatterTest01 extends TestCase {

    public void testFormatString01() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT col1 FROM table1 AS t1 WHERE col1>'値 \"value'");
        assertEquals("> が横と隣接している場合に適切に処理できるかどうかの試験", "SELECT\n"
                + "        col1\n" + "    FROM\n" + "        table1 AS t1\n"
                + "    WHERE\n" + "        col1 > '値 \"value'", strResult);
        // System.out.println(strResult);
    }

    /**
     * String format のテスト中のクラス(String)
     */
    public void testFormatString() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        // TODO: THEN と ELSE は ANSI-SQLかどうかを確認
        // TODO: FOR UPDATEの字下げを考慮。
        // blanco的には # は英文字の一部である。
        String strResult = formatter
                .format("SELECT t1.a_b AS a_b, (char 'abcd') AS _abcd,"
                        + "(varchar 'efgh') AS efgh_,"
                        + "(timestamp '2000/01/01') AS time_stamp_20000101,"
                        + "CASE WHEN t1.g_h IS null THEN 'NULL' ELSE 'NOTNULL' END AS null_check_column"
                        + " FROM table_1 AS t1"
                        + " left outer join _table2 AS t2" + " using (a_b )"
                        + " LEFT OUTER JOIN table3_ AS t3"
                        + " USING (a_b) WHERE  t1.a_1 = #a_b for update");
        // System.out.println(strResult);
        assertEquals("CASE WHENに関する試験 アンダースコアも試験", "SELECT\n"
                + "        t1.a_b AS a_b\n" + "        ,(\n"
                + "            CHAR 'abcd'\n" + "        ) AS _abcd\n"
                + "        ,(\n" + "            VARCHAR 'efgh'\n"
                + "        ) AS efgh_\n" + "        ,(\n"
                + "            TIMESTAMP '2000/01/01'\n"
                + "        ) AS time_stamp_20000101\n" + "        ,CASE\n"
                + "            WHEN t1.g_h IS NULL\n"
                + "            THEN 'NULL'\n" + "            ELSE 'NOTNULL'\n"
                + "        END AS null_check_column\n" + "    FROM\n"
                + "        table_1 AS t1 LEFT OUTER JOIN _table2 AS t2\n"
                + "            USING (a_b) LEFT OUTER JOIN table3_ AS t3\n"
                + "            USING (a_b)\n" + "    WHERE\n"
                + "        t1.a_1 = #a_b FOR UPDATE", strResult);
    }

    public void testFormatString02() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter.format("SELECT 列1 AS col1,col列2 AS 列2col"
                + " FROM 日本語表名1 AS t1, table日本語2 as t2");
        assertEquals("日本語の列名・表名の試験", "SELECT\n" + "        列1 AS col1\n"
                + "        ,col列2 AS 列2col\n" + "    FROM\n"
                + "        日本語表名1 AS t1\n" + "        ,table日本語2 AS t2",
                strResult);
        // System.out.println(strResult);
    }

    public void testFormatString03() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        // <= が適切に処理できるかどうかの試験
        String strResult = formatter
                .format("SELECT \tcol1 FROM table1 AS t1\tWHERE col1 = '値 \t\"value'");
        assertEquals("タブ文字の試験", "SELECT\n" + "        col1\n" + "    FROM\n"
                + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 = '値 \t\"value'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString04() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT col1 FROM table1 AS t1 WHERE col1<='値 \"value'");
        assertEquals("<=が適切に処理できることの試験", "SELECT\n" + "        col1\n"
                + "    FROM\n" + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 <= '値 \"value'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString05() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT col1 FROM table1 AS t1 WHERE col1>='値 \"値2'");
        assertEquals(">=が適切に処理できることの試験", "SELECT\n" + "        col1\n"
                + "    FROM\n" + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 >= '値 \"値2'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString06() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT t1.col1,t1.col2,col3 FROM table1 AS t1 WHERE col1>='値' GROUP\n BY col1");
        assertEquals("ORDER BY, GROUP BYの試験", "SELECT\n" + "        t1.col1\n"
                + "        ,t1.col2\n" + "        ,col3\n" + "    FROM\n"
                + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 >= '値'\n" + "    GROUP BY\n" + "        col1",
                strResult);
        // System.out.println(strResult);
    }

    public void testFormatString07() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter.format("truncate table table1");
        assertEquals("TRUNCATE文の試験", "TRUNCATE\n" + "    TABLE\n"
                + "        table1", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString08() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter.format("drop table table1,table2");
        assertEquals("DROP TABLEの試験", "DROP\n" + "    TABLE\n"
                + "        table1\n" + "        ,table2", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString09() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        // TODO: 型のサイズ指定の際に改行してしまっている点は不満です。
        // TODO: 現在セミコロンが消失してしまっています。
        String strResult = formatter
                .format("    CREATE TABLE table1 (colID DECIMAL( 10),name    CHAR VARYING(40),PRIMARY KEY(colID)        ); ");
        assertEquals("CREATE TABLEの試験。現状型のサイズで改行およびセミコロンが消失の不満点があり", "CREATE\n"
                + "    TABLE\n" + "        table1 (\n"
                + "            colID DECIMAL (10)\n"
                + "            ,name CHAR VARYING (40)\n"
                + "            ,PRIMARY KEY (colID)\n" + "        )\n;",
                strResult);
        // System.out.println(strResult);
    }

}
