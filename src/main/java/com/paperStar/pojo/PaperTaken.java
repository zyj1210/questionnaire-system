package com.paperStar.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperTaken implements Serializable {
    private String paperTitle;//问卷标题
}
