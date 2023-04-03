package com.ada.integratingprojectharrison.service

import com.ada.integratingprojectharrison.data.SearchResultDto

interface MovieFinderService {

    fun findMoviesByTitle(tittle: String,  movieNumber: Int): SearchResultDto

}