package ru.dedateam.innorumors.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CastomErrorController implements ErrorController {

    public static final String ERROR_PAGE_WITH_DESCRIPTION = "errors/error-with_description";
    public static final String ERROR_ACCESS = "errors/error-not_access";
    public static final String ERROR = "errors/error";

    public static final String ERROR_TITLE_REGISTRATION = "ОШИБКА РЕГИСТРАЦИИ";
    public static final String ERROR_TITLE_CREATE_POST = "ОШИБКА СОЗДАНИЯ ПОСТА";
    public static final String ERROR_TITLE_CREATE_COMMENT = "ОШИБКА СОЗДАНИЯ КОММЕНТАРИЯ";
    public static final String ERROR_DESCRIPTION_EMPTY_FIELD = "Заполните все поля";


    @GetMapping(path = "/error")
    public String getError(HttpServletRequest request,
                           Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == 404) {
                setErrorDescription(model,
                        statusCode + " PAGE NOT FOUND",
                        "Старница с таким адресом не существует");
                return ERROR_PAGE_WITH_DESCRIPTION;
            } else {
                return ERROR;
            }
        } else {
            return ERROR;
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    public static void setErrorDescription(Model model, String title, String description) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
    }
}

