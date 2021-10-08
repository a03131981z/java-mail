package enviando.mail.limadruga;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ObjetoEnviaEmail {

	private String userName = "mlindembergh@gmail.com";
	private String senha = "Arthur2011";
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	public ObjetoEnviaEmail(String lista, String nome, String assunto, String texto) {
		this.listaDestinatarios = lista;
		this.nomeRemetente = nome;
		this.assuntoEmail  = assunto;
		this.textoEmail = texto;
	}
	
	public void enviaEmail() throws MessagingException {
	
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
		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(userName, nomeRemetente));/*Quem está enviando*
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		
		message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
		message.setSubject(assuntoEmail); /*Assunto do email*/
		message.setText(textoEmail);
		
		Transport.send(message);
	}
}
