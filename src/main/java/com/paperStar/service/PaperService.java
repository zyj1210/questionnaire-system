package com.paperStar.service;

import com.paperStar.pojo.Paper;
import com.paperStar.pojo.PaperSaved;
import com.paperStar.pojo.PaperSend;
import com.paperStar.pojo.PaperTaken;

import java.io.IOException;
import java.util.List;

public interface PaperService {
    /**
     * 新增问卷
     */
    int add(int uid) throws IOException;

    void save(int uid, Paper paper) throws IOException;

    int[] send(int uid, Paper paper) throws IOException;

    Paper display(int uid, int pid);

    void renew(int uid, int pid,int cid, Paper paper);

    List<String> show(int uid, int pid) throws IOException;

    Paper modify(int uid, int pid) throws IOException;

    List<PaperSaved> showDraft(int uid);

    List<PaperSend> showSend(int uid);

    List<PaperTaken> showTaken(int uid);

    Paper change(int uid, int pid);
}
