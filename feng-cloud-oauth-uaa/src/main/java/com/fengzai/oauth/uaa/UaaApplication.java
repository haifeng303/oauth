package com.fengzai.oauth.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.fengzai.upms.feign","com.fengzai.oauth.uaa"})
//@EnableEurekaClient
@EnableDiscoveryClient
@ServletComponentScan
@EnableFeignClients(basePackages = {"com.fengzai.upms.feign"})
public class UaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }

}
