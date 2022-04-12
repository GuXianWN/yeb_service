package com.guxian.yebserver;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;


class YebServerApplicationTests {
    @Test
    void test() {
        Claims claims = null;
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE2NDM2ODU5ODgwMDYsImV4cCI6MTY0NDI5MDc4OH0.uqsGut1OxCghIYzXZejnPSGTQhmOXnyQlqHEDibbqc0gKjMuWE9iWofxSe_JW7e1xRQgjFtLGdCvqu0OPjJARw";
        String secret = "yeb-secret";
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(claims);
    }
}
