package com.paperStar.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper implements Serializable {
    private int paperId;//问卷ID号
    private int userId;//用户ID
    private String paperTitle;//问卷标题
    private String paperSummary;//问卷摘要介绍
    private int paperStatus;//问卷状态，1表示已发送，0表示只是保存
    private LocalDateTime paperStartTime;//问卷发布日期
    private int qstNum;//问卷问题个数
    private int paperCount;//问卷回答次数
    private List<String> qstTypes;//问卷问题列表类型
    private List<Question> questions;//问卷问题列表


    //我不想要这两个选项
    private LocalDateTime paperEndTime;//问卷结束日期
    private int paperType;//问卷分类


}
