package com.dol.domain.user.entity

import com.dol.common.entity.BaseUUIDEntity
import com.dol.domain.follow.entity.Follow
import com.dol.domain.user.enums.Authority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Column(name = "idx", columnDefinition = "BINARY(16)", nullable = false)
    override val idx: UUID,

    @Column(nullable = false, length = 50)
    val id: String,

    @Column(nullable = false, length = 20)
    val name: String,

    @Column(nullable = false, length = 10)
    val nickName: String,

    @Column(nullable = false, length = 20)
    val phoneNumber: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, length = 30)
    val city: String,

    @Column(nullable = false, length = 30)
    val gu: String,

    @Column(nullable = true, columnDefinition = "TEXT")
    var profileUrl: String?,

    @Enumerated(EnumType.STRING)
    val authority: Authority,

    @OneToMany(mappedBy = "toUser")
    val following: List<Follow> = listOf(),

    @OneToMany(mappedBy = "fromUser")
    val follower: List<Follow> = listOf()
): BaseUUIDEntity(idx) {

    fun updateProfileUrl(profileUrl: String) : User =
        User(
            idx = this.idx,
            id = this.id,
            name = this.name,
            nickName = this.nickName,
            phoneNumber = this.phoneNumber,
            password = this.password,
            city = this.city,
            gu = this.gu,
            profileUrl = profileUrl,
            authority = this.authority,
            following = this.following,
            follower = this.follower
        )

}