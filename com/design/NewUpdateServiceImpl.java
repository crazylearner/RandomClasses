package com.design;

public class NewUpdateServiceImpl implements NewUpdateService{

  public void update(AssetUpdateProperties assetUpdateProperties, List<Object> dataToUpdate) {
    List<UpdateServiceVO> updateServiceVOs = assetUpdateProperties.getUpdateServiceVos();
    for(UpdateServiceVO updateServiceVO: updateServiceVOs) {
      UpdateType updateType = updateServiceVO.updateType;
      updateType.update(updateServiceVO, dataToUpdate);
    }
  }
}
