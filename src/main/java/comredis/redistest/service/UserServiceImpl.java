package comredis.redistest.service;

import com.alibaba.fastjson.JSONObject;
import comredis.redistest.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    private static final String  cacheKey  ="userEntity";  
    /** 
     * 缓存存储 
     */
    @Autowired
    private RedisCacheStorageImpl<UserEntity> storageCache;

    public void setStorageCache(RedisCacheStorageImpl<UserEntity> storageCache) {  
        this.storageCache = storageCache;  
    }  
    /** 
     * 新增 
     * @param entity 
     * @return 
     */  
    @Override  
    public boolean addUserEntity(UserEntity entity) {
        //非空  
        if(entity ==null || StringUtils.isEmpty(entity.getUserId())){
            return false;  
        }  
        /** 
         * 做数据库持久化
         */  
        System.out.println("先插入数据库中,.........");  
        //然后接下来做非关系型数据库持久化  
        return storageCache.hset(cacheKey, entity.getUserId(), entity);  
    }  

    @Override  
    public boolean deleteUserEntity(UserEntity entity) {
        return false;  
    }  
    /** 
     * 根据id 查询 
     * @return 
     */  
    @Override  
    public UserEntity queryUserEntityByUserId(UserEntity userEntity) {  
        //非空  
        if(userEntity ==null || StringUtils.isEmpty(userEntity.getUserId())){  
            return null;  
        }  
        //先去缓存中查询 是否存在，不存在在查询  
         UserEntity reslut = storageCache.hget(cacheKey, userEntity.getUserId());
        if(reslut!=null){  
            return reslut;  
        }else{  
            //查询数据库  
            System.out.println("查询数据库");  
        }  
        return null;  
    }

    @Override
    public Map<String,UserEntity> queryAll() {
        //先去缓存中查询 是否存在，不存在在查询
        Map<String,UserEntity> reslut = storageCache.hget(cacheKey);
        if(reslut!=null){
            return reslut;
        }else{
            //查询数据库
            System.out.println("查询数据库");
        }
        return null;
    }


}