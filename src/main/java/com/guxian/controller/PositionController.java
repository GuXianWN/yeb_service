package com.guxian.controller;


import com.guxian.entity.Position;
import com.guxian.entity.RespBean;
import com.guxian.service.impl.PositionServiceImpl;
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
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private PositionServiceImpl positionService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        position.setCreatedate(LocalDateTime.now());
        if (positionService.save(position)) {
            updatePositionFoRedis();
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            updatePositionFoRedis();
            return RespBean.success("更新成功");
        }
        return RespBean.success("更新失败");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            updatePositionFoRedis();
            return RespBean.success("删除成功");
        }
        return RespBean.success("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            updatePositionFoRedis();
            return RespBean.success("删除成功");
        }
        return RespBean.success("删除失败");
    }

    public void updatePositionFoRedis(){
        ValueOperations<String,Object> valueOperations=redisTemplate.opsForValue();
        valueOperations.set("position", positionService.list());
    }
}
