package com.guxian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共返回对象
 *
 * @author GuXianWN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RespBean {
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public static RespBean success(String message) {
        RespBean respBean = new RespBean();
        respBean.setCode(200);
        respBean.setMessage(message);
        return respBean;
    }

    public static RespBean error(String message) {
        RespBean respBean = new RespBean();
        respBean.setCode(500);
        respBean.setMessage(message);
        return respBean;
    }

    public RespBean data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
