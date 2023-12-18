package com.dol.domain.hotplace.entity

import com.dol.common.entity.BaseUUIDEntity
import com.dol.domain.hotplace.enums.ApproveStatus
import com.dol.domain.user.entity.User
import java.util.UUID
import javax.persistence.*

@Entity
class Hotplace(
    @Column(name = "idx", columnDefinition = "BINARY(16)", nullable = false)
    override val idx: UUID,

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    val name: String,

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    val address: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = true)
    val instagramId: String?,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    val imgURL: String?,

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    val longitude: String,

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    val latitude: String,

    @Enumerated(EnumType.STRING)
    val approveStatus: ApproveStatus,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) : BaseUUIDEntity(idx)