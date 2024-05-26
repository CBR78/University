package com.cbr.university.soap;

import com.cbr.university.soap.gen.GetCountryRequest;
import com.cbr.university.soap.gen.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint // Регистрирует в Spring WS текущий класс, в качестве конечной точки веб-службы
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    private CountryRepository countryRepository;

    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Указывает метод-обработчик в соответствии с пространством имен и атрибутами localPart
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")

    // Указывает, что этот метод возвращает значение, которое будет сопоставлено с полезными данными ответа
    @ResponsePayload
    public GetCountryResponse getCountry(
            // Указывает, что этот метод принимает параметр для сопоставления из входящего запроса.
            @RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}

