package br.inf.gentec.site.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator
{

    //private static final String PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    public EmailValidator()
    {
        pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2" +
",})$"
);
    }

    public void validate(FacesContext context, UIComponent component, Object obj)
        throws ValidatorException
    {
        matcher = pattern.matcher(obj.toString());
        if(!matcher.matches())
        {
            FacesMessage msg = new FacesMessage("E-mail: Erro de Valida\347\343o : E-mail inv\341lido.", "E-mail: Erro de Valida\347\343o : E-mail inv\341lido.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else
        {
            return;
        }
    }
}
