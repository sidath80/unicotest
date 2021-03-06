package com.assignment.gcd.core;

import javax.jms.ConnectionFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.assignment.gcd.core.jpa.domain.IntNumber;
import com.assignment.gcd.core.jpa.repository.GcdRepository;


@SpringBootApplication
@EnableJms
public class GcdCoreApplication {
	
	
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the
		// message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		return factory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	public static void main(String[] args) {
		SpringApplication.run(GcdCoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(GcdRepository gcdRepository) {
		return (args) -> {
			gcdRepository.deleteAll();
			gcdRepository.save(new IntNumber(10));
			gcdRepository.save(new IntNumber(20));
			gcdRepository.save(new IntNumber(30));
			gcdRepository.save(new IntNumber(40));
			//logger.info("The sample data has been generated");
		};
	}

}
