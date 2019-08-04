package cn.blabla.service;

import cn.blabla.dto.PaginationDto;
import cn.blabla.dto.QuestionDto;
import cn.blabla.mapper.QuestionMapper;
import cn.blabla.mapper.UserMapper;
import cn.blabla.model.Question;
import cn.blabla.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    public PaginationDto list(Integer page, Integer size) {
        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList();

        PaginationDto paginationDto = new PaginationDto();
        for(Question question:questions){
           User user = userMapper.findById(question.getCreator());
           QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        Integer totalCount = questionMapper.count();
        paginationDto.setPagination(totalCount,page,size);
        return paginationDto;
    }
}
