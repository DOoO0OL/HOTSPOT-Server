package com.dol.domain.follow.entity

import com.dol.common.entity.BaseUUIDEntity
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id

@Entity
@EntityListeners(AuditingEntityListener::class)
class Follow(
    @Column(name = "idx", columnDefinition = "BINARY(16)", nullable = false)
    override val idx: UUID,

    @Column(name = "user_idx", nullable = false)
    val userIdx: UUID,

    @Column(name = "from_user_idx", nullable = false)
    val fromUserIdx: UUID,

    @CreatedDate
    val createdAt: LocalDateTime
) : BaseUUIDEntity(idx)