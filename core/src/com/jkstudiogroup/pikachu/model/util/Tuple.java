package com.jkstudiogroup.pikachu.model.util;

public class Tuple<X, Y> {
  public X obj;
  public Y delta;

  Tuple(X x, Y y){
    obj = x;
    delta = y;
  }
}
