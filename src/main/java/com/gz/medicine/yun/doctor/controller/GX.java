package com.gz.medicine.yun.doctor.controller;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by dlf on 2017/12/20 0020.
 * Email 1429264916@qq.com
 */
public class GX {
    public static void main(String[] args) {



        PooledDataSource pd=new  PooledDataSource("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@106.14.160.67:1521:centerxu", "centeradmin", "Eru43wPo");
        try {
            Connection coon= pd.getConnection();
            PreparedStatement pstm=  coon.prepareStatement("select  'ss' as aa from dual");
            ResultSet rs=pstm.executeQuery();
           while (rs.next()){

               System.out.println(rs.getString(1));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println( pd.toString());
    }
}
