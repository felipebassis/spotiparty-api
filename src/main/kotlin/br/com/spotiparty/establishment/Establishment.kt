package br.com.spotiparty.establishment

import br.com.spotiparty.entity.Geolocation
import br.com.spotiparty.entity.softdelete.SoftDeleteEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "establishment")
class Establishment(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "name")
    var name: String,

    @Embedded
    var geoLocation: Geolocation
) : SoftDeleteEntity()


