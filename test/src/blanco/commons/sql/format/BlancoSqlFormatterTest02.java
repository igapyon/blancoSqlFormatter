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
public class BlancoSqlFormatterTest02 extends TestCase {

    public void testFormatString01() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT col1 FROM table1 AS t1 WHERE col1>'値 \"value' ; SELECT col1 FROM table1 AS t1 WHERE col1>'値 \"value'");
        assertEquals("セミコロンに関する試験", "SELECT\n" + "        col1\n"
                + "    FROM\n" + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 > '値 \"value'\n" + "; SELECT\n"
                + "        col1\n" + "    FROM\n" + "        table1 AS t1\n"
                + "    WHERE\n" + "        col1 > '値 \"value'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString02() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select uriage * 0.05 as 消費税 from 台帳");
        assertEquals("掛け算に関する試験", "SELECT\n" + "        uriage * 0.05 AS 消費税\n"
                + "    FROM\n" + "        台帳", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString03() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select user_name from 得意先 where kaisya_name like '日本%'");
        assertEquals("ワイルドカードに関する試験", "SELECT\n" + "        user_name\n"
                + "    FROM\n" + "        得意先\n" + "    WHERE\n"
                + "        kaisya_name LIKE '日本%'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString04() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select user_name || 'さん' as 継承付名称 from 得意先");
        assertEquals("文字列結合に関する試験", "SELECT\n"
                + "        user_name || 'さん' AS 継承付名称\n" + "    FROM\n"
                + "        得意先", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString05() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select a,b from (select a,b from table1 where user_cd = '0123' union select a,b from table1 where user_cd = '0122') where zip_code = '105'");
        assertEquals("UNIONに関する試験", "SELECT\n" + "        a\n" + "        ,b\n"
                + "    FROM\n" + "        (\n" + "            SELECT\n"
                + "                    a\n" + "                    ,b\n"
                + "                FROM\n" + "                    table1\n"
                + "                WHERE\n"
                + "                    user_cd = '0123'\n"
                + "            UNION\n" + "            SELECT\n"
                + "                    a\n" + "                    ,b\n"
                + "                FROM\n" + "                    table1\n"
                + "                WHERE\n"
                + "                    user_cd = '0122'\n" + "        )\n"
                + "    WHERE\n" + "        zip_code = '105'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString06() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select user_name || 'さん' as 継承付名称 from 得意先\n");
        assertEquals("改行に関する試験", "SELECT\n"
                + "        user_name || 'さん' AS 継承付名称\n" + "    FROM\n"
                + "        得意先\n", strResult);
        // System.out.println(strResult);
    }
}
