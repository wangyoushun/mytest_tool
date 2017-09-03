package cn.six.utils.test;


import java.sql.Connection;
import java.util.Date;

import org.junit.Test;

import cn.six.utils.DBUtil;
import cn.six.utils.FileTool;

public class DBUtilTest {

	
	@Test
	public void testInsertData() throws Exception {
		Connection openConnection = DBUtil.openConnection();
		long start = System.currentTimeMillis();
		for(int i=0; i<100000; i++){
			Msg msg = new Msg();
			msg.setGid(i);
			msg.setCreateDate(i);
			msg.setCreateTime(new Date());
			msg.setContent("skdl --"+i);
			
			Integer saveReturnKey = DBUtil.saveReturnKey(openConnection, msg);
			System.out.println(saveReturnKey);
		}
		DBUtil.closeConnection();
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	@Test
	public void testCreateTable() throws Exception {
		String sqlStrToEntity = FileTool.sqlStrToEntity("D://tmp//sql.txt", true);
		System.out.println(sqlStrToEntity);
		
	}
	
	
}
