package com.project.quotes.service;

import com.project.quotes.model.ConfirmationToken;
import com.project.quotes.model.Quote;
import com.project.quotes.model.User;
import com.project.quotes.repository.UserRepository;
import com.project.quotes.request.LoginRequest;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.UUID;

@CrossOrigin
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
//                String.format("Cannot find User by Username %s", email)));
//    }

    public User getUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Cannot find User by Username %s", email)));
    }

    //TODO migrate to spring security
    public String login(LoginRequest request){
        User user = getUserByEmail(request.getEmail());

        boolean validPassword = bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword());

        if(user == null && !validPassword){
            throw new IllegalStateException("Invalid Login Credentials");
        }
        if(!user.isEnabled()){
            throw new IllegalStateException("Account is Disabled");
        }

        //TODO send user to db and send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public String signup(User user) {
        System.out.println("req recieved");
        System.out.println(user);
        boolean userExists = userRepository.findByEmail(user.getUsername()).isPresent();
        if(userExists)
            throw new IllegalStateException("Email already taken");
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        //TODO send user to db and send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO send email

        return token;
    }

    public void enableUser(String email) {
        User savedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find User by Email %s", email)));

        savedUser.setEnabled(true);

        userRepository.save(savedUser);

    }
}

