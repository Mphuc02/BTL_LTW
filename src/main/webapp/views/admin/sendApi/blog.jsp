<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.impl.client.HttpClientBuilder" %>
<%@ page import="org.apache.http.entity.StringEntity" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.HttpEntity" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ page import="org.apache.http.client.methods.HttpPut" %>
<%@ page import="com.example.btl_web.dto.BlogDto" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.example.btl_web.utils.CookieUtils" %>
<%@ page import="com.example.btl_web.constant.Constant.*" %>
<%
    String cookieValue = CookieUtils.getInstance().getValue(request, "JSESSIONID", request.getSession().getId()).toString();

    String blogIdStr = request.getParameter("id");
    String statusStr = request.getParameter("status");

    BlogDto editBlog = new BlogDto();
    if(blogIdStr != null || statusStr != null)
    {
        editBlog.setBlogId(Long.parseLong(blogIdStr));
        editBlog.setStatus(Integer.parseInt(statusStr));
    }

    Gson gson = new Gson();
    String blogJson = gson.toJson(editBlog);

    String url = "http://localhost:8080/api-blog";
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpPut sendApi = new HttpPut(url);
    StringEntity params = new StringEntity(blogJson);
    sendApi.addHeader("content-type", "application/json");
    sendApi.setHeader("Cookie", "JSESSIONID=" + cookieValue);
    sendApi.setEntity(params);

    //Nhận phản hồi
    HttpResponse apiResponse = httpClient.execute(sendApi);
    HttpEntity entity = apiResponse.getEntity();
    String responseString = EntityUtils.toString(entity, "UTF-8");
    String status = "alert";
    //System.out.println(responseString);

    int statusCode = apiResponse.getStatusLine().getStatusCode();
    if(statusCode == 200)
    {
        status = "notice";
    }

    //Xoá bỏ "" ở đầu và cuối của Json
    responseString = responseString.replaceAll("\"", "");
    request.setAttribute("status", status);
    request.setAttribute("message", responseString);

    RequestDispatcher rd = request.getRequestDispatcher(Admin.BLOGS_JSP);
    rd.forward(request, response);
%>