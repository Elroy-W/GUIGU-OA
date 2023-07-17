package com.atguigu.security.custom;

/**
 * ClassName: LoginUserInfoHelper
 * Package: com.atguigu.security.custom
 * Description:
 *
 * @Author Elroy
 * @Create 2023/6/27 0:04
 * @Version 1.0
 */
public class LoginUserInfoHelper {

    private static ThreadLocal<Long> userId = new ThreadLocal<Long>();
    private static ThreadLocal<String> username = new ThreadLocal<String>();

    public static void setUserId(Long _userId) {
        userId.set(_userId);
    }
    public static Long getUserId() {
        return userId.get();
    }
    public static void removeUserId() {
        userId.remove();
    }
    public static void setUsername(String _username) {
        username.set(_username);
    }
    public static String getUsername() {
        return username.get();
    }
    public static void removeUsername() {
        username.remove();
    }
}