package rudaks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateExample {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        int count = 100;
        for (int i=0; i<count; i++) {
            ResponseEntity<ReceptionRdo> responseEntity = restTemplate.postForEntity("http://localhost:8040/receptions", Reception.sample(), ReceptionRdo.class);
            ReceptionRdo receptionRdo = responseEntity.getBody();
            System.out.println("[" + count + "] receptionId : " + receptionRdo.getReceptionId());
        }
    }
}
