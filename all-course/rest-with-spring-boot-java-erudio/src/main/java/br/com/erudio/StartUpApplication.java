package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartUpApplication {
    public static void main(String[] args) {
	SpringApplication.run(StartUpApplication.class, args);
	
	
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder("53cr37", 16, 3,Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256));
//        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder("53cr37", 16, 3,Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256));
//        
//        String result = passwordEncoder.encode("admin234");
//        System.out.println("My hash " + result);
        
	
    }
}
