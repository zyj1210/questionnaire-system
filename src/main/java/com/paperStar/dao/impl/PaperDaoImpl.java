package com.paperStar.dao.impl;

import com.alibaba.excel.EasyExcel;
import com.paperStar.dao.PaperDao;
import com.paperStar.pojo.*;
import com.paperStar.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaperDaoImpl implements PaperDao {
    //win10
    //private String pubPath="D:\\软件开发综合实训\\开发\\paperStar";

    //linux
    private String pubPath = "/paperStar/paperStar";

    @Autowired
    private AliOSSUtils aliOSSUtils;
    //用来读取一个用户保存过的问卷数据
    @Override
    public List<PaperSaved> findAllPaperSaved(int uid){

        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\saved.txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/saved.txt";

        File file = new File(priPath);
        List<PaperSaved> paperSaved=null;
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            return null;}
        else if(!file.exists()){
            return null;
        }
        else{
            ObjectInputStream ois=null;

            try {

                //创建ObjectIntputStream对象
                ois=new ObjectInputStream(new FileInputStream(file));
                //ObjectIntputStream对象readObject方法反序列化对象（Paper）
                paperSaved=(List<PaperSaved>)ois.readObject();
               return paperSaved;

            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }
    }
    @Override
    public List<PaperSend> findAllPaperSend(int uid){
        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\send.txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/send.txt";

        File file = new File(priPath);
        List<PaperSend> paperSend=null;
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            return null;}
        else if(!file.exists()){
            return null;
        }
        else{
            ObjectInputStream ois=null;

            try {

                //创建ObjectIntputStream对象
                ois=new ObjectInputStream(new FileInputStream(file));
                //ObjectIntputStream对象readObject方法反序列化对象（Paper）
                paperSend=(List<PaperSend>)ois.readObject();
                return paperSend;

            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }
    }
    @Override
    public List<PaperTaken> findAllPaperTaken(int uid){
        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\taken.txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/taken.txt";

        File file = new File(priPath);
        List<PaperTaken> paperTaken=null;
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            return null;}
        else if(!file.exists()){
            return null;
        }
        else{
            ObjectInputStream ois=null;

            try {

                //创建ObjectIntputStream对象
                ois=new ObjectInputStream(new FileInputStream(file));
                //ObjectIntputStream对象readObject方法反序列化对象（Paper）
                paperTaken=(List<PaperTaken>)ois.readObject();
                return paperTaken;

            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }
    }



    @Override
    public int add(int uid) throws IOException {

        //window
        //String priPath=pubPath+"\\numbers\\u"+uid+".txt";

        //linux
        String priPath=pubPath+"/numbers/u"+uid+".txt";

        File file = new File(priPath);

        if (!file.getParentFile().exists()) {//判断父目录路径是否存在，即"u"+uid+".txt"前的pubPath+"\\numbers\\"
            try {
                file.getParentFile().mkdirs();//不存在则创建父目录
                file.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                //向文件中写入内容
                bw.write(String.valueOf (1));
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
        else if(!file.exists()){
            try {
                file.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                //向文件中写入内容
                bw.write(String.valueOf (1));
                bw.flush();
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
        else{
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine(); // 读取文件的一行
                int i= Integer.parseInt(line.trim()); // 将字符串转换为整数
                br.close();
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));//向文件中写入内容
                bw.write(String.valueOf (i+1));
                bw.flush();
                bw.close();
                return (i+1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;

        }
    }

    @Override
    public void save(int uid, Paper paper) throws IOException {
        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\papers\\p"+paper.getPaperId()+".txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/papers/p"+paper.getPaperId()+".txt";

        File file = new File(priPath);
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            try {
                file.getParentFile().mkdirs();//不存在则创建父目录
                file.createNewFile();
                ObjectOutputStream oos=null;

                try {
                    //创建用ObjectOutputStream对象
                    oos  = new ObjectOutputStream(new FileOutputStream(file));

                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paper);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if(!file.exists()){
            try {
                file.createNewFile();

                ObjectOutputStream oos = null;

                try {

                    //创建用ObjectOutputStream对象
                    oos = new ObjectOutputStream(new FileOutputStream(file));

                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paper);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{

            ObjectOutputStream oos = null;

            try {

                //创建用ObjectOutputStream对象
                oos = new ObjectOutputStream(new FileOutputStream(file));

                //ObjectOutputStream对象writeObject方法把对象序列化到流中
                oos.writeObject(paper);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List<PaperSaved> paperSaved=findAllPaperSaved(uid);
        //window
        //priPath=pubPath+"\\users\\u"+uid+"\\saved.txt";

        //linux
        priPath=pubPath+"/users/u"+uid+"/saved.txt";
        file = new File(priPath);
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            try {
                file.getParentFile().mkdirs();//不存在则创建父目录
                file.createNewFile();
                ObjectOutputStream oos=null;
                try {
                    //创建用ObjectOutputStream对象
                    oos  = new ObjectOutputStream(new FileOutputStream(file));
                    List<PaperSaved> paperSavedO=new ArrayList<>();
                    paperSavedO.add(new PaperSaved(paper.getPaperId(),paper.getUserId(),paper.getPaperTitle(),0));
                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paperSavedO);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if(!file.exists()){
            try {
                file.createNewFile();

                ObjectOutputStream oos = null;

                try {

                    //创建用ObjectOutputStream对象
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    List<PaperSaved> paperSavedO=new ArrayList<>();
                    paperSavedO.add(new PaperSaved(paper.getPaperId(),paper.getUserId(),paper.getPaperTitle(),0));
                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paperSavedO);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {

            ObjectOutputStream oos = null;

            try {

                //创建用ObjectOutputStream对象
                oos = new ObjectOutputStream(new FileOutputStream(file));
                if(paperSaved!=null){
                    int flag=0;
                    for(int i=0;i< paperSaved.size();i++){
                        if(paperSaved.get(i).getPaperId()==paper.getPaperId()){
                            paperSaved.get(i).setPaperTitle(paper.getPaperTitle());
                            flag=1;
                        }
                    }
                    if(flag==0){
                        paperSaved.add(new PaperSaved(paper.getPaperId(),paper.getUserId(),paper.getPaperTitle(),0));
                    }
                }

                else{
                    paperSaved.add(new PaperSaved(paper.getPaperId(),paper.getUserId(),paper.getPaperTitle(),0));
                }

                //ObjectOutputStream对象writeObject方法把对象序列化到流中
                oos.writeObject(paperSaved);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public int[] send(int uid, Paper paper) throws IOException {
        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\papers\\p"+paper.getPaperId()+".txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/papers/p"+paper.getPaperId()+".txt";

        File file = new File(priPath);
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            try {
                file.getParentFile().mkdirs();//不存在则创建父目录
                file.createNewFile();
                ObjectOutputStream oos=null;

                try {
                    //创建用ObjectOutputStream对象
                    oos  = new ObjectOutputStream(new FileOutputStream(file));

                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paper);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if(!file.exists()){
            try {
                file.createNewFile();

                ObjectOutputStream oos = null;

                try {

                    //创建用ObjectOutputStream对象
                    oos = new ObjectOutputStream(new FileOutputStream(file));

                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paper);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{

            ObjectOutputStream oos = null;

            try {

                //创建用ObjectOutputStream对象
                oos = new ObjectOutputStream(new FileOutputStream(file));

                //ObjectOutputStream对象writeObject方法把对象序列化到流中
                oos.writeObject(paper);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        List<PaperSend> paperSend = findAllPaperSend(uid);
        //window
        //priPath=pubPath+"\\users\\u"+uid+"\\send.txt";

        //linux
        priPath=pubPath+"/users/u"+uid+"/send.txt";
        file = new File(priPath);
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            try {
                file.getParentFile().mkdirs();//不存在则创建父目录
                file.createNewFile();
                ObjectOutputStream oos=null;

                try {
                    //创建用ObjectOutputStream对象
                    oos  = new ObjectOutputStream(new FileOutputStream(file));
                    List<PaperSend> paperSendO=new ArrayList<>();
                    paperSendO.add(new PaperSend(paper.getPaperId(),paper.getUserId(),paper.getPaperTitle()));
                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paperSendO);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if(!file.exists()){
            try {
                file.createNewFile();

                ObjectOutputStream oos = null;

                try {

                    //创建用ObjectOutputStream对象
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    List<PaperSend> paperSendO=new ArrayList<>();
                    paperSendO.add(new PaperSend(paper.getPaperId(),paper.getUserId(),paper.getPaperTitle()));
                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paperSendO);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {

            ObjectOutputStream oos = null;

            try {

                //创建用ObjectOutputStream对象
                oos = new ObjectOutputStream(new FileOutputStream(file));

                if(!paperSend.contains(new PaperSend(paper.getPaperId(),paper.getUserId(),paper.getPaperTitle()))){
                    paperSend.add(new PaperSend(paper.getPaperId(),paper.getUserId(),paper.getPaperTitle()));
                }



                //ObjectOutputStream对象writeObject方法把对象序列化到流中
                oos.writeObject(paperSend);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        List<PaperSaved> paperSaved=findAllPaperSaved(uid);

        //window
        //priPath=pubPath+"\\users\\u"+uid+"\\saved.txt";

        //linux
        priPath=pubPath+"/users/u"+uid+"/saved.txt";
        file = new File(priPath);
        if(paperSaved!=null){
            for (int i = 0; i < paperSaved.size(); i++) {
                if (paperSaved.get(i).getPaperId() == paper.getPaperId()) {
                    paperSaved.get(i).setIsSend(1);

                }
            }


            ObjectOutputStream oos = null;

            try {

                //创建用ObjectOutputStream对象
                oos = new ObjectOutputStream(new FileOutputStream(file));

                //ObjectOutputStream对象writeObject方法把对象序列化到流中
                oos.writeObject(paperSaved);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        int[] up={uid,paper.getPaperId()};
        return up;
    }

    @Override
    public Paper display(int uid, int pid) {
        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\papers\\p"+pid+".txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/papers/p"+pid+".txt";
        File file = new File(priPath);
        Paper paper=null;
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            return null;}
        else if(!file.exists()){
            return null;
        }
        else{
            ObjectInputStream ois=null;

            try {

                //创建ObjectIntputStream对象
                ois=new ObjectInputStream(new FileInputStream(file));
                //ObjectIntputStream对象readObject方法反序列化对象（Paper）
                paper=(Paper)ois.readObject();
                if(paper.getPaperStatus()==1){return paper;}
                else {return null;}

            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }
    }

    @Override
    public void renew(int uid, int pid,int cid, Paper paper) {
        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\papers\\p"+pid+".txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/papers/p"+pid+".txt";
        File file = new File(priPath);
        Paper paperReal=null;
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            }
        else if(!file.exists()){

        }
        else{
            ObjectInputStream ois=null;

            try {

                //创建ObjectIntputStream对象
                ois=new ObjectInputStream(new FileInputStream(file));
                //ObjectIntputStream对象readObject方法反序列化对象（Paper）
                paperReal=(Paper)ois.readObject();
                //根据paper的内容更新paperReal统计结果
                paperReal.setPaperCount(paperReal.getPaperCount()+1);
                //根据paper的内容更新paperReal问题的统计结果
                List<Question> questions=paperReal.getQuestions();
                for(int i=0;i<paperReal.getQstNum();i++){
                    Question curQst=paper.getQuestions().get(i);//现有单个问题回答
                    if(questions.get(i).getQstType().equals("radio")){//处理单选题
                        if(curQst.getCurAns1()!=null){
                            questions.get(i).setQstCount(questions.get(i).getQstCount()+1);

                            questions.get(i).getAnsNum().set(questions.get(i).getQstOption().indexOf(curQst.getCurAns1()),questions.get(i).getAnsNum().get(questions.get(i).getQstOption().indexOf(curQst.getCurAns1()))+1);
                            for(int j=0;j<questions.get(i).getOptionNum();j++){
                                questions.get(i).getAnsPer().set(j,(float)(questions.get(i).getAnsNum().get(j))/(float)(questions.get(i).getQstCount()));
                            }

                            System.out.println("单选题被处理了");

                        }

                    }
                    else if(questions.get(i).getQstType().equals("checkbox")){//处理多选题
                        if(curQst.getCurAns2()!=null){
                            questions.get(i).setQstCount(questions.get(i).getQstCount()+1);
                            System.out.println("多选题被处理了");
                            for(int j=0;j<curQst.getCurAns2().size();j++){
                                questions.get(i).getAnsNum().set(questions.get(i).getQstOption().indexOf(curQst.getCurAns2().get(j)),questions.get(i).getAnsNum().get(questions.get(i).getQstOption().indexOf(curQst.getCurAns2().get(j)))+1);
                            }
                        }
                    }
                    else if(questions.get(i).getQstType().equals("text")){//处理填空
                        if(curQst.getCurAns1()!=null){
                            questions.get(i).setQstCount(questions.get(i).getQstCount()+1);
                            questions.get(i).getAns().add(curQst.getCurAns1());

                            System.out.println("填空题被处理了");

                        }
                    }
                    else if(questions.get(i).getQstType().equals("file")){//处理图片
                        if(curQst.getCurAns1()!=null){
                            questions.get(i).setQstCount(questions.get(i).getQstCount()+1);
                            questions.get(i).getAns().add(curQst.getCurAns1());

                            System.out.println("图片题被处理了");
                        }
                    }
                    else{//处理评价，不填默认为50
                        questions.get(i).setTotal(questions.get(i).getTotal()+curQst.getCurAns3());
                        questions.get(i).setQstCount(questions.get(i).getQstCount()+1);
                        questions.get(i).setAvg((float)(questions.get(i).getTotal())/(float)(questions.get(i).getQstCount()));
                        if(curQst.getCurAns3() < 20){
                            questions.get(i).getAnsNum().set(0,questions.get(i).getAnsNum().get(0)+1);
                        }
                        else if(curQst.getCurAns3() < 40){
                            questions.get(i).getAnsNum().set(1,questions.get(i).getAnsNum().get(1)+1);
                        }
                        else if(curQst.getCurAns3() < 60){
                            questions.get(i).getAnsNum().set(2,questions.get(i).getAnsNum().get(2)+1);
                        }
                        else if(curQst.getCurAns3() < 80){
                            questions.get(i).getAnsNum().set(3,questions.get(i).getAnsNum().get(3)+1);
                        }
                        else{
                            questions.get(i).getAnsNum().set(4,questions.get(i).getAnsNum().get(4)+1);
                        }
                        for(int j=0;j<5;j++){
                            questions.get(i).getAnsPer().set(j,(float)(questions.get(i).getAnsNum().get(j))/(float)(questions.get(i).getQstCount()));
                        }
                        System.out.println("评价题被处理了");

                    }
                }
                //更新问题
                paperReal.setQuestions(questions);

            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //更新问卷
            //window
            //priPath=pubPath+"\\users\\u"+uid+"\\papers\\p"+paperReal.getPaperId()+".txt";

            //linux
            priPath=pubPath+"/users/u"+uid+"/papers/p"+paperReal.getPaperId()+".txt";
            file = new File(priPath);
            if (!file.getParentFile().exists()) {//判断父目录路径是否存在
                try {
                    file.getParentFile().mkdirs();//不存在则创建父目录
                    file.createNewFile();
                    ObjectOutputStream oos=null;

                    try {
                        //创建用ObjectOutputStream对象
                        oos  = new ObjectOutputStream(new FileOutputStream(file));

                        //ObjectOutputStream对象writeObject方法把对象序列化到流中
                        oos.writeObject(paperReal);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            else if(!file.exists()){
                try {
                    file.createNewFile();

                    ObjectOutputStream oos = null;

                    try {

                        //创建用ObjectOutputStream对象
                        oos = new ObjectOutputStream(new FileOutputStream(file));

                        //ObjectOutputStream对象writeObject方法把对象序列化到流中
                        oos.writeObject(paperReal);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else{

                ObjectOutputStream oos = null;

                try {

                    //创建用ObjectOutputStream对象
                    oos = new ObjectOutputStream(new FileOutputStream(file));

                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paperReal);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //更新填写用户的填写情况
            List<PaperTaken> paperTaken=findAllPaperTaken(cid);
            //window
            //priPath=pubPath+"\\users\\u"+cid+"\\taken.txt";

            //linux
            priPath=pubPath+"/users/u"+cid+"/taken.txt";
            file = new File(priPath);
            if (!file.getParentFile().exists()) {//判断父目录路径是否存在
                try {
                    file.getParentFile().mkdirs();//不存在则创建父目录
                    file.createNewFile();
                    ObjectOutputStream oos=null;

                    try {
                        //创建用ObjectOutputStream对象
                        oos  = new ObjectOutputStream(new FileOutputStream(file));
                        List<PaperTaken> paperTakenO=new ArrayList<>();
                        paperTakenO.add(new PaperTaken(paperReal.getPaperTitle()));
                        //ObjectOutputStream对象writeObject方法把对象序列化到流中
                        oos.writeObject(paperTakenO);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            else if(!file.exists()){
                try {
                    file.createNewFile();

                    ObjectOutputStream oos = null;

                    try {

                        //创建用ObjectOutputStream对象
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        List<PaperTaken> paperTakenO=new ArrayList<>();
                        paperTakenO.add(new PaperTaken(paperReal.getPaperTitle()));
                        //ObjectOutputStream对象writeObject方法把对象序列化到流中
                        oos.writeObject(paperTakenO);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else {

                ObjectOutputStream oos = null;

                try {

                    //创建用ObjectOutputStream对象
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    paperTaken.add(new PaperTaken(paperReal.getPaperTitle()));

                    //ObjectOutputStream对象writeObject方法把对象序列化到流中
                    oos.writeObject(paperTaken);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        //window
        //file = new File(pubPath+"\\users\\u"+uid+"\\sta\\p"+pid+".xlsx");

        //linux
        file = new File(pubPath+"/users/u"+uid+"/sta/p"+pid+".xlsx");

        if (!file.getParentFile().exists()){
            // 第一次写入需要表头
            if(paperReal.getQuestions()!=null) {
                try {
                    file.getParentFile().mkdirs();//不存在则创建父目录
                    file.createNewFile();
                    List<List<String>> headList=new ArrayList<>();
                    List<String> head=new ArrayList<>();
                    head.add("用户ID");
                    headList.add(head);
                    head=new ArrayList<>();
                    head.add("问卷提交日期");
                    headList.add(head);

                    for(int i=0;i<paperReal.getQstNum();i++){
                        head=new ArrayList<>();
                        if(paperReal.getQuestions().get(i).getQstTitle()!=null){
                            head.add((i+1)+"."+paperReal.getQuestions().get(i).getQstTitle());
                        }
                        else{
                            head.add((i+1)+".无题干");
                        }
                        headList.add(head);
                    }

                    List<List<String>> dataList = new ArrayList<>();
                    if (paper != null) {

                        List<String> data =new ArrayList<>();
                        data.add(String.valueOf(cid));
                        data.add(String.valueOf(LocalDateTime.now()));
                        for(int i=0;i<paperReal.getQstNum();i++){
                            if(paperReal.getQstTypes().get(i).equals("radio")){
                                if(paper.getQuestions().get(i).getCurAns1()==null){
                                    data.add("空");
                                }
                                else{
                                    data.add(paper.getQuestions().get(i).getCurAns1());
                                }

                            }
                            else if(paperReal.getQstTypes().get(i).equals("checkbox")){
                                if(paper.getQuestions().get(i).getCurAns2()==null){
                                    data.add("空");
                                }
                                else{
                                    data.add(paper.getQuestions().get(i).getCurAns2().toString());
                                }

                            }
                            else if(paperReal.getQstTypes().get(i).equals("text")){
                                if(paper.getQuestions().get(i).getCurAns1()==null){
                                    data.add("空");
                                }
                                else{
                                    data.add(paper.getQuestions().get(i).getCurAns1());
                                }

                            }
                            else if(paperReal.getQstTypes().get(i).equals("file")){
                                if(paper.getQuestions().get(i).getCurAns1()==null){
                                    data.add("空");
                                }
                                else{
                                    data.add(paper.getQuestions().get(i).getCurAns1());
                                }
                            }
                            else{
                                data.add(String.valueOf(paper.getQuestions().get(i).getCurAns3()));
                            }


                        }
                        dataList.add(data);


                        EasyExcel.write(file).head(headList).sheet("提交记录").doWrite(dataList);
                    }


                }catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        else if(!file.exists()){
            // 第一次写入需要表头
            if(paperReal.getQuestions()!=null) {
                try {
                    file.createNewFile();

                    List<List<String>> headList=new ArrayList<>();
                    List<String> head=new ArrayList<>();
                    head.add("用户ID");
                    headList.add(head);
                    head=new ArrayList<>();
                    head.add("问卷提交日期");
                    headList.add(head);

                    for(int i=0;i<paperReal.getQstNum();i++){
                        head=new ArrayList<>();
                        if(paperReal.getQuestions().get(i).getQstTitle()!=null){
                            head.add((i+1)+"."+paperReal.getQuestions().get(i).getQstTitle());
                        }
                        else{
                            head.add((i+1)+".无题干");
                        }
                        headList.add(head);
                    }

                    List<List<String>> dataList = new ArrayList<>();
                    if (paper != null) {

                        List<String> data =new ArrayList<>();
                        data.add(String.valueOf(cid));
                        data.add(String.valueOf(LocalDateTime.now()));
                        for(int i=0;i<paperReal.getQstNum();i++){
                            if(paperReal.getQstTypes().get(i).equals("radio")){
                                if(paper.getQuestions().get(i).getCurAns1()==null){
                                    data.add("空");
                                }
                                else{
                                    data.add(paper.getQuestions().get(i).getCurAns1());
                                }

                            }
                            else if(paperReal.getQstTypes().get(i).equals("checkbox")){
                                if(paper.getQuestions().get(i).getCurAns2()==null){
                                    data.add("空");
                                }
                                else{
                                    data.add(paper.getQuestions().get(i).getCurAns2().toString());
                                }

                            }
                            else if(paperReal.getQstTypes().get(i).equals("text")){
                                if(paper.getQuestions().get(i).getCurAns1()==null){
                                    data.add("空");
                                }
                                else{
                                    data.add(paper.getQuestions().get(i).getCurAns1());
                                }

                            }
                            else if(paperReal.getQstTypes().get(i).equals("file")){
                                if(paper.getQuestions().get(i).getCurAns1()==null){
                                    data.add("空");
                                }
                                else{
                                    data.add(paper.getQuestions().get(i).getCurAns1());
                                }
                            }
                            else{
                                data.add(String.valueOf(paper.getQuestions().get(i).getCurAns3()));
                            }


                        }
                        dataList.add(data);


                        EasyExcel.write(file).head(headList).sheet("提交记录").doWrite(dataList);

                    }

                }catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        else {
            // 第二次按照原有格式，不需要表头，追加写入
            if(paperReal.getQuestions()!=null) {
                //window
                //File temp = new File(pubPath+"\\users\\u"+uid+"\\sta\\p"+pid+"temp.xlsx");

                //linux
                File temp = new File(pubPath+"/users/u"+uid+"/sta/p"+pid+"temp.xlsx");
                if(!temp.exists()){
                    try{
                        temp.createNewFile();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                List<List<String>> dataList = new ArrayList<>();
                if (paper != null) {

                    List<String> data =new ArrayList<>();
                    data.add(String.valueOf(cid));
                    data.add(String.valueOf(LocalDateTime.now()));
                    for(int i=0;i<paperReal.getQstNum();i++){
                        if(paperReal.getQstTypes().get(i).equals("radio")){
                            if(paper.getQuestions().get(i).getCurAns1()==null){
                                data.add("空");
                            }
                            else{
                                data.add(paper.getQuestions().get(i).getCurAns1());
                            }

                        }
                        else if(paperReal.getQstTypes().get(i).equals("checkbox")){
                            if(paper.getQuestions().get(i).getCurAns2()==null){
                                data.add("空");
                            }
                            else{
                                data.add(paper.getQuestions().get(i).getCurAns2().toString());
                            }

                        }
                        else if(paperReal.getQstTypes().get(i).equals("text")){
                            if(paper.getQuestions().get(i).getCurAns1()==null){
                                data.add("空");
                            }
                            else{
                                data.add(paper.getQuestions().get(i).getCurAns1());
                            }

                        }
                        else if(paperReal.getQstTypes().get(i).equals("file")){
                            if(paper.getQuestions().get(i).getCurAns1()==null){
                                data.add("空");
                            }
                            else{
                                data.add(paper.getQuestions().get(i).getCurAns1());


                            }
                        }
                        else{
                            data.add(String.valueOf(paper.getQuestions().get(i).getCurAns3()));
                        }


                    }
                    dataList.add(data);


                    EasyExcel.write(file).needHead(false)
                            .withTemplate(file)
                            .file(temp).sheet("提交记录").doWrite(dataList);
                    file.delete();
                    temp.renameTo(file);
                }

            }
        }



    }

    @Override
    public List<String> show(int uid, int pid) throws IOException {
        Paper paper=display(uid,pid);
        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\ana\\p"+pid+".txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/ana/p"+pid+".txt";
        File file = new File(priPath);

        if (!file.getParentFile().exists()) {//判断父目录路径是否存在，即"u"+uid+".txt"前的pubPath+"\\numbers\\"
            try {
                file.getParentFile().mkdirs();//不存在则创建父目录
                file.createNewFile();
                if(paper!=null){
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    //向文件中写入内容
                    bw.write("这是用户"+paper.getUserId()+"创建的第"+paper.getPaperId()+"份问卷");
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷标题："+paper.getPaperTitle());
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷摘要:"+paper.getPaperSummary());
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷发布于:"+paper.getPaperStartTime());
                    bw.newLine();
                    bw.flush();

                    bw.write("截止到目前时间：:"+ LocalDateTime.now()+" 有"+paper.getPaperCount()+"人填写了该问卷");
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷有"+paper.getQstNum()+"个问题");
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷的问题类型分别为：");
                    for(int i =0;i<paper.getQstNum();i++){
                        bw.write("第"+(i+1)+"题:"+paper.getQstTypes().get(i)+" ");
                    }
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷的结果分析如下：");
                    bw.newLine();
                    bw.flush();

                    for(int i =0;i<paper.getQstNum();i++){
                        bw.write("第"+(i+1)+"题:"+paper.getQuestions().get(i).getQstType());
                        bw.newLine();
                        bw.flush();
                        if(paper.getQuestions().get(i).getQstTitle()!=null){
                            bw.write("题干："+paper.getQuestions().get(i).getQstTitle());
                            bw.newLine();
                            bw.flush();
                        }
                        else{
                            bw.write("题干：无题干");
                            bw.newLine();
                            bw.flush();
                        }
                        bw.write("该问题的有效回答人数为"+paper.getQuestions().get(i).getQstCount());
                        bw.newLine();
                        bw.flush();
                        if(paper.getQuestions().get(i).getQstType().equals("radio")){
                            if(paper.getQuestions().get(i).getQstOption()!=null){

                                bw.write("该单选题的选项，选择人数和选择百分比分别为：");
                                bw.newLine();
                                bw.flush();
                                for(int j=0;j<paper.getQuestions().get(i).getOptionNum();j++){
                                    bw.write(paper.getQuestions().get(i).getQstOption().get(j)+" "+paper.getQuestions().get(i).getAnsNum().get(j)+" "+paper.getQuestions().get(i).getAnsPer().get(j));
                                    bw.newLine();
                                    bw.flush();
                                }
                            }
                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("checkbox")){
                            if(paper.getQuestions().get(i).getQstOption()!=null){

                                bw.write("该多选题的选项和选择人数分别为：");
                                bw.newLine();
                                bw.flush();
                                for(int j=0;j<paper.getQuestions().get(i).getOptionNum();j++){
                                    bw.write(paper.getQuestions().get(i).getQstOption().get(j)+" "+paper.getQuestions().get(i).getAnsNum().get(j));
                                    bw.newLine();
                                    bw.flush();
                                }
                            }
                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("text")){

                                if(paper.getQuestions().get(i).getAns()!=null){
                                    bw.write("该填空题的回答有：");
                                    bw.newLine();
                                    bw.flush();
                                    for(int j=0;j<paper.getQuestions().get(i).getQstCount();j++){
                                        bw.write(paper.getQuestions().get(i).getAns().get(j));
                                        bw.newLine();
                                        bw.flush();
                                    }
                                }

                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("file")){

                                if(paper.getQuestions().get(i).getAns()!=null){
                                    bw.write("该图片上传题的回答链接有：");
                                    bw.newLine();
                                    bw.flush();
                                    for(int j=0;j<paper.getQuestions().get(i).getQstCount();j++){
                                        bw.write(paper.getQuestions().get(i).getAns().get(j));
                                        bw.newLine();
                                        bw.flush();
                                    }
                                }

                        }else{

                                bw.write("该评价题的回答平均值是："+paper.getQuestions().get(i).getAvg());
                                bw.newLine();
                                bw.flush();
                                bw.write("该评价题的五个分数段(0-20,20-40,40-60,60-80,80-100)的人数分别是："+paper.getQuestions().get(i).getAnsNum().get(0)+" "+paper.getQuestions().get(i).getAnsNum().get(1)+" "+paper.getQuestions().get(i).getAnsNum().get(2)+" "+paper.getQuestions().get(i).getAnsNum().get(3)+" "+paper.getQuestions().get(i).getAnsNum().get(4));
                                bw.newLine();
                                bw.flush();
                                bw.write("该评价题的五个分数段(0-20,20-40,40-60,60-80,80-100)的百分比分别是："+paper.getQuestions().get(i).getAnsPer().get(0)+" "+paper.getQuestions().get(i).getAnsPer().get(1)+" "+paper.getQuestions().get(i).getAnsPer().get(2)+" "+paper.getQuestions().get(i).getAnsPer().get(3)+" "+paper.getQuestions().get(i).getAnsPer().get(4));
                                bw.newLine();
                                bw.flush();
                            }

                    }

                    bw.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(!file.exists()){
            try {
                file.createNewFile();
                if(paper!=null){
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    //向文件中写入内容
                    bw.write("这是用户"+paper.getUserId()+"创建的第"+paper.getPaperId()+"份问卷");
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷标题："+paper.getPaperTitle());
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷摘要:"+paper.getPaperSummary());
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷发布于:"+paper.getPaperStartTime());
                    bw.newLine();
                    bw.flush();

                    bw.write("截止到目前时间：:"+ LocalDateTime.now()+" 有"+paper.getPaperCount()+"人填写了该问卷");
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷有"+paper.getQstNum()+"个问题");
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷的问题类型分别为：");
                    for(int i =0;i<paper.getQstNum();i++){
                        bw.write("第"+(i+1)+"题:"+paper.getQstTypes().get(i)+" ");
                    }
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷的结果分析如下：");
                    bw.newLine();
                    bw.flush();

                    for(int i =0;i<paper.getQstNum();i++){
                        bw.write("第"+(i+1)+"题:"+paper.getQuestions().get(i).getQstType());
                        bw.newLine();
                        bw.flush();
                        if(paper.getQuestions().get(i).getQstTitle()!=null){
                            bw.write("题干："+paper.getQuestions().get(i).getQstTitle());
                            bw.newLine();
                            bw.flush();
                        }
                        else{
                            bw.write("题干：无题干");
                            bw.newLine();
                            bw.flush();
                        }
                        bw.write("该问题的有效回答人数为"+paper.getQuestions().get(i).getQstCount());
                        bw.newLine();
                        bw.flush();
                        if(paper.getQuestions().get(i).getQstType().equals("radio")){
                            if(paper.getQuestions().get(i).getQstOption()!=null){

                                bw.write("该单选题的选项，选择人数和选择百分比分别为：");
                                bw.newLine();
                                bw.flush();
                                for(int j=0;j<paper.getQuestions().get(i).getOptionNum();j++){
                                    bw.write(paper.getQuestions().get(i).getQstOption().get(j)+" "+paper.getQuestions().get(i).getAnsNum().get(j)+" "+paper.getQuestions().get(i).getAnsPer().get(j));
                                    bw.newLine();
                                    bw.flush();
                                }
                            }
                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("checkbox")){
                            if(paper.getQuestions().get(i).getQstOption()!=null){

                                bw.write("该多选题的选项和选择人数分别为：");
                                bw.newLine();
                                bw.flush();
                                for(int j=0;j<paper.getQuestions().get(i).getOptionNum();j++){
                                    bw.write(paper.getQuestions().get(i).getQstOption().get(j)+" "+paper.getQuestions().get(i).getAnsNum().get(j));
                                    bw.newLine();
                                    bw.flush();
                                }
                            }
                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("text")){

                                if(paper.getQuestions().get(i).getAns()!=null){
                                    bw.write("该填空题的回答有：");
                                    bw.newLine();
                                    bw.flush();
                                    for(int j=0;j<paper.getQuestions().get(i).getQstCount();j++){
                                        bw.write(paper.getQuestions().get(i).getAns().get(j));
                                        bw.newLine();
                                        bw.flush();
                                    }
                                }

                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("file")){

                                if(paper.getQuestions().get(i).getAns()!=null){
                                    bw.write("该图片上传题的回答链接有：");
                                    bw.newLine();
                                    bw.flush();
                                    for(int j=0;j<paper.getQuestions().get(i).getQstCount();j++){
                                        bw.write(paper.getQuestions().get(i).getAns().get(j));
                                        bw.newLine();
                                        bw.flush();
                                    }
                                }

                        }else{

                                bw.write("该评价题的回答平均值是："+paper.getQuestions().get(i).getAvg());
                                bw.newLine();
                                bw.flush();
                                bw.write("该评价题的五个分数段(0-20,20-40,40-60,60-80,80-100)的人数分别是："+paper.getQuestions().get(i).getAnsNum().get(0)+" "+paper.getQuestions().get(i).getAnsNum().get(1)+" "+paper.getQuestions().get(i).getAnsNum().get(2)+" "+paper.getQuestions().get(i).getAnsNum().get(3)+" "+paper.getQuestions().get(i).getAnsNum().get(4));
                                bw.newLine();
                                bw.flush();
                                bw.write("该评价题的五个分数段(0-20,20-40,40-60,60-80,80-100)的百分比分别是："+paper.getQuestions().get(i).getAnsPer().get(0)+" "+paper.getQuestions().get(i).getAnsPer().get(1)+" "+paper.getQuestions().get(i).getAnsPer().get(2)+" "+paper.getQuestions().get(i).getAnsPer().get(3)+" "+paper.getQuestions().get(i).getAnsPer().get(4));
                                bw.newLine();
                                bw.flush();

                        }
                    }

                    bw.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            try {
                if(paper!=null){
                    /*BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine(); // 读取文件的一行
                    int i= Integer.parseInt(line.trim()); // 将字符串转换为整数
                    br.close();*/
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));//向文件中写入内容
                    //向文件中写入内容
                    bw.write("这是用户"+paper.getUserId()+"创建的第"+paper.getPaperId()+"份问卷");
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷标题："+paper.getPaperTitle());
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷摘要:"+paper.getPaperSummary());
                    bw.newLine();
                    bw.flush();

                    bw.write("问卷发布于:"+paper.getPaperStartTime());
                    bw.newLine();
                    bw.flush();

                    bw.write("截止到目前时间：:"+ LocalDateTime.now()+" 有"+paper.getPaperCount()+"人填写了该问卷");
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷有"+paper.getQstNum()+"个问题");
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷的问题类型分别为：");
                    for(int i =0;i<paper.getQstNum();i++){
                        bw.write("第"+(i+1)+"题:"+paper.getQstTypes().get(i)+" ");
                    }
                    bw.newLine();
                    bw.flush();

                    bw.write("该问卷的结果分析如下：");
                    bw.newLine();
                    bw.flush();

                    for(int i =0;i<paper.getQstNum();i++){
                        bw.write("第"+(i+1)+"题:"+paper.getQuestions().get(i).getQstType());
                        bw.newLine();
                        bw.flush();
                        if(paper.getQuestions().get(i).getQstTitle()!=null){
                            bw.write("题干："+paper.getQuestions().get(i).getQstTitle());
                            bw.newLine();
                            bw.flush();
                        }
                        else{
                            bw.write("题干：无题干");
                            bw.newLine();
                            bw.flush();
                        }
                        bw.write("该问题的有效回答人数为"+paper.getQuestions().get(i).getQstCount());
                        bw.newLine();
                        bw.flush();
                        if(paper.getQuestions().get(i).getQstType().equals("radio")){
                            if(paper.getQuestions().get(i).getQstOption()!=null){

                                bw.write("该单选题的选项，选择人数和选择百分比分别为：");
                                bw.newLine();
                                bw.flush();
                                for(int j=0;j<paper.getQuestions().get(i).getOptionNum();j++){
                                    bw.write(paper.getQuestions().get(i).getQstOption().get(j)+" "+paper.getQuestions().get(i).getAnsNum().get(j)+" "+paper.getQuestions().get(i).getAnsPer().get(j));
                                    bw.newLine();
                                    bw.flush();
                                }
                            }
                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("checkbox")){
                            if(paper.getQuestions().get(i).getQstOption()!=null){

                                bw.write("该多选题的选项和选择人数分别为：");
                                bw.newLine();
                                bw.flush();
                                for(int j=0;j<paper.getQuestions().get(i).getOptionNum();j++){
                                    bw.write(paper.getQuestions().get(i).getQstOption().get(j)+" "+paper.getQuestions().get(i).getAnsNum().get(j));
                                    bw.newLine();
                                    bw.flush();
                                }
                            }
                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("text")){

                                if(paper.getQuestions().get(i).getAns()!=null){
                                    bw.write("该填空题的回答有：");
                                    bw.newLine();
                                    bw.flush();
                                    for(int j=0;j<paper.getQuestions().get(i).getQstCount();j++){
                                        bw.write(paper.getQuestions().get(i).getAns().get(j));
                                        bw.newLine();
                                        bw.flush();
                                    }
                                }

                        }
                        else if(paper.getQuestions().get(i).getQstType().equals("file")){

                                if(paper.getQuestions().get(i).getAns()!=null){
                                    bw.write("该图片上传题的回答链接有：");
                                    bw.newLine();
                                    bw.flush();
                                    for(int j=0;j<paper.getQuestions().get(i).getQstCount();j++){
                                        bw.write(paper.getQuestions().get(i).getAns().get(j));
                                        bw.newLine();
                                        bw.flush();
                                    }
                                }

                        }else{

                                bw.write("该评价题的回答平均值是："+paper.getQuestions().get(i).getAvg());
                                bw.newLine();
                                bw.flush();
                                bw.write("该评价题的五个分数段(0-20,20-40,40-60,60-80,80-100)的人数分别是："+paper.getQuestions().get(i).getAnsNum().get(0)+" "+paper.getQuestions().get(i).getAnsNum().get(1)+" "+paper.getQuestions().get(i).getAnsNum().get(2)+" "+paper.getQuestions().get(i).getAnsNum().get(3)+" "+paper.getQuestions().get(i).getAnsNum().get(4));
                                bw.newLine();
                                bw.flush();
                                bw.write("该评价题的五个分数段(0-20,20-40,40-60,60-80,80-100)的百分比分别是："+paper.getQuestions().get(i).getAnsPer().get(0)+" "+paper.getQuestions().get(i).getAnsPer().get(1)+" "+paper.getQuestions().get(i).getAnsPer().get(2)+" "+paper.getQuestions().get(i).getAnsPer().get(3)+" "+paper.getQuestions().get(i).getAnsPer().get(4));
                                bw.newLine();
                                bw.flush();


                        }
                    }

                    bw.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<String> urls=new ArrayList<>();

        MultipartFile cMultiFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
        urls.add( aliOSSUtils.upload(cMultiFile));
        //window
        //priPath=pubPath+"\\users\\u"+uid+"\\sta\\p"+pid+".xlsx";

        //linux
        priPath=pubPath+"/users/u"+uid+"/sta/p"+pid+".xlsx";
        file = new File(priPath);
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            }
        else if(!file.exists()){

        }
        else{
            cMultiFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
            urls.add( aliOSSUtils.upload(cMultiFile));
        }
        //调用阿里云OSS工具类进行文件上传
        return  urls;


    }

    @Override
    public Paper modify(int uid, int pid) throws IOException {
        int newPid= add(uid);
        Paper paper=display(uid,pid);
        paper.setPaperId(newPid);
        return paper;
    }
    @Override
    public Paper change(int uid, int pid) {
        //window
        //String priPath=pubPath+"\\users\\u"+uid+"\\papers\\p"+pid+".txt";

        //linux
        String priPath=pubPath+"/users/u"+uid+"/papers/p"+pid+".txt";
        File file = new File(priPath);
        Paper paper=null;
        if (!file.getParentFile().exists()) {//判断父目录路径是否存在
            return null;}
        else if(!file.exists()){
            return null;
        }
        else{
            ObjectInputStream ois=null;

            try {

                //创建ObjectIntputStream对象
                ois=new ObjectInputStream(new FileInputStream(file));
                //ObjectIntputStream对象readObject方法反序列化对象（Paper）
                paper=(Paper)ois.readObject();
                return paper;

            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }
    }

}
