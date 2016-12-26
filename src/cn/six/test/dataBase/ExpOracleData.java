package cn.six.test.dataBase;

/**
 * java调用命令导入导出oracle数据
 */
import org.junit.Test;

public class ExpOracleData {

	String expStr = "cmd /c start exp dzpzd/dzpzd@172.20.34.214:1521/orcl file=e://table20161202.dmp tables=(ETCMP_DEPT)";
	String impStr = "cmd /c start imp dzpzd/dzpzd@172.20.34.214:1521/orcl file=e://table20161202.dmp tables=(ETCMP_DEPT)";

	@Test
	public void testName() throws Exception {
		Runtime rt = Runtime.getRuntime();
		Process exec = rt.exec(expStr);
		exec.waitFor();

		rt.exec(impStr);
		exec.waitFor();
	}
}
