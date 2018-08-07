package cn.habitdiary.form.utils;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cooki操作工具类
 */
public class CookieUtil {

    public static final int COOKIE_MAX_AGE = 7 * 24 * 3600;

    public static void removeCookie(HttpServletRequest request,
                                    HttpServletResponse response, String name) {
        if (null == name) {
            return;
        }
        Cookie cookie = getCookie(request, name);
        if(null != cookie){
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || null == name || name.length() == 0) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        return cookie;
    }



    /**
     * 添加一条新的Cookie
     *
     * @param response
     * @param name
     * @param value
     * @param maxValue
     */
    public static void setCookie(HttpServletResponse response, String name,
                                 String value, int maxValue) {
        if (null == name) {
            return;
        }
        if (null == value) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxValue);
        response.addCookie(cookie);
    }
}
