package com.example.androidexpert2.ui.tvshow;

import com.example.androidexpert2.model.Movie;

import java.util.ArrayList;

class TvShowData {
    private static String[][] data = new String[][]{
            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                    "Arrow",
                    "2012",
                    "Crime,Drama,Mystery,Action,Adventure",
                    "5.8/10",
                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                    "42",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/fki3kBlwJzFp8QohL43g9ReV455.jpg",
                    "The Flash",
                    "2014",
                    "Drama,Sci-Fi",
                    "6.6/10",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won\'t be long before the world learns what Barry Allen has become...The Flash.",
                    "44",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w220_and_h330_face/gwPSoYUHAKmdyVywgLpKKA4BjRr.jpg",
                    "Game of Thrones",
                    "2011",
                    "Sci-Fi,Fantasy,Drama,Action,Adventure",
                    "8.1/10",
                    "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night\'s Watch, is all that stands between the realms of men and icy horrors beyond.",
                    "60",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/suopoADq0k8YZr4dQXcU6pToj6s.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
                    "Gotham",
                    "2014",
                    "Drama,Fantasy,Crime",
                    "6.9/10",
                    "Before there was Batman, there was GOTHAM.\n" +
                            "\n" +
                            "Everyone knows the name Commissioner Gordon. He is one of the crime world\'s greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon\'s story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                    "43-60",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/bRFmBU0VPr9s1oeJHuS5Zb5nZQS.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/nv4nLXbDhcISPP8C1mgaxKU50KO.jpg",
                    "Marvel\'s Iron Fist",
                    "2017",
                    "Action,Adventure,Crime,Drama,Sci-Fi,Fantasy",
                    "7.7/10",
                    "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                    "55",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/xHCfWGlxwbtMeeOnTvxUCZRGnkk.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/eoj15m14Zpf2bUWXqNIs7itZK9w.jpg",
                    "NCIS",
                    "2003",
                    "Action,Adventure,Crime,Drame",
                    "6.7/10",
                    "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                    "45",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/ms8XxpJwTPYaUcbwhO2kJS6SGVM.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/iRyQTp2L437k6zfFCvZSOWaxQFA.jpg",
                    "Shameless",
                    "2011",
                    "Drama,Comedy",
                    "7.8/10",
                    "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                    "55-60",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/wbPCtpinXTxdBR9DvXlKJG3FU0f.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/vqBsgL9nd2v04ZvCqPzwtckDdFD.jpg",
                    "Supergirl",
                    "2015",
                    "Action,Adventure,Drama,Sci-Fi",
                    "5.8/10",
                    "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                    "42",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/3x1lVyxtsLKsZyR2E3qRe1EAXG4.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/3iFm6Kz7iYoFaEcj4fLyZHAmTQA.jpg",
                    "Supernatural",
                    "2005",
                    "Drama,Mystery,Sci-Fi,Fantasy",
                    "7.3/10",
                    "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their \'67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                    "45",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/ji6ehQWPo00KScXhdmub8wQA6H.jpg"
            },

            {
                    "https://image.tmdb.org/t/p/w185_and_h278_bestv2/bjU4tLlyp8W4yTB0Hqn8J1IDUnD.jpg",
                    "The Walking Dead",
                    "2010",
                    "Action,Adventure,Drama,Sci-Fi,Fantasy",
                    "7.3/10",
                    "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                    "42-60",
                    "https://image.tmdb.org/t/p/w500_and_h282_face/o6R0cyCLqFEQd2PWR2B79DDtjWD.jpg"
            }
    };

    static ArrayList<Movie> getListData() {
        ArrayList<Movie> list = new ArrayList<>();
        for (String[] aData : data) {
            Movie tvShow = new Movie();
            tvShow.setPhoto(aData[0]);
            tvShow.setTitle(aData[1]);
            tvShow.setReleaseYear(aData[2]);
            tvShow.setGenre(aData[3]);
            tvShow.setRating(aData[4]);
            tvShow.setDescription(aData[5]);
            tvShow.setDuration(aData[6]);
            tvShow.setBanner(aData[7]);

            list.add(tvShow);
        }
        return list;
    }
}
