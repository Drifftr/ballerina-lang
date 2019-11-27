/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*/
package org.wso2.ballerinalang.programfile;

import org.wso2.ballerinalang.programfile.attributes.AttributeInfo;
import org.wso2.ballerinalang.programfile.attributes.AttributeInfoPool;
import org.wso2.ballerinalang.programfile.cpentries.ConstantPool;
import org.wso2.ballerinalang.programfile.cpentries.ConstantPoolEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * {@code PackageInfo} contains metadata of a Ballerina package entry in the program file.
 *
 * @since 0.87
 */
@Deprecated
public class PackageInfo implements ConstantPool, AttributeInfoPool {

    public int orgNameCPIndex;
    public int nameCPIndex;
    public int versionCPIndex;

    private List<ConstantPoolEntry> constantPoolEntries = new ArrayList<>();

    public List<Instruction> instructionList = new ArrayList<>();

    public Set<ImportPackageInfo> importPkgInfoSet = new HashSet<>();

    public Map<String, ConstantInfo> constantInfoMap = new LinkedHashMap<>();

    public Map<String, PackageVarInfo> pkgVarInfoMap = new LinkedHashMap<>();

    public Map<String, FunctionInfo> functionInfoMap = new LinkedHashMap<>();

    public Map<String, TypeDefInfo> typeDefInfoMap = new LinkedHashMap<>();

    public Map<String, AnnotationInfo> annotationInfoMap = new HashMap<>();

    public Map<String, ServiceInfo> serviceInfoMap = new HashMap<>();

    private Map<String, StructureTypeInfo> structureTypeInfoMap = new HashMap<>();

    private Map<AttributeInfo.Kind, AttributeInfo> attributeInfoMap = new HashMap<>();

    public int addCPEntry(ConstantPoolEntry cpEntry) {
        if (constantPoolEntries.contains(cpEntry)) {
            return constantPoolEntries.indexOf(cpEntry);
        }

        constantPoolEntries.add(cpEntry);
        return constantPoolEntries.size() - 1;
    }

    public ConstantPoolEntry getCPEntry(int index) {
        return constantPoolEntries.get(index);
    }

    public int getCPEntryIndex(ConstantPoolEntry cpEntry) {
        return constantPoolEntries.indexOf(cpEntry);
    }

    public ConstantPoolEntry[] getConstPoolEntries() {
        return constantPoolEntries.toArray(new ConstantPoolEntry[0]);
    }

    public ConstantInfo[] getConstantInfoEntries() {
        return constantInfoMap.values().toArray(new ConstantInfo[0]);
    }

    public PackageVarInfo[] getPackageInfoEntries() {
        return pkgVarInfoMap.values().toArray(new PackageVarInfo[0]);
    }

    public void addTypeDefInfo(String typeDefinitionName, TypeDefInfo typeDefInfo) {
        typeDefInfoMap.put(typeDefinitionName, typeDefInfo);
        structureTypeInfoMap.put(typeDefinitionName, typeDefInfo);
    }

    public TypeDefInfo[] getTypeDefInfoEntries() {
        return typeDefInfoMap.values().toArray(new TypeDefInfo[0]);
    }

    public AnnotationInfo[] getAnnotationInfoEntries() {
        return annotationInfoMap.values().toArray(new AnnotationInfo[0]);
    }

    public ServiceInfo[] getServiceInfoEntries() {
        return serviceInfoMap.values().toArray(new ServiceInfo[0]);
    }

    public ServiceInfo getServiceInfo(String serviceName) {
        return serviceInfoMap.get(serviceName);
    }

    public void addServiceInfo(String serviceName, ServiceInfo serviceInfo) {
        serviceInfoMap.put(serviceName, serviceInfo);
        structureTypeInfoMap.put(serviceName, serviceInfo);
    }

    @Override
    public AttributeInfo getAttributeInfo(AttributeInfo.Kind attributeKind) {
        return attributeInfoMap.get(attributeKind);
    }

    @Override
    public void addAttributeInfo(AttributeInfo.Kind attributeKind, AttributeInfo attributeInfo) {
        attributeInfoMap.put(attributeKind, attributeInfo);
    }

    @Override
    public AttributeInfo[] getAttributeInfoEntries() {
        return attributeInfoMap.values().toArray(new AttributeInfo[0]);
    }

}
