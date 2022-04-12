package com.guxian.controller;


import com.guxian.entity.Joblevel;
import com.guxian.entity.RespBean;
import com.guxian.service.impl.JoblevelServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {
    @Autowired
    private JoblevelServiceImpl joblevelService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "获取所有职称信息")
    @GetMapping("/")
    public List<Joblevel> getAllPositions() {
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Joblevel joblevel) {
        joblevel.setCreatedate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            updatePositionFoRedis();
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新职称信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            updatePositionFoRedis();
            return RespBean.success("更新成功");
        }
        return RespBean.success("更新失败");
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            updatePositionFoRedis();
            return RespBean.success("删除成功");
        }
        return RespBean.success("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            updatePositionFoRedis();
            return RespBean.success("删除成功");
        }
        return RespBean.success("删除失败");
    }

    /**
     * 更新redis
     */
    public void updatePositionFoRedis() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("joblevel", joblevelService.list());
    }
}
