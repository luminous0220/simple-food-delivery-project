package io.github.luminous0220.foodDelivery.service.impl;

import io.github.luminous0220.foodDelivery.constant.MessageConstant;
import io.github.luminous0220.foodDelivery.constant.PasswordConstant;
import io.github.luminous0220.foodDelivery.constant.StatusConstant;
import io.github.luminous0220.foodDelivery.context.BaseContxt;
import io.github.luminous0220.foodDelivery.dto.EmpDTO;
import io.github.luminous0220.foodDelivery.dto.EmpLoginDTO;
import io.github.luminous0220.foodDelivery.entity.Emp;
import io.github.luminous0220.foodDelivery.exception.AccountForbiddenException;
import io.github.luminous0220.foodDelivery.exception.AccountNotExistException;
import io.github.luminous0220.foodDelivery.exception.BadException;
import io.github.luminous0220.foodDelivery.exception.PasswordErrorException;
import io.github.luminous0220.foodDelivery.mapper.EmpMapper;
import io.github.luminous0220.foodDelivery.service.EmpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public Emp login(EmpLoginDTO empLoginDto) {
        String username = empLoginDto.getUsername();
        String password = empLoginDto.getPassword();

        password =DigestUtils.md5DigestAsHex(password.getBytes());

        Emp emp =empMapper.getByUsername(username);


        if (emp == null) {
            //账号不存在
            throw new AccountNotExistException();
        }

        //密码比对
        if (!password.equals(emp.getPassword())) {
            //密码错误
            throw new PasswordErrorException();
        }

        if (Objects.equals(emp.getStatus(), StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountForbiddenException();
        }

        return emp;
    }

    @Override
    public void insert(EmpDTO empDTO) {

        String encryptPwd= DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes());

        long empId=BaseContxt.getCurrentId();

        Emp emp = Emp.builder()
                .status(StatusConstant.ENABLE)
                .password(encryptPwd)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(empId)
                .updateUser(empId)
                .build();

        BeanUtils.copyProperties(empDTO, emp);

        empMapper.insert(emp);
    }
}
