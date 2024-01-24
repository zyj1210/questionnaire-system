package com.paperStar.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    //所有题目类型都具有的共同特征
    private int qstId;//题号ID
    private int paperId;//所属问卷ID
    private String qstType;//题目类型 填空text,文件file,评价range,单选radio,多选checkbox
    private String qstTitle;//题干
    private int qstCount;//这个问题的回答次数，当当前回答不为空时，就会加1

    //题目选项，只有单选，多选的题目才有选项
    private List<String> qstOption;//选项,适用于单选，多选
    private int optionNum;//选项个数,适用于单选，多选

    //当前用户回答
    transient private String curAns1;//当前用户的回答，适用于单选，填空，图片问题，不会保存到文件系统
    transient private List<String> curAns2;//当前用户的回答，适用于多选问题，不会保存到文件系统
    transient private int curAns3;//当前用户的回答，适用于评价

    //全部回答结果
    //private List<String> ansOption;//统计结果选项，适用于单选,多选单项和评价,这里只包含了用户回答过的选项
    private List<Integer> ansNum;//统计结果数量,适用于单选，多选单项,和上面的qstOption一一对应；还有评价题的五个区间的值
   /* private List<List<String>> ansDT;//多选统计结果
    private List<Integer> ansNumN;//统计结果数量，适用于多选多项的最终呈现结果*/
    private List<Float> ansPer;//统计结果百分比,适用于单选和评价
    private List<String> ans;//图片类问题，为其保存url链接；填空题为其保存内容
    private float avg;//评价题的平均值
    private int total;//评价题的总和
}
