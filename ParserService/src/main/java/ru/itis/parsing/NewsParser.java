package ru.itis.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.itis.model.CarNews;
import ru.itis.model.FilmNews;
import ru.itis.model.HabrNews;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
@EnableRabbit
public class NewsParser {
    private static final String HABR_URL = "https://habr.com/ru/search/?target_type=posts&q=java&order_by=date";
    private static final String CAR_URL = "https://news.drom.ru";
    private static final String FILM_URL = "https://www.kinonews.ru/news";
    private Document document;
    @RabbitListener(queues = "getHabrNews")
    public List<HabrNews> habrNewsParse() throws IOException { //fixme Add try catch block
        List<HabrNews> habrNews = new ArrayList<HabrNews>();
        document = Jsoup.connect(HABR_URL).get();
        Elements title = document.getElementsByAttributeValue("class", "post__title_link").select("a");
        Elements postTime = document.getElementsByAttributeValue("class", "post__time").select("span");
        for (int i = 0; i < 10; i++) {
            habrNews.add(HabrNews.builder().title(title.get(i).text()).url(title.get(i).attr("href")).postTime(postTime.get(i).text()).build());
        }
        return habrNews;
    }
    @RabbitListener(queues = "getCarNews")
    public List<CarNews> carNewsParse() throws IOException { //fixme Add try catch block
        List<CarNews> carNews = new ArrayList<CarNews>();
        document = Jsoup.connect(CAR_URL).get();
        Elements title = document.getElementsByAttributeValue("class", "b-info-block__title b-link").select("div");
        Elements href = document.getElementsByAttributeValue("class", "b-info-block__cont b-info-block__cont_state_reviews").select("a");
        Elements postTime = document.getElementsByAttributeValue("class", "b-info-block__text b-info-block__text_type_news-date").select("div");
        for (int i = 0; i < 10; i++) {
            carNews.add(CarNews.builder().title(title.get(i).text()).url(href.get(i).attr("href")).postTime(postTime.get(i).text()).build());
        }
        return carNews;
    }
    @RabbitListener(queues = "getFilmNews")
    public List<FilmNews> filmNewsParse() throws IOException { //fixme Add try catch block
        List<FilmNews> filmNews = new ArrayList<FilmNews>();
        Document document = Jsoup.connect(FILM_URL).get();
        Elements title = document.getElementsByAttributeValue("class", "anons-title-new").select("a");
        Elements postTime = document.getElementsByAttributeValue("class", "anons-date-new").select("div");
        for (int i = 0; i < 10; i++) {
            filmNews.add(FilmNews.builder().title(title.get(i).text()).url(FILM_URL.toString() + title.get(i).attr("href").toString()).postTime(postTime.get(i).text()).build());
        }
        return filmNews;
    }
}
