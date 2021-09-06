package epam.task.finaltaskepam.service.menu;

import com.google.protobuf.ServiceException;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface MenuService {

    List<Dish> getMenu() throws ServiceRuntimeException;

//    /**
//     * This method is used to get list of movies of a particular country.
//     *
//     * @param offset         starting from
//     * @param recordsPerPage amount of movies
//     * @param country        of movies
//     * @return list of filled movie beans
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    List<Dish> getMoviesByCountry(int offset, int recordsPerPage, String country) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to get movies of a particular genre.
//     *
//     * @param offset         starting from
//     * @param recordsPerPage amount of movies
//     * @param genre          of movies
//     * @return list of filled movie beans
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    List<Dish> getMoviesByGenre(int offset, int recordsPerPage, String genre) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to get detailed information about particular movie.
//     *
//     * @param offset         starting from
//     * @param recordsPerPage amount of movies
//     * @param id             of movie
//     * @param lang           language of reviews
//     * @return completely filled movie bean
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    Dish getMovieByID(int offset, int recordsPerPage, String id, String lang) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to search for any movie in the system.
//     *
//     * @param title of movie
//     * @return list of filled movie beans
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    List<Dish> findMovieByTitle(String title) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to get movies by a particular decade.
//     *
//     * @param years of decade
//     * @return list of filled movie beans
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    List<Dish> getMoviesOfTenYearsPeriod(String years) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to get movies of a particular decade.
//     *
//     * @param year of movies
//     * @return list of filled movie beans
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    List<Dish> getMoviesOfYear(String year) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to add review for a particular movie.
//     *
//     * @param movieID      id of movie
//     * @param userNickname reviewer nickname
//     * @param review       text of review
//     * @param lang         language of review
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    void addReview(String movieID, String userNickname, String review, String lang) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to vote for any review.
//     *
//     * @param movieID          id of movie
//     * @param reviewerNickname reviewer nickname
//     * @param score            like or dislike
//     * @param userNickname     nickname of user who votes
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    void likeReview(String movieID, String reviewerNickname, String score, String userNickname) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to add 1-10 rating for a particular movie.
//     *
//     * @param movieID      id of movie
//     * @param userNickname user nickname
//     * @param rating       1-10 rating
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    void addRating(String movieID, String userNickname, String rating) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to get movies with the latest reviews.
//     *
//     * @param lang of reviews
//     * @return list of filled movie beans
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    List<Dish> getLatestReviews(String lang) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to get new movies in the system.
//     *
//     * @return list of filled movie beans
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    List<Dish> getLatestMovies() throws ServiceRuntimeException;
//
//    /**
//     * This method is used to count amount of all movies in the system.
//     *
//     * @return number of movies
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    int countAllMoviesAmount() throws ServiceRuntimeException;
//
//    /**
//     * This method is used to count amount of all movies of a particular country.
//     *
//     * @param country name of country
//     * @return number of movies
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    int countMoviesByCountry(String country) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to count amount of all movies of a particular genre.
//     *
//     * @param genre of movie
//     * @return number of movies
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    int countMoviesByGenre(String genre) throws ServiceRuntimeException;
//
//    /**
//     * This method is used to count number of reviews for a particular movie.
//     *
//     * @param id   of movie
//     * @param lang language of reviews
//     * @return number of reviews
//     * @throws ServiceException if any error occurred while processing method.
//     */
//    int countReviewsForMovie(String id, String lang) throws ServiceRuntimeException;
}
