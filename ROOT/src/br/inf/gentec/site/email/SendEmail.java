package br.inf.gentec.site.email;

import br.inf.gentec.site.model.Cotacao;
import java.io.Serializable;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendEmail
    implements Serializable
{

    private static final long serialVersionUID = 1L;

    public SendEmail()
    {
    }

    public static void sendPromocao(Cotacao cotacao)
    {
        HtmlEmail email = new HtmlEmail();
        try
        {
            email.setHostName("mail.gentec.inf.br");
            email.setSmtpPort(587);
            email.setAuthentication("cotacao@gentec.inf.br", "123456");
            email.addTo("carlos@gentec.inf.br");
            email.setFrom("cotacao@gentec.inf.br", "Pedido de Cota\347\343o");
            email.setCharset("ISO-8859-1");
            email.setSubject("Gentec IT");
            email.setHtmlMsg((new StringBuilder("<html><body><h1> Empresa: ")).append(cotacao.getNomeEmpresa()).append("</h1>").append("<br/>").append("<div>").append("Nome de Cliente: ").append(cotacao.getNomeCliente()).append("<br/>").append("CNPJ: ").append(cotacao.getNumeroCnpj()).append("<br/>").append("Telefone: ").append(cotacao.getNumeroTelefone()).append("<br/>").append("E-mail: ").append(cotacao.getEmail()).append("<br/>").append("Observa\347\365es: ").append(cotacao.getObservacoes()).append("<br/>").append("</div>").append("</body>").append("</html>").toString());
            email.setTextMsg("Seu servidor de e-mail n\343o suporta mensagem HTML");
            email.send();
        }
        catch(EmailException e)
        {
            e.printStackTrace();
        }
    }
}