package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.TipoDeMensagem;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo("alyson.siqueira@dbccompany.com");
        message.setSubject("Assunto 1");
        message.setText("Meu e-mail!!");
        emailSender.send(message);
    }

    public void sendEmail(PessoaDTO pessoaDTO, String tipoDeMensagem) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoaDTO.getEmail());
            if (tipoDeMensagem.equals(TipoDeMensagem.CREATE.getTipo())){
                mimeMessageHelper.setSubject("Cadastro realizado");
            } else if (tipoDeMensagem.equals(TipoDeMensagem.UPDATE.getTipo())) {
                mimeMessageHelper.setSubject("Cadastro atualizado");
            } else {
                mimeMessageHelper.setSubject("Cadastro deletado");
            }
            mimeMessageHelper.setText(getContentFromTemplate(pessoaDTO, tipoDeMensagem), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }

    }

    public String getContentFromTemplate(PessoaDTO pessoaDTO, String tipoDeMensagem) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", pessoaDTO.getNome());
        dados.put("id", pessoaDTO.getIdPessoa());
        dados.put("email", this.from);

        Template template;
        if(tipoDeMensagem.equals(TipoDeMensagem.CREATE.getTipo())){
            template = fmConfiguration.getTemplate("email-template-create.ftl");
        } else if (tipoDeMensagem.equals(TipoDeMensagem.UPDATE.getTipo())) {
            template = fmConfiguration.getTemplate("email-template-update.ftl");
        } else {
            template = fmConfiguration.getTemplate("email-template-delete.ftl");
        }
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public void sendEmailWithAddress(PessoaDTO pessoaDTO, EnderecoDTO enderecoDTO, String tipoDeMensagem) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoaDTO.getEmail());
            if (tipoDeMensagem.equals(TipoDeMensagem.CREATE.getTipo())){
                mimeMessageHelper.setSubject("Endereço cadastrado");
            } else if (tipoDeMensagem.equals(TipoDeMensagem.UPDATE.getTipo())) {
                mimeMessageHelper.setSubject("Endereço atualizado");
            } else {
                mimeMessageHelper.setSubject("Endereço deletado");
            }
            mimeMessageHelper.setText(getContentFromTemplateWithAddress(pessoaDTO, enderecoDTO, tipoDeMensagem), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }

    }

    public String getContentFromTemplateWithAddress(PessoaDTO pessoaDTO, EnderecoDTO enderecoDTO, String tipoDeMensagem) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", pessoaDTO.getNome());
        dados.put("id", pessoaDTO.getIdPessoa());
        dados.put("idEndereco", enderecoDTO.getIdEndereco());
        dados.put("email", this.from);

        Template template;
        if(tipoDeMensagem.equals(TipoDeMensagem.CREATE.getTipo())){
            template = fmConfiguration.getTemplate("email-template-address-create.ftl");
        } else if (tipoDeMensagem.equals(TipoDeMensagem.UPDATE.getTipo())) {
            template = fmConfiguration.getTemplate("email-template-address-update.ftl");
        } else {
            template = fmConfiguration.getTemplate("email-template-address-delete.ftl");
        }
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}
