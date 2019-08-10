package cn.blabla.community.controller;

import cn.blabla.community.dto.CommentDto;
import cn.blabla.community.dto.ResultDto;
import cn.blabla.community.exception.CustomizeErrorCode;
import cn.blabla.community.mapper.CommentMapper;
import cn.blabla.community.model.Comment;
import cn.blabla.community.model.User;
import cn.blabla.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object comment(@RequestBody CommentDto commentDto,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1L);
        commentMapper.insert(comment);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message", "评论成功");
        return ResultDto.okOf();
    }
}
