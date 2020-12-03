package ru.itis.javalab.utils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


@Parameters(separators = "=")
public class Args {
    @Parameter(names =  "--server-ip" , description = "server IP")
    public String serverIP;

    @Parameter(names = "--server-port", description = "server port")
    public int port;

}