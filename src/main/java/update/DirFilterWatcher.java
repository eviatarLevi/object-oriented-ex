package main.java.update;

import java.io.*;

public class DirFilterWatcher implements FileFilter {
  private String filter;

  public DirFilterWatcher() {
    this.filter = "";
  }

  public DirFilterWatcher(String filter) {
    this.filter = filter;
  }

  public boolean accept(File file) {
    if ("".equals(filter)) {
      return true;
    }
    return (file.getName().endsWith(filter));
  }
}