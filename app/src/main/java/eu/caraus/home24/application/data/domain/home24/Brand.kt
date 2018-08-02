package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class Brand(

	@field:SerializedName("logo")
	val logo: List<String?>? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)