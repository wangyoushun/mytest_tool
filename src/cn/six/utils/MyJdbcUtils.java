package cn.six.utils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC工具类 c3p0 连接池
 * 
 * @author iwantfly
 */
public class MyJdbcUtils {
	private static final Log log = LogFactory.getLog(MyJdbcUtils.class);
	private static String url = null;
	private static String username = null;
	private static String password = null;
	private static String driverClassName = null;
	private static int poolMaxSize = 20;
	private static int poolMinSize = 5;
	private static Properties props = new Properties();
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	static {
		try {
			props.load(MyJdbcUtils.class
					.getResourceAsStream("/jdbc.properties"));
		} catch (IOException e) {
			log.error("#ERROR# :系统加载sysconfig.properties配置文件异常，请检查！", e);
		}
		url = (props.getProperty("jdbc.url"));
		username = (props.getProperty("jdbc.username"));
		password = (props.getProperty("jdbc.password"));
		driverClassName = (props.getProperty("jdbc.driverClassName"));
		poolMaxSize = Integer.parseInt(props
				.getProperty("c3p0.pool.maxPoolSize"));
		poolMinSize = Integer.parseInt(props
				.getProperty("c3p0.pool.minPoolSize"));

		try {
			dataSource.setDriverClass(driverClassName);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(username);
			dataSource.setPassword(password);
			dataSource.setMaxPoolSize(poolMaxSize);
			dataSource.setMinPoolSize(poolMinSize);

		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * 创建一个数据库连接
	 * 
	 * @return 一个数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		// 创建数据库连接
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			log.error("#ERROR# :创建数据库连接发生异常，请检查！", e);
		}
		return conn;
	}

	/**
	 * 在一个数据库连接上执行一个静态SQL语句查询
	 * 
	 * @param conn
	 *            数据库连接
	 * @param staticSql
	 *            静态SQL语句字符串
	 * @return 返回查询结果集ResultSet对象
	 */
	public static ResultSet executeQuery(Connection conn, String staticSql) {
		ResultSet rs = null;
		try {
			// 创建执行SQL的对象
			Statement stmt = conn.createStatement();
			// 执行SQL，并获取返回结果
			rs = stmt.executeQuery(staticSql);
		} catch (SQLException e) {
			log.error("#ERROR# :执行SQL语句出错，请检查！\n" + staticSql, e);
		}
		return rs;
	}

	/**
	 * 在一个数据库连接上执行一批静态SQL语句
	 * 
	 * @param conn
	 *            数据库连接
	 * @param sqlList
	 *            静态SQL语句字符串集合
	 */
	public static void executeBatchSQL(Connection conn, List<String> sqlList) {
		try {
			// 创建执行SQL的对象
			Statement stmt = conn.createStatement();
			for (String sql : sqlList) {
				stmt.addBatch(sql);
			}
			// 执行SQL，并获取返回结果
			stmt.executeBatch();
		} catch (SQLException e) {
			log.error("#ERROR# :执行批量SQL语句出错，请检查！", e);
		}
	}

	public static void closeConnection(Connection conn) {
		if (conn == null)
			return;
		try {
			if (!conn.isClosed()) {
				// 关闭数据库连接
				conn.close();
			}
		} catch (SQLException e) {
			log.error("#ERROR# :关闭数据库连接发生异常，请检查！", e);
		}
	}

