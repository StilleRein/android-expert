package com.example.androidexpert2.ui.movie;

import com.example.androidexpert2.model.Movie;

import java.util.ArrayList;

class MovieData {
    private static String[][] data = new String[][]{
            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                    "Alita: Battle Angel",
                    "2019",
                    "Action,Sci-Fi,Thriller,Adventure",
                    "6.8/10",
                    "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                    "122",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/8RKBHHRqOMOLh5qW3sS6TSFTd8h.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                    "Aquaman",
                    "2018",
                    "Action,Adventure,Fantasy",
                    "6.8/10",
                    "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\\'s half-human, half-Atlantean brother and true heir to the throne.",
                    "144",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/9QusGjxcYvfPD1THg6oW3RLeNn7.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w220_and_h330_face/uyJgTzAsp3Za2TaPiZt2yaKYRIR.jpg",
                    "Fantastic Beasts: The Crimes of Grindelwald",
                    "2018",
                    "Adventure,Fantasy,Family",
                    "6.9/10",
                    "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause-elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                    "134",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/qrtRKRzoNkf5vemO9tJ2Y4DrHxQ.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                    "Glass",
                    "2019",
                    "Thriller,Drama,Sci-Fi",
                    "6.5/10",
                    "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                    "129",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/ngBFDOsx13sFXiMweDoL54XYknR.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
                    "How to Train Your Dragon: The Hidden World",
                    "2019",
                    "Animation,Family,Adventure",
                    "7.7/10",
                    "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless\' discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup\'s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                    "104",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/h3KN24PrOheHVYs9ypuOIdFBEpX.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                    "Avengers: Infinity War",
                    "2018",
                    "Adventure,Action,Sci-Fi",
                    "8.3/10",
                    "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                    "149",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w220_and_h330_face/nkCoAik5I4j3Gkd2upj9xv4F0QN.jpg",
                    "Master Z: Ip Man Legacy",
                    "2018",
                    "Action",
                    "5.3/10",
                    "Following his defeat by Master Ip, Cheung Tin Chi (Zhang) tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it s not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                    "107",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/zjeGNkEu134qo4DBFZvHi3IRDgg.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
                    "Mortal Engines",
                    "2018",
                    "Adventure,Fantasy",
                    "6.0/10",
                    "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                    "129",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/rm2oMykm5nX6SzXFr7TGHkO6r8Z.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w220_and_h330_face/lvfIaThG5HA8THf76nghKinjjji.jpg",
                    "Ralph Breaks the Internet",
                    "2018",
                    "Family,Animation,Comedy,Adventure",
                    "7.2/10",
                    "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                    "112",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/88poTBTafMXaz73vYi3c74g0y2k.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/AiRfixFcfTkNbn2A73qVJPlpkUo.jpg",
                    "Robin Hood",
                    "2018",
                    "Adventure,Action,Thriller",
                    "5.8/10",
                    "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                    "116",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/zSJT1sKGRKcmP4I9b8dIOuepw6I.jpg"
            }
    };

    static ArrayList<Movie> getListData() {
        ArrayList<Movie> list = new ArrayList<>();
        for (String[] aData : data) {
            Movie movie = new Movie();
            movie.setPhoto(aData[0]);
            movie.setTitle(aData[1]);
            movie.setReleaseYear(aData[2]);
            movie.setGenre(aData[3]);
            movie.setRating(aData[4]);
            movie.setDescription(aData[5]);
            movie.setDuration(aData[6]);
            movie.setBanner(aData[7]);

            list.add(movie);
        }
        return list;
    }
}
