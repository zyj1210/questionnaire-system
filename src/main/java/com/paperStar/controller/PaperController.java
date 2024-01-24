package com.paperStar.controller;

import com.paperStar.pojo.*;
import com.paperStar.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class PaperController {
    @Autowired
    private PaperService paperService;
    /**
     * 用户按下侧边栏创建问卷按钮，后端返回该用户的草稿问卷
     */
    @GetMapping("/savedPaper/{uid}")
    public Result showDraft(@PathVariable int uid) throws IOException {
        log.info("展示userId为{}的用户未发送的草稿问卷", uid);
        //调用service展示用户保存未发送的问卷列表
        List<PaperSaved> paperSaved= paperService.showDraft(uid);
        return Result.success(paperSaved);
    }
    /**
     * 用户按下侧边栏已发送问卷按钮，后端返回该用户的已发送问卷
     */
    @GetMapping("/sendPaper/{uid}")
    public Result showSend(@PathVariable int uid) throws IOException {


        log.info("展示userId为{}的用户已发送的问卷", uid);
        //调用service展示用户已发送的问卷列表
        List<PaperSend> paperSend= paperService.showSend(uid);
        return Result.success(paperSend);
    }
    /**
     * 用户按下侧边栏已填写问卷按钮，后端返回该用户的已填写问卷
     */
    @GetMapping("/takenPaper/{uid}")
    public Result showTaken(@PathVariable int uid) throws IOException {
        log.info("展示userId为{}的用户已填写的问卷", uid);
        //调用service展示用户已填写的问卷列表
        List<PaperTaken> paperTaken= paperService.showTaken(uid);
        return Result.success(paperTaken);
    }
    /**
     * 新增问卷
     */
    @GetMapping ("/paper/{uid}")
    public Result add(@PathVariable int uid) throws IOException {
        log.info("给userId为{}的用户新增问卷", uid);
        //调用service新增问卷
        int pid= paperService.add(uid);
        return Result.success(1,pid);
    }

    /**
     * 保存问卷
     */
    @PostMapping ("/paperSave/{uid}/{pid}")
    public Result save(@PathVariable int uid,@PathVariable int pid,@RequestBody Paper paper) throws IOException {
        log.info("给userId为{}的用户保存paperId为{}的问卷{}", uid, pid,paper);
        //调用service保存问卷
        paper.setPaperId(pid);
        paperService.save(uid, paper);
        return Result.success();
    }

    /**
     * 发送问卷
     */
    @PostMapping ("/paperSend/{uid}/{pid}")
    public Result send(@PathVariable int uid ,@PathVariable int pid,@RequestBody Paper paper) throws IOException {
        log.info("给userId为{}的用户发送paperId为{}的问卷{}", uid ,pid,paper);
        //调用service发送问卷
        paper.setPaperId(pid);
        if(paper.getQuestions()==null){
            return Result.error("该问卷没有填写问题，不可以被发送！");
        }
        else if(paper.getQuestions().size()==0){
            return Result.error("该问卷没有填写问题，不可以被发送！");
        }
        int[] uq=paperService.send(uid, paper);
        return Result.success(uq);
    }

    /**
     * 用户按下搜索问卷按钮，后端返回问卷实体数据
     */
    @GetMapping("/paper/{uid}/{pid}")
    public Result display(@PathVariable int uid,@PathVariable int pid){
        log.info("返回userId为{}的用户创建的paperId为{}的问卷", uid, pid);
        //调用service返回问卷
        Paper paper=paperService.display(uid,pid);
        if(paper!=null){
            return Result.success(paper);
        }
        else{
            return Result.error("该问卷不存在或者未被发送");
        }

    }

    /**
     * 用户按下提交问卷按钮，后端更新问卷数据
     */
    @PutMapping("/paper/{cid}")
    public Result renew(@PathVariable int cid,@RequestBody Paper paper){
        int uid=paper.getUserId();
        int pid=paper.getPaperId();
        log.info("有人给userId为{}的用户创建的paperId为{}的问卷提交了一份答卷{}", uid, pid, paper);
        //调用service更新问卷统计内容

        paperService.renew(uid,pid,cid,paper);
        return Result.success();
    }

    /**
     * 用户按下展示问卷分析结果按钮，后端返回问卷实体
     */
    @GetMapping ("/paperAna/{uid}/{pid}")
    public Result show(@PathVariable int uid,@PathVariable int pid) throws IOException {
        log.info("展示userId为{}的用户创建的paperId为{}的问卷分析结果", uid, pid);
        //调用service展示问卷统计内容
        List<String> urls = paperService.show(uid,pid);
        return Result.success(urls);
    }
    /**
     * 用户按下编辑已发送按钮，后端新建Id并返回之前的问卷实体
     */
    @GetMapping("/paperMod/{uid}/{pid}")
    public Result modify(@PathVariable int uid,@PathVariable int pid) throws IOException {
        log.info("更改userId为{}的用户创建的paperId为{}的问卷，这是旧问卷的副本", uid, pid);
        //调用service展示问卷统计内容
        Paper paper = paperService.modify(uid,pid);
        return Result.success(paper);
    }
    /**
     * 用户按下历史草稿栏，后端返回之前的问卷实体
     */
    @GetMapping("/paperChange/{uid}/{pid}")
    public Result change(@PathVariable int uid,@PathVariable int pid) throws IOException {
        log.info("更改userId为{}的用户创建的paperId为{}的历史草稿问卷 ",uid, pid);
        //调用service展示问卷统计内容
        Paper paper = paperService.change(uid,pid);
        return Result.success(paper);
    }
}
