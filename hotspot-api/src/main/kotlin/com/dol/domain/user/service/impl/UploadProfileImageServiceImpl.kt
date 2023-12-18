package com.dol.domain.user.service.impl

import com.dol.common.util.UserUtil
import com.dol.domain.image.presentation.data.response.ImageUrlResponse
import com.dol.domain.image.service.ImageUploadService
import com.dol.domain.user.exception.UserNotFoundException
import com.dol.domain.user.repository.UserRepository
import com.dol.domain.user.service.UploadProfileImageService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(rollbackFor = [Exception::class])
class UploadProfileImageServiceImpl(
    private val imageUploadService: ImageUploadService,
    private val userRepository: UserRepository,
    private val userUtil: UserUtil
) : UploadProfileImageService {
    override fun execute(file: MultipartFile) : ImageUrlResponse {
        val currentUserIdx = userUtil.getCurrentUserIdx()
        val user = userRepository.findByIdOrNull(currentUserIdx)
            ?: throw UserNotFoundException("사용자를 찾을 수 없습니다.")
        val profileUrl = imageUploadService.execute(file)
        userRepository.save(user.updateProfileUrl(profileUrl.url))
        return profileUrl
    }
}