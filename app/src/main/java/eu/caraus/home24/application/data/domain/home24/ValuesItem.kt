package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class ValuesItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("priority")
	val priority: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("_metadata")
	val metadata: Metadata? = null
)