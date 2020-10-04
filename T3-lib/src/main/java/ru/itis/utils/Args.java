package ru.itis.utils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java.util.List;

@Parameters(separators = "=")
public class Args {

  @Parameter(names =  "--mode" , description = "0ne or many treads")
  public String mode;

  @Parameter(names = "--count", description = "number of treads")
  public int count;
  
  @Parameter(names =  "--files" , splitter = SplitterWithSemicolon.class, description = "paths")
  public List <String> files;
  

  @Parameter(names = "--folder", description = "folder")
  public String folder;
}