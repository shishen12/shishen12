package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data

@TableName("users")
public class User {

    @TableField("user_name")
    private String name;
    @TableField("user_pwd")
    private String password;

}
