package com.cc.component;

import com.cc.entity.constants.Constants;
import com.cc.entity.dto.SysSettingsDto;
import com.cc.entity.dto.UserSpaceDto;
import com.cc.entity.po.User;
import com.cc.entity.query.UserQuery;
import com.cc.mappers.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("redisComponent")
public class RedisComponent {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 获取系统设置
     */
    public SysSettingsDto getSysSettingsDto() {
        SysSettingsDto sysSettingsDto = (SysSettingsDto) redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);
        if (sysSettingsDto == null) {
            sysSettingsDto = new SysSettingsDto();
            redisUtils.set(Constants.REDIS_KEY_SYS_SETTING, sysSettingsDto);
        }
        return sysSettingsDto;
    }

    /**
     *保存用户空间
     */
    public void saveUserSpaceUse(String userId, UserSpaceDto userSpaceDto){
        redisUtils.setex(Constants.REDIS_KEY_USER_SPACE_USE + userId,userSpaceDto,Constants.REDIS_KEY_EXPIRES_DAY);

    }


}
