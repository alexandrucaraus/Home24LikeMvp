package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class Time(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("renderAs")
	val renderAs: String? = null,

	@field:SerializedName("units")
	val units: String? = null
)