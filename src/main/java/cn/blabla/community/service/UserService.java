package cn.blabla.community.service;

import cn.blabla.community.mapper.UserMapper;
import cn.blabla.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
       User dbUser = userMapper.findByAccountId(user.getAccountId());
       if(dbUser==null){
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insert(user);
       }else {
           dbUser.setGmtModified(System.currentTimeMillis());
           dbUser.setGmtCreate(user.getGmtModified());
           dbUser.setName(user.getName());
           dbUser.setToken(user.getToken());
           userMapper.update(dbUser);
       }
    }
}
