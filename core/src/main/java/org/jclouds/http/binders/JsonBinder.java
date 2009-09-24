/**
 *
 * Copyright (C) 2009 Global Cloud Specialists, Inc. <info@globalcloudspecialists.com>
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 */
package org.jclouds.http.binders;

import java.util.Collections;
import static com.google.common.base.Preconditions.checkState;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.jclouds.http.HttpRequest;
import org.jclouds.rest.MapEntityBinder;

import com.google.gson.Gson;
import javax.inject.Inject;

/**
 * Binds the object to the request as a json object.
 * 
 * @author Adrian Cole
 * @since 4.0
 */
public class JsonBinder implements MapEntityBinder {

   @Inject
   protected Gson gson;

   public void addEntityToRequest(Map<String, String> postParams, HttpRequest request) {
      addEntityToRequest((Object) postParams, request);
   }

   public void addEntityToRequest(Object toBind, HttpRequest request) {
      checkState(gson != null, "Program error: gson should have been injected at this point");
      String json = gson.toJson(toBind);
      request.setEntity(json);
      request.getHeaders().replaceValues(HttpHeaders.CONTENT_LENGTH,
               Collections.singletonList(json.getBytes().length + ""));
      request.getHeaders().replaceValues(HttpHeaders.CONTENT_TYPE,
               Collections.singletonList(MediaType.APPLICATION_JSON));
   }

}