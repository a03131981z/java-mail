package enviando.mail.limadruga;
import java.io.IOException;

import javax.mail.MessagingException;

public class AppTest {
    
    @org.junit.Test
    public void testeEmail() throws IOException, Exception {
    	
    	StringBuilder stringBuilderTextoEmail = new StringBuilder();
    	stringBuilderTextoEmail.append("<br>Olá<br/>");
    	stringBuilderTextoEmail.append("<h2>Você está recebendo o acesso ao curso de Java<h2/> <br/><br/>");
    	stringBuilderTextoEmail.append("Para ter acesso clique no botão abaixo.<br/><br/>");
    	stringBuilderTextoEmail.append("<b>Login:</b> limadruga@gmail.com<br/>");
    	stringBuilderTextoEmail.append("<b>Senha:</b> hulkthor<br/><br/>");
    	stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"http://projetojavaweb.com/certificado-aluno/login\" style=\"color:#2525a7; padding: 14px 25px; text-align: center; text-decoration: none; display: inline-block; border-radius: 30px; font-size: 20px; font-family: courier; border: 3px solid green; background-color: #99DA39;\">Acessar portal do aluno</a><br/>");
    	
    	stringBuilderTextoEmail.append("<span style=\"font-size: 8px\">Ass.: Lindembergh Madruga</span>");
    	
    	ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("mlindembergh@gmail.com, contato@jdevtreinamento.com.br", "Alex do JDev Treinamentos", "Testando e-mail com Java", 
    			stringBuilderTextoEmail.toString());
    	
    	enviaEmail.enviarEmailAnexo(true);
    	/*Olhe as configurações smtp do seu email*/
		/*Caso o email não esteja sendo enviado então coloque um tempo de espera mais isso só pode ser usado para testes.*/
		Thread.sleep(5000);
    }
}
