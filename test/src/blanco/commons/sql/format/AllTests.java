/*
 * �쐬��: 2005/07/07
 *
 * TODO ���̐������ꂽ�t�@�C���̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */
package blanco.commons.sql.format;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author iga
 * 
 * TODO ���̐������ꂽ�^�R�����g�̃e���v���[�g��ύX����ɂ͎��փW�����v: �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� -
 * �R�[�h�E�e���v���[�g
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
