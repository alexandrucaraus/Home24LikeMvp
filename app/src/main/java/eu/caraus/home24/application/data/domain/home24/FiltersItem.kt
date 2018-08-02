package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class FiltersItem(

		@field:SerializedName("unit")
	val unit: String? = null,

		@field:SerializedName("min")
	val min: Int? = null,

		@field:SerializedName("max")
	val max: Int? = null,

		@field:SerializedName("id")
	val id: String? = null,

		@field:SerializedName("currentMax")
	val currentMax: Int? = null,

		@field:SerializedName("priority")
	val priority: Int? = null,

		@field:SerializedName("title")
	val title: String? = null,

		@field:SerializedName("_metadata")
	val metadata: Metadata? = null,

		@field:SerializedName("currentMin")
	val currentMin: Int? = null,

		@field:SerializedName("values")
	val values: List<ValuesItem?>? = null
)