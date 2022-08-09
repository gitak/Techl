package com.umc.techl.src.model.home;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Bookmark {
    private int userIdx;
    private int bookIdx;
    private String type;
}
