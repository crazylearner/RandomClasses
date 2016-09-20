package com.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class NewUpdateServiceImpl implements NewUpdateService{

  public void update(AssetUpdateProperties assetUpdateProperties, List<Object> dataToUpdate) {
    List<UpdateServiceVO> updateServiceVOs = assetUpdateProperties.getUpdateServiceVos();
    for(UpdateServiceVO updateServiceVO: updateServiceVOs) {
      UpdateType updateType = updateServiceVO.updateType;
      updateType.update(updateServiceVO, dataToUpdate);
    }
  }
}
