package com.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by schellamuthu on 9/9/16.
 */
public class HowICallUpdate1 {

  public static void main(String[] args) {
    NewUpdateServiceImpl updateServiceImpl = new NewUpdateServiceImpl();
    AssetUpdateProperties assetUpdateProperties = new PlaybookGuy();
    List<Object> dataList = new ArrayList();
    updateServiceImpl.update(assetUpdateProperties, dataList);
  }
}
