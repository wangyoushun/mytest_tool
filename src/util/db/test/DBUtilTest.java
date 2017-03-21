package util.db.test;


import static org.junit.Assert.fail;
import static util.db.DBUtil.executeAsBatch;
import static util.db.DBUtil.executeProcedure;
import static util.db.DBUtil.openConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.db.DBUtil;
import util.db.IResultSetCall;

/**
 * @author yinbin 注意： 可以替换Employess1为Employess2,看看查询结果有什么区别。。
 */
public class DBUtilTest {

	private Connection con = null;

	@Before
	public void setUp() throws Exception {
		try {
			con = DBUtil.openConnection();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
		try {
			DBUtil.closeConnection();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryBeanListConnectionStringClassOfT() {
		String sql = "SELECT * FROM employees";
		try {
			List<Employees1> emList = DBUtil.queryBeanList(con, sql, Employees1.class);
			print(emList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryBeanListConnectionStringClassOfTObjectArray() {
		String sql = "SELECT * FROM employees t WHERE t.salary > ? and T.JOB_ID = ?";
		try {
			List<Employees1> emList = DBUtil.queryBeanList(con, sql, Employees1.class, 5000, "ST_MAN");
			print(emList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryBeanListConnectionStringIResultSetCallOfTObjectArray() {
		String sql = "SELECT first_name, last_name, salary FROM employees t WHERE t.salary > ? and T.JOB_ID = ?";
		try {
			List<Employees1> emList = DBUtil.queryBeanList(con, sql, new IResultSetCall<Employees1>() {
				public Employees1 invoke(ResultSet rs) throws SQLException {
					Employees1 e = new Employees1();
					e.setFirst_name(rs.getString("first_name"));
					e.setLast_name(rs.getString("last_name"));
					e.setSalary(rs.getDouble("salary"));
					return e;
				}
			}, 5000, "ST_MAN");
			print(emList);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryObjectListConnectionStringClassOfT() {
		String sql = "SELECT email FROM employees t";
		try {
			List<String> lists = DBUtil.queryObjectList(con, sql, String.class);
			print(lists);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryObjectListConnectionStringClassOfTObjectArray() {
		String sql = "SELECT salary FROM employees t WHERE t.salary > ? and T.JOB_ID = ?";
		try {
			List<Double> lists = DBUtil.queryObjectList(con, sql, Double.class, 2000, "ST_MAN");
			print(lists);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryBeanConnectionStringClassOfT() {
		String sql = "SELECT * FROM employees t WHERE t.employee_id in (120)";
		try {
			Employees1 emp = DBUtil.queryBean(con, sql, Employees1.class);
			print(emp);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryBeanConnectionStringClassOfTObjectArray() {
		String sql = "SELECT * FROM employees t WHERE t.employee_id = ?";
		try {
			Employees1 emp = DBUtil.queryBean(con, sql, Employees1.class, 120);
			print(emp);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryObjectConnectionStringClassOfT() {
		String sql = "SELECT email FROM employees t WHERE t.employee_id in (120)";
		try {
			String s = DBUtil.queryObject(con, sql, String.class);
			print(s);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryObjectConnectionStringClassOfTObjectArray() {
		String sql = "SELECT salary FROM employees t WHERE t.employee_id in (?)";
		try {
			Double d = DBUtil.queryObject(con, sql, Double.class, 12);
			print(d);
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testExecuteConnectionStringObjectArray() {
		String sql = "UPDATE employees t SET t.salary =? WHERE t.employee_id =?";
		try {
			con.setAutoCommit(false);
			int count = DBUtil.execute(con, sql, 20000, 120);
			Assert.assertTrue(count == 1);
			sql = "SELECT t.salary FROM employees t WHERE t.employee_id =?";
			Double d = DBUtil.queryObject(con, sql, Double.class, 120);
			Assert.assertTrue(d - 20000 == 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testQueryMapList() {
		String sql = "SELECT first_name, last_name, salary FROM employees t WHERE t.salary > ? and T.JOB_ID = ?";
		try {
			List<Map<String, Object>> lists = DBUtil.queryMapList(con, sql, 3000, "ST_MAN");
			print(lists);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testExecuteProcedure() {
		try {
			executeProcedure(openConnection(), "{CALL prc_updatedata_for_daochong(?,?,?,?)}", "3000000993447731",
					"060000019213", "50", "0010701848");
			System.out.println("执行存储过程更新采购订单表上的数据成功");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testExecuteAsBatch() {
		try {
			List<String> sqlList = new ArrayList<String>();
			sqlList.add("UPDATE sm_user t SET t.password = 'ok' WHERE t.row_id = '232s43' ");
			sqlList.add("UPDATE sm_user t SET t.password = 'ok' WHERE t.row_id = '232f42' ");
			sqlList.add("UPDATE sm_user t SET t.password = 'ok' WHERE t.row_id = '23g2423' ");
			sqlList.add("UPDATE sm_user t SET t.password = 'ok' WHERE t.row_id = '232434s' ");
			executeAsBatch(openConnection(), sqlList);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testExecuteAsBatchForPre() {
		try {
			executeAsBatch(con, "UPDATE employees t SET t.first_name = ? WHERE t.last_name = ? ", new Object[][] {
					{ "ok", "235jklsd" }, { "no", "jg4ti324" }, { "no1", "111" }, { "no2", "32423" } });
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	private void print(Object obj) {
		if (obj instanceof List) {
			List list = (List) obj;
			for (Object o : list) {
				if (o instanceof Map) {
					Map<String, Object> map = (Map<String, Object>) o;
					Set<String> set = map.keySet();
					for (String key : set) {
						Object value = map.get(key);
						System.out.print(key + ":" + value + "\t");
					}
					System.out.println();
				} else {
					System.out.println(o);
				}
			}
			System.out.println("总共查询出数据数量是：" + list.size());
		} else {
			System.out.println(obj);
		}
	}

}
