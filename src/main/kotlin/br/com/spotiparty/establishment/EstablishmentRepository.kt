package br.com.spotiparty.establishment

import br.com.spotiparty.entity.softdelete.SoftDeleteRepository
import java.util.*

interface EstablishmentRepository : SoftDeleteRepository<Establishment, UUID>
