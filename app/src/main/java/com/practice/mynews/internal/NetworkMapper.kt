package com.practice.mynews.internal

interface NetworkMapper<NetworkObject, EntityObject, NetworkResult> {
    fun toEntityObject(networkResult:NetworkResult):EntityObject
    fun toEntityObjectList(networkObject: NetworkObject):List<EntityObject>
}