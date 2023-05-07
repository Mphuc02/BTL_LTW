package com.example.btl_web.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {
    private static CookieUtils cookieUtils;
    public static CookieUtils getInstance()
    {
        if(cookieUtils == null)
            cookieUtils = new CookieUtils();
        return cookieUtils;
    }
    public void addValue(HttpServletResponse resp, String key, String value)
    {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(-1);//Khi thoát khỏi trình duyệt sẽ xoá cookie
        resp.addCookie(cookie);
    }

    public Object getValue(HttpServletRequest req, String key, String sessionId)
    {
        Cookie cookies[] = req.getCookies();
        for(Cookie cookie: cookies)
        {
            if(cookie.getValue().equals(sessionId) && cookie.getName().equals(key))
            {
                return cookie.getValue();
            }
        }
        return null;
    }
}