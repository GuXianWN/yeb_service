package com.guxian.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_employee")
@ApiModel(value = "Employee对象", description = "")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "员工姓名")
    @ApiModelProperty(value = "员工姓名")
    private String name;

    @Excel(name = "性别")
    @ApiModelProperty(value = "性别")
    private String gender;

    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate birthday;

    @Excel(name = "身份证号", width = 30)
    @ApiModelProperty(value = "身份证号")
    @TableField("idCard")
    private String idcard;

    @Excel(name = "婚姻状况")
    @ApiModelProperty(value = "婚姻状况")
    private String wedlock;

    @ApiModelProperty(value = "民族")
    @TableField("nationId")
    private Integer nationid;

    @Excel(name = "籍贯")
    @ApiModelProperty(value = "籍贯")
    @TableField("nativePlace")
    private String nativeplace;

    @ApiModelProperty(value = "政治面貌")
    @TableField("politicId")
    private Integer politicid;

    @Excel(name = "邮箱", width = 30)
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Excel(name = "电话号码", width = 20)
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @Excel(name = "联系地址", width = 40)
    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "所属部门")
    @TableField("departmentId")
    private Integer departmentid;

    @ApiModelProperty(value = "职称ID")
    @TableField("jobLevelId")
    private Integer joblevelid;

    @ApiModelProperty(value = "职位ID")
    @TableField("posId")
    private Integer posid;

    @Excel(name = "聘用形式")
    @ApiModelProperty(value = "聘用形式")
    @TableField("engageForm")
    private String engageform;

    @Excel(name = "最高学历")
    @ApiModelProperty(value = "最高学历")
    @TableField("tiptopDegree")
    private String tiptopdegree;

    @Excel(name = "所属专业", width = 20)
    @ApiModelProperty(value = "所属专业")
    private String specialty;

    @Excel(name = "毕业院校", width = 20)
    @ApiModelProperty(value = "毕业院校")
    private String school;

    @Excel(name = "入职日期", width = 20, format = "yyyy-MM-dd")
    @ApiModelProperty(value = "入职日期")
    @TableField("beginDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate begindate;

    @Excel(name = "在职状态")
    @ApiModelProperty(value = "在职状态")
    @TableField("workState")
    private String workstate;

    @Excel(name = "工号")
    @ApiModelProperty(value = "工号")
    @TableField("workID")
    private String workid;

    @Excel(name = "合同期限", suffix = "年")
    @ApiModelProperty(value = "合同期限")
    @TableField("contractTerm")
    private Double contractterm;

    @Excel(name = "转正日期", width = 20, format = "yyyy-MM-dd")
    @ApiModelProperty(value = "转正日期")
    @TableField("conversionTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate conversiontime;

    @Excel(name = "离职日期", width = 20, format = "yyyy-MM-dd")
    @ApiModelProperty(value = "离职日期")
    @TableField("notWorkDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate notworkdate;

    @Excel(name = "合同起始日期", width = 20, format = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同起始日期")
    @TableField("beginContract")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate begincontract;

    @Excel(name = "合同终止日期", width = 20, format = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同终止日期")
    @TableField("endContract")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate endcontract;

    @Excel(name = "工龄")
    @ApiModelProperty(value = "工龄")
    @TableField("workAge")
    private Integer workage;

    @ApiModelProperty(value = "工资账套ID")
    @TableField("salaryId")
    private Integer salaryid;

    @ExcelEntity(name = "民族")
    @ApiModelProperty(value = "民族")
    @TableField(exist = false)
    private Nation nation;

    @ExcelEntity(name = "政治面貌")
    @ApiModelProperty(value = "政治面貌")
    @TableField(exist = false)
    private PoliticsStatus politicsStatus;

    @ExcelEntity(name = "部门")
    @ApiModelProperty(value = "部门")
    @TableField(exist = false)
    private Department department;

    @ExcelEntity(name = "职称")
    @ApiModelProperty(value = "职称")
    @TableField(exist = false)
    private Joblevel joblevel;

    @ExcelEntity(name = "职位")
    @ApiModelProperty(value = "职位")
    @TableField(exist = false)
    private Position position;
}
