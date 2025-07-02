package io.github.luminous0220.foodDelivery.service;

import io.github.luminous0220.foodDelivery.dto.EmpDTO;
import io.github.luminous0220.foodDelivery.dto.EmpLoginDTO;
import io.github.luminous0220.foodDelivery.entity.Emp;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;

public interface EmpService {
    Emp login(EmpLoginDTO empLoginDto);

    void insert(EmpDTO empDTO);
}
