package cn.oddworld;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangminghui
 * @create 2021-08-16 10:55
 */
public class SplitStrUtil {
    /**
     * 分割搜索关键字，"abcdefghijk" -> ["abcde","defgh","ghijk","jklmn"]
     *
     * abcdefghijkfasdfasdfasdfasdfasd
     *
     * abcde-hijkfasdfa-defgh-ghijk-jklmn
     */
    public static List<String> getSplitStr(String value) {
        List<String> result = new ArrayList();
        value = value.toLowerCase();
        int codePointCount = value.codePointCount(0, value.length());
        if(codePointCount <= 5){
            result.add(value);
        } else {
            for(int i = 0;; i = i + 3){
                if(i >= codePointCount - 5){
                    i = codePointCount - 5;
                    String splitStr = getSubstring(value, i, i + 5);
                    if (!StringUtils.isEmpty(splitStr)){
                        result.add(splitStr);
                    }
                    break;
                }
                String splitStr = getSubstring(value, i, i + 5);
                if (!StringUtils.isEmpty(splitStr)){
                    result.add(splitStr);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> bcdefgh = getSplitStr("bcdefg123123");
        System.out.println(bcdefgh);
    }

    public static String getSubstring(String source, int start, int end) {
        String result;
        try {
            result = source.substring(source.offsetByCodePoints(0, start),
                    source.offsetByCodePoints(0, end));
        }catch (Exception e) {
            result ="";
        }
        return result;
    }
}
