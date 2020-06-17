package jp.ac.hcs.s3a208.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * 天気の詳細情報を検索し、結果画面を表示する
     * @param principal ログイン情報
     * @param model モデル
     * @return 結果画面 - 天気
     */
    @PostMapping("/weather")
    public String postWeather(Principal principal, Model model) {

            //札幌に固定
            String citycode = "016010";

            WeatherEntity weatherEntity = weatherService.getWeather(citycode);
            model.addAttribute("weatherEntity", weatherEntity);

            return "weather/weather";
    }
}

