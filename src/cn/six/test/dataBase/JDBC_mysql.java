package cn.six.test.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;

import cn.six.test.Lgdata;
import cn.six.test.User;
import cn.six.utils.JdbcUtils;
import cn.six.utils.MyJdbcUtils;

public class JDBC_mysql {

	@Test
	public void saveObj() throws Exception{
		int j=0;
		for(int i=0; i<10; i++){
			Lgdata lgdata = new Lgdata();
			lgdata.setId(UUID.randomUUID().toString());
			lgdata.setDepartureCode("wys");
			lgdata.setArriveCode("wys");
			lgdata.setPassengerTicketnum("12345");
			lgdata.setAircraftDate(new Date());
			 MyJdbcUtils.save(lgdata);
			j++;
		}
		
		
		System.out.println(j);
		
	}

	@Test
	public void testCamelTounderline() throws Exception {
		String s = "lgDataT";
		String camelTounderline = MyJdbcUtils.camelTounderline(s);
		System.out.println(camelTounderline);

	}

	@Test
	public void insertTest() throws SQLException {
		String sql = "insert into lgdata (id, ARRIVE_CODE, PASSENGER_TICKETNUM, DEPARTURE_CODE, AIRCRAFT_DATE) values(?,?,?,?,?)";
		String id = UUID.randomUUID().toString();
		System.out.println(sql);
		Object[] objs = { id, "wys", "3434545", "SHA", new Date() };
		String printSql = MyJdbcUtils.printSql(sql, objs);
		System.out.println(printSql);
		boolean b = MyJdbcUtils.executeSQL(sql, true, objs);
		System.out.println(b);
	}

	@Test
	public void updateTest() throws SQLException {
		String sql = "update lgdata t set t.ARRIVE_CODE=? where date_format(t.AIRCRAFT_DATE,'%Y-%m-%d')=?";
		Object[] objs = { "wys", "2016-03-07" };
		boolean executeSQL = MyJdbcUtils.executeSQL(sql, true, objs);
		System.out.println(executeSQL);
	}

	@Test
	public void deleteTest() throws SQLException {
		String sql = "delete from lgdata   where date_format(AIRCRAFT_DATE,'%Y-%m-%d')=?";
		Object[] objs = { "2016-03-07" };
		boolean executeSQL = MyJdbcUtils.executeSQL(sql, true, objs);
		System.out.println(executeSQL);
		String printSql = MyJdbcUtils.printSql(sql, objs);
		System.out.println(printSql);
	}

	@Test
	public void testCount() throws Exception {
		String sql = "select count(1) from lgdata where DEPARTURE_CODE=?";
		Object[] objs = { "PVG" };
		Object obj = MyJdbcUtils.getSingle(sql, true, objs);
		long a = (long) obj;
		System.out.println(a);
	}

	// test lgdata
	@Test
	public void queryMysql4() throws Exception {
		Object[] objs1 = { "\'7818530571330\'", "\'7818528214821\'",
				"\'8809790414384\'" };

		String sql = "select * from lgdata t where t.PASSENGER_TICKETNUM in(";
		for (Object object : objs1) {
			sql += "?,";
		}

		sql = sql.substring(0, sql.length() - 1) + ")";
		// String sql = "select * from sys_user";
		System.out.println(sql);
		// List<Map<String,Object>> list= MyJdbcUtils.queryToList(sql,true);

		Object[] objs = { "7818530571330", "7818528214821", "8809790414384" };

		List<Lgdata> list = MyJdbcUtils.queryToList(sql, true, Lgdata.class,
				objs);
		System.out.println(list.size());
		System.out.println(list);
		System.out.println("==========");
		String printSql = MyJdbcUtils.printSql(sql, objs);
		System.out.println(printSql);
		System.out.println(Arrays.toString(objs));
	}

	// list pojo
	@Test
	public void queryMysql3() throws Exception {
		String sql = "select * from sys_user where id>? and user_code!=? and user_password=?";
		// String sql = "select * from sys_user";
		System.out.println(sql);
		// List<Map<String,Object>> list= MyJdbcUtils.queryToList(sql,true);

		Object[] objs = { 23, "24", "admin" };
		List<User> list = MyJdbcUtils.queryToList(sql, true, User.class, objs);
		System.out.println(list.size());
		System.out.println(list);
		System.out.println("==========");
		String printSql = MyJdbcUtils.printSql(sql, objs);
		System.out.println(printSql);
		System.out.println(Arrays.toString(objs));
	}

	// listmap
	@Test
	public void queryMysql2() throws SQLException {
		String sql = "select * from sys_user where id>? and user_code!=?";
		// String sql = "select * from sys_user";
		System.out.println(sql);
		// List<Map<String,Object>> list= MyJdbcUtils.queryToList(sql,true);

		Object[] objs = { 215, "24" };
		List<Map<String, Object>> list = MyJdbcUtils.queryToListMap(sql, true,
				objs);
		System.out.println(list.size());
		System.out.println(list);
		for (Map<String, Object> map : list) {
			System.out.println(map.get("id") + ", " + map.get("user_code"));
		}
		System.out.println("==========");

	}

	private void preExecuteQuery(Connection connection) {
		// TODO Auto-generated method stub

	}

	// rs
	@Test
	public void queryMysql() throws SQLException {
		Connection connection = MyJdbcUtils.getConnection();
		String sql = "select * from sys_user where user_name=?";
		System.out.println(sql);
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "小王_24");
		ResultSet rs = prepareStatement.executeQuery();
		// rs.last();
		// System.out.println(rs.getRow());
		// rs.first();

		if (rs != null) {
			while (rs.next()) {

				String code = rs.getString("user_code");
				String name = rs.getString("user_name");
				System.out.println(code + ", " + name);

			}
		}

		JdbcUtils.closeConnection(connection);

	}

}
