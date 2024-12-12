package br.com.spotiparty.entity.tenant

import br.com.spotiparty.entity.softdelete.SoftDeleteEntity
import br.com.spotiparty.establishment.Establishment
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Filter
import org.hibernate.annotations.FilterDef
import org.hibernate.annotations.ParamDef
import java.util.*

@MappedSuperclass
@Filter(name = "tenantEntityFilter", condition = "establishment_id = :establishmentId")
@FilterDef(name = "tenantEntityFilter", parameters = [ParamDef(name = "establishmentId", type = UUID::class)])
abstract class TenantEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "establishment_id")
    open val establishment: Establishment
) : SoftDeleteEntity()