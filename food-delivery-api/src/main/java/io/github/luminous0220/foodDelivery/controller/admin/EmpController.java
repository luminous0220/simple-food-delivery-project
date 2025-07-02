package io.github.luminous0220.foodDelivery.controller.admin;


import io.github.luminous0220.foodDelivery.constant.JwtClaimsConstant;
import io.github.luminous0220.foodDelivery.constant.PasswordConstant;
import io.github.luminous0220.foodDelivery.constant.StatusConstant;
import io.github.luminous0220.foodDelivery.dto.EmpDTO;
import io.github.luminous0220.foodDelivery.dto.EmpLoginDTO;
import io.github.luminous0220.foodDelivery.entity.Emp;
import io.github.luminous0220.foodDelivery.mapper.EmpMapper;
import io.github.luminous0220.foodDelivery.properties.JwtProperties;
import io.github.luminous0220.foodDelivery.result.Result;
import io.github.luminous0220.foodDelivery.service.EmpService;
import io.github.luminous0220.foodDelivery.utils.JwtUtils;
import io.github.luminous0220.foodDelivery.vo.EmpLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "员工管理")
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private EmpMapper empMapper;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result<EmpLoginVO> login(@RequestBody EmpLoginDTO empLoginDTO) throws AccountLockedException, AccountNotFoundException {
        log.info("员工登录: {}", empLoginDTO);

        Emp emp=empService.login(empLoginDTO);

        Map<String, Object> claims=new HashMap<>();

        claims.put(JwtClaimsConstant.EMP_ID, emp.getId());

        String token = JwtUtils.createJwt(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmpLoginVO employeeLoginVO = EmpLoginVO.builder()
                .id(emp.getId())
                .userName(emp.getUsername())
                .name(emp.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    @PostMapping
    public Result save(@RequestBody() EmpDTO empDTO)  {

        empService.insert(empDTO);

        return Result.success();
    }
}
