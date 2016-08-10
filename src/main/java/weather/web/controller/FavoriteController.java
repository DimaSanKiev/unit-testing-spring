package weather.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import weather.domain.Favorite;
import weather.exception.FavoriteDeleteException;
import weather.exception.FavoriteNotFoundException;
import weather.exception.FavoriteSaveException;
import weather.service.FavoriteService;
import weather.service.WeatherService;
import weather.service.dto.geocoding.PlacesResult;
import weather.service.dto.weather.Weather;
import weather.service.resttemplate.PlacesService;
import weather.web.FlashMessage;

import javax.servlet.http.HttpServletRequest;

import static weather.web.FlashMessage.Status.FAILURE;
import static weather.web.FlashMessage.Status.SUCCESS;
import static weather.web.ReferrerInterceptor.redirect;

@Controller
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private PlacesService placesService;

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/favorites")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String index(Model model) {
        model.addAttribute("favorites", favoriteService.findAll());
        return "favorite/index";
    }

    @RequestMapping("/favorites/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Favorite fave = favoriteService.findById(id);
        PlacesResult placesResult = placesService.findByPlaceId(fave.getPlaceId());
        Weather weather = weatherService.findByLocation(placesResult.getGeometry().getLocation());
        model.addAttribute("favorite", fave);
        model.addAttribute("weather", weather);
        return "favorite/detail";
    }

    @RequestMapping(path = "/favorites", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String add(Favorite favorite, RedirectAttributes redirectAttributes) {
        favoriteService.save(favorite);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Added to favorites", SUCCESS));
        return "redirect:/favorites/" + favorite.getId();
    }

    @RequestMapping(path = "/favorites/{id}/delete", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String remove(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        favoriteService.delete(id);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Favorite deleted", SUCCESS));
        return "redirect:/favorites";
    }

    @ExceptionHandler(FavoriteNotFoundException.class)
    public String notFound(Model model, Exception ex) {
        model.addAttribute("ex", ex);
        return "error";
    }

    @ExceptionHandler({FavoriteSaveException.class, FavoriteDeleteException.class})
    public String databaseError(Model model, HttpServletRequest req, Exception ex) {
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(req);
        if (flashMap != null) {
            flashMap.put("flash", new FlashMessage(ex.getMessage(), FAILURE));
        }
        return redirect(req);
    }
}
