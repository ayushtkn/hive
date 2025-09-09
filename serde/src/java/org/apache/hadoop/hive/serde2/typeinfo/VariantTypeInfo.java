/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;

public class VariantTypeInfo extends TypeInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  // Fixed field names and types for variant
  private static final List<String> FIELD_NAMES = Arrays.asList("metadata", "value");
  private static final List<TypeInfo> FIELD_TYPES =
      Arrays.asList(TypeInfoFactory.binaryTypeInfo, TypeInfoFactory.binaryTypeInfo);

  // Pre-computed ObjectInspectors for efficiency
  private static final List<ObjectInspector> FIELD_OBJECT_INSPECTORS =
      Arrays.asList(TypeInfoUtils.getStandardJavaObjectInspectorFromTypeInfo(TypeInfoFactory.binaryTypeInfo),
          TypeInfoUtils.getStandardJavaObjectInspectorFromTypeInfo(TypeInfoFactory.binaryTypeInfo));

  public VariantTypeInfo() {
    // Default constructor for serialization
  }

  @Override
  public String getTypeName() {
    return "variant";
  }

  @Override
  public ObjectInspector.Category getCategory() {
    return ObjectInspector.Category.VARIANT;
  }

  public List<TypeInfo> getAllStructFieldTypeInfos() {
    return FIELD_TYPES;
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof VariantTypeInfo;
  }

  @Override
  public int hashCode() {
    return getTypeName().hashCode();
  }

  @Override
  public String toString() {
    return getTypeName();
  }

  public ObjectInspector getObjectInspector() {
    return ObjectInspectorFactory.getStandardStructObjectInspector(FIELD_NAMES, FIELD_OBJECT_INSPECTORS);
  }
}