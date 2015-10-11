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
 * 追加のSQL整形コーディングルールに従い、単体試験を実施します。 <br>
 * <br>
 * 例外発生にまつわる試験を行います。
 * 
 * @author iga
 */
public class BlancoSqlFormatterTest03 extends TestCase {

    public void testFormatString01() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        try {
            String strResult = formatter
                    .format("select a,b from (select a,b from table1 where user_cd = '0123");
            strResult.trim();
            fail("カンマが不整合であるのに例外が発生しませんでした。");
        } catch (BlancoSqlFormatterException ex) {
        }
    }

    public void testFormatString02() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        try {
            String strResult = formatter
                    .format("select a,b from (select a,b from \"table1 where user_cd = '0123'");
            strResult.trim();
            fail("ダブルクオーテーションが不整合であるのに例外が発生しませんでした。");
        } catch (BlancoSqlFormatterException ex) {
        }
    }

    public void testFormatString03() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        {
            // これは現在の仕様では例外は発生しません。
            String strResult = formatter
                    .format("INSERT INTO table1 VALUES (1000,'ABC',256");
            strResult.trim();
            assertEquals("INSERT\n" + "    INTO\n" + "        table1\n"
                    + "    VALUES (\n" + "        1000\n" + "        ,'ABC'\n"
                    + "        ,256", strResult);
        }
    }
}
