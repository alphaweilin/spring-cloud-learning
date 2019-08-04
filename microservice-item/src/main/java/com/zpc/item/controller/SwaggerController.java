package com.zpc.item.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
@Api("SwaggerDemo控制器")
@RestController
public class SwaggerController {

    @ApiOperation("Swagger演示")
    @GetMapping("/swaggerIndex")
    public String swaggerIndex(String msg) {
        return "This is swaggerIndex!" + msg;
    }

    @ApiOperation("获取商品详情")
    @ApiImplicitParam(name = "itemName", value = "商品名称", required = true, dataType = "String")
    @PostMapping("/getItemInfo")
    public String getItemInfo(String itemName) {
        return "商品名：" + itemName + " 商品价格：" + new BigDecimal(Math.random() * 100).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
