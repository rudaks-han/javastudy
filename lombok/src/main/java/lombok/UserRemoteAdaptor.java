package lombok;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

public class UserRemoteAdaptor {
    public UserRdo find(String userId) {

        String uri = "http://localhost:8080/users";

        HttpEntity headers = new HttpEntity(new LinkedMultiValueMap<>());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserRdo> responseEntity =
            restTemplate.exchange(uri, HttpMethod.GET, headers, UserRdo.class);

        return responseEntity.getBody();
    }
}

