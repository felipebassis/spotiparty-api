package br.com.spotiparty.entity.softdelete

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Filter
import org.hibernate.annotations.FilterDef
import org.hibernate.annotations.ParamDef

@MappedSuperclass
@Filter(name = "deletedEntityFilter", condition = "deleted = :isDeleted")
@FilterDef(name = "deletedEntityFilter", parameters = [ParamDef(name = "isDeleted", type = Boolean::class)])
abstract class SoftDeleteEntity(
    @Column(name = "deleted")
    open var deleted: Boolean = false
)