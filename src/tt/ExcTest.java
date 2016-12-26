package tt;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExcTest {

	@Test
	public void testName() throws Exception {
		Runtime rt = Runtime.getRuntime();
		Process exec = rt
				.exec("cmd /c start exp dzpzd/dzpzd@172.20.34.214:1521/orcl file=e://table20161202.dmp tables=(ETCMP_DEPT)");
		int waitFor = exec.waitFor();

	}
}
