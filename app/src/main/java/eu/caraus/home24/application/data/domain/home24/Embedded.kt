package eu.caraus.home24.application.data.domain.home24


import com.google.gson.annotations.SerializedName


data class Embedded(

	@field:SerializedName("filters")
	val filters: List<FiltersItem?>? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null
)