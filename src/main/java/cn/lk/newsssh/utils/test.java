package cn.lk.newsssh.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author BushRo
 * @Description: TOTO
 * @date 2019-05-17
 */
public class test {
    public static void main(String[] args) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //明文密码
        Object credentials = "1234";
        //盐值
        Object salt = ByteSource.Util.bytes("nchu");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
        SimpleHash simpleHash = new SimpleHash("MD5", "admin", "abcdefghijklmnopqrstuvwx", 1024);
    }
}
