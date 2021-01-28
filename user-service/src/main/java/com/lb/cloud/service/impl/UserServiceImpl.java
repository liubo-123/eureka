package com.lb.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.lb.cloud.mapper.UserMapper;
import com.lb.cloud.model.User;
import com.lb.cloud.model.UserExample;
import com.lb.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public int countByExample(UserExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UserExample example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<User> selectByExample(UserExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public List<User> selectUser(int pageNum, int pageSize,UserExample example) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.selectByExample(example);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(User record, UserExample example) {
        return mapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(User record, UserExample example) {
        return mapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {

        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return mapper.updateByPrimaryKey(record);
    }
}
