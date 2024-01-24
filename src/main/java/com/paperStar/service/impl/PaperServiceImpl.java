package com.paperStar.service.impl;

import com.paperStar.dao.PaperDao;
import com.paperStar.pojo.*;
import com.paperStar.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperDao paperDao;
    @Override
    public int add(int uid) throws IOException {
        return paperDao.add(uid);
    }

    @Override
    public void save(int uid, Paper paper) throws IOException {
        paper.setUserId(uid);

        paper.setPaperCount(0);
        paper.setQstNum(paper.getQuestions().size());
        List<String> qstTypes=new ArrayList<String>();
        List<Question> questions=paper.getQuestions();
        paper.setPaperStatus(0);
        //为这个问卷每个问题的Type设值
        for(int j=0;j<paper.getQstNum();j++){
            qstTypes.add(j,questions.get(j).getQstType());
            questions.get(j).setQstCount(0);
            int optionNum=0;
            if(questions.get(j).getQstOption()!=null){//根据有无选项判断应该初始化什么值
                optionNum=questions.get(j).getQstOption().size();
                questions.get(j).setOptionNum(optionNum);
                questions.get(j).setAnsNum(new ArrayList<Integer>());
                questions.get(j).setAnsPer(new ArrayList<Float>());
                for(int k=0;k<optionNum;k++){
                    questions.get(j).getAnsNum().add(0);
                    questions.get(j).getAnsPer().add((float)(0));
                }

            }
            else{
                questions.get(j).setTotal(0);
                questions.get(j).setOptionNum(optionNum);
                questions.get(j).setAns(new ArrayList<String>());
                questions.get(j).setAnsNum(new ArrayList<Integer>());
                questions.get(j).setAnsPer(new ArrayList<Float>());
                for(int k=0;k<5;k++){
                    questions.get(j).getAnsNum().add(0);
                    questions.get(j).getAnsPer().add((float)(0));
                }
            }

        }
        paper.setQstTypes(qstTypes);
        paper.setQuestions(questions);
        paperDao.save(uid, paper);
    }

    @Override
    public int[] send(int uid, Paper paper) throws IOException {
        paper.setPaperStartTime(LocalDateTime.now());
        paper.setUserId(uid);
        paper.setPaperCount(0);
        paper.setQstNum(paper.getQuestions().size());
        List<String> qstTypes=new ArrayList<String>();
        List<Question> questions=paper.getQuestions();
        paper.setPaperStatus(1);
        //为这个问卷每个问题的Type设值
        for(int j=0;j<paper.getQstNum();j++){
            qstTypes.add(j,questions.get(j).getQstType());
            questions.get(j).setQstCount(0);
            questions.get(j).setAnsNum(new ArrayList<Integer>());
            questions.get(j).setAnsPer(new ArrayList<Float>());
            int optionNum=0;
            if(questions.get(j).getQstOption()!=null){//根据有无选项判断应该初始化什么值
                optionNum=questions.get(j).getQstOption().size();
                questions.get(j).setOptionNum(optionNum);
                for(int k=0;k<optionNum;k++){
                    questions.get(j).getAnsNum().add(0);
                    questions.get(j).getAnsPer().add((float)(0));
                }
            }
            else{
                questions.get(j).setTotal(0);
                questions.get(j).setOptionNum(optionNum);
                questions.get(j).setAns(new ArrayList<String>());
                questions.get(j).setAnsNum(new ArrayList<Integer>());
                questions.get(j).setAnsPer(new ArrayList<Float>());
                for(int k=0;k<5;k++){
                    questions.get(j).getAnsNum().add(0);
                    questions.get(j).getAnsPer().add((float)(0));
                }
            }
        }
        paper.setQstTypes(qstTypes);
        paper.setQuestions(questions);
        return paperDao.send(uid, paper);
    }

    @Override
    public Paper display(int uid, int pid) {
        return paperDao.display(uid, pid);
    }

    @Override
    public void renew(int uid, int pid,int cid, Paper paper) {
        paperDao.renew(uid, pid,cid, paper);
    }

    @Override
    public List<String> show(int uid, int pid) throws IOException {
        return paperDao.show(uid, pid);
    }

    @Override
    public Paper modify(int uid, int pid) throws IOException {
        return paperDao.modify(uid,pid);
    }

    @Override
    public List<PaperSaved> showDraft(int uid) {
        List<PaperSaved> paperSaved=new ArrayList<>();
        if(paperDao.findAllPaperSaved(uid)!=null){
            for(int i=0;i<paperDao.findAllPaperSaved(uid).size();i++){
                if(paperDao.findAllPaperSaved(uid).get(i).getIsSend()!=1){

                    paperSaved.add(paperDao.findAllPaperSaved(uid).get(i));
                }
            }
        }

        return paperSaved;
    }

    @Override
    public List<PaperSend> showSend(int uid) {
        return paperDao.findAllPaperSend(uid);
    }

    @Override
    public List<PaperTaken> showTaken(int uid) {
        return paperDao.findAllPaperTaken(uid);
    }

    @Override
    public Paper change(int uid, int pid) {
        return paperDao.change(uid,pid);
    }
}
