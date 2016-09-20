package com.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UpdateServiceVO {
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
