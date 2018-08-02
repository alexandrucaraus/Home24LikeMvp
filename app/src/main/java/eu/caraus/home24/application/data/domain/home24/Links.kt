package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class Links(

		@field:SerializedName("webShopUrl")
	val webShopUrl: WebShopUrl? = null,

		@field:SerializedName("self")
	val self: Self? = null
)