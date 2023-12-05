package com.dol.domain.hotplace.entity

import com.dol.common.entity.BaseUUIDEntity
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
    val description: String,

    @Column(columnDefinition = "DECIMAL", nullable = false)
    val altitude: Long,

    @Column(columnDefinition = "DECIMAL", nullable = false)
    val latitude: Long,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) : BaseUUIDEntity(idx)