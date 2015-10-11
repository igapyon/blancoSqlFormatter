/*
 * blanco Framework
 * Copyright (C) 2004-2006 WATANABE Yoshinori
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.commons.sql.format;

/**
 * BlancoSqlFormatter: SQL���`�c�[��. SQL�������߂�ꂽ���[���ɏ]�����`���܂��B
 * 
 * �t�H�[�}�b�g�����{���邽�߂ɂ́A���͂����SQL��SQL���Ƃ��đÓ��ł��邱�Ƃ��O������ƂȂ�܂��B
 * 
 * ���̃N���X����������SQL���`�̃��[���ɂ��ẮA���LURL���Q�Ƃ��������B
 * http://homepage2.nifty.com/igat/igapyon/diary/2005/ig050613.html
 * 
 * ���̃N���X�͊e�탆�[�e�B���e�B�������킦��N���X�ł�.
 * 
 * @author WATANABE Yoshinori (a-san) : original version at 2005.07.04.
 * @author IGA Tosiki : marge into blanc Framework at 2005.07.04
 */
public class BlancoSqlUtil {
    /**
     * �������u��������. ���ꕶ������ɕ����̕ϊ��Ώۂ������Ă��悢�B
     * 
     * @param argTargetString
     *            �����ΏۂƂȂ镶����B
     * @param argFrom
     *            �ϊ��O�̕�����B(ex." <")
     * @param argTo
     *            �ϊ���̕�����B(ex."&lt;")
     * @return �u�����ꂽ��̕�����B
     */
    public static String replace(final String argTargetString,
            final String argFrom, final String argTo) {
        String newStr = "";
        int lastpos = 0;

        for (;;) {
            final int pos = argTargetString.indexOf(argFrom, lastpos);
            if (pos == -1) {
                break;
            }

            newStr += argTargetString.substring(lastpos, pos);
            newStr += argTo;
            lastpos = pos + argFrom.length();
        }

        return newStr + argTargetString.substring(lastpos);
    }
}
