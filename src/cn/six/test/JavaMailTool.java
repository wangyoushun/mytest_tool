/**
 * 
 */
package cn.six.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * @author : wangyoushun
 * @createTime : 2017年8月22日 下午2:56:32
 * @version : 1.0
 * @description
 */
public class JavaMailTool {// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
	public static String myEmailAccount = "jenkins@9tongmail.com";
	public static String myEmailPassword = "9Tongadmin";

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	public static String myEmailSMTPHost = "smtp.exmail.qq.com";

	// 收件人邮箱（替换为自己知道的有效邮箱）
	// public static String receiveMailAccount = "2436093367@qq.com";

	public static void sendEmail(String receiveMailAccount, List<File> fileList, String sendUserName,
			String receiveUserName, String subject) throws Exception {
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP
																// 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
		// 3. 创建一封邮件
		MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, fileList, sendUserName,
				receiveUserName, subject);
		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();
		// 5. 使用 邮箱账号 和 密码 连接邮件服务器
		// 这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
		transport.connect(myEmailAccount, myEmailPassword);
		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人,
		// 抄送人, 密送人
		transport.sendMessage(message, message.getAllRecipients());
		// 7. 关闭连接
		transport.close();
	}

	/**
	 * 创建一封邮件
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,
			List<File> fileList, String sendUserName, String receiveUserName, String subject) throws Exception {
		// 1. 创建邮件对象
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, sendUserName, "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.addRecipient(RecipientType.TO, new InternetAddress(receiveMail, receiveUserName, "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject(subject, "UTF-8");
		MimeMultipart mm = new MimeMultipart();
		for (File file : fileList) {
			// 9. 创建附件“节点”
			MimeBodyPart attachment = new MimeBodyPart();
			DataHandler dh2 = new DataHandler(new FileDataSource(file)); // 读取本地文件
			attachment.setDataHandler(dh2); // 将附件数据添加到“节点”
			attachment.setFileName(MimeUtility.encodeText(dh2.getName())); // 设置附件的文件名（需要编码）
			mm.addBodyPart(attachment);
		}

		// 11. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
		message.setContent(mm);

		// 12. 设置发件时间
		message.setSentDate(new Date());

		// 13. 保存上面的所有设置
		message.saveChanges();

		return message;
	}

	public static void main(String[] args) {
		String receiveMailAccount = "2436093367@qq.com";
		List<File> fileList = new ArrayList<File>();
		fileList.add(new File("F://21.xlsx"));
		fileList.add(new File("F://22.xlsx"));
		fileList.add(new File("F://23.xlsx"));
		String sendUserName = "aa";
		String receiveUserName = "bb";
		String subject = "test";

		try {
			JavaMailTool.sendEmail(receiveMailAccount, fileList, sendUserName, receiveUserName, subject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
