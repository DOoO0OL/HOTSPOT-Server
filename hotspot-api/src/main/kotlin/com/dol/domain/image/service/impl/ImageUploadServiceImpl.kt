package com.dol.domain.image.service.impl

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.dol.domain.image.presentation.data.response.ImageUrlResponse
import com.dol.domain.image.service.ImageUploadService
import com.dol.thirdparty.aws.exception.FailedFileUploadException
import com.dol.thirdparty.aws.properties.s3.AwsS3Properties
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class ImageUploadServiceImpl(
    private val amazonS3: AmazonS3,
    private val awsS3Properties: AwsS3Properties
) : ImageUploadService {
    override fun execute(file: MultipartFile): ImageUrlResponse {
        val fileName = UUID.randomUUID().toString() + file.originalFilename.toString()
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = file.size
        objectMetadata.contentType = file.contentType
        kotlin.runCatching {
            amazonS3.putObject(
                awsS3Properties.bucket,
                fileName,
                file.inputStream,
                objectMetadata
            )
        }.onFailure {
            throw FailedFileUploadException("이미지 업로드에 실패하였습니다.")
        }

        return ImageUrlResponse(
            url = amazonS3.getUrl(awsS3Properties.bucket, fileName).toString()
        )
    }
}