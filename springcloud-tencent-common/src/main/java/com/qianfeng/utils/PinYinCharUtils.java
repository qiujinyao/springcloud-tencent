package com.qianfeng.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/29
 */
public class PinYinCharUtils {

        public  static  String  getPinYin(String  target){
            StringBuilder stringBuilder=new StringBuilder();
            HanyuPinyinOutputFormat   defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            char[] chars = target.toCharArray();
            for (char aChar : chars) {
                try {
                    String[] strings = PinyinHelper.toHanyuPinyinStringArray(aChar,defaultFormat);
                    stringBuilder.append(strings[0]);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
            return stringBuilder.toString();
        }
}
