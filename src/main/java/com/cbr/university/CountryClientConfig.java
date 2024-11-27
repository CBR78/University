package com.cbr.university;

import com.cbr.university.soap.client.CountryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountryClientConfig {

    // Все, что нам нужно для настройки нашего клиента Spring WS, — это два компонента

    // Jaxb2Marshaller - для преобразования сообщений в XML и обратно
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.cbr.university.soap.client.gen");
        return marshaller;
    }
    // Здесь нам нужно убедиться, что контекстный путь маршаллера  совпадает с GeneratePackage , указанным в конфигурации плагина нашего pom.xml

    // CountryClient - который будет подключаться к bean-компоненту marshaller
    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
        CountryClient client = new CountryClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
