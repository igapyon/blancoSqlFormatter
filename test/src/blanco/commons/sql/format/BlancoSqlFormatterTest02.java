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
public class BlancoSqlFormatterTest02 extends TestCase {

    public void testFormatString01() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT col1 FROM table1 AS t1 WHERE col1>'�l \"value' ; SELECT col1 FROM table1 AS t1 WHERE col1>'�l \"value'");
        assertEquals("�Z�~�R�����Ɋւ��鎎��", "SELECT\n" + "        col1\n"
                + "    FROM\n" + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 > '�l \"value'\n" + "; SELECT\n"
                + "        col1\n" + "    FROM\n" + "        table1 AS t1\n"
                + "    WHERE\n" + "        col1 > '�l \"value'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString02() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select uriage * 0.05 as ����� from �䒠");
        assertEquals("�|���Z�Ɋւ��鎎��", "SELECT\n" + "        uriage * 0.05 AS �����\n"
                + "    FROM\n" + "        �䒠", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString03() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select user_name from ���Ӑ� where kaisya_name like '���{%'");
        assertEquals("���C���h�J�[�h�Ɋւ��鎎��", "SELECT\n" + "        user_name\n"
                + "    FROM\n" + "        ���Ӑ�\n" + "    WHERE\n"
                + "        kaisya_name LIKE '���{%'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString04() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select user_name || '����' as �p���t���� from ���Ӑ�");
        assertEquals("�����񌋍��Ɋւ��鎎��", "SELECT\n"
                + "        user_name || '����' AS �p���t����\n" + "    FROM\n"
                + "        ���Ӑ�", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString05() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("select a,b from (select a,b from table1 where user_cd = '0123' union select a,b from table1 where user_cd = '0122') where zip_code = '105'");
        assertEquals("UNION�Ɋւ��鎎��", "SELECT\n" + "        a\n" + "        ,b\n"
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
                .format("select user_name || '����' as �p���t���� from ���Ӑ�\n");
        assertEquals("���s�Ɋւ��鎎��", "SELECT\n"
                + "        user_name || '����' AS �p���t����\n" + "    FROM\n"
                + "        ���Ӑ�\n", strResult);
        // System.out.println(strResult);
    }
}
