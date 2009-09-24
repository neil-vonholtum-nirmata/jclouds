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
package org.jclouds.azure.storage.blob.internal;

import org.jclouds.azure.storage.blob.AzureBlobStore;
import org.jclouds.azure.storage.blob.domain.Blob;
import org.jclouds.azure.storage.blob.domain.BlobMetadata;
import org.jclouds.azure.storage.blob.domain.ContainerMetadata;
import org.jclouds.blobstore.LiveInputStreamMap;

import javax.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Map representation of a live connection to AzureBlob. All put operations will result in ETag
 * calculation. If this is not desired, use {@link LiveAzureBlobObjectMap} instead.
 * 
 * @author Adrian Cole
 * @see AzureBlobStore
 * @see LiveInputStreamMap
 */
public class LiveAzureBlobInputStreamMap extends
         LiveInputStreamMap<ContainerMetadata, BlobMetadata, Blob> {

   @Override
   public boolean containsValue(Object value) {
      throw new UnsupportedOperationException(
               "http://code.google.com/p/jclouds/issues/detail?id=90");
   }

   @Inject
   public LiveAzureBlobInputStreamMap(AzureBlobStore connection, @Assisted String container) {
      super(connection, container);
   }

   @Override
   protected Blob createBlob(String s) {
      return new Blob(s);
   }
}
