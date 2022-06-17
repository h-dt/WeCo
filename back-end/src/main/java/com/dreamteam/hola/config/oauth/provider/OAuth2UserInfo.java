package com.dreamteam.hola.config.oauth.provider;

import java.util.HashMap;
import java.util.Map;

public interface OAuth2UserInfo {

  Map<String,Object> getAttributes();
  String getProviderId();
  String getProvider();
  String getEmail();
  String getName();
  String profileImage();
  String nickname();

}
