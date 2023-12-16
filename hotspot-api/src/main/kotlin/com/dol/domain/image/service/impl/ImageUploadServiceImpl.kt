package com.dol.domain.image.service.impl

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.dol.domain.image.service.ImageUploadService
import com.dol.thirdparty.aws.exception.FailedFileUploadException
import com.dol.thirdparty.aws.properties.s3.AwsS3Properties
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class ImageUploadServiceImpl(
    private val amazonS3: AmazonS3,
    private val awsS3Properties: AwsS3Properties
) : ImageUploadService {
    override fun execute(file: MultipartFile): String {
        val fileName = UUID.randomUUID().toString() + file.originalFilename.toString()
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = file.size
        objectMetadata.contentType = file.contentType
        try {
            val inputStream: InputStream = file.inputStream
            amazonS3.putObject(
                PutObjectRequest(awsS3Properties.bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)
            )
        } catch (e: IOException) {
            throw FailedFileUploadException("이미지 업로드에 실패하였습니다.")
        }
        return fileName

    }

}