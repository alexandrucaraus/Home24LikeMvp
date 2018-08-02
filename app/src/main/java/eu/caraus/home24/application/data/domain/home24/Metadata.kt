package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class Metadata(

	@field:SerializedName("type")
	val type: String? = null
)