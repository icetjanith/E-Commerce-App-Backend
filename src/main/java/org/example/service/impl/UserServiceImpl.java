package org.example.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.dto.SignInRequest;
import org.example.dto.Users;
import org.example.entity.UsersEntity;
import org.example.repository.UsersRepository;
import org.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JavaMailSender mailSender;
    public static final String SENDER_EMAIL = "taskinahned774@gmail.com";
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Users saveUser(Users user) {
        UsersEntity usersEntity = usersRepository.save(modelMapper.map(user, UsersEntity.class));

        String subject = "Registration Successful";
        String body = "Dear " + user.getFullName() + ",\n\n" +
                "Your registration was successful. You can now login to the system.\n\n" +
                "Your Id is : "+usersEntity.getUserId() +
                "Best regards,\n" +
                "Taskin Ahned";
        sendEmail(user.getEmail(), subject, body);

        return modelMapper.map(usersEntity, Users.class);
    }

    @Override
    public Users authenticateUser(SignInRequest signInRequest) throws Exception {
        UsersEntity byEmail = usersRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()->new Exception("Could not find"));
        if(byEmail.getPassword().equals(signInRequest.getPassword())){
            return modelMapper.map(byEmail, Users.class);
        }else{
            throw new Exception("Invalid password");
        }
    }

    boolean sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(SENDER_EMAIL);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);

            return true;
        } catch (MessagingException e) {

            throw new RuntimeException("Failed to send email", e);
        }
    }
}
