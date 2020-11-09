package com.practice.mynews.internal

interface EntityMapper<EntityObject, ModelObject> {
    fun toModelObject(entityObject: EntityObject):ModelObject
    fun toModelObjectList(entityObjectList: List<EntityObject>):List<ModelObject>
}