	/**
	 * 查询 返回一个map组成的list
	 * 
	 * @param sql
	 * @param b
	 * @param paramters
	 * @return list<map>
	 * @throws SQLException
	 * @date 2016-3-5 21:45:29
	 */
	public static List<Map<String, Object>> queryToListMap(String sql,
			boolean b, Object... paramters) throws SQLException {
		log.info("start====queryToListMap");
		long startTime = System.currentTimeMillis();

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;

		List<Map<String, Object>> rsList = null;
		connection = dataSource.getConnection();
		try {
			if (b) {
				// PreparedStatement
				log.info("#info#: paramters " + Arrays.toString(paramters));
				prepareStatement = connection.prepareStatement(sql);
				if (paramters.length > 0) {
					for (int i = 0; i < paramters.length; i++) {
						prepareStatement.setObject(i + 1, paramters[i]);
					}
				}
				rs = prepareStatement.executeQuery();
			} else {
				// Statement
				Statement createStatement = connection.createStatement();
				rs = createStatement.executeQuery(sql);
			}
			rsList = resultToListMap(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("#ERROR#: method + cn.six.utils.MyJdbcUtils.queryToList(String, boolean, Object...)");
			e.printStackTrace();
			throw new SQLException();
		} finally {
			closeConnection(connection);
		}
		long endTime = System.currentTimeMillis();
		log.info("end====queryToListMap, spend time is "
				+ (endTime - startTime) + " ms ");
		return rsList;
	}

	/**
	 * rs结果集转为list<map>
	 * 
	 * @param rs
	 * @return list<map>
	 * @throws SQLException
	 * @date 2016-3-5 21:45:29
	 */
	private static List<Map<String, Object>> resultToListMap(ResultSet rs)
			throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (rs != null) {
			while (rs.next()) {
				ResultSetMetaData metaData = rs.getMetaData();
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i < metaData.getColumnCount(); i++) {
					map.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 返回list pojo
	 * 
	 * @param sql
	 *            sql语句
	 * @param b
	 *            true为PreparedStatement, 反之是Statement
	 * @param clazz
	 *            要转换的pojo
	 * @param paramters
	 *            sql条件
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> queryToList(String sql, boolean b,
			Class<T> clazz, Object... paramters) throws Exception {
		log.info("start====queryToListMap");
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		long startTime = System.currentTimeMillis();
		List<T> list = new ArrayList<T>();
		connection = dataSource.getConnection();
		try {
			if (b) {
				// PreparedStatement
				log.info("#info#: paramters " + Arrays.toString(paramters));
				prepareStatement = connection.prepareStatement(sql);
				System.out.println(paramters.length);
				if (paramters.length > 0) {
					for (int i = 0; i < paramters.length; i++) {
						System.out.println("1;" + paramters[i]);
						prepareStatement.setObject(i + 1, paramters[i]);
					}
				}
				rs = prepareStatement.executeQuery();
			} else {
				// Statement
				Statement createStatement = connection.createStatement();
				rs = createStatement.executeQuery(sql);
			}
			list = resultToListObj(rs, clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("#ERROR#: method + cn.six.utils.MyJdbcUtils.queryToList(String, boolean, Object...)");
			e.printStackTrace();
			throw new SQLException();
		} finally {
			closeConnection(connection);
		}
		long endTime = System.currentTimeMillis();
		log.info("end====queryToListMap, spend time is "
				+ (endTime - startTime) + " ms ");
		return list;
	}

	/**
	 * 结果集转为对象
	 * 
	 * @param rs
	 *            结果集
	 * @param clazz
	 *            要转换的pojo类型
	 * @return list<T> 返回集合
	 * @throws SQLException
	 * @throws Exception
	 */
	private static <T> List<T> resultToListObj(ResultSet rs, Class<T> clazz)
			throws SQLException, Exception {
		List<T> list = new ArrayList<T>();
		if (rs != null) {
			T obj = clazz.newInstance();
			while (rs.next()) {
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					String key = underlineToCamel(metaData.getColumnLabel(i)
							.toLowerCase());
					Method[] methods = clazz.getMethods();
					for (Method method : methods) {
						String name = method.getName();
						if (name != null && name.length() > 3
								&& "set".equals(name.substring(0, 3))) {
							name = name.substring(3);
							if (name.equals(key.substring(0, 1).toUpperCase()
									+ key.substring(1))) {
								Class<?>[] parameterTypes = method
										.getParameterTypes();
								if (parameterTypes[0] == Integer.class) {
									method.invoke(obj, rs.getInt(i));
								} else if (parameterTypes[0] == String.class) {
									method.invoke(obj, rs.getString(i));
								} else if (parameterTypes[0] == Long.class) {
									method.invoke(obj, rs.getLong(i));
								} else if (parameterTypes[0] == Double.class) {
									method.invoke(obj, rs.getDate(i));
								} else if (parameterTypes[0] == Byte.class) {
									method.invoke(obj, rs.getByte(i));
								} else if (parameterTypes[0] == Float.class) {
									method.invoke(obj, rs.getFloat(i));
								} else if (parameterTypes[0] == Date.class) {
									method.invoke(obj, rs.getDate(i));
								}
							}
						}
					}
				}
				list.add(obj);
			}
		}
		return list;
	}

	/**
	 * old 废弃 map结果集 转为对象结果集
	 * 
	 * @param sql
	 * @param b
	 * @param clazz
	 * @param paramters
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> queryToList2(String sql, boolean b,
			Class<T> clazz, Object... paramters) throws Exception {
		List<Map<String, Object>> queryToListMap = queryToListMap(sql, b,
				paramters);
		List<T> list = new ArrayList<T>();
		for (Map<String, Object> map : queryToListMap) {
			T object = clazz.newInstance();
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				String lowerCase = key.toLowerCase();
				System.out.println(lowerCase);
				String keyNew = underlineToCamel(key.toLowerCase());
				// System.out.println(keyNew);
				Method[] declaredMethods = clazz.getDeclaredMethods();
				for (Method method : declaredMethods) {
					// System.out.println(method.getName());
					String name = method.getName();
					if (name != null && name.length() > 3
							&& "set".equals(name.substring(0, 3))) {
						name = name.substring(3);
						// System.out.println(name);
						if (name.equals(keyNew.substring(0, 1).toUpperCase()
								+ keyNew.substring(1))) {
							// System.out.println(name);
							Class<?>[] parameterTypes = method
									.getParameterTypes();
							if (parameterTypes[0] == Integer.class) {
								method.invoke(object,
										Integer.parseInt(map.get(key) + ""));
							} else if (parameterTypes[0] == String.class) {
								method.invoke(object, (String) map.get(key));
							}

							list.add(object);
						}

					}
				}

			}
		}

		return list;
	}

	/**
	 * 字符串下划线转为峰拖发
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		char ch = '_';
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == ch) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰转下划线
	 * 
	 * @param param
	 * @return
	 */
	public static String camelTounderline(String param) {
		// a-z：97-122
		// A-Z：65-90
		// 0-9：48-57
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);

			if (i > 0 && c >= 65 && c <= 90) { // 大写字符
				if (i < len) {
					sb.append("_");
					sb.append(Character.toLowerCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 打印sql
	 * 
	 * @param param
	 * @return
	 */
	public static String printSql(String sql, Object... objects) {
		char ch = '?';
		if (sql == null || "".equals(sql.trim())) {
			return "";
		}
		int len = sql.length();
		int count = 0;
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = sql.charAt(i);
			if (c == ch) {
				if (i < len) {
					sb.append("'" + objects[count++] + "'");
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 获取单个结果集
	 * 
	 * @param sql
	 * @param b
	 * @param paramters
	 * @return Object
	 * @throws SQLException
	 */
	public static Object getSingle(String sql, boolean b, Object... paramters)
			throws SQLException {
		Object result = null;
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		Statement createStatement = null;
		connection = dataSource.getConnection();
		if (b) {
			pstmt = connection.prepareStatement(sql);
			if (paramters.length > 0) {
				for (int i = 0; i < paramters.length; i++) {
					pstmt.setObject(i + 1, paramters[i]);
				}
			}
			rs = pstmt.executeQuery();

		} else {
			createStatement = connection.createStatement();
			rs = createStatement.executeQuery(sql);

		}
		if (rs != null && rs.next()) {
			result = rs.getObject(1);
		}

		closeConnection(connection);
		return result;
	}

	/**
	 * 执行静态sql
	 * 
	 * @param sql
	 * @param b
	 * @param paramters
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean executeSQL(String sql, boolean b, Object... paramters)
			throws SQLException {
		int count = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		Statement createStatement = null;
		connection = dataSource.getConnection();

		if (b) {
			pstmt = connection.prepareStatement(sql);
			if (paramters.length > 0) {
				for (int i = 0; i < paramters.length; i++) {
					pstmt.setObject(i + 1, paramters[i]);
				}
			}
			count = pstmt.executeUpdate();
		} else {
			createStatement = connection.createStatement();
			count = createStatement.executeUpdate(sql);
		}
		closeConnection(connection);
		log.info("#INFO#: count = " + count);
		return count == 0 ? false : true;
	}

	/**
	 * 面向对象， save方法
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> long save(T obj) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ";

		if (obj == null)
			return 0;

		connection = dataSource.getConnection();
		Class<? extends Object> clazz = obj.getClass();
		String objName = clazz.getName();
		// System.out.println(objName.substring(objName.lastIndexOf(".") + 1));
		sql += camelTounderline(objName.substring(objName.lastIndexOf(".") + 1))
				+ "( ";
		List<Object> list = new ArrayList<Object>();

		// 通过反射获取参数
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (name.length() > 3 && "get".equals(name.substring(0, 3))
					&& !"getClass".equals(name)) {
				sql += camelTounderline(name.substring(3)) + ",";
				list.add(method.invoke(obj));
			}
		}

		sql = sql.substring(0, sql.length() - 1) + ") values ( ";
		for (int j = 0; j < list.size(); j++) {
			sql += "?,";
		}

		sql = sql.substring(0, sql.length() - 1) + ")";

		connection = dataSource.getConnection();
		pstmt = connection.prepareStatement(sql);

		for (int k = 0; k < list.size(); k++) {
			pstmt.setObject(k + 1, list.get(k));
		}

		int executeUpdate = pstmt.executeUpdate();
		closeConnection(connection);
		return executeUpdate;
	}
}
