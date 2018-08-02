package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class Price(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("isRecommendedRetailPrice")
	val isRecommendedRetailPrice: Boolean? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)