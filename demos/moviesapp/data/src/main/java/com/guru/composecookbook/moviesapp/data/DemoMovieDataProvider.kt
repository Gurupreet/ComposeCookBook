package com.guru.composecookbook.moviesapp.data

import com.guru.composecookbook.moviesapp.data.db.models.Movie

object DemoMovieDataProvider {

    val movie = Movie(
        id = 1,
        title = "Joker",
        name = "",
        release_date = "21 Aug",
        vote_average = 4.5,
        genre_ids = emptyList(),
        poster_path = "https://cdnb.artstation.com/p/assets/images/images/017/022/569/medium/amirhosein-naseri-new-age.jpg",
        backdrop_path = "https://cdnb.artstation.com/p/assets/images/images/017/022/569/medium/amirhosein-naseri-new-age.jpg",
        overview = "This is a blockbuster movie starring Jaquin Phoenix by DC comics",
        adult = true,
        tagline = "Joker is here",
        budget = 1223322.3,
        revenue = 1233343433.4,
        runtime = 130,
        homepage = "https://dc.com",
        status = "Running",
        addedTime = 123434343
    )

    val movies = listOf(
        movie,
        movie.copy(id = 2, title = "Batman"),
        movie.copy(id = 3, title = "Avengers"),
        movie.copy(id = 4, title = "Tenent"),
        movie.copy(id = 5, title = "Mulan"),
        movie.copy(id = 6, title = "Mulan"),
        movie.copy(id = 7, title = "Mulan"),
        movie.copy(id = 8, title = "Mulan"),
        movie.copy(id = 9, title = "Mulan")
    )
}