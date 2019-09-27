package comredis.redistest.service;

import comredis.redistest.entity.UserEntity;

import java.util.Map;

public interface UserService {

    boolean addUserEntity(UserEntity entity);

    boolean deleteUserEntity(UserEntity entity);

    UserEntity queryUserEntityByUserId(UserEntity userEntity);

    Map<String,UserEntity> queryAll();
}
