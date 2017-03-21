package cn.six.jsoup.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import oracle.jdbc.OracleTypes;

/**
 * 数据库工具类
 */
public class DBUtil {

	// private static final Log LOG = LogFactory.getLog(DBUtil.class);
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
			props.load(DBUtil.class.getResourceAsStream("/jdbc.properties"));
			url = (props.getProperty("jdbc.url"));
			username = (props.getProperty("jdbc.username"));
			password = (props.getProperty("jdbc.password"));
			driverClassName = (props.getProperty("jdbc.driverClassName"));
			poolMaxSize = Integer.parseInt(props
					.getProperty("c3p0.pool.maxPoolSize"));
			poolMinSize = Integer.parseInt(props
					.getProperty("c3p0.pool.minPoolSize"));

			dataSource.setDriverClass(driverClassName);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(username);
			dataSource.setPassword(password);
			dataSource.setMaxPoolSize(poolMaxSize);
			dataSource.setMinPoolSize(poolMinSize);
		} catch (NumberFormatException | IOException | PropertyVetoException e) {
			e.printStackTrace();
			throw new RuntimeException("==========连接池初始化失败==============");
		}
	}

	public static Connection openConnection() throws SQLException,
			ClassNotFoundException, IOException {
		return dataSource.getConnection();
	}

	public static void closeConnection() throws SQLException {
		dataSource.close();
	}

	public static List<Map<String, Object>> queryMapList(Connection con,
			String sql) throws SQLException, InstantiationException,
			IllegalAccessException {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Statement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = con.createStatement();
			rs = preStmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (null != rs && rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String name = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(name);
					map.put(name, value);
				}
				lists.add(map);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != preStmt)
				preStmt.close();
		}
		return lists;
	}

	public static List<Map<String, Object>> queryMapList(Connection con,
			String sql, Object... params) throws SQLException,
			InstantiationException, IllegalAccessException {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				preStmt.setObject(i + 1, params[i]);// 下标从1开始
			rs = preStmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (null != rs && rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String name = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(name);
					map.put(name, value);
				}
				lists.add(map);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != preStmt)
				preStmt.close();
		}
		return lists;
	}

	public static <T> List<T> queryBeanList(Connection con, String sql,
			Class<T> beanClass) throws SQLException, InstantiationException,
			IllegalAccessException {
		List<T> lists = new ArrayList<T>();
		Statement stmt = null;
		ResultSet rs = null;
		Field[] fields = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			fields = beanClass.getDeclaredFields();
			for (Field f : fields)
				f.setAccessible(true);
			while (null != rs && rs.next()) {
				T t = beanClass.newInstance();
				for (Field f : fields) {
					String name = f.getName();
					try {
						Object value = rs.getObject(name);
						setValue(t, f, value);
					} catch (Exception e) {
					}
				}
				lists.add(t);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return lists;
	}

	public static <T> List<T> queryBeanList(Connection con, String sql,
			Class<T> beanClass, Object... params) throws SQLException,
			InstantiationException, IllegalAccessException {
		List<T> lists = new ArrayList<T>();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		Field[] fields = null;
		try {
			preStmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				preStmt.setObject(i + 1, params[i]);// 下标从1开始
			rs = preStmt.executeQuery();
			fields = beanClass.getDeclaredFields();
			for (Field f : fields)
				f.setAccessible(true);
			while (null != rs && rs.next()) {
				T t = beanClass.newInstance();
				for (Field f : fields) {
					String name = f.getName();
					try {
						Object value = rs.getObject(name);
						setValue(t, f, value);
					} catch (Exception e) {
					}
				}
				lists.add(t);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != preStmt)
				preStmt.close();
		}
		return lists;
	}

	public static <T> List<T> queryBeanList(Connection con, String sql,
			IResultSetCall<T> qdi) throws SQLException {
		List<T> lists = new ArrayList<T>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (null != rs && rs.next())
				lists.add(qdi.invoke(rs));
		} finally {
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return lists;
	}

	public static <T> List<T> queryBeanList(Connection con, String sql,
			IResultSetCall<T> qdi, Object... params) throws SQLException {
		List<T> lists = new ArrayList<T>();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				preStmt.setObject(i + 1, params[i]);
			rs = preStmt.executeQuery();
			while (null != rs && rs.next())
				lists.add(qdi.invoke(rs));
		} finally {
			if (null != rs)
				rs.close();
			if (null != preStmt)
				preStmt.close();
		}
		return lists;
	}

	public static <T> T queryBean(Connection con, String sql, Class<T> beanClass)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<T> lists = queryBeanList(con, sql, beanClass);
		if (lists.size() != 1)
			throw new SQLException("SqlError：期待一行返回值，却返回了太多行！");
		return lists.get(0);
	}

	public static <T> T queryBean(Connection con, String sql,
			Class<T> beanClass, Object... params) throws SQLException,
			InstantiationException, IllegalAccessException {
		List<T> lists = queryBeanList(con, sql, beanClass, params);
		if (lists.size() != 1)
			throw new SQLException("SqlError：期待一行返回值，却返回了太多行！");
		return lists.get(0);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> queryObjectList(Connection con, String sql,
			Class<T> objClass) throws SQLException, InstantiationException,
			IllegalAccessException {
		List<T> lists = new ArrayList<T>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			label: while (null != rs && rs.next()) {
				Constructor<?>[] constor = objClass.getConstructors();
				for (Constructor<?> c : constor) {
					Object value = rs.getObject(1);
					try {
						lists.add((T) c.newInstance(value));
						continue label;
					} catch (Exception e) {
					}
				}
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return lists;
	}

	public static <T> List<T> queryObjectList(Connection con, String sql,
			Class<T> objClass, Object... params) throws SQLException,
			InstantiationException, IllegalAccessException {
		List<T> lists = new ArrayList<T>();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				preStmt.setObject(i + 1, params[i]);
			rs = preStmt.executeQuery();
			label: while (null != rs && rs.next()) {
				Constructor<?>[] constor = objClass.getConstructors();
				for (Constructor<?> c : constor) {
					String value = rs.getObject(1).toString();
					try {
						@SuppressWarnings("unchecked")
						T t = (T) c.newInstance(value);
						lists.add(t);
						continue label;
					} catch (Exception e) {
					}
				}
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != preStmt)
				preStmt.close();
		}
		return lists;
	}

	public static <T> T queryObject(Connection con, String sql,
			Class<T> objClass) throws SQLException, InstantiationException,
			IllegalAccessException {
		List<T> lists = queryObjectList(con, sql, objClass);
		if (lists.size() != 1)
			throw new SQLException("SqlError：期待一行返回值，却返回了太多行！");
		return lists.get(0);
	}

	public static <T> T queryObject(Connection con, String sql,
			Class<T> objClass, Object... params) throws SQLException,
			InstantiationException, IllegalAccessException {
		List<T> lists = queryObjectList(con, sql, objClass, params);
		if (lists.size() != 1)
			throw new SQLException("SqlError：期待一行返回值，却返回了太多行！");
		return lists.get(0);
	}

	public static int execute(Connection con, String sql) throws SQLException {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			return stmt.executeUpdate(sql);
		} finally {
			if (null != stmt)
				stmt.close();
		}
	}

	public static int execute(Connection con, String sql, Object... params)
			throws SQLException {
		PreparedStatement preStmt = null;
		try {
			preStmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				preStmt.setObject(i + 1, params[i]);// 下标从1开始
			return preStmt.executeUpdate();
		} finally {
			if (null != preStmt)
				preStmt.close();
		}
	}

	// 执行一条sql 并返回主键值
	public static Integer executeRetrunKey(Connection con, String sql,
			Object... params) throws SQLException {
		Integer id = null;
		PreparedStatement preStmt = null;
		try {
			preStmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				preStmt.setObject(i + 1, params[i]);// 下标从1开始

			preStmt.executeUpdate();
			ResultSet generatedKeys = preStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
			return id;
		} finally {
			if (null != preStmt)
				preStmt.close();
		}
	}

	/**
	 * 面向对象， save方法 返回主键
	 */
	public static <T> Integer saveReturnKey(Connection connection,T obj) throws Exception {
		Integer id=null;
		PreparedStatement pstmt = null;
		String sql = "insert into ";

		if (obj == null)
			return 0;

		Class<? extends Object> clazz = obj.getClass();
		String objName = clazz.getName();
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
		try {
			pstmt = connection.prepareStatement(sql);

			for (int k = 0; k < list.size(); k++) {
				pstmt.setObject(k + 1, list.get(k));
			}
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
			return id;
		} finally {
			if (null != pstmt)
				pstmt.close();
		}
	}

	public static int[] executeAsBatch(Connection con, List<String> sqlList)
			throws SQLException {
		return executeAsBatch(con, sqlList.toArray(new String[] {}));
	}

	public static int[] executeAsBatch(Connection con, String[] sqlArray)
			throws SQLException {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			for (String sql : sqlArray) {
				stmt.addBatch(sql);
			}
			return stmt.executeBatch();
		} finally {
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	public static int[] executeAsBatch(Connection con, String sql,
			Object[][] params) throws SQLException {
		PreparedStatement preStmt = null;
		try {
			preStmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				Object[] rowParams = params[i];
				for (int k = 0; k < rowParams.length; k++) {
					Object obj = rowParams[k];
					preStmt.setObject(k + 1, obj);
				}
				preStmt.addBatch();
			}
			return preStmt.executeBatch();
		} finally {
			if (null != preStmt) {
				preStmt.close();
			}
		}
	}

	private static <T> void setValue(T t, Field f, Object value)
			throws IllegalAccessException {
		// TODO 以数据库类型为准绳，还是以java数据类型为准绳？还是混合两种方式？
		if (null == value)
			return;
		String v = value.toString();
		String n = f.getType().getName();
		if ("java.lang.Byte".equals(n) || "byte".equals(n)) {
			f.set(t, Byte.parseByte(v));
		} else if ("java.lang.Short".equals(n) || "short".equals(n)) {
			f.set(t, Short.parseShort(v));
		} else if ("java.lang.Integer".equals(n) || "int".equals(n)) {
			f.set(t, Integer.parseInt(v));
		} else if ("java.lang.Long".equals(n) || "long".equals(n)) {
			f.set(t, Long.parseLong(v));
		} else if ("java.lang.Float".equals(n) || "float".equals(n)) {
			f.set(t, Float.parseFloat(v));
		} else if ("java.lang.Double".equals(n) || "double".equals(n)) {
			f.set(t, Double.parseDouble(v));
		} else if ("java.lang.String".equals(n)) {
			f.set(t, value.toString());
		} else if ("java.lang.Character".equals(n) || "char".equals(n)) {
			f.set(t, (Character) value);
		} else if ("java.lang.Date".equals(n)) {
			f.set(t, new Date(((java.sql.Date) value).getTime()));
		} else if ("java.lang.Timer".equals(n)) {
			f.set(t, new Time(((java.sql.Time) value).getTime()));
		} else if ("java.sql.Timestamp".equals(n)) {
			f.set(t, (java.sql.Timestamp) value);
		} else {
			System.out.println("SqlError：暂时不支持此数据类型，请使用其他类型代替此类型！");
		}
	}

	public static void executeProcedure(Connection con, String procedureName,
			Object... params) throws SQLException {
		CallableStatement proc = null;
		try {
			proc = con.prepareCall(procedureName);
			for (int i = 0; i < params.length; i++) {
				proc.setObject(i + 1, params[i]);
			}
			proc.execute();
		} finally {
			if (null != proc)
				proc.close();
		}
	}

	public static boolean executeProcedureReturnErrorMsg(Connection con,
			String procedureName, StringBuffer errorMsg, Object... params)
			throws SQLException {
		CallableStatement proc = null;
		try {
			proc = con.prepareCall(procedureName);
			proc.registerOutParameter(1, OracleTypes.VARCHAR);
			for (int i = 0; i < params.length; i++) {
				proc.setObject(i + 2, params[i]);
			}
			boolean b = proc.execute();
			errorMsg.append(proc.getString(1));
			return b;
		} finally {
			if (null != proc)
				proc.close();
		}
	}

	public static <T> List<T> executeProcedureReturnCursor(Connection con,
			String procedureName, Class<T> beanClass, Object... params)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<T> lists = new ArrayList<T>();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			proc = con.prepareCall(procedureName);
			proc.registerOutParameter(1, OracleTypes.CURSOR);
			for (int i = 0; i < params.length; i++) {
				proc.setObject(i + 2, params[i]);
			}
			boolean b = proc.execute();
			if (b) {
				rs = (ResultSet) proc.getObject(1);
				while (null != rs && rs.next()) {
					T t = beanClass.newInstance();
					Field[] fields = beanClass.getDeclaredFields();
					for (Field f : fields) {
						f.setAccessible(true);
						String name = f.getName();
						try {
							Object value = rs.getObject(name);
							setValue(t, f, value);
						} catch (Exception e) {
						}
					}
					lists.add(t);
				}
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != proc)
				proc.close();
		}
		return lists;
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

	public static <T> List<List<T>> listLimit(List<T> lists, int pageSize) {
		List<List<T>> llists = new ArrayList<List<T>>();
		for (int i = 0; i < lists.size(); i = i + pageSize) {
			try {
				List<T> list = lists.subList(i, i + pageSize);
				llists.add(list);
			} catch (IndexOutOfBoundsException e) {
				List<T> list = lists.subList(i, i + (lists.size() % pageSize));
				llists.add(list);
			}
		}
		return llists;
	}

	/*
	 * public static void executeProcedure(Connection con, String sql) {
	 * CallableStatement callStmt = null; try { callStmt = con.prepareCall(sql);
	 * callStmt.exe } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

}
