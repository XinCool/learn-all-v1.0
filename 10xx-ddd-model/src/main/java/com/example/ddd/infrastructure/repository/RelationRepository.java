package com.example.ddd.infrastructure.repository;

import cn.hutool.core.bean.BeanUtil;
import com.example.ddd.domain.device.repository.IRelationRepository;
import com.example.ddd.domain.device.vo.RelationInfo;
import com.example.ddd.infrastructure.dao.RelationDAO_HI;
import com.example.ddd.infrastructure.entities.RelationEntity_HI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cool
 * @create 2020-11-16
 */
@Component("relationRepository")
public class RelationRepository implements IRelationRepository {

    @Autowired
    private RelationDAO_HI relationDAO_HI;

    @Override
    public RelationInfo findParentByToId(String toId, String fromType, String toType, String relationType, String relationTypeGroup) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("fromType",fromType);
        paramMap.put("toId",toId);
        paramMap.put("relationType",relationType);
        paramMap.put("toType",toType);
        paramMap.put("relationTypeGroup",relationTypeGroup);
        List<RelationEntity_HI> relationEntity_his = relationDAO_HI.findByProperty(paramMap);
        if (relationEntity_his!=null&&relationEntity_his.size()>0)
        {
            RelationEntity_HI relationEntity = relationEntity_his.get(0);
            RelationInfo relationInfo = new RelationInfo();
            BeanUtil.copyProperties(relationEntity,relationInfo);
            return relationInfo;
        }
        return null ;
    }
}
