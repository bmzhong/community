package cn.blabla.community.mapper;

import cn.blabla.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionExtMapper {
    int increaseView(Question record);
}