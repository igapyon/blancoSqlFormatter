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
 * �ǉ���SQL���`�R�[�f�B���O���[���ɏ]���A�P�̎��������{���܂��B
 * 
 * @author iga
 */
public class BlancoSqlFormatterTest04 extends TestCase {

    public void testFormatString01() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT a ,avg (b * case when c is null then 0 else c End) from t1 left outer join s Using (a) group by e");
        // System.out.println(strResult);
        assertEquals("CASE WHEN�Ɋւ��鎎��", "SELECT\n" + "        a\n"
                + "        ,AVG (\n" + "            b * CASE\n"
                + "                WHEN c IS NULL"
                + "\n                THEN 0\n" + "                ELSE c\n"
                + "            END\n" + "        )\n" + "    FROM\n"
                + "        t1 LEFT OUTER JOIN s\n" + "            USING (a)\n"
                + "    GROUP BY\n" + "        e", strResult);
    }

    /**
     * HAVING�̐��`
     * 
     * @throws Exception
     */
    public void testFormatString02() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT a ,MAX (b) FROM table_c GROUP BY a having MAX (b) > 10");
        // System.out.println(strResult);
        assertEquals("HAVING�Ɋւ��鎎��", "SELECT\n" + "        a\n"
                + "        ,MAX (b)\n" + "    FROM\n" + "        table_c\n"
                + "    GROUP BY\n" + "        a\n" + "    HAVING\n"
                + "        MAX (b) > 10", strResult);
    }

}
