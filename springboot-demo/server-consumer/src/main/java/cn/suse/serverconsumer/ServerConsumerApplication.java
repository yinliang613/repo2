package cn.suse.serverconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ServerConsumerApplication {
    @Bean
    @LoadBalanced //开启负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {

        SpringApplication.run(ServerConsumerApplication.class, args);
    }

}
