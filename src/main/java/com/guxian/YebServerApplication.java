package com.guxian;

import com.guxian.service.impl.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
@MapperScan("com.guxian.mapper")
public class YebServerApplication implements CommandLineRunner {
    @Autowired
    private PoliticsStatusServiceImpl politicsStatusService;
    @Autowired
    private JoblevelServiceImpl joblevelService;
    @Autowired
    private NationServiceImpl nationService;
    @Autowired
    private PositionServiceImpl positionService;
    @Autowired
    private DepartmentServiceImpl departmentService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MenuServiceImpl menuService;

    public static void main(String[] args) {
        SpringApplication.run(YebServerApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("politicsStatus", politicsStatusService.list());
        valueOperations.set("joblevel", joblevelService.list());
        valueOperations.set("nation", nationService.list());
        valueOperations.set("position", positionService.list());
        valueOperations.set("department", departmentService.list());
        valueOperations.set("menu",menuService.getMenusByRole());
        valueOperations.set("department", departmentService.getAllDepartment());
        System.out.println("-------->redis初始化完成<----------");
    }
}
