package enviando.mail.limadruga;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppTest {
	
	private String userName = "mlindembergh@gmail.com";
	private String senha = "Arthur2011";
    
    @org.junit.Test
    public void testeEmail() {
    	/*Olhe as configurações smtp do seu email*/
		try {
			Properties properties = new Properties();
			
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");/*Autorização*/
			properties.put("mail.smtp.starttls", "true");/*Autenticação*/
			properties.put("mail.smtp.host","smtp.gmail.com");/*Servidor gmail Google*/
			properties.put("mail.smtp.port", "465");/*Porta do servidor*/
			properties.put("mail.smtp.socketFactory.port", "465");/*Especifica a porta a ser conectada pelo socket*/
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao SMTP*/
			
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, senha);
				}
			});
			Address[] toUser = InternetAddress.parse("mlindembergh@gmail.com, lindemberghferreira@gmail.com, arthurraquel3@gmail.com");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, "Alex JDev - Treinamentos"));/*Quem está enviando*/
			message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
			message.setSubject("Chegou email enviado com Java"); /*Assunto do email*/
			message.setText("Olá programador, vc acaba de receber um email enviado com Java do curso Formação Java Web do Alex");
			
			Transport.send(message);
			
			/*Caso o email não esteja sendo enviado então coloque um tempo de espera mais isso só pode ser usado para testes.*/
			Thread.sleep(5000);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
}
