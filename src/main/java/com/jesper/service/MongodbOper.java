package com.jesper.service;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.LinkedHashMap;

@Service
public class MongodbOper {
    @Autowired
    private  GridFsTemplate gridFsTemplate;
    public String SaveFile(File file,String name)  {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            String content = getFileType(name);
            GridFSFile fsFile = gridFsTemplate.store(in,name,content);
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
            return fsFile.getId().toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }

    }
    public String FindFile(String id,String file)  {
        Query query = Query.query(Criteria.where("_id").is(id));
        GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(query);
        if (gridFSDBFile == null){
            return "";
        }
        FileOutputStream outputStream = null;
        try {
            String filepath = file+gridFSDBFile.getFilename()+gridFSDBFile.getContentType();
            outputStream = new FileOutputStream(filepath);
            gridFSDBFile.writeTo(outputStream);
            outputStream.close();
            return gridFSDBFile.getFilename()+gridFSDBFile.getContentType();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getFileType(String file){
        int index = file.indexOf(".");
        String tmp = file.substring(index);
        return tmp;

    }
}
