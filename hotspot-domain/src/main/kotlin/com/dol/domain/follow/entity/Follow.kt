package com.dol.domain.follow.entity

import com.dol.common.entity.BaseUUIDEntity
import com.dol.domain.user.entity.User
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Follow(
    @Column(name = "idx", columnDefinition = "BINARY(16)", nullable = false)
    override val idx: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_idx")
    val toUser: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_idx")
    val fromUser: User
) : BaseUUIDEntity(idx)