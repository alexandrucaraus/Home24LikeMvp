package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class ArticlesItem(

		@field:SerializedName("delivery")
	val delivery: Delivery? = null,

		@field:SerializedName("manufacturePrice")
	val manufacturePrice: Any? = null,

		@field:SerializedName("_links")
	val links: Links? = null,

		@field:SerializedName("description")
	val description: Any? = null,

		@field:SerializedName("media")
	val media: List<MediaItem?>? = null,

		@field:SerializedName("availability")
	val availability: Any? = null,

		@field:SerializedName("title")
	val title: String? = null,

		@field:SerializedName("url")
	val url: Any? = null,

		@field:SerializedName("prevPrice")
	val prevPrice: Any? = null,

		@field:SerializedName("assemblyService")
	val assemblyService: Any? = null,

		@field:SerializedName("price")
	val price: Price? = null,

		@field:SerializedName("sku")
	val sku: String? = null,

		@field:SerializedName("_metadata")
	val metadata: Metadata? = null,

		@field:SerializedName("brand")
	val brand: Brand? = null,

		@field:SerializedName("energyClass")
	val energyClass: Any? = null
)