package com.zpc.order.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zpc.order.entity.Item;
import com.zpc.order.fallback.ItemServiceFallback;
/**
 * 申明这是一个Feign客户端，并且指明服务id
 * @author Evan
 */
@FeignClient(value = "app-item",fallback = ItemServiceFallback.class)
public interface ItemFeignClient {
    /**
     * 这里定义了类似于SpringMVC用法的方法，就可以进行RESTful方式的调用了
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    Item queryItemById(@PathVariable("id") Long id);
}