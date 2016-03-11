<jsp:useBean id="bussinessRunner" class="com.mindstorm.apputils.BusinessRunner"/>
<html>
<p>
    To query userInfo
    <br/>
    <%=bussinessRunner.queryUserInfo(123)%>
    <br/>
</p>
<p>
    To login,and result is
    <br/>
    <%=bussinessRunner.userLogin(123, "add")%>
    <br/>
</p>
</html>
