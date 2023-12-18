package com.dol.domain.hotplace.service.impl

import com.dol.common.util.UserUtil
import com.dol.domain.hotplace.entity.Hotplace
import com.dol.domain.hotplace.enums.ApproveStatus
import com.dol.domain.hotplace.presentation.data.request.CreateHotplaceRequest
import com.dol.domain.hotplace.repository.HotplaceRepository
import com.dol.domain.hotplace.service.CreateHotplaceService
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import com.dol.thirdparty.kakao.util.KakaoUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class CreateHotplaceServiceImpl(
    private val userRepository: UserRepository,
    private val hotplaceRepository: HotplaceRepository,
    private val userUtil: UserUtil,
    private val kakaoUtil: KakaoUtil
) : CreateHotplaceService {
    override fun execute(createHotplaceRequest: CreateHotplaceRequest) {
        val user = userRepository.findByIdOrNull(userUtil.getCurrentUserIdx())
            ?: throw UserNotFoundException("해당 유저를 찾을 수 없습니다. info [ userIdx = ${userUtil.getCurrentUserIdx()} ]")

        val (longitude, latitude) = kakaoUtil.addressInCoordinate(createHotplaceRequest.address)

        val hotplace = Hotplace(
            idx = UUID.randomUUID(),
            name = createHotplaceRequest.name,
            address = createHotplaceRequest.address,
            instagramId = createHotplaceRequest.instagramId,
            imgURL = createHotplaceRequest.imgURL,
            longitude = longitude,
            latitude = latitude,
            approveStatus = ApproveStatus.PENDING,
            user = user
        )

        hotplaceRepository.save(hotplace)
    }
}