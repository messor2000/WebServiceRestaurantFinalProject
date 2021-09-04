package epam.task.finaltaskepam.dao.dish;

import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.pool.ConnectionPoolImpl;
import epam.task.finaltaskepam.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class DishDaoImpl implements DishDao {
//    private static final Logger logger = LogManager.getLogger(DishDaoImpl.class);
//
//    private final static String SHOW_ALL =
//            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
//                    "FROM movies LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
//                    "GROUP BY m_id ORDER BY m_rating DESC LIMIT ?, ?;";
//
//    private final static String SHOW_BY_ID =
//            "SELECT m_id, m_title_ru, m_title_en, m_year, m_budget, m_gross, m_image," +
//                    "AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes FROM movies\n" +
//                    "LEFT JOIN rating\n" +
//                    "ON movies.m_id=rating.movies_m_id\n" +
//                    "WHERE m_id=?\n" +
//                    "GROUP BY m_id;";
//
//    private final static String SHOW_BY_COUNTRY =
//            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
//                    "FROM movies\n" +
//                    "LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
//                    "LEFT JOIN country ON movies.m_id = country.movies_m_id\n" +
//                    "WHERE country.country_en = ? GROUP BY m_id ORDER BY m_rating DESC LIMIT ?, ?;";
//
//    private static final String SHOW_BY_GENRE =
//            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
//                    "FROM movies\n" +
//                    "LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
//                    "LEFT JOIN genres ON movies.m_id = genres.movies_m_id " +
//                    "WHERE genres.genres_genre_en = ? GROUP BY m_id ORDER BY m_rating DESC LIMIT ?, ?;";
//
//    private static final String FIND_BY_TITLE =
//            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
//                    "FROM movies\n" +
//                    "LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
//                    "WHERE `m_title_en` LIKE ? OR `m_title_ru` LIKE ?" +
//                    "GROUP BY m_id;";
//
//    private static final String SHOW_OF_TEN_YEARS_PERIOD =
//            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
//                    "FROM movies LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
//                    "WHERE movies.m_year BETWEEN ? AND ?\n" +
//                    "GROUP BY m_id ORDER BY m_rating DESC;";
//
//    private static final String SHOW_OF_YEAR =
//            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
//                    "FROM movies LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
//                    "WHERE movies.m_year=?\n" +
//                    "GROUP BY m_id ORDER BY m_rating DESC;";
//
//    private final static String ADD_MOVIE =
//            "INSERT INTO movies (m_title_ru, m_title_en, `m_year`, `m_budget`, `m_gross`) VALUES (?, ?, ?, ?, ?)";
//
//    private final static String UPDATE_BY_ID =
//            "UPDATE `jackdb`.`movies`\n" +
//                    "SET m_title_ru = ?, m_title_en = ?, `m_year` = ?, `m_budget` = ?,`m_gross` = ?\n" +
//                    "WHERE `m_id` = ?;\n";
//    private static final String UPDATE_IMAGE =
//            "UPDATE movies SET m_image= ? WHERE m_id= ?;";
//    private static final String MOVIES_FOR_ACTOR =
//            "SELECT DISTINCT m_id, m_title_ru, m_title_en, IFNULL(rating.m_rating, 0) AS m_rating, IFNULL(rating.m_votes, 0) AS m_votes\n" +
//                    "FROM movies\n" +
//                    "LEFT JOIN (\n" +
//                    "SELECT movies_m_id, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes FROM rating GROUP BY movies_m_id)\n" +
//                    "rating ON (movies.m_id = movies_m_id) \n" +
//                    "LEFT JOIN actor_starred ON m_id= actor_starred.movies_m_id\n" +
//                    "LEFT JOIN director ON m_id= director.movies_m_id\n" +
//                    "WHERE director.actors_a_id = ? OR actor_starred.actors_a_id = ?;";
//    private static final String MOVIES_FOR_NEWS =
//            "SELECT DISTINCT m_id, m_title_ru, m_title_en\n" +
//                    "FROM movies\n" +
//                    "LEFT JOIN news_about_movies ON m_id= news_about_movies.movies_m_id\n" +
//                    "WHERE news_about_movies.news_n_id = ?;";
//    private static final String MOVIES_WITH_LATEST_REVIEWS =
//            "SELECT m_id, m_title_ru, m_title_en,\n" +
//                    "user_u_nick, review, review_date, u_image\n" +
//                    "FROM movies\n" +
//                    "LEFT JOIN reviews ON m_id= reviews.movies_m_id \n" +
//                    "LEFT JOIN user ON user_u_nick= u_nick \n" +
//                    "WHERE reviews.review_lang = ?\n" +
//                    "ORDER BY reviews.review_date DESC LIMIT 10;";
//    private static final String SHOW_LATEST_MOVIES =
//            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
//                    "FROM movies LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
//                    "GROUP BY m_id ORDER BY m_id DESC LIMIT 10";
//    private static final String COUNT_ALL_MOVIES =
//            "SELECT COUNT(m_id) AS amount FROM movies";
//    private static final String COUNT_ALL_MOVIES_BY_COUNTRY =
//            "SELECT COUNT(movies_m_id) AS amount FROM country WHERE country_en = ?;";
//    private static final String COUNT_ALL_MOVIES_BY_GENRE =
//            "SELECT COUNT(movies_m_id) AS amount FROM genres WHERE genres_genre_en = ?;";
//    private static final String DELETE_BY_ID =
//            "DELETE FROM `jackdb`.`movies` WHERE m_id=?;";
//    private static final String LAST_INSERTED_MOVIE =
//            "SELECT * FROM jackdb.movies ORDER BY m_id DESC LIMIT 1;";
//
//
//    private static final String M_ID = "m_id";
//    private static final String M_TITLE_RU = "m_title_ru";
//    private static final String M_TITLE_EN = "m_title_en";
//    private static final String M_YEAR = "m_year";
//    private static final String M_BUDGET = "m_budget";
//    private static final String M_GROSS = "m_gross";
//    private static final String M_IMAGE = "m_image";

    private static final DishDao instance = new DishDaoImpl();

    private DishDaoImpl(){}

    public static DishDao getInstance() {
        return instance;
    }

    @Override
    public List<Dish> getAllDishes() throws DaoRuntimeException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = ConnectionPoolImpl.getInstance().takeConnection();
//
//            statement = connection.prepareStatement(SHOW_ALL);
//
//            resultSet = statement.executeQuery();
//            List<Dish> dishes = new ArrayList<>();
//            Dish dish;
//
//            while (resultSet.next()) {
//                dish = new Dish();
//                dish.setDishId(resultSet.getInt(M_ID));
//                dish.setTitleRu(resultSet.getString(M_TITLE_RU));
//                dish.setTitleEn(resultSet.getString(M_TITLE_EN));
//                dish.setAvgRating(resultSet.getDouble(M_RATING));
//                dish.setRatingVotes(resultSet.getInt(M_VOTES));
//                dishes.add(dish);
//            }
//            return dishes;
//
//        } catch (SQLException e) {
//            throw new DaoRuntimeException("Dish sql error", e);
//        } catch (ConnectionPoolException e) {
//            throw new DaoRuntimeException("Dish pool connection error", e);
//        } finally {
//            Util.closeResource(connection, statement, resultSet);
//        }

        return null;
    }

    @Override
    public List<Dish> getDishesByName(String name) throws DaoRuntimeException {
        return null;
    }

    @Override
    public List<Dish> getDishesByPrice(Long price) throws DaoRuntimeException {
        return null;
    }

    @Override
    public List<Dish> getDishesByNCategory(String category) throws DaoRuntimeException {
        return null;
    }

    @Override
    public Dish getDishById(int id) throws DaoRuntimeException {
        return null;
    }

    @Override
    public void addDish(int id, String name, long price, String category) throws DaoRuntimeException {

    }
}
