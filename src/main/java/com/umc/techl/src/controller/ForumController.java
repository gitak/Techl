package com.umc.techl.src.controller;

import com.umc.techl.config.BaseException;
import com.umc.techl.config.BaseResponse;
import com.umc.techl.config.BaseResponseStatus;
import com.umc.techl.src.model.forum.*;
import com.umc.techl.src.service.ForumService;
import com.umc.techl.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.umc.techl.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
public class ForumController {

    private final ForumService forumService;
    private final JwtService jwtService;

    @ResponseBody
    @GetMapping("")
    public BaseResponse<GetForumInfoRes> getForumInfo(@RequestParam int bookIdx) {
        try{
            GetForumInfoRes forumInfo = forumService.getForumInfo(bookIdx);
            return new BaseResponse<>(forumInfo);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/new-forum")
    public BaseResponse<GetBookInfoRes> getBookInfo(@RequestParam int bookIdx) {

        try{
            String accessToken = jwtService.getJwt();
            if(accessToken == null || accessToken.length() == 0){
                throw new BaseException(EMPTY_JWT);
            }

            GetBookInfoRes bookInfo = forumService.getBookInfo(bookIdx);
            return new BaseResponse<>(bookInfo);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("/new-forum/create")
    public BaseResponse<PostForumContentsRes> createForumContents(@RequestParam int bookIdx,
                                                                  @RequestBody PostForumContentsReq postForumContentsReq) {

        try{
            String accessToken = jwtService.getJwt();
            if(accessToken == null || accessToken.length() == 0){
                throw new BaseException(EMPTY_JWT);
            }

            if (postForumContentsReq.getTitle() == null || postForumContentsReq.getTitle().length() == 0) {
                throw new BaseException(POST_EMPTY_TITLE);
            }

            if (postForumContentsReq.getContent() == null || postForumContentsReq.getContent().length() == 0) {
                throw new BaseException(POST_EMPTY_CONTENTS);
            }

            PostForumContentsRes postForumContentsRes = forumService.createForumContents(bookIdx, postForumContentsReq);
            return new BaseResponse<>(postForumContentsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{forumIdx}")
    public BaseResponse<GetForumContentsRes> getForumContentsInfo(@PathVariable("forumIdx")int forumIdx) {
        try{
            GetForumContentsRes getForumContentsInfo = forumService.getForumContentsInfo(forumIdx);
            return new BaseResponse<>(getForumContentsInfo);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("/{forumIdx}/new-forum-comment")
    public BaseResponse<PostNewCommentRes> getForumContentsInfo(@PathVariable("forumIdx")int forumIdx,
                                                                @RequestBody PostNewCommentReq postNewCommentReq) {

        try{
            String accessToken = jwtService.getJwt();
            if(accessToken == null || accessToken.length() == 0){
                throw new BaseException(EMPTY_JWT);
            }

            if (postNewCommentReq.getContent() == null || postNewCommentReq.getContent().length() == 0) {
                throw new BaseException(POST_EMPTY_CONTENTS);
            }

            PostNewCommentRes postNewCommentRes = forumService.createNewForumComment(forumIdx, postNewCommentReq);
            return new BaseResponse<>(postNewCommentRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/{forumIdx}/bookmark")
    public BaseResponse bookmark(@PathVariable("forumIdx") int forumIdx) {
        try {
            String accessToken = jwtService.getJwt();
            if(accessToken == null || accessToken.length() == 0){
                throw new BaseException(EMPTY_JWT);
            }

            forumService.bookmark(forumIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/{forumIdx}/forum-upvote")
    public BaseResponse forumUpvote(@PathVariable("forumIdx") int forumIdx) {
        try {
            String accessToken = jwtService.getJwt();
            if(accessToken == null || accessToken.length() == 0){
                throw new BaseException(EMPTY_JWT);
            }

            forumService.forumUpvote(forumIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/{forumCommentIdx}/forum-comment-upvote")
    public BaseResponse forumCommentUpvote(@PathVariable("forumCommentIdx") int forumCommentIdx) {
        try {
            String accessToken = jwtService.getJwt();
            if(accessToken == null || accessToken.length() == 0){
                throw new BaseException(EMPTY_JWT);
            }

            forumService.forumCommentUpvote(forumCommentIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}