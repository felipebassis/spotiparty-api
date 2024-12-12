package br.com.spotiparty.entity.softdelete

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface SoftDeleteRepository<E : SoftDeleteEntity, ID> : JpaRepository<E, ID> {

    override fun delete(entity: E) {
        entity.deleted = true
        save(entity)
    }

    override fun deleteAll(entities: MutableIterable<E>) {
        entities.forEach { it.deleted = true }
        saveAll(entities)
    }

    override fun deleteAllInBatch(entities: MutableIterable<E>) {
        deleteAll(entities)
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<ID>) {
        throw UnsupportedOperationException("This entity should have a custom query for this operation")
    }

    override fun deleteAllInBatch() {
        throw UnsupportedOperationException("This entity should have a custom query for this operation")
    }

    override fun deleteById(id: ID & Any) {
        throw UnsupportedOperationException("This entity should have a custom query for this operation")
    }

    override fun deleteAllById(ids: MutableIterable<ID>) {
        throw UnsupportedOperationException("This entity should have a custom query for this operation")
    }
}