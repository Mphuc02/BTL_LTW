package com.example.btl_web.utils;

import jakarta.servlet.http.HttpServletRequest;

public class SesstionUtils {
    private static SesstionUtils sesstionUtils = null;
    public static SesstionUtils getInstance()
    {
        if(sesstionUtils == null)
            sesstionUtils = new SesstionUtils();
        return sesstionUtils;
    }

    public void putValue(HttpServletRequest request, String key, Object value)
    {
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key)
    {
        return request.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest request, String key)
    {
        request.getSession().removeAttribute(key);
    }
}
