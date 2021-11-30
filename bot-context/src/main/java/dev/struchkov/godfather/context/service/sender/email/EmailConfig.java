package dev.struchkov.godfather.context.service.sender.email;

import lombok.Getter;

import java.util.Properties;

@Getter
public class EmailConfig {

    private Properties props = new Properties();
    private String username;
    private String password;

    private EmailConfig() {

    }

    public static Builder builder() {
        return new EmailConfig().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder smtpHost(String smtpHost) {
            EmailConfig.this.props.setProperty("mail.smtp.host", smtpHost);
            return this;
        }

        public Builder smtpSocketFactoryPort(Integer smtpSocketFactoryPortort) {
            EmailConfig.this.props.setProperty("mail.smtp.socketFactory.port", smtpSocketFactoryPortort.toString());
            return this;
        }

        public Builder smtpSocketFactoryClass(String smtpSocketFactoryClass) {
            EmailConfig.this.props.setProperty("mail.smtp.socketFactory.class", smtpSocketFactoryClass);
            return this;
        }

        public Builder smtpAuth(Boolean smtpAuth) {
            EmailConfig.this.props.setProperty("mail.smtp.auth", (smtpAuth) ? "true" : "false");
            return this;
        }

        public Builder smtpPort(Integer smtpPort) {
            EmailConfig.this.props.setProperty("mail.smtp.port", smtpPort.toString());
            return this;
        }

        public Builder email(String username) {
            EmailConfig.this.username = username;
            return this;
        }

        public Builder password(String password) {
            EmailConfig.this.password = password;
            return this;
        }

        public EmailConfig build() {
            return EmailConfig.this;
        }

    }
}
