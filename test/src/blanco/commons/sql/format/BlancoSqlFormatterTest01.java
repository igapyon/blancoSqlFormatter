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
public class BlancoSqlFormatterTest01 extends TestCase {

    public void testFormatString01() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT col1 FROM table1 AS t1 WHERE col1>'�l \"value'");
        assertEquals("> �����Ɨאڂ��Ă���ꍇ�ɓK�؂ɏ����ł��邩�ǂ����̎���", "SELECT\n"
                + "        col1\n" + "    FROM\n" + "        table1 AS t1\n"
                + "    WHERE\n" + "        col1 > '�l \"value'", strResult);
        // System.out.println(strResult);
    }

    /**
     * String format �̃e�X�g���̃N���X(String)
     */
    public void testFormatString() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        // TODO: THEN �� ELSE �� ANSI-SQL���ǂ������m�F
        // TODO: FOR UPDATE�̎��������l���B
        // blanco�I�ɂ� # �͉p�����̈ꕔ�ł���B
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
        assertEquals("CASE WHEN�Ɋւ��鎎�� �A���_�[�X�R�A������", "SELECT\n"
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

        String strResult = formatter.format("SELECT ��1 AS col1,col��2 AS ��2col"
                + " FROM ���{��\��1 AS t1, table���{��2 as t2");
        assertEquals("���{��̗񖼁E�\���̎���", "SELECT\n" + "        ��1 AS col1\n"
                + "        ,col��2 AS ��2col\n" + "    FROM\n"
                + "        ���{��\��1 AS t1\n" + "        ,table���{��2 AS t2",
                strResult);
        // System.out.println(strResult);
    }

    public void testFormatString03() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        // <= ���K�؂ɏ����ł��邩�ǂ����̎���
        String strResult = formatter
                .format("SELECT \tcol1 FROM table1 AS t1\tWHERE col1 = '�l \t\"value'");
        assertEquals("�^�u�����̎���", "SELECT\n" + "        col1\n" + "    FROM\n"
                + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 = '�l \t\"value'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString04() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT col1 FROM table1 AS t1 WHERE col1<='�l \"value'");
        assertEquals("<=���K�؂ɏ����ł��邱�Ƃ̎���", "SELECT\n" + "        col1\n"
                + "    FROM\n" + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 <= '�l \"value'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString05() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT col1 FROM table1 AS t1 WHERE col1>='�l \"�l2'");
        assertEquals(">=���K�؂ɏ����ł��邱�Ƃ̎���", "SELECT\n" + "        col1\n"
                + "    FROM\n" + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 >= '�l \"�l2'", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString06() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter
                .format("SELECT t1.col1,t1.col2,col3 FROM table1 AS t1 WHERE col1>='�l' GROUP\n BY col1");
        assertEquals("ORDER BY, GROUP BY�̎���", "SELECT\n" + "        t1.col1\n"
                + "        ,t1.col2\n" + "        ,col3\n" + "    FROM\n"
                + "        table1 AS t1\n" + "    WHERE\n"
                + "        col1 >= '�l'\n" + "    GROUP BY\n" + "        col1",
                strResult);
        // System.out.println(strResult);
    }

    public void testFormatString07() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter.format("truncate table table1");
        assertEquals("TRUNCATE���̎���", "TRUNCATE\n" + "    TABLE\n"
                + "        table1", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString08() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        String strResult = formatter.format("drop table table1,table2");
        assertEquals("DROP TABLE�̎���", "DROP\n" + "    TABLE\n"
                + "        table1\n" + "        ,table2", strResult);
        // System.out.println(strResult);
    }

    public void testFormatString09() throws Exception {
        BlancoSqlFormatter formatter = new BlancoSqlFormatter(
                new BlancoSqlRule());

        // TODO: �^�̃T�C�Y�w��̍ۂɉ��s���Ă��܂��Ă���_�͕s���ł��B
        // TODO: ���݃Z�~�R�������������Ă��܂��Ă��܂��B
        String strResult = formatter
                .format("    CREATE TABLE table1 (colID DECIMAL( 10),name    CHAR VARYING(40),PRIMARY KEY(colID)        ); ");
        assertEquals("CREATE TABLE�̎����B����^�̃T�C�Y�ŉ��s����уZ�~�R�����������̕s���_������", "CREATE\n"
                + "    TABLE\n" + "        table1 (\n"
                + "            colID DECIMAL (10)\n"
                + "            ,name CHAR VARYING (40)\n"
                + "            ,PRIMARY KEY (colID)\n" + "        )\n;",
                strResult);
        // System.out.println(strResult);
    }

}
