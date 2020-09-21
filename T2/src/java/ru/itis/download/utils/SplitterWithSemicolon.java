package ru.itis.download.utils;

import com.beust.jcommander.converters.IParameterSplitter;

import java.util.Arrays;
import java.util.List;

public  class SplitterWithSemicolon implements IParameterSplitter {
   public List<String> split(String value) {
      return Arrays.asList(value.split("LLL"));
    }
}