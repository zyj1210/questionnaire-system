package com.paperStar.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperSend  implements Serializable {
    private int paperId;//问卷ID号
    private int userId;//用户ID
    private String paperTitle;//问卷标题
}
