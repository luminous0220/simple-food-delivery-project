package io.github.luminous0220.foodDelivery.dto;

import lombok.Data;

import java.io.Serializable;

@Data
// 这段代码定义了一个名为 EmpDTO 的类，并实现 Serializable 接口，表示该类的对象可以被序列化，便于在网络上传输或保存到文件中
public class EmpDTO implements Serializable {
    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

}
