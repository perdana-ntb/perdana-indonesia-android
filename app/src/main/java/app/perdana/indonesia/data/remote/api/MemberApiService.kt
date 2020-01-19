package app.perdana.indonesia.data.remote.api

import app.perdana.indonesia.data.remote.model.ArcherMemberResponse
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by ebysofyan on 12/2/19.
 */
interface MemberApiService {
    @GET("/api/v1/user/profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<JsonElement>

    @GET("/api/v1/user/members")
    suspend fun fetchMembers(@Header("Authorization") token: String): Response<List<ArcherMemberResponse>>

    @GET("/api/v1/user/applicants")
    suspend fun fetchMemberApplicants(@Header("Authorization") token: String): Response<List<ArcherMemberResponse>>

    @GET("/api/v1/user/applicants/{id}")
    suspend fun getMemberApplicant(@Header("Authorization") token: String, @Path("id") id: String): Response<ArcherMemberResponse>

    @FormUrlEncoded
    @POST("/api/v1/user/applicants/{id}/approve/")
    suspend fun approveApplicantMember(
        @Header("Authorization") token: String, @Path("id") id: String, @Field(
            "register_number"
        ) registerNumber: String
    ): Response<ArcherMemberResponse>
}