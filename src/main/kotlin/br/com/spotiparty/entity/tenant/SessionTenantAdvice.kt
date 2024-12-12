package br.com.spotiparty.entity.tenant

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class SessionTenantAdvice {

    @AfterReturning(
        pointcut = "execution(* jakarta.persistence.EntityManagerFactory.createEntityManager(..))",
        returning = "entityManager"
    )
    fun enableTenantDeleteFilter(entityManager: Any) {
//        TODO("Implementar filtro tenante")
//        if (LoggedUser.SEE_DELETED.get() == false) {
//            (entityManager as EntityManager).getSession()
//                .enableFilter("tenantEntityFilter")
//                .setParameter("isDeleted", false)
//        }
    }
}