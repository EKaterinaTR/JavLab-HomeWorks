package ru.itis.download.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Task implements Runnable {
    String url ;
    String path ;

    public Task(String url,String path){
        this.path = path;
        this.url = url;

    }

    
    @Override
    public void run() {
        URL connection;
        InputStream inputStream;
        FileOutputStream out;

        try {
             connection = new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }

        try {
             inputStream = connection.openStream();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        try {
            String [] strings = url.split("/");
             out = new FileOutputStream(path +"\\" + strings[strings.length - 1]);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        byte buffer[] = new byte[10];
        int c;
        try {
            c = inputStream.read(buffer);
            while (c > 0) {
            out.write(buffer, 0, c);
            c = inputStream.read(buffer);
            out.flush();
            }
            out.close();
            inputStream.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}