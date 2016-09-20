package com.gainsight.migrator.service.updater;

import com.gainsight.migrator.utils.LogDataIdentifier;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Created by schellamuthu on 9/9/16.
 */
public class HowICallUpdate1 {

  public static void main(String[] args) {
    NewUpdateServiceImpl updateServiceImpl = new NewUpdateServiceImpl();
    AssetUpdateProperties assetUpdateProperties = new PlaybookGuy();
    List<Object> dataList = Lists.newArrayList();

    CompletableFuture updateAction = CompletableFuture.supplyAsync(()-> {
      updateServiceImpl.update(assetUpdateProperties, dataList);
      return "";
    });

    CollectionUtils.isNotEmpty(null);

    updateAction.thenAccept((response)-> {
      updateServiceImpl.update(assetUpdateProperties, dataList);

    });
  }
}

interface NewUpdateService {
  void update(AssetUpdateProperties assetUpdateProperties, List<Object> dataToUpdate);
}

class NewUpdateServiceImpl implements NewUpdateService{

  public void update(AssetUpdateProperties assetUpdateProperties, List<Object> dataToUpdate) {
    List<UpdateServiceVO> updateServiceVOs = assetUpdateProperties.getUpdateServiceVos();
    for(UpdateServiceVO updateServiceVO: updateServiceVOs) {
      UpdateType updateType = updateServiceVO.updateType;
      updateType.update(updateServiceVO, dataToUpdate);
    }
  }
}

interface AssetUpdateProperties {
  List<UpdateServiceVO> getUpdateServiceVos();
}

class PlaybookGuy implements AssetUpdateProperties {

  @Override
  public List<UpdateServiceVO> getUpdateServiceVos() {
    List<UpdateServiceVO> updateServiceVOs = Lists.newArrayList();
    UpdateServiceVO updateServiceVO = new UpdateServiceVO();
    updateServiceVO.requestObj = "MyRequest";
    updateServiceVO.transformResponse = this::transformResponse;
    String test = "";
    updateServiceVO.assetIndentifiersMap = () -> {
        System.out.print(test);
        Map<String,LogDataIdentifier> map = Maps.newHashMap();
        return map;
    };
    updateServiceVO.updateType = new SFDCRestServiceUpdate();
    return updateServiceVOs;
  }

  private Object transformResponse(Object response) {
    return "Transformed Response";
  }
}

class UpdateServiceVO {
  UpdateType updateType;
  Object requestObj;
  Function<Object,Object> transformResponse;
  Function<Object,String> identifyObject;
  Function<Object,Object> preResolve;
  Function<Object,Object> postResolve;
  //Can add update hook also.
  Function<Object,Object> preUpdate;
  Function<Object,Object> postUpdate;
  AssetIdentifiersMap assetIndentifiersMap;
}

@FunctionalInterface
interface AssetIdentifiersMap {
  Map<String, LogDataIdentifier> get();
}

interface UpdateType {
  void update(UpdateServiceVO updateServiceVO, List<Object> dataToUpdate);
}

class SFDCRestServiceUpdate implements UpdateType {

  @Override
  public void update(UpdateServiceVO updateServiceVO, List<Object> dataToUpdate) {
    for(Object data: dataToUpdate) {
      //Get resolver object
      //Read the Log Data Identifiers using the Object identifier Map
      Map<String, LogDataIdentifier> objectIdentifiersMap = updateServiceVO.assetIndentifiersMap.get();
      String objectName = updateServiceVO.identifyObject.apply(data);
      data = updateServiceVO.preResolve.apply(data);
      //resolve using the resolver
      data = updateServiceVO.postResolve.apply(data);
      //if(data is not null or empty )
      data = updateServiceVO.preUpdate.apply(data);

      //Update By Bulk Write
      Object response = null;

      //data = updateServiceVO.postUpdate.apply(data); -- needed??

      //transform the response
      Object processedResp = updateServiceVO.transformResponse.apply(response);

      //update the log using the transformed response, source data
    }
  }
}


class SFDCBulkWriteUpdate implements UpdateType {

  @Override
  public void update(UpdateServiceVO updateServiceVO, List<Object> dataToUpdate) {
    for(Object data: dataToUpdate) {
      //Get resolver object
      //Identify Object Name
      //Read the Log Data Identifiers using the Object identifier Map
      Map<String, LogDataIdentifier> objectIdentifiersMap = updateServiceVO.assetIndentifiersMap.get();
      String objectName = updateServiceVO.identifyObject.apply(data);
      data = updateServiceVO.preResolve.apply(data);
      //resolve using the resolver
      data = updateServiceVO.postResolve.apply(data);
      //if(data is not null or empty )+
      data = updateServiceVO.preUpdate.apply(data);

      Object request = updateServiceVO.requestObj;
      //Call the SFDC REST service with the Request
      Object response = null;

      // data = updateServiceVO.postUpdate.apply(data); -- needed??

      //transform the response
      Object processedResp = updateServiceVO.transformResponse.apply(response);

      //Update the log using the transformed response, source data, logIdentifiers
      //Update the ID Map using the transformed response, source data, logIdentifiers

    }
  }
}
