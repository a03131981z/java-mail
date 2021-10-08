package enviando.mail.limadruga;
import javax.mail.MessagingException;

public class AppTest {
    
    @org.junit.Test
    public void testeEmail() throws InterruptedException, MessagingException {
    	
    	ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("mlindembergh@gmail.com, contato@jdevtreinamento.com.br", "Alex do JDev Treinamentos", "Testando e-mail com Java", "Esse texto é a descrição do meu e-mail");
    	
    	enviaEmail.enviaEmail();
    	/*Olhe as configurações smtp do seu email*/
		/*Caso o email não esteja sendo enviado então coloque um tempo de espera mais isso só pode ser usado para testes.*/
		Thread.sleep(5000);
    }
}
