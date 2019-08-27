//package com.znv.mall.authserver.service.impl;
//
//import com.znv.mall.authserver.dao.ResourceMapper;
//import com.znv.mall.authserver.entity.Resource;
//import com.znv.mall.authserver.service.IResourceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//
//@Service
//public class ResourceService implements IResourceService {
//    @Autowired
//    private ResourceMapper resourceMapper;
//
//    @Override
//    public Set<Resource> findAll() {
//        return resourceMapper.findAll();
//    }
//
//    @Override
//    public Set<Resource> queryByRoleCodes(String[] roleCodes) {
//        return resourceMapper.queryByRoleCodes(roleCodes);
//    }
//}
