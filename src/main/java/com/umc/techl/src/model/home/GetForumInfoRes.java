package com.umc.techl.src.model.home;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetForumInfoRes {
    private int forumIdx;
    private String title;
    private String contentsImage;
    private String nickName;
    private int countUpvote;
    private int countComment;
    private String createdDate;
}
