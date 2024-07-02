package com.baking.bakingapi.config;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * <p>
 *  MD5+salt密码加密配置
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-03
 */
public class MD5Config {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass) {
        String str = String.valueOf(salt.charAt(0))+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = String.valueOf(salt.charAt(0))+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        return formPassToDBPass(formPass, saltDB);
    }
    //编写主类进行测试
    public static void main(String[] args) {
        String s = "Kodak";
        System.out.println("原始：" + s);
        System.out.println("加密后："+MD5Config.inputPassToFormPass(s));
    }
}
