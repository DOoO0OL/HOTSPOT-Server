package com.dol.domain.follow.repository.custom.impl

import com.dol.domain.follow.entity.QFollow
import com.dol.domain.follow.repository.custom.CustomFollowRepository
import com.dol.domain.user.entity.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomFollowRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : CustomFollowRepository {
    override fun delete(toUser: User, fromUser: User) {
        val qFollow = QFollow.follow
        queryFactory.delete(qFollow)
            .where(qFollow.toUser.eq(toUser).and(qFollow.fromUser.eq(fromUser)))
            .execute()
    }
}