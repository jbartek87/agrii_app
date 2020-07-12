package com.jbartek.agrii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LorDto {
    private String textLength;
    private String textType;
    private int paragraphs;
    private String content;


}
