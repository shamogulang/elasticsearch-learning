package cn.oddworld.concurent;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class EncrTest {

    public static void main(String[] args) {
//        //这里使用32位的字符串作为秘钥,长度可以自行调整 更多请自行百度
//        String secretkey="c62ae32096004ba281032d3771f2bc94";
//        String code = "hello word";
//        String data_key = secretkey.substring(0, 16); //前16位用于参数加密
//        String sign_key = secretkey.substring(16);    //后16位用于签名窜加密
//        String encryptData = org.apache.commons.codec.binary.Base64.encodeBase64String(AES.encode(code, data_key));
//        String signData = DigestUtils.sha1Hex(code + sign_key);//sha1Hex加密 类似MD5 当然你也可以用MD5
//        System.out.println("加密后的数据:"+encryptData); //加密后的数据
//        System.out.println("加密后的签名串："+signData);//加密后的签名串 跟数据匹配确保唯一
//
//        //解密
//        String respPlain = AES.decode(org.apache.commons.codec.binary.Base64.decodeBase64(encryptData), data_key);
//        System.out.println("解密的数据:"+respPlain);//解密后的数据
//        String new_signData = DigestUtils.sha1Hex(respPlain + sign_key);
//        System.out.println("解密后的签名串："+new_signData);//解密后的签名串

        String code = "jeffchan codec";
        String encode_code = Base64.encodeBase64String(code.getBytes());
        System.out.println("加密后："+encode_code);
        String decode_code = new String(Base64.decodeBase64(encode_code));
        System.out.println("解密后："+decode_code);
    }
}
