package com.paperStar.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperSaved  implements Serializable {
    private int paperId;//问卷ID号
    private int userId;//用户ID
    private String paperTitle;//问卷标题
    private int isSend;//该问卷是否已经被发送，1表示已发送，0表示未发送
}
