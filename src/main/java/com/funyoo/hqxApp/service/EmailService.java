package com.funyoo.hqxApp.service;

import com.funyoo.hqxApp.dao.EmailDao;
import com.funyoo.hqxApp.model.EmailModel;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    EmailDao emailDao;

    public boolean sendCaptcha(String target, String captcha) throws Exception {
        String text = "<h2>【红旗下】欢迎您的注册！</h2><br/>" +
                "<p>您的验证码为 " + captcha +
                " 不要告诉别人哦~</p>";
        return sendEmail(target, "红旗下 验证码", text);
    }

    public boolean sendEmail(String target, String subject, String text) throws Exception {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        // 根据propertise创建会话
        Session session = Session.getInstance(props);

        // 根据会话创建邮件信息
        Message msg = new MimeMessage(session);
        // 邮件主题
        msg.setSubject(subject);

        // 创建文本"节点"
        MimeBodyPart mtext = new MimeBodyPart();
        // 这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        mtext.setContent(text, "text/html;charset=UTF-8");

        // 创建混合节点
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(mtext);
        // 将混合节点加入邮件中
        msg.setContent(multipart);

        // 设置邮件发送方
        msg.setFrom(new InternetAddress("401476968@qq.com"));

        // 开始会传输
        Transport transport = session.getTransport();
        // 连接邮箱   加入自己（发送方）授权码
        transport.connect("smtp.qq.com", "401476968@qq.com", "vuhptxrdvdexcahd");

//        List<EmailModel> recivers = emailDao.getRecivers();
//        // 给目标邮箱发送邮件
//        for (EmailModel reciver : recivers) {
//            transport.sendMessage(msg, new Address[] { new InternetAddress(reciver.getEmail()) });
//        }
        transport.sendMessage(msg, new Address[] {new InternetAddress(target)});

        transport.close();
        return true;
    }
}
