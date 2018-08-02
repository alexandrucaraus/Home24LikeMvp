package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class Delivery(

		@field:SerializedName("deliveredBy")
	val deliveredBy: Any? = null,

		@field:SerializedName("terms")
	val terms: Any? = null,

		@field:SerializedName("typeLabelLink")
	val typeLabelLink: String? = null,

		@field:SerializedName("time")
	val time: Time? = null,

		@field:SerializedName("text")
	val text: String? = null,

		@field:SerializedName("type")
	val type: String? = null
)