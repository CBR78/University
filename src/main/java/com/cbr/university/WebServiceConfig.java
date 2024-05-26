package com.cbr.university;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs // Включает функции веб-службы SOAP в этом приложении Spring Boot
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    // WsConfigurerAdapter - настраивает модель программирования Spring-WS на основе аннотаций.
    // bean definitions

    @Bean // MessageDispatcherServlet - для обработки SOAP-запросов
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext); // чтобы Spring-WS мог найти другие bean-компоненты Spring
        servlet.setTransformWsdlLocations(true); // включим преобразование сервлета местоположения WSDL.
        // Это преобразует атрибут местоположения "soap:address" в WSDL так, чтобы он отражал URL-адрес входящего запроса.
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }


    // DefaultWsdl11Definition - предоставляет стандартный WSDL 1.1 с использованием XsdSchema.
    // Имя WSDL будет таким же, как имя компонента
    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {

        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.baeldung.com/springsoap/gen");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }

}
