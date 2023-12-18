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

    @Column(columnDefinition = "VARCHAR(50)", nullable = false, length = 50)
    val name: String,

    @Column(columnDefinition = "VARCHAR(200)", nullable = false, length = 200)
    val address: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = true, length = 20)
    val instagramId: String?,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false, length = 100)
    val imgURL: String,

    @Column(columnDefinition = "DECIMAL", nullable = false)
    val longitude: Double,

    @Column(columnDefinition = "DECIMAL", nullable = false)
    val latitude: Double,

    @Enumerated(EnumType.STRING)
    val approveStatus: ApproveStatus,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) : BaseUUIDEntity(idx)