package enviando.mail.limadruga;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
	
	public void enviarEmail(boolean envioHtml) throws MessagingException {
	
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
			message.setFrom(new InternetAddress(userName, nomeRemetente));/*Quem está enviando*/
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		
		}finally {
			message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
			message.setSubject(assuntoEmail); /*Assunto do email*/
			
			if(envioHtml) {
				message.setContent(textoEmail, "text/html; charset=utf-8");
			}else {
				message.setText(textoEmail);
			}
			
			Transport.send(message);
		}
	}	
	
	public void enviarEmailAnexo(boolean envioHtml) throws IOException, Exception {
		
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
			message.setFrom(new InternetAddress(userName, nomeRemetente));/*Quem está enviando*/
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		
		}finally {
			message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
			message.setSubject(assuntoEmail); /*Assunto do email*/
			
			/*Parte 1 do e-mail que é texto e adescrição do e-mail*/
			MimeBodyPart corpoEmail = new MimeBodyPart();
			
			if(envioHtml) {
				corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
			}else {
				corpoEmail.setText(textoEmail);
			}
			List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());
			

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			
			int index = 0;
			for(FileInputStream fileInputStream: arquivos) {
				/*Parte 2 do e-mail que são os anexos em pdf*/
				MimeBodyPart anexoEmail = new MimeBodyPart();
				
				/*Onde é passado o simuladorDePDF você passa o seu arquivo gravado no banco de dados*/
				anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
				anexoEmail.setFileName("anexoemail"+index+".pdf");
				multipart.addBodyPart(anexoEmail);
				index++;
			}
			message.setContent(multipart);
			
			Transport.send(message);
		}
	}	
	
	/*
	 * Esse método simula o PDF ou qualquer arquivo que possa ser enviado por anexo no email
	 * Vc pode pegar o arquivo no seu banco de dados base64, byte[], Stream de arquivos.
	 * Pode estar em um banco de dados, ou em uma pasta.
	 * */
	private FileInputStream simuladorDePDF() throws Exception{
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("conteúdo do PDF anexo com Java Mail, esse é o texto do PDF"));
		document.close();
		
		return new FileInputStream(file);
	}
}
