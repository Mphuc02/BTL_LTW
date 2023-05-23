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
<%@ page import="com.example.btl_web.dto.UserDto" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%
    String userIdStr = request.getParameter("id");
    String statsStr = request.getParameter("status");
    UserDto editUser = null;
    if(userIdStr != null && statsStr != null)
    {
        editUser = new UserDto();
        editUser.setUserId(Long.parseLong(userIdStr));
        editUser.setStatus(Integer.parseInt(statsStr));
    }

    Gson gson = new Gson();
    String userJson = gson.toJson(editUser);

    String cookieValue = CookieUtils.getInstance().getValue(request, "JSESSIONID", request.getSession().getId()).toString();

    String url = "http://localhost:8080/api-admin-user";
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpPut sendApi = new HttpPut(url);
    StringEntity jsonEntity = new StringEntity(userJson);
    sendApi.addHeader("content-type", "application/json");
    sendApi.setHeader("Cookie", "JSESSIONID=" + cookieValue);
    sendApi.setEntity(jsonEntity);

    //Nhận phản hồi
    HttpResponse apiResponse = httpClient.execute(sendApi);
    HttpEntity entity = apiResponse.getEntity();
    String responseString = EntityUtils.toString(entity, "UTF-8");
    String []error = (new ObjectMapper()).readValue(responseString, String[].class);

    String status = "alert";
    //System.out.println(responseString);

    int statusCode = apiResponse.getStatusLine().getStatusCode();
    if(statusCode == 200)
    {
        status = "notice";
    }

    request.setAttribute("status", status);
    request.setAttribute("message", error[0]);

    RequestDispatcher rd = request.getRequestDispatcher(Constant.Admin.USERS_JSP);
    rd.forward(request, response);
%>