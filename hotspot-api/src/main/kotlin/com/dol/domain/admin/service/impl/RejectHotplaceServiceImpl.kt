package com.dol.domain.admin.service.impl

import com.dol.domain.admin.service.RejectHotplaceService
import com.dol.domain.hotplace.entity.Hotplace
import com.dol.domain.hotplace.enums.ApproveStatus
import com.dol.domain.hotplace.exception.HotplaceNotFoundException
import com.dol.domain.hotplace.repository.HotplaceRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class RejectHotplaceServiceImpl(
    private val hotplaceRepository: HotplaceRepository
) : RejectHotplaceService {
    override fun execute(hotplaceIdx: UUID) {
        val hotplace = hotplaceRepository.findByIdOrNull(hotplaceIdx)
            ?: throw HotplaceNotFoundException("해당 핫플레이스를 찾을 수 없습니다. info [ hotplaceIdx = $hotplaceIdx ]")


        val approvedHotplace = hotplace.run {
            Hotplace(
                idx = hotplaceIdx,
                name = name,
                address = address,
                instagramId = instagramId,
                imgURL = imgURL,
                latitude = latitude,
                longitude = longitude,
                approveStatus = ApproveStatus.REJECTED,
                user = user
            )
        }

        hotplaceRepository.save(approvedHotplace)
    }
}