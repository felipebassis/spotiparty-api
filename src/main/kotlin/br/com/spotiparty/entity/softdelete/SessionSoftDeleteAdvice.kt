package br.com.spotiparty.entity.softdelete

import br.com.spotiparty.extensions.getSession
import br.com.spotiparty.user.LoggedUser
import jakarta.persistence.EntityManager
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class SessionSoftDeleteAdvice {

    @AfterReturning(
        pointcut = "execution(* jakarta.persistence.EntityManagerFactory.createEntityManager(..))",
        returning = "entityManager"
    )
    fun enableSoftDeleteFilter(entityManager: Any) {
        if (LoggedUser.SEE_DELETED.get() == false) {
            (entityManager as EntityManager).getSession()
                .enableFilter("deletedEntityFilter")
                .setParameter("isDeleted", false)
        }
    }
}