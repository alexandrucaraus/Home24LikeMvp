package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class Home24ApiData(

		@field:SerializedName("_links")
	val links: Links? = null,

		@field:SerializedName("_embedded")
	val embedded: Embedded? = null,

		@field:SerializedName("articlesCount")
	val articlesCount: Int? = null,

		@field:SerializedName("resourceType")
	val resourceType: String? = null
)