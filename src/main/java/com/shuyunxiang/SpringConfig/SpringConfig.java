package com.shuyunxiang.SpringConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//表名这个类是个配置类,相当于配置文件
@ComponentScan(basePackages = "com.shuyunxiang")//扫描包,把注册的类扫描进spring容器管理
public class SpringConfig {


}
