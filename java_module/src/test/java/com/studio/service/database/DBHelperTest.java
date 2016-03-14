package com.studio.service.database;

import org.junit.Test;


/**
 * Created by Administrator on 2016/3/10.
 */
public class DBHelperTest {

    @org.junit.Test
    public void testQueryUser() throws Exception {
//        User user = new DBHelper().queryUser("123");
//        BaseTemplate<ITemplateMsg, User> temp = new BaseTemplate<>(true, null, user, "000");
//        Assert.assertEquals(temp.toString(), temp);
    }

    @org.junit.Test
    public void testCreateDB() throws Exception {

    }

    @org.junit.Test
    public void testInsertUser() throws Exception {
//        InputStream inputXML = new BufferedInputStream(new FileInputStream("D:\\Idea workspace\\GradleWebAppSample-master\\java_module\\src\\main\\resources\\user_xls_cfg.xml"));
//        XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
//        InputStream inputXLS = new BufferedInputStream(new FileInputStream("D:\\Idea workspace\\GradleWebAppSample-master\\java_module\\src\\main\\resources\\xls_res.xlsx"));
//        Student stu = new Student();
//        List<Student> stus = new ArrayList();
//        List<Student> stus1 = new ArrayList();
//        Map beans = new HashMap();
//        beans.put("stu", stu);
//        beans.put("stus", stus);
//        beans.put("stus1", stus1);
//        XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
//        System.out.print("*****\n");
//        System.out.print("*****\n");
//        System.out.print("*****\n");
//        System.out.println("stu:" + stu);
//        System.out.println("stus:" + stus);
//        System.out.print("*****\n");
//        System.out.print("*****\n");
//        System.out.print("*****\n");
//        Assert.assertEquals(true, readStatus.isStatusOK());
    }

    @Test
    public void testHqlUser() throws Exception {
//        User user = new DBHelper().hqlUser("123");
//        Assert.assertEquals("123", user.uid);
    }

    @Test
    public void testHqlInsertUser() throws Exception {
//        Assert.assertEquals(true,new DBHelper().hqlInsertUser("12345"));
    }

    @Test
    public void testHqlQuery() throws Exception {
//        Assert.assertEquals(true,new DBHelper().hqlQuery("12345"));
    }
}