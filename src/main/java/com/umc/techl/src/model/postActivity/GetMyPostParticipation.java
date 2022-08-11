package com.umc.techl.src.model.postActivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMyPostParticipation {
    private String title;
    private String period;
    private String joinCount;
}