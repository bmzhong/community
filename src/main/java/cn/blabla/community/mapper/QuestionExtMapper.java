package cn.blabla.community.mapper;

import cn.blabla.community.model.Question;
import cn.blabla.community.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExtMapper {
    int increaseView(Question record);
}