package cn.suse.serverconsumer.controler;

import cn.suse.serverconsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("user")
    @ResponseBody
    //@HystrixCommand(fallbackMethod = "queryUserByIdFallBack")
    public String getUserById(@RequestParam("id") Long id){
        /*// 根据服务名称，获取服务实例。有可能是集群，所以是service实例集合
        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        // 因为只有一个Service-provider。所以获取第一个实例
        ServiceInstance instance = instances.get(0);*/
        // 获取ip和端口信息，拼接成服务地址
        String user = this.restTemplate.getForObject("http://service-provider/user/" + id, String.class);
        return user;
    }
    public String queryUserByIdFallBack(){
        return "服务器正忙，请稍后再试！";
    }
}
