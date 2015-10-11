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
 * BlancoSqlFormatter: SQL���`�c�[��. SQL�������߂�ꂽ���[���ɏ]�����`���܂��B <br>
 * SQL���Ƃ��Đ��������Ƃ��O������ł��B
 * http://homepage2.nifty.com/igat/igapyon/diary/2005/ig050613.html <br>
 * ������SQL���`�R�[�f�B���O���[���ɏ]���A�P�̎��������{���܂��B
 * 
 * @author iga
 */
public class BlancoSqlFormatterTest extends TestCase {

    /**
     * String format �̃e�X�g���̃N���X(String)
     */
    public void testFormatString() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select t1.col1,col2,col3,col4,col5,col6,col7,col8,\n"
                        + "max(col1 ) AS maxcol1 FROM table1 t1\n"
                        + " ,table2 t2 WHERE t1.col1 = t2.col2 OR "
                        + "col1>3 OR col2<5 ORDER BY col1 DESC\n");
        // ���l���_ MAX�ɂ��āA�Ƃ肠�����͌��� ���̂悤�Ȏd�l�ƂȂ��Ă��܂��B
        // ���l���_ �h�b�g�̑O��ɋ󔒂������Ă��܂��܂��B
        assertEquals("SELECT\n" + "        t1.col1\n" + "        ,col2\n"
                + "        ,col3\n" + "        ,col4\n" + "        ,col5\n"
                + "        ,col6\n" + "        ,col7\n" + "        ,col8\n"
                + "        ,MAX (col1) AS maxcol1\n" + "    FROM\n"
                + "        table1 t1\n" + "        ,table2 t2\n"
                + "    WHERE\n" + "        t1.col1 = t2.col2\n"
                + "        OR col1 > 3\n" + "        OR col2 < 5\n"
                + "    ORDER BY\n" + "        col1 DESC\n", strResult);
        // System.out.println(strResult);

        strResult = formatter
                .format("INSERT INTO table1 VALUES (1000,'ABC',256)");
        assertEquals("INSERT\n" + "    INTO\n" + "        table1\n"
                + "    VALUES (\n" + "        1000\n" + "        ,'ABC'\n"
                + "        ,256\n" + "    )", strResult);
        // System.out.println(strResult);

        strResult = formatter
                .format("INSERT INTO \"table name1\" (col1,col2,col3)"
                        + "VALUES(1000,'ABC',256)");
        assertEquals("INSERT\n" + "    INTO\n" + "        \"table name1\" (\n"
                + "            col1\n" + "            ,col2\n"
                + "            ,col3\n" + "        )\n" + "    VALUES (\n"
                + "        1000\n" + "        ,'ABC'\n" + "        ,256\n"
                + "    )", strResult);
        // System.out.println(strResult);

        strResult = formatter
                .format("UPDATE table1 SET col2 = 'ABC' ,col3 = 123 WHERE col1 = 5");
        assertEquals("UPDATE\n" + "        table1\n" + "    SET\n"
                + "        col2 = 'ABC'\n" + "        ,col3 = 123\n"
                + "    WHERE\n" + "        col1 = 5", strResult);
        // System.out.println(strResult);

        // ���V���O�����C���R�����g�̎��������ꕔ�ύX�B
        strResult = formatter.format("DELETE -- single line comment \n    FROM"
                + "/* multi line comment\n          comment part is ignored */"
                + "table1 WHERE col1 = 5");
        assertEquals("DELETE\n" + "        -- single line comment \n"
                + "    FROM\n" + "        /* multi line comment\n"
                + "          comment part is ignored */\n" + "        table1\n"
                + "    WHERE\n" + "        col1 = 5", strResult);
        // System.out.println(strResult);

        strResult = formatter
                .format("SELECT col1,col2,col3,col4,col5,col6,col7,col8 FROM table1 WHERE col1 > 3 AND col2 < 5 ORDER BY col1"
                        + " UNION SELECT col11 ,col12 ,col13,col14 ,col15,col16,col17,col18 FROM table2 WHERE col11 > 3 ORDER BY col11");
        assertEquals("SELECT\n" + "        col1\n" + "        ,col2\n"
                + "        ,col3\n" + "        ,col4\n" + "        ,col5\n"
                + "        ,col6\n" + "        ,col7\n" + "        ,col8\n"
                + "    FROM\n" + "        table1\n" + "    WHERE\n"
                + "        col1 > 3\n" + "        AND col2 < 5\n"
                + "    ORDER BY\n" + "        col1\n" + "UNION\n" + "SELECT\n"
                + "        col11\n" + "        ,col12\n" + "        ,col13\n"
                + "        ,col14\n" + "        ,col15\n" + "        ,col16\n"
                + "        ,col17\n" + "        ,col18\n" + "    FROM\n"
                + "        table2\n" + "    WHERE\n" + "        col11 > 3\n"
                + "    ORDER BY\n" + "        col11", strResult);
        // System.out.println(strResult);

        strResult = formatter
                .format("SELECT \r\ncol1,col2,col3,col4,col5,col6,col7,col8 FROM table1 WHERE EXISTS(SELECT col1 FROM table2 WHERE col1 = 3)");
        assertEquals("SELECT\n" + "        col1\n" + "        ,col2\n"
                + "        ,col3\n" + "        ,col4\n" + "        ,col5\n"
                + "        ,col6\n" + "        ,col7\n" + "        ,col8\n"
                + "    FROM\n" + "        table1\n" + "    WHERE\n"
                + "        EXISTS (\n" + "            SELECT\n"
                + "                    col1\n" + "                FROM\n"
                + "                    table2\n" + "                WHERE\n"
                + "                    col1 = 3\n" + "        )", strResult);
        // System.out.println(strResult);
    }

}
