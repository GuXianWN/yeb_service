package com.guxian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页返回对象
 *
 * @author GuXianWN
 * @date 2022/02/11 12:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据
     */
    private List<?> data;
}
