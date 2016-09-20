package com.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PlaybookGuy implements AssetUpdateProperties {

  @Override
  public List<UpdateServiceVO> getUpdateServiceVos() {
    List<UpdateServiceVO> updateServiceVOs = new ArrayList();
    UpdateServiceVO updateServiceVO = new UpdateServiceVO();
    updateServiceVO.requestObj = "MyRequest";
    updateServiceVO.transformResponse = this::transformResponse;
    String test = "";
    updateServiceVO.assetIndentifiersMap = () -> {
      Map map = new HashMap();
      return map;
    };
    updateServiceVO.updateType = new SFDCRestServiceUpdate();
    return updateServiceVOs;
  }

  private Object transformResponse(Object response) {
    return "Transformed Response";
  }
}
