package com.dol.domain.user.entity

import com.dol.common.entity.BaseUUIDEntity
import com.dol.domain.user.enums.Authority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Column(name = "user_idx", columnDefinition = "BINARY(16)", nullable = false)
    override val idx: UUID,

    @Column(nullable = false, length = 20)
    val id: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Column(nullable = false, length = 10)
    val nickName: String,

    @Column(nullable = false, length = 15)
    val phoneNumber: String,

    @Column(nullable = false, length = 60)
    val password: String,

    @Column(nullable = false, length = 15)
    val city: String,

    @Column(nullable = false, length = 15)
    val gu: String,

    @Column(nullable = true, columnDefinition = "TEXT")
    val profileUrl: String?,

    @Enumerated(EnumType.STRING)
    val authority: Authority
): BaseUUIDEntity(idx)