<jsp:useBean id="bussinessRunner" class="com.mindstorm.apputils.BusinessRunner"/>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
    Api文档查询:
    <p>
        一,添加用户：
        1,URL
        /account/user_add
        2,METHOD
        POST
        3,PARAMS;
        参数名     是否必须    类型     描述
        uname       是        String    用户账号
        upassword  是        String      密码
        4,eg.
        http://192.168.1.101:8080/web/account/user_add?uname=zsf&upassword=zsfpas
        5,response

        success:
        {
        "access_token": "cd4b22f0b5ac4e2493460304cdee6ec8",//用户授权token
        "token_type": "bearer",                             //token类型
        "expires_in": 2592000,                              //过期时间
        "refresh_token": null,                              //
        "scope": "read",
        "uid": "1227f72da6114de38c42db82beefe22aq",
        "resvip": true
        }

        failure:

    </p>

</html>
