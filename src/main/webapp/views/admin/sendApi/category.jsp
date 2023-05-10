<%@ page import="com.example.btl_web.dto.CategoryDto" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.impl.client.HttpClientBuilder" %>
<%@ page import="org.apache.http.client.methods.HttpPut" %>
<%@ page import="org.apache.http.entity.StringEntity" %>
<%@ page import="com.example.btl_web.utils.CookieUtils" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="com.example.btl_web.constant.Constant" %>
<%@ page import="org.apache.http.HttpEntity" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%
    String categoryIdStr = request.getParameter("id");
    String statsStr = request.getParameter("status");
    CategoryDto editCategory = null;
    if(categoryIdStr != null && statsStr != null)
    {
        editCategory = new CategoryDto();
        editCategory.setCategoryId(Long.parseLong(categoryIdStr));
        editCategory.setStatus(Integer.parseInt(statsStr));
    }

    Gson gson = new Gson();
    String categoryJson = gson.toJson(editCategory);

    String cookieValue = CookieUtils.getInstance().getValue(request, "JSESSIONID", request.getSession().getId()).toString();

    String url = "http://localhost:8080/api-admin-category";
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpPut sendApi = new HttpPut(url);
    StringEntity jsonEntity = new StringEntity(categoryJson);
    sendApi.addHeader("content-type", "application/json");
    sendApi.setHeader("Cookie", "JSESSIONID=" + cookieValue);
    sendApi.setEntity(jsonEntity);

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

    RequestDispatcher rd = request.getRequestDispatcher(Constant.Admin.CATEGORIES_JSP);
    rd.forward(request, response);
%>