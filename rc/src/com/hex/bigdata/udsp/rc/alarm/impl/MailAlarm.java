package com.hex.bigdata.udsp.rc.alarm.impl;

import com.hex.bigdata.udsp.rc.alarm.Alarm;
import com.hex.bigdata.udsp.rc.alarm.impl.model.MailConfig;
import com.hex.bigdata.udsp.rc.alarm.model.Config;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;

/**
 * 邮件警报类
 */
@Component("com.hex.bigdata.udsp.rc.alarm.impl.MailAlarm")
public class MailAlarm implements Alarm {
    @Override
    public void send(Config config, String message) throws Exception {
        MailConfig mailConfig = new MailConfig(config.getPropertyMap());
        HtmlEmail email = new HtmlEmail();
        email.setTextMsg(message);
        email.setHostName(mailConfig.getMailSmtpHost());
        email.setSmtpPort(mailConfig.getMailSmtpPort());
        email.setSSLCheckServerIdentity(mailConfig.getMailSmtpSslCheckServerIdentity());
        String username = mailConfig.getMailSmtpUsername();
        String password = mailConfig.getMailSmtpPassword();
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            email.setAuthentication(username, password);
        }
        email.setFrom(mailConfig.getMailSmtpFrom());
        email.setSubject(mailConfig.getMailSmtpSubject());
        String to = mailConfig.getMailSmtpTo();
        if (StringUtils.isNotBlank(to)) {
            if (to.contains(";")) {
                email.addTo(to.split(";"));
            } else {
                email.addTo(to);
            }
        }
        String cc = mailConfig.getMailSmtpCc();
        if (StringUtils.isNotBlank(cc)) {
            if (cc.contains(";")) {
                email.addCc(cc.split(";"));
            } else {
                email.addCc(cc);
            }
        }
        String bcc = mailConfig.getMailSmtpBcc();
        if (StringUtils.isNotBlank(bcc)) {
            if (bcc.contains(";")) {
                email.addBcc(bcc.split(";"));
            } else {
                email.addBcc(bcc);
            }
        }
        email.send();
    }
}
