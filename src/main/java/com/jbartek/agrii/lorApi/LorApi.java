package com.jbartek.agrii.lorApi;

import com.jbartek.agrii.dto.LorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
@Component
public class LorApi {
    @Value("${loripsum.api.endpoint.prod}")
    private String loripsumApiEndPoint;
    private RestTemplate restTemplate;
    private LorDto loripsumDto = new LorDto();

    @Autowired
    public LorApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLoripsum() {
        loripsumDto.setTextLength("medium");
        loripsumDto.setTextType("plaintext");
        loripsumDto.setParagraphs(3);

        try {
            loripsumDto.setContent(restTemplate.getForObject(loripsumApiEndPoint + "/" + loripsumDto.getTextLength() +
                    "/" + loripsumDto.getTextType() + "/" + loripsumDto.getParagraphs(), String.class));

        } catch (RestClientException e) {
            System.out.println(e);
            loripsumDto.setContent("There was an error with request to the API...");
        }

        return loripsumDto.getContent();
    }
}
