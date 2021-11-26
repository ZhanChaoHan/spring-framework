package com.jachs.aspectj.entity;


import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * @author zhanchaohan
 */
@Data
public class T_User {
    final @Id
    private String uId;
    private String uName;
    private String uPwd;
}
