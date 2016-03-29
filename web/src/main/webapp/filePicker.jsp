<jsp:useBean id="bussinessRunner" class="com.mindstorm.apputils.BusinessRunner"/>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
上传文件程序应用示例
<form action="/web/do_upload" method="post" enctype="multipart/form-data">
    <%-- 类型enctype用multipart/form-data，这样可以把文件中的数据作为流式数据上传，不管是什么文件类型，均可上传。--%>
    请选择要上传的文件1<input type="file" name="upfile" size="50">
    请选择要上传的文件2<input type="file" name="upfile" size="50">
    <input type="submit" value="提交">
</form>
</html>
