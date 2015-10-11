/*
 * 作成日: 2005/07/07
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package blanco.commons.sql.format;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author iga
 * 
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ: ウィンドウ - 設定 - Java - コード・スタイル -
 * コード・テンプレート
 */
public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for blanco.commons.sql.format");
        // $JUnit-BEGIN$
        suite.addTestSuite(BlancoSqlFormatterTest.class);
        suite.addTestSuite(BlancoSqlFormatterTest01.class);
        suite.addTestSuite(BlancoSqlFormatterTest02.class);
        suite.addTestSuite(BlancoSqlFormatterTest03.class);
        suite.addTestSuite(BlancoSqlFormatterTest04.class);
        // $JUnit-END$
        return suite;
    }
}